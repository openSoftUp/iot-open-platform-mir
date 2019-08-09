package com.open.iot.db.config;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.open.iot.db.config.util.DataSourceKey;
import com.open.iot.db.config.util.DynamicDataSource;
import com.open.iot.db.config.util.ModuloDatabaseShardingAlgorithm;
import com.open.iot.db.config.util.ModuloTableShardingAlgorithm;

import io.shardingjdbc.core.api.config.ShardingRuleConfiguration;
import io.shardingjdbc.core.api.config.TableRuleConfiguration;
import io.shardingjdbc.core.api.config.strategy.StandardShardingStrategyConfiguration;
import io.shardingjdbc.core.jdbc.core.datasource.ShardingDataSource;

/**
 * 
* @ClassName: ShardingDataSourceConfig  
* @Description: 在设置了spring.datasource.sharding.enable 等于true是开启分表分库
* @author huy  
* @date 2019年8月9日  
*
 */
@Configuration
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class}) //排除DataSourceConfiguratrion
@ConditionalOnProperty(name = { "spring.datasource.sharding.enable" }, matchIfMissing = false, havingValue = "true")
public class ShardingDataSourceConfig {


	// 创建数据源
	// 所有引入db-core的模块都需要一个核心库
	@Bean
	@ConfigurationProperties("spring.datasource.druid.master")
	public DataSource dataSourceMaster() {
		return DruidDataSourceBuilder.create().build();
	}

	// 所有的核心库共享一个日志中心模块，改模块不采用mysql中的innodb引擎，采用归档引擎
	@Bean
	@ConfigurationProperties("spring.datasource.druid.slave")
	public DataSource dataSourceSlave() {
		return DruidDataSourceBuilder.create().build();
	}

	@Bean
	@ConfigurationProperties("spring.datasource.druid.single")
	public DataSource dataSourceSingle() {
		return DruidDataSourceBuilder.create().build();
	}
	
	
	
	@Bean(name = "shardingDataSource")
	public DataSource getShardingDataSource(@Qualifier("dataSourceMaster") DataSource dataSourceMaster,
			@Qualifier("dataSourceSlave") DataSource dataSourceSlave) throws SQLException {
		ShardingRuleConfiguration shardingRuleConfig;
		shardingRuleConfig = new ShardingRuleConfiguration();
		shardingRuleConfig.getTableRuleConfigs().add(getUserTableRuleConfiguration());
		shardingRuleConfig.getBindingTableGroups().add("t_order");
		shardingRuleConfig.setDefaultDatabaseShardingStrategyConfig(
				new StandardShardingStrategyConfiguration("user_id", ModuloDatabaseShardingAlgorithm.class.getName()));
		shardingRuleConfig.setDefaultTableShardingStrategyConfig(
				new StandardShardingStrategyConfiguration("order_id", ModuloTableShardingAlgorithm.class.getName()));

		Map<String, DataSource> dataSourceMap = new HashMap<>(); // 设置分库映射
		dataSourceMap.put("test_msg0", dataSourceMaster);
		dataSourceMap.put("test_msg1", dataSourceSlave);

		return new ShardingDataSource(shardingRuleConfig.build(dataSourceMap));
	}
	
	
	@Bean // 只需要纳入动态数据源到spring容器
    @Primary
    public DataSource dataSource(@Qualifier("shardingDataSource") DataSource shardingDataSource) {
        DynamicDataSource dataSource = new DynamicDataSource();
        DataSource coreDataSource = this.dataSourceSingle();
        dataSource.addDataSource(DataSourceKey.sharding, shardingDataSource);
        dataSource.addDataSource(DataSourceKey.core, coreDataSource);
        dataSource.setDefaultTargetDataSource(coreDataSource);
        return dataSource;
    }
	

	/**
	 * 设置表的node
	 * 
	 * @return
	 */
	@Bean
	public TableRuleConfiguration getUserTableRuleConfiguration() {
		TableRuleConfiguration orderTableRuleConfig = new TableRuleConfiguration();
		orderTableRuleConfig.setLogicTable("t_order");
		orderTableRuleConfig.setActualDataNodes("test_msg${0..1}.t_order_${0..1}");
		orderTableRuleConfig.setKeyGeneratorColumnName("order_id");
		return orderTableRuleConfig;
	}

	/**
	 * 需要手动配置事务管理器
	 * 
	 * @param dataSource
	 * @return
	 */
	@Bean
	public DataSourceTransactionManager transactitonManager(@Qualifier("dataSource") DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	@Bean(name = "sqlSessionFactory")
	public SqlSessionFactory testSqlSessionFactory(@Qualifier("dataSource") DataSource dataSource) throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);
		bean.setMapperLocations(
				new PathMatchingResourcePatternResolver().getResources("classpath*:com/central/**/dao/*.xml"));
		return bean.getObject();
	}

	@Bean // 将数据源纳入spring事物管理
	public DataSourceTransactionManager transactionManager(@Qualifier("dataSource") DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

}
