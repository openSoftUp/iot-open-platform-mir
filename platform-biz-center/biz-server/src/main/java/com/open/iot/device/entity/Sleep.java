package com.open.iot.device.entity;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "sleep")
@Data
public class Sleep implements Serializable{
	
	/**  
	* @Fields field:field:{todo}(用一句话描述这个变量表示什么)  
	*/
	private static final long serialVersionUID = 1L;
	
	//索引
	private String id;

}
