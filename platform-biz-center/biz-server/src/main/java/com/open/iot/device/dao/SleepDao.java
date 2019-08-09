package com.open.iot.device.dao;

import org.springframework.stereotype.Repository;

import com.open.iot.common.mongo.BaseDao;
import com.open.iot.device.entity.Sleep;

@Repository
public class SleepDao extends BaseDao<String,Sleep>{

	@Override
	protected Class<Sleep> getEntityClass() {
		return Sleep.class;
	}

}
