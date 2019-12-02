package com.open.iot.netdevicemgr.dao;

import com.open.iot.netdevicemgr.entity.Site;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 站点模型 Mapper 接口
 * </p>
 *
 * @author huy
 * @since 2019-12-02
 */
@Mapper
public interface SiteMapper extends BaseMapper<Site> {

}
