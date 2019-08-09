package com.open.iot.ucpm.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.collect.Maps;
import com.open.iot.annotation.log.LogAnnotation;
import com.open.iot.model.system.SysPermission;
import com.open.iot.modelandutils.base.CommonErrorCode;
import com.open.iot.modelandutils.base.Result;
import com.open.iot.ucpm.service.SysPermissionService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * 
* @ClassName: SysPermissionController 
* @Description: 权限管理
* @author huy
* @date 2019年6月16日 下午12:40:02 
*
 */
@RestController
@Api(tags = "权限模块api")
public class SysPermissionController {

	@Autowired
	private SysPermissionService sysPermissionService;
	/**
	 * 删除权限标识
	 * 参考 /permissions/1
	 * @param id
	 */
	@PreAuthorize("hasAuthority('permission:delete/permissions/{id}')")
	@ApiOperation(value = "后台管理删除权限标识")
	@DeleteMapping("/permissions/{id}")
	@LogAnnotation(module="user-center",recordRequestParam=false)
	public Result<?> delete(@PathVariable Long id) {

		try{
			sysPermissionService.delete(id);
			return  Result.succeed();
		}catch (Exception ex){
			ex.printStackTrace();
			return  Result.failed();
		}

	}


	
	@PreAuthorize("hasAuthority('permission:get/permissions')")
	@ApiOperation(value = "后台管理查询所有的权限标识")
	@LogAnnotation(module="user-center",recordRequestParam=false)
	@GetMapping("/permissions")
	public Result<?> findPermissions() throws JsonProcessingException {
		return Result.succeed(sysPermissionService.findPermissions(Maps.newHashMap()), CommonErrorCode.OPERATION_SUCCESS.getMessage());
	}

	/**
	 * 权限新增或者更新
	 * @param sysPermission
	 * @return
	 */
	@PreAuthorize("hasAnyAuthority('permission:put/permissions','permission:post/permissions')")
	@PostMapping("/permissions/saveOrUpdate")
	@LogAnnotation(module="user-center",recordRequestParam=false)
	public Result<?> saveOrUpdate(@RequestBody SysPermission sysPermission) {
		try{
			if (sysPermission.getId()!=null){
				sysPermissionService.update(sysPermission);
			}else {
				sysPermissionService.save(sysPermission);
			}
			return Result.succeed();
		}catch (Exception ex){
			return Result.failed();
		}
	}

	/**
	 * 给角色分配权限
	 * @throws JsonProcessingException 
	 */
	@PreAuthorize("hasAuthority('permission:post/permissions/granted')")
	@ApiOperation(value = "角色分配权限")
	@PostMapping("/permissions/granted")
	@LogAnnotation(module="user-center",recordRequestParam=false)
	public Result<?> setAuthToRole(@RequestBody SysPermission sysPermission) throws JsonProcessingException {
		sysPermissionService.setAuthToRole(sysPermission.getRoleId(),sysPermission.getAuthIds());
		return Result.succeed();
	}

}
