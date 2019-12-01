package com.open.iot.auth.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.UnapprovedClientAuthenticationException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.OAuth2RequestFactory;
import org.springframework.security.oauth2.provider.TokenRequest;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.oauth2.provider.client.ClientCredentialsTokenGranter;
import org.springframework.security.oauth2.provider.refresh.RefreshTokenGranter;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import com.open.iot.annotation.log.LogAnnotation;
import com.open.iot.common.oauth2.RedisClientDetailsService;
import com.open.iot.dto.AppAuthDto;
import com.open.iot.modelandutils.base.CommonErrorCode;
import com.open.iot.modelandutils.base.Result;
import com.open.iot.utils.SpringUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;


@Api(tags = "OAuth2相关操作")
@RestController
@Slf4j
@RequestMapping("/oauth")
public class OAuth2Controller {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Resource
	private ObjectMapper objectMapper; // springmvc启动时自动装配json处理类

	@Autowired
	private TokenStore tokenStore;

	
	@ApiOperation(value = "登录,自己测试使用，不需要验证码:用户名密码获取token")
	@PostMapping("/user/token/pwd")
	public Result<?> getMyUserTokenInfo(
			@ApiParam(required = true, name = "username", value = "账号") @RequestParam(value = "username") String username,
			@ApiParam(required = true, name = "password", value = "密码") @RequestParam(value = "password") String password,
			HttpServletRequest request, HttpServletResponse response) {
		String clientId = "app";
		String clientSecret = "app";

		try {
			RedisClientDetailsService clientDetailsService = SpringUtil.getBean(RedisClientDetailsService.class);
			ClientDetails clientDetails = clientDetailsService.loadClientByClientId(clientId);

			if (clientDetails == null) {
				throw new UnapprovedClientAuthenticationException("clientId对应的信息不存在");
			} else if (!passwordEncoder.matches(clientSecret, clientDetails.getClientSecret())) {
				throw new UnapprovedClientAuthenticationException("clientSecret不匹配");
			}
			TokenRequest tokenRequest = new TokenRequest(MapUtils.EMPTY_MAP, clientId, clientDetails.getScope(),
					"password");
			OAuth2Request oAuth2Request = tokenRequest.createOAuth2Request(clientDetails);

			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);

			AuthenticationManager authenticationManager = SpringUtil.getBean(AuthenticationManager.class);

			Authentication authentication = authenticationManager.authenticate(token);
			SecurityContextHolder.getContext().setAuthentication(authentication);

			OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(oAuth2Request, authentication);

			AuthorizationServerTokenServices authorizationServerTokenServices = SpringUtil
					.getBean("defaultAuthorizationServerTokenServices", AuthorizationServerTokenServices.class);

			OAuth2AccessToken oAuth2AccessToken = authorizationServerTokenServices
					.createAccessToken(oAuth2Authentication);
			oAuth2Authentication.setAuthenticated(true);
			Result result = Result.succeed(oAuth2AccessToken, CommonErrorCode.OPERATION_SUCCESS.getMessage());
			return result;
		} catch (Exception e) {
			log.error("error:",e);
			Result result = Result.failedWith(null, CommonErrorCode.USER_PWD_ERROR.value(), CommonErrorCode.USER_PWD_ERROR.getMessage());
			return result;
		}
	}
	
	@ApiOperation(value = "登录,需要验证码：用户名密码获取token,使用此接口")
	@PostMapping("/user/token")
	public Result<?> getUserTokenInfo(
			@ApiParam(required = true, name = "username", value = "账号") @RequestParam(value = "username") String username,
			@ApiParam(required = true, name = "password", value = "密码") @RequestParam(value = "password") String password,
			HttpServletRequest request, HttpServletResponse response) {
		String clientId = request.getHeader("client_id");
		String clientSecret = request.getHeader("client_secret");

		try {

			if (clientId == null || "".equals(clientId)) {
				throw new UnapprovedClientAuthenticationException("请求头中无client_id信息");
			}

			if (clientSecret == null || "".equals(clientSecret)) {
				throw new UnapprovedClientAuthenticationException("请求头中无client_secret信息");
			}

			RedisClientDetailsService clientDetailsService = SpringUtil.getBean(RedisClientDetailsService.class);

			ClientDetails clientDetails = clientDetailsService.loadClientByClientId(clientId);

			if (clientDetails == null) {
				throw new UnapprovedClientAuthenticationException("clientId对应的信息不存在");
			} else if (!passwordEncoder.matches(clientSecret, clientDetails.getClientSecret())) {
				throw new UnapprovedClientAuthenticationException("clientSecret不匹配");
			}
			TokenRequest tokenRequest = new TokenRequest(MapUtils.EMPTY_MAP, clientId, clientDetails.getScope(),
					"password");
			OAuth2Request oAuth2Request = tokenRequest.createOAuth2Request(clientDetails);
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
			AuthenticationManager authenticationManager = SpringUtil.getBean(AuthenticationManager.class);
			Authentication authentication = authenticationManager.authenticate(token);
			SecurityContextHolder.getContext().setAuthentication(authentication);
			OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(oAuth2Request, authentication);
			AuthorizationServerTokenServices authorizationServerTokenServices = SpringUtil
					.getBean("defaultAuthorizationServerTokenServices", AuthorizationServerTokenServices.class);
			OAuth2AccessToken oAuth2AccessToken = authorizationServerTokenServices
					.createAccessToken(oAuth2Authentication);
			oAuth2Authentication.setAuthenticated(true);
			Result result = Result.succeed(oAuth2AccessToken, CommonErrorCode.OPERATION_SUCCESS.getMessage());
			return result;
		} catch (Exception e) {
			log.error("error:",e);
			Result result = Result.failedWith(null, CommonErrorCode.USER_PWD_ERROR.value(), CommonErrorCode.USER_PWD_ERROR.getMessage());
			return result;
		}
	}
	
	@ApiOperation(value = "access_token刷新token")
	@PostMapping(value = "/refresh/token", params = "access_token")
	public Result<?> refreshTokenInfo(String access_token, HttpServletRequest request, HttpServletResponse response) {
		// 拿到当前用户信息
		try {
			Authentication user = SecurityContextHolder.getContext().getAuthentication();

			if (user != null) {
				if (user instanceof OAuth2Authentication) {
					Authentication athentication = (Authentication) user;
					OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) athentication.getDetails();
				}

			}
			OAuth2AccessToken accessToken = tokenStore.readAccessToken(access_token);
			OAuth2Authentication auth = (OAuth2Authentication) user;
			RedisClientDetailsService clientDetailsService = SpringUtil.getBean(RedisClientDetailsService.class);

			ClientDetails clientDetails = clientDetailsService
					.loadClientByClientId(auth.getOAuth2Request().getClientId());

			AuthorizationServerTokenServices authorizationServerTokenServices = SpringUtil
					.getBean("defaultAuthorizationServerTokenServices", AuthorizationServerTokenServices.class);
			OAuth2RequestFactory requestFactory = new DefaultOAuth2RequestFactory(clientDetailsService);

			RefreshTokenGranter refreshTokenGranter = new RefreshTokenGranter(authorizationServerTokenServices,
					clientDetailsService, requestFactory);

			Map<String, String> map = Maps.newHashMap();
			map.put("grant_type", "refresh_token");
			map.put("refresh_token", accessToken.getRefreshToken().getValue());
			TokenRequest tokenRequest = new TokenRequest(map, auth.getOAuth2Request().getClientId(),
					auth.getOAuth2Request().getScope(), "refresh_token");

			OAuth2AccessToken oAuth2AccessToken = refreshTokenGranter.grant("refresh_token", tokenRequest);

			tokenStore.removeAccessToken(accessToken);

			Result result = Result.succeed(oAuth2AccessToken, CommonErrorCode.OPERATION_SUCCESS.getMessage());
			return result;
		} catch (Exception e) {
			log.error("error:",e);
			Result result = Result.failedWith(null, CommonErrorCode.UNAUTHORIZED.value(), CommonErrorCode.UNAUTHORIZED.getMessage());
			return result;
		}

	}

	/**
	 * 移除access_token和refresh_token
	 * 
	 * @param access_token
	 */
	@ApiOperation(value = "退出登录：移除token")
	@PostMapping(value = "/remove/token", params = "access_token")
	public void removeToken(String access_token) {
		// 拿到当前用户信息
		Authentication user = SecurityContextHolder.getContext().getAuthentication();
		if (user != null) {
			if (user instanceof OAuth2Authentication) {
				Authentication athentication = (Authentication) user;
				OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) athentication.getDetails();
			}
		}
		OAuth2AccessToken accessToken = tokenStore.readAccessToken(access_token);
		if (accessToken != null) {
			// 移除access_token
			tokenStore.removeAccessToken(accessToken);
			// 移除refresh_token
			if (accessToken.getRefreshToken() != null) {
				tokenStore.removeRefreshToken(accessToken.getRefreshToken());
			}

		}
	}

	@ApiOperation(value = "获取token信息")
	@PostMapping(value = "/get/token", params = "access_token")
	public Result<?> getTokenInfo(String access_token) {
		// 拿到当前用户信息
		Authentication user = SecurityContextHolder.getContext().getAuthentication();
		if (user != null) {
			if (user instanceof OAuth2Authentication) {
				Authentication athentication = (Authentication) user;
				OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) athentication.getDetails();
			}
		}
		OAuth2AccessToken accessToken = tokenStore.readAccessToken(access_token);
		return Result.succeed(accessToken, CommonErrorCode.OPERATION_SUCCESS.getMessage());

	}

	@ApiOperation(value = "当前登陆用户信息")
	@GetMapping(value = { "/userinfo" }, produces = "application/json") 
	public Result<?> getCurrentUserDetail() {
		Map<String, Object> userInfo = Maps.newHashMap();
		userInfo.put("user", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		log.debug("认证详细信息{}:"+ SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
		log.info("返回信息:{}", userInfo);
		return Result.succeed(userInfo, CommonErrorCode.OPERATION_SUCCESS.getMessage());
	}
	
	@ApiOperation(value = "clientId获取token")
	@PostMapping("/client/token")
	@LogAnnotation(module = "biz-server", recordRequestParam = false)
	public Result<?> getClientTokenInfo(@RequestBody AppAuthDto dto) {

		String clientId = dto.getClientId();
		String clientSecret = dto.getClientSecret();
		try {
			if (clientId == null || "".equals(clientId)) {
				throw new UnapprovedClientAuthenticationException("请求参数中无clientId信息");
			}
			if (clientSecret == null || "".equals(clientSecret)) {
				throw new UnapprovedClientAuthenticationException("请求参数中无clientSecret信息");
			}
			RedisClientDetailsService clientDetailsService = SpringUtil.getBean(RedisClientDetailsService.class);
			ClientDetails clientDetails = clientDetailsService.loadClientByClientId(clientId);
			if (clientDetails == null) {
				throw new UnapprovedClientAuthenticationException("clientId对应的信息不存在");
			} else if (!passwordEncoder.matches(clientSecret, clientDetails.getClientSecret())) {
				throw new UnapprovedClientAuthenticationException("clientSecret不匹配");
			}
			Map<String, String> map = new HashMap<>();
			map.put("client_secret", clientSecret);
			map.put("client_id", clientId);
			map.put("grant_type", "client_credentials");
			TokenRequest tokenRequest = new TokenRequest(map, clientId, clientDetails.getScope(), "client_credentials");
			OAuth2Request oAuth2Request = tokenRequest.createOAuth2Request(clientDetails);
			AuthorizationServerTokenServices authorizationServerTokenServices = SpringUtil
					.getBean("defaultAuthorizationServerTokenServices", AuthorizationServerTokenServices.class);
			OAuth2RequestFactory requestFactory = new DefaultOAuth2RequestFactory(clientDetailsService);
			ClientCredentialsTokenGranter clientCredentialsTokenGranter = new ClientCredentialsTokenGranter(
					authorizationServerTokenServices, clientDetailsService, requestFactory);
			clientCredentialsTokenGranter.setAllowRefresh(true);
			OAuth2AccessToken oAuth2AccessToken = clientCredentialsTokenGranter.grant("client_credentials",
					tokenRequest);
			return Result.succeed(oAuth2AccessToken, CommonErrorCode.OPERATION_SUCCESS.getMessage());
		} catch (Exception e) {
			Map<String, Object> rsp = new HashMap<>();
			rsp.put("code", HttpStatus.UNAUTHORIZED.value());
			rsp.put("msg", e.getMessage());
			Result result = Result.failedWith(rsp, CommonErrorCode.UNAUTHORIZED.value(), CommonErrorCode.UNAUTHORIZED.getMessage());
			return result;

		}
	}

}
