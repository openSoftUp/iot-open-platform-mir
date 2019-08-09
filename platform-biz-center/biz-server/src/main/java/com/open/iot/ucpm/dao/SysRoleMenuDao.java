package com.open.iot.ucpm.dao;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.open.iot.model.system.SysMenu;

/**
 * 
* @ClassName: SysRoleMenuDao 
* @Description: 角色菜单
* @author huy
* @date 2019年6月16日 下午12:41:01 
*
 */
@Mapper
public interface SysRoleMenuDao {

	@Insert("insert into sys_role_menu(roleId, menuId) values(#{roleId}, #{menuId})")
	int save(@Param("roleId") Long roleId, @Param("menuId") Long menuId);

	int delete(@Param("roleId") Long roleId, @Param("menuId") Long menuId);

	@Select("select t.menuId from sys_role_menu t where t.roleId = #{roleId}")
	Set<Long> findMenuIdsByRoleId(Long roleId);

	List<SysMenu> findMenusByRoleIds(@Param("roleIds") Set<Long> roleIds);
}
