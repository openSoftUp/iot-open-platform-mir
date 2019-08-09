package com.open.iot.ucpm.service;

import java.util.Map;
import java.util.Set;

import com.github.pagehelper.PageInfo;
import com.open.iot.model.system.SysPermission;
import com.open.iot.model.system.SysRole;
import com.open.iot.modelandutils.base.Result;

public interface SysRoleService {

	/**
	 * 保存角色
	 * @param sysRole
	 */
	void save(SysRole sysRole);

	/**
	 * 修改角色
	 * @param sysRole
	 */
	void update(SysRole sysRole);

	/**
	 * 删除角色
	 * @param id
	 */
	void deleteRole(Long id);

	/**
	 * 分配权限
	 * @param id
	 * @param permissionIds
	 */
	void setPermissionToRole(Long id, Set<Long> permissionIds);

	/**
	 * ID获取角色
	 * @param id
	 * @return
	 */
	SysRole findById(Long id);

	/**
	 * 角色列表
	 * @param params
	 * @return
	 */
	PageInfo findRoles(Map<String, Object> params);

	/**
	 * 角色权限列表
	 * @param roleId
	 * @return
	 */
	Set<SysPermission> findPermissionsByRoleId(Long roleId);

	/**
	 * 更新角色
	 * @param sysRole
	 */
	Result saveOrUpdate(SysRole sysRole);

}
