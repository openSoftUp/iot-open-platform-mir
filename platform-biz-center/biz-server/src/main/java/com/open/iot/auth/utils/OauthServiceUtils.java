package com.open.iot.auth.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class OauthServiceUtils {

	public String getOauthDetail() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String clientId = (String) authentication.getPrincipal();
		return clientId;
	}
}
