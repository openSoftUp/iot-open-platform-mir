package com.open.iot.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 
* @ClassName: PasswordConfig  
* @Description: 装配密码匹配器  
* @author auth  
* @date 2019年8月6日  
*
 */
@Configuration
public class PasswordConfig {
	@Bean
	public PasswordEncoder passwordEncoder()	{
		return new BCryptPasswordEncoder();
	}
}
