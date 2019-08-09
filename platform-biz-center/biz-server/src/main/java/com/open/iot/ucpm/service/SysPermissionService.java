package com.open.iot.ucpm.service;

import java.util.Map;
import java.util.Set;

import com.github.pagehelper.PageInfo;
import com.open.iot.model.system.SysPermission;

public interface SysPermissionService {

	/**
	 * 根绝角色ids获取权限集合
	 * 
	 * @param roleIds
	 * @return
	 */
	Set<SysPermission> findByRoleIds(Set<Long> roleIds);

	/**
	 * 保存权限
	 * @param sysPermission
	 */
	void save(SysPermission sysPermission);

	/**
	 * 修改权限
	 * @param sysPermission
	 */
	void update(SysPermission sysPermission);

	/**
	 * 删除权限
	 * @param id
	 */
	void delete(Long id);

	/**
	 * 权限列表
	 * @param params
	 * @return
	 */
	PageInfo findPermissions(Map<String, Object> params);

	/**
	 * 授权
	 * @param roleId
	 * @param authIds
	 */
	void setAuthToRole(Long roleId, Set<Long> authIds);

}
