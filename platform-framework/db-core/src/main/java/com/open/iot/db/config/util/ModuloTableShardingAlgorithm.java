package com.open.iot.db.config.util;

import java.util.Collection;

import io.shardingjdbc.core.api.algorithm.sharding.PreciseShardingValue;
import io.shardingjdbc.core.api.algorithm.sharding.standard.PreciseShardingAlgorithm;

/**
 * 
* @ClassName: ModuloTableShardingAlgorithm  
* @Description: 分表策略的基本实现  
* @author auth  
* @date 2019年8月9日  
*
 */
public class ModuloTableShardingAlgorithm implements PreciseShardingAlgorithm<Long> {
    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<Long> preciseShardingValue) {
        for (String each : collection) {
            if (each.endsWith(Long.parseLong(preciseShardingValue.getValue().toString()) % 2+"")) {
                return each;
            }
        }
        throw new IllegalArgumentException();
    }


}