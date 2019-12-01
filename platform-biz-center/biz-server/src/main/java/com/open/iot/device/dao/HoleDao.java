package com.open.iot.device.dao;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.open.iot.device.entity.Hole;

/**
 * @author miaosc
 * @date 11/28/2019
 */
@Mapper
public interface HoleDao extends BaseMapper<Hole> {
}
