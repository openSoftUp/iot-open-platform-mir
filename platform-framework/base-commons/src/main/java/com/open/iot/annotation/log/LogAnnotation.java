package com.open.iot.annotation.log;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
* @ClassName: LogAnnotation 
* @Description: 日志注解；注解了才生效日志
* @author huy
* @date 2019年6月16日 下午12:43:10 
*
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogAnnotation {

	/**
	 * 模块
	 * @return
	 */
	String module();

	/**
	 * 是否记录执行参数
	 * @return
	 */
	boolean recordRequestParam() default true;
}
