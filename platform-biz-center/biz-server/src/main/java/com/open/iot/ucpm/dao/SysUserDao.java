package com.open.iot.ucpm.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.open.iot.model.system.SysUser;

/**
 * 
* @ClassName: SysUserDao 
* @Description:  用户管理
* @author huy
* @date 2019年6月16日 下午12:41:23 
*
 */
@Mapper
public interface SysUserDao  extends BaseMapper<SysUser> {

	@Options(useGeneratedKeys = true, keyProperty = "id")
	@Insert("insert into sys_user(username, password, nickname, headImgUrl, phone, sex, enabled, type, createTime, updateTime) "
			+ "values(#{username}, #{password}, #{nickname}, #{headImgUrl}, #{phone}, #{sex}, #{enabled}, #{type}, #{createTime}, #{updateTime})")
	int save(SysUser sysUser);

	int updateByOps(SysUser sysUser);

	@Select("select * from sys_user t where t.username = #{username}")
	SysUser findByUsername(String username);

	@Select("select * from sys_user t where t.id = #{id}")
	SysUser findById(Long id);

	int count(Map<String, Object> params);

	List<SysUser> findList(Map<String, Object> params);
	
	
	@Select("select u.* from sys_user u   where u.username = #{username}")
	SysUser findUserByUsername(String username);

}
