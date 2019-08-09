package com.open.iot.autoconfigure;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * 
* @ClassName: LoggingConfigurationSelector  
* @Description: 装配bean
* @author huy  
* @date 2019年8月6日  
*
 */
public class LoggingConfigurationSelector implements ImportSelector {

	@Override
	public String[] selectImports(AnnotationMetadata importingClassMetadata) {
		return new String[] { 
				"com.open.iot.autoconfigure.datasource.DataSourceAspect",
				"com.open.iot.autoconfigure.log.LogAnnotationAspect"
		};
	}

}
