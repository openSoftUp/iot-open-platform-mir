package com.open.iot.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

import com.open.iot.props.FilterIgnorePropertiesConfig;
import com.open.iot.props.PermitUrlProperties;

/**
 * 
* @ClassName: SecurityConfig  
* @Description: spring security配置
*               在WebSecurityConfigurerAdapter不拦截oauth要开放的资源
* @author huy  
* @date 2019年8月6日  
*
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableConfigurationProperties(PermitUrlProperties.class)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private AuthenticationSuccessHandler authenticationSuccessHandler;
	@Autowired
	private AuthenticationFailureHandler authenticationFailureHandler;
	// @Autowired
	// private LogoutSuccessHandler logoutSuccessHandler;
	@Autowired(required = false)
	private AuthenticationEntryPoint authenticationEntryPoint;
	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private OauthLogoutHandler oauthLogoutHandler;
	@Autowired
	private PermitUrlProperties permitUrlProperties ;
	
	@Autowired
	private FilterIgnorePropertiesConfig ignorePropertiesConfig;
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources", "/configuration/security",
				"/swagger-ui.html", "/webjars/**", "/login.html");
		web.ignoring().antMatchers("/js/**");
		web.ignoring().antMatchers("/css/**");
		web.ignoring().antMatchers("/health");
		// 忽略登录界面
		web.ignoring().antMatchers("/login.html");
		web.ignoring().antMatchers("/index.html");
		web.ignoring().antMatchers("/oauth/user/token");
		web.ignoring().antMatchers("/oauth/client/token");
		web.ignoring().antMatchers("/oauth/user/token/pwd");
		web.ignoring().antMatchers("/validata/code/**");
		web.ignoring().antMatchers(permitUrlProperties.getIgnored());
		
	}
	/**
	 * 认证管理
	 * 
	 * @return 认证管理对象
	 * @throws Exception
	 *             认证异常信息
	 */
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();

		http.authorizeRequests()
				.anyRequest().authenticated();
		http.formLogin().loginPage("/login.html").loginProcessingUrl("/user/login")
				.successHandler(authenticationSuccessHandler).failureHandler(authenticationFailureHandler);

		// 基于密码 等模式可以无session,不支持授权码模式
		if (authenticationEntryPoint != null) {
			http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint);
			http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		} else {
			// 授权码模式单独处理，需要session的支持，此模式可以支持所有oauth2的认证
			http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);
		}

		http.logout().logoutSuccessUrl("/login.html")
				.logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler())
				.addLogoutHandler(oauthLogoutHandler).clearAuthentication(true);

		//TODO 增加验证码处理
		http.headers().frameOptions().disable();
		
		ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = http
				.authorizeRequests();
		ignorePropertiesConfig.getUrls().forEach(url -> registry.antMatchers(url).permitAll());
		registry.anyRequest().authenticated().and().csrf().disable();
		
		http.headers().cacheControl();

	}

	/**
	 * 全局用户信息
	 * 
	 * @param auth
	 *            认证管理
	 * @throws Exception
	 *             用户认证异常信息
	 */
	@Autowired
	public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	}


}
