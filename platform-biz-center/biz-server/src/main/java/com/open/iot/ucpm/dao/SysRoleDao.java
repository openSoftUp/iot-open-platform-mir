package com.open.iot.ucpm.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.open.iot.model.system.SysRole;

/**
* @ClassName: SysRoleDao 
* @Description: 角色
* @author huy
* @date 2019年6月16日 下午12:40:50 
*
 */
@Mapper
public interface SysRoleDao {

	@Options(useGeneratedKeys = true, keyProperty = "id")
	@Insert("insert into sys_role(code, name, createTime, updateTime) values(#{code}, #{name}, #{createTime}, #{createTime})")
	int save(SysRole sysRole);

	@Update("update sys_role t set t.name = #{name} ,t.updateTime = #{updateTime} where t.id = #{id}")
	int updateByOps(SysRole sysRole);

	@Select("select * from sys_role t where t.id = #{id}")
	SysRole findById(Long id);

	@Select("select * from sys_role t where t.code = #{code}")
	SysRole findByCode(String code);

	@Delete("delete from sys_role where id = #{id}")
	int delete(Long id);

	int count(Map<String, Object> params);

	List<SysRole> findList(Map<String, Object> params);

}
