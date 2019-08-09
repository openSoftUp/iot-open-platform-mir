package com.open.iot.common.config;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.common.exceptions.InvalidScopeException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.common.exceptions.RedirectMismatchException;
import org.springframework.security.oauth2.common.exceptions.UnsupportedResponseTypeException;
import org.springframework.security.oauth2.provider.error.DefaultWebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.open.iot.modelandutils.base.CommonErrorCode;
import com.open.iot.modelandutils.base.Result;
import com.open.iot.modelandutils.constant.CommonStant;

/**
 * 
* @ClassName: SecurityHandlerConfig  
* @Description: TODO(这里用一句话描述这个类的作用)  
* @author huy  
* @date 2019年8月6日  
*
 */
@Component
@Configuration
public class SecurityHandlerConfig {

	@Resource
	private ObjectMapper objectMapper; // springmvc启动时自动装配json处理类


	@Autowired(required = false)
	private AuthenticationEntryPoint authenticationEntryPoint;


	/**
	 * 登陆成功，返回Token 装配此bean不支持授权码模式
	 * 
	 * @return
	 */
	@Bean
	public AuthenticationSuccessHandler loginSuccessHandler() {
		return new SavedRequestAwareAuthenticationSuccessHandler() {

			private RequestCache requestCache = new HttpSessionRequestCache();

			@Override
			public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
					Authentication authentication) throws IOException, ServletException {

				super.onAuthenticationSuccess(request, response, authentication);
				return;

			}
		};
	}

	/**
	 * 登陆失败
	 * 
	 * @return
	 */
	@Bean
	public AuthenticationFailureHandler loginFailureHandler() {
		return new AuthenticationFailureHandler() {

			@Override
			public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
					AuthenticationException exception) throws IOException, ServletException {
				String msg = null;
				if (exception instanceof BadCredentialsException) {
					msg = "密码错误";
				} else {
					msg = exception.getMessage();
				}

				Result result = Result.failed(CommonErrorCode.USER_PWD_ERROR);

				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().write(objectMapper.writeValueAsString(result));
				response.getWriter().flush();
				response.getWriter().close();

			}
		};

	}
	
	/**
	 * 未登录，返回401
	 * 
	 * @return
	 */
	@Bean
	public AuthenticationEntryPoint authenticationEntryPoint() {
		return new AuthenticationEntryPoint() {

			@Override
			public void commence(HttpServletRequest request, HttpServletResponse response,
					AuthenticationException authException) throws IOException, ServletException {
				
				Map<String ,String > rsp =new HashMap<>();  
				
				Result result = Result.failed(CommonErrorCode.USER_PWD_ERROR);
                response.setContentType("application/json;charset=UTF-8");
    			response.getWriter().write(objectMapper.writeValueAsString(result));
    			response.getWriter().flush();
    			response.getWriter().close();
                
			}
		};
	}
	
	/**
	 * 处理spring security oauth 处理失败返回消息格式
	 * CommonStant.RESULT_CODE
	 * resp_desc
	 */
    @Bean
    public OAuth2AccessDeniedHandler oAuth2AccessDeniedHandler(){
    	return new OAuth2AccessDeniedHandler(){
    	    
    	    @Override
    	    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException authException) throws IOException, ServletException {

    	    	Map<String ,String > rsp =new HashMap<>();  
    	    	response.setContentType("application/json;charset=UTF-8");

    	    	Result result = Result.failed(CommonErrorCode.USER_PWD_ERROR);
                
                response.setContentType("application/json;charset=UTF-8");
    			response.getWriter().write(objectMapper.writeValueAsString(result));
    			response.getWriter().flush();
    			response.getWriter().close();
    	        
    	    }
    	};
    }

	@Bean
	public WebResponseExceptionTranslator webResponseExceptionTranslator() {
		return new DefaultWebResponseExceptionTranslator() {

			public static final String BAD_MSG = "坏的凭证";

			@Override
			public ResponseEntity<OAuth2Exception> translate(Exception e) throws Exception {
				// e.printStackTrace();
				OAuth2Exception oAuth2Exception;
				if (e.getMessage() != null && e.getMessage().equals(BAD_MSG)) {
					oAuth2Exception = new InvalidGrantException("用户名或密码错误", e);
				} else if (e instanceof InternalAuthenticationServiceException) {
					oAuth2Exception = new InvalidGrantException(e.getMessage(), e);
				} else if (e instanceof RedirectMismatchException) {
					oAuth2Exception = new InvalidGrantException(e.getMessage(), e);
				} else if (e instanceof InvalidScopeException) {
					oAuth2Exception = new InvalidGrantException(e.getMessage(), e);
				} else {
					oAuth2Exception = new UnsupportedResponseTypeException("服务内部错误", e);
				}

				ResponseEntity<OAuth2Exception> response = super.translate(oAuth2Exception);
				ResponseEntity.status(oAuth2Exception.getHttpErrorCode());
				response.getBody().addAdditionalInformation(CommonStant.RESULT_CODE, "10000400");
				response.getBody().addAdditionalInformation(CommonStant.RESULT_MSG, oAuth2Exception.getMessage());

				return response;
			}

		};
	}
	 
	@Bean
	public OauthLogoutHandler oauthLogoutHandler() {
		return new OauthLogoutHandler();
	}
	 

}
