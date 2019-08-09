package com.open.iot.ucpm.service;

import java.util.Map;
import java.util.Set;

import com.github.pagehelper.PageInfo;
import com.open.iot.model.system.LoginAppUser;
import com.open.iot.model.system.SysRole;
import com.open.iot.model.system.SysUser;
import com.open.iot.modelandutils.base.Result;

public interface SysUserService {

	/**
	 * 添加用户
	 * @param sysUser
	 */
	void addSysUser(SysUser sysUser);

	/**
	 * 修改用户
	 * @param sysUser
	 */
	SysUser updateSysUser(SysUser sysUser);

	/**
	 * 获取UserDetails对象
	 * @param username
	 * @return
	 */
	LoginAppUser findByUsername(String username);

	SysUser findById(Long id);

	/**
	 * 用户分配角色
	 * @param id
	 * @param roleIds
	 */
	void setRoleToUser(Long id, Set<Long> roleIds);

	/**
	 * 更新密码
	 * @param id
	 * @param oldPassword
	 * @param newPassword
	 * @return
	 */
	Result updatePassword(Long id, String oldPassword, String newPassword);

	/**
	 * 用户列表
	 * @param params
	 * @return
	 */
	PageInfo findUsers(Map<String, Object> params);

	/**
	 * 用户角色列表
	 * @param userId
	 * @return
	 */
	Set<SysRole> findRolesByUserId(Long userId);

	/**
	 * 状态变更
	 * @param params
	 * @return
	 */
	Result updateEnabled(Map<String, Object> params);

	/**
	 * 更新
	 * @param sysUser
	 * @return
	 */
	Result saveOrUpdate(SysUser sysUser);



}
