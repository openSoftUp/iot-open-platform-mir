package com.open.iot.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;

import com.open.iot.autoconfigure.LoggingConfigurationSelector;



/**
 * 
* @ClassName: EnableLogging 
* @Description: 启动日志框架支持,starter
* @author huy
* @date 2019年6月16日 下午12:43:22 
*
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
//自动装配starter
@Import(LoggingConfigurationSelector.class)
public @interface EnableLogging{
//	String name() ;
}