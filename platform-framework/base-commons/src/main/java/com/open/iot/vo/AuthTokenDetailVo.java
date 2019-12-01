package com.open.iot.vo;

import java.util.Set;

import lombok.Data;

@Data
public class AuthTokenDetailVo {

	private String access_token;
	private String refresh_token;
	private String token_type;
	private Integer expires_in;
	private Set<String> scope;
	private String client_id;
}
