package com.open.iot.common.config;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.util.Assert;

import com.open.iot.common.oauth2.RedisTemplateTokenStore;
import com.open.iot.common.oauth2.ResJwtAccessTokenConverter;

/**
 * 
* @ClassName: TokenStoreConfig  
* @Description: redis存储token
* @author huy  
* @date 2019年8月6日  
*
 */
@Configuration
public class TokenStoreConfig  {


	@Resource
	private DataSource dataSource ;
	
	@Autowired(required=false)
	private RedisTemplate<String, Object>  redisTemplate ;


	@Bean
	@ConditionalOnProperty(prefix="security.oauth2.token.store",name="type" ,havingValue="jdbc" ,matchIfMissing=false)
	public JdbcTokenStore jdbcTokenStore(){
 
		return new JdbcTokenStore( dataSource ) ;

	}
	@Bean
	@ConditionalOnProperty(prefix="security.oauth2.token.store",name="type" ,havingValue="redis" ,matchIfMissing=true)
	public RedisTemplateTokenStore redisTokenStore(){
//		return new RedisTokenStore( redisTemplate.getConnectionFactory() ) ; //单台redis服务器
		Assert.state(redisTemplate != null, "RedisTemplate must be provided");

		RedisTemplateTokenStore redisTemplateStore = new RedisTemplateTokenStore()  ;
		redisTemplateStore.setRedisTemplate(redisTemplate);
		return redisTemplateStore ;
		 

	}
	
	//使用jwt替换原有的uuid生成token方式
	@Configuration
	@ConditionalOnProperty(prefix="security.oauth2.token.store",name="type" ,havingValue="jwt" ,matchIfMissing=false)
	public static class JWTTokenConfig {
		@Bean
		public JwtTokenStore jwtTokenStore(){
			return new JwtTokenStore( jwtAccessTokenConverter() ) ;
		}
		
		@Bean
		public JwtAccessTokenConverter jwtAccessTokenConverter(){
			JwtAccessTokenConverter accessTokenConverter = new ResJwtAccessTokenConverter();
			accessTokenConverter.setSigningKey("huy");
			return accessTokenConverter ;
		}
	}
	
	
	
}
