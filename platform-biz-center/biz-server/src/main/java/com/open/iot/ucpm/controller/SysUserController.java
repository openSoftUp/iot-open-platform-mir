package com.open.iot.ucpm.controller;

import java.util.Map;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.collect.Maps;
import com.open.iot.annotation.log.LogAnnotation;
import com.open.iot.model.system.LoginAppUser;
import com.open.iot.model.system.SysUser;
import com.open.iot.modelandutils.base.CommonErrorCode;
import com.open.iot.modelandutils.base.Result;
import com.open.iot.ucpm.service.SysUserService;
import com.open.iot.utils.SysUserUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * 
* @ClassName: SysUserController 
* @Description: 用户
* @author huy
* @date 2019年6月16日 下午12:40:14 
*
 */
@Slf4j
@RestController
@Api(tags = "用户模块api")
public class SysUserController {

    @Autowired
    private SysUserService appUserService;

    /**
     * 当前登录用户 LoginAppUser
     *
     * @return
     * @throws JsonProcessingException 
     */
    @ApiOperation(value = "根据access_token当前登录用户")
    @GetMapping("/users/current")
    @LogAnnotation(module="user-center",recordRequestParam=true)
    public LoginAppUser getLoginAppUser()   {
		LoginAppUser loginUser = null ;
		try {
			loginUser = SysUserUtil.getLoginAppUser() ;
		} catch (Exception e) {
		}
		
        return loginUser ;
    }
    
    @GetMapping(value = "/users-anon/login", params = "username")
    @ApiOperation(value = "根据用户名查询用户")
    @LogAnnotation(module="user-center",recordRequestParam=false)
    public LoginAppUser findByUsername(String username) {
        return appUserService.findByUsername(username);
    }



    @PreAuthorize("hasAuthority('user:get/users')")
    @GetMapping("/users/{id}")
    @LogAnnotation(module="user-center",recordRequestParam=false)
    public SysUser findUserById(@PathVariable Long id) {
        return appUserService.findById(id);
    }

    /**
     * 管理后台，给用户重置密码
     *
     * @param id
     * @param newPassword
     */
    @PutMapping(value = "/users/{id}/password", params = {"newPassword"})
    @LogAnnotation(module="user-center",recordRequestParam=false)
    public Result<?> resetPassword(@PathVariable Long id, String newPassword) {
        appUserService.updatePassword(id, null, newPassword);
        return Result.succeed();
    }

    
    @ApiOperation(value = "用户查询列表")
    @GetMapping("/users")
    @LogAnnotation(module="user-center",recordRequestParam=false)
    public Result<?> findUsers() throws JsonProcessingException {
        return Result.succeed(appUserService.findUsers(Maps.newHashMap()), CommonErrorCode.OPERATION_SUCCESS.getMessage());
    }

    /**
     * 修改密码
     *
     * @param sysUser
     * @throws JsonProcessingException 
     */
    @PutMapping(value = "/users/password")
    @PreAuthorize("hasAuthority('user:put/users/password')")
    @LogAnnotation(module="user-center",recordRequestParam=false)
    public Result<?> updatePassword(@RequestBody SysUser sysUser) throws JsonProcessingException {
        if (StringUtils.isBlank(sysUser.getOldPassword())) {
            throw new IllegalArgumentException("旧密码不能为空");
        }
        if (StringUtils.isBlank(sysUser.getNewPassword())) {
            throw new IllegalArgumentException("新密码不能为空");
        }

        if (sysUser.getId() == 1L){
            return Result.failed("超级管理员不给予修改");
        }

        return appUserService.updatePassword(sysUser.getId(), sysUser.getOldPassword(), sysUser.getNewPassword());
    }

    /**
     *  修改用户状态
     * @param params
     * @return
     */
    @ApiOperation(value = "修改用户状态")
    @GetMapping("/users/updateEnabled")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "enabled",value = "是否启用", required = true, dataType = "Boolean")
    })
    @LogAnnotation(module="user-center",recordRequestParam=false)
    @PreAuthorize("hasAnyAuthority('user:get/users/updateEnabled' ,'user:put/users/me')")
    public Result<?> updateEnabled(@RequestParam Map<String, Object> params){
        Long id = MapUtils.getLong(params, "id");
        if (id == 1L){
            return Result.failed("超级管理员不给予修改");
        }
        return appUserService.updateEnabled(params);
    }

    /**
     * 管理后台，给用户重置密码
     * @param id
     */
    @PreAuthorize("hasAuthority('user:post/users/{id}/resetPassword' )")
    @PostMapping(value = "/users/{id}/resetPassword")
    @LogAnnotation(module="user-center",recordRequestParam=false)
    public Result<?> resetPassword(@PathVariable Long id) {
        if (id == 1L){
            return Result.failed("超级管理员不给予修改");
        }
        appUserService.updatePassword(id, null, "123456");
        return Result.succeed();
    }

}
