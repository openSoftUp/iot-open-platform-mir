package com.open.iot.redis.config.constant;

/**
 * redis通道KEY
* @ClassName: RedisCacheConstant  
* @Description: TODO(这里用一句话描述这个类的作用)  
* @author huy  
* @date 2019年8月2日  
*
 */
public interface RedisCacheConstant {

	//---------------user服务  start
	//用户相关业务模块缓存前缀
	public static final String USER_CENTER_PRE = "user:center:";
	
	
	//---------------user服务  end
	
	//--------device服务 start
	public static final String DEVICE_CENTER_PRE = "device:center:";
	
	//--------device服务 end
	
	public static final String REDIS_EXPIRED_PRE="__keyevent@2__:expired";
}
