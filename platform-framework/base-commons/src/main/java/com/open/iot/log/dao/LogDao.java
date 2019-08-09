package com.open.iot.log.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import com.open.iot.model.log.SysLog;

@Mapper
public interface LogDao {

	@Insert("insert into sys_log(username, module, params, remark, flag, createTime) values(#{username}, #{module}, #{params}, #{remark}, #{flag}, #{createTime})")
	int save(SysLog log);

}
