package com.open.iot.ucpm.dao;

import java.util.Set;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.open.iot.model.system.SysPermission;

 
/**
 * 
* @ClassName: SysRolePermissionDao 
* @Description: 角色权限关系
* @author huy
* @date 2019年6月16日 下午12:41:13 
*
 */
@Mapper
public interface SysRolePermissionDao {

	@Insert("insert into sys_role_permission(roleId, permissionId) values(#{roleId}, #{permissionId})")
	int saveRolePermission(@Param("roleId") Long roleId, @Param("permissionId") Long permissionId);

	int deleteRolePermission(@Param("roleId") Long roleId, @Param("permissionId") Long permissionId);

	Set<SysPermission> findPermissionsByRoleIds(@Param("roleIds") Set<Long> roleIds);

}
