package com.open.iot.dto;


import lombok.Data;

@Data
public class ClientDto {

	private Long id;
	private String clientId;
	private String clientSecretStr;
	
	private String webServerRedirectUri;

}
