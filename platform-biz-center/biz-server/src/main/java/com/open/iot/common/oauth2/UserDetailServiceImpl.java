package com.open.iot.common.oauth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.open.iot.model.system.LoginAppUser;
import com.open.iot.ucpm.service.SysUserService;

/**
 * 
* @ClassName: UserDetailServiceImpl  
* @Description:用户
* @author huy  
* @date 2019年8月9日  
*
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

	
	@Autowired
	private SysUserService sysUserService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

//      可集成
        LoginAppUser loginAppUser = sysUserService.findByUsername(username);
        if (loginAppUser == null) {
            throw new AuthenticationCredentialsNotFoundException("用户不存在");
        } else if (!loginAppUser.isEnabled()) {
            throw new DisabledException("无效用户");
        }

        return loginAppUser;
    }


     
}
