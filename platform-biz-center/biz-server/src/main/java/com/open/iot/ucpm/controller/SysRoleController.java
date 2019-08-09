package com.open.iot.ucpm.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.open.iot.annotation.log.LogAnnotation;
import com.open.iot.model.system.SysRole;
import com.open.iot.modelandutils.base.CommonErrorCode;
import com.open.iot.modelandutils.base.Result;
import com.open.iot.ucpm.service.SysRoleService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 
* @ClassName: SysRoleController 
* @Description: * 角色管理
* @author huy
* @date 2019年6月16日 下午12:39:41 
*
 */
@RestController
@Api(tags = "角色模块api")
public class SysRoleController {

	@Autowired
	private SysRoleService sysRoleService;


	/**
	 * 后台管理查询角色
	 * @param params
	 * @return
	 * @throws JsonProcessingException 
	 */
	@PreAuthorize("hasAuthority('role:get/roles')")
	@ApiOperation(value = "后台管理查询角色")
	@GetMapping("/roles")
	@LogAnnotation(module="user-center",recordRequestParam=false)
	public Result<?> findRoles(@RequestParam Map<String, Object> params) throws JsonProcessingException {
		return Result.succeed(sysRoleService.findRoles(params), CommonErrorCode.OPERATION_SUCCESS.getMessage());
	}

	/**
	 * 角色新增或者更新
	 * @param sysRole
	 * @return
	 * @throws JsonProcessingException 
	 */
	@PreAuthorize("hasAnyAuthority('role:post/roles','role:put/roles')")
	@PostMapping("/roles/saveOrUpdate")
	@LogAnnotation(module="user-center",recordRequestParam=false)
	public Result<?> saveOrUpdate(@RequestBody SysRole sysRole) throws JsonProcessingException {
		return sysRoleService.saveOrUpdate(sysRole);
	}

	/**
	 * 后台管理删除角色
	 * delete /role/1
	 * @param id
	 */
	@PreAuthorize("hasAuthority('role:delete/roles/{id}')")
	@ApiOperation(value = "后台管理删除角色")
	@DeleteMapping("/roles/{id}")
	@LogAnnotation(module="user-center",recordRequestParam=false)
	public Result<?> deleteRole(@PathVariable Long id) {
		try {
			if (id == 1L){
				return Result.failed("管理员不可以删除");
			}
			sysRoleService.deleteRole(id);
			return Result.succeed();
		}catch (Exception e){
			e.printStackTrace();
			return Result.failed();
		}
	}

}
