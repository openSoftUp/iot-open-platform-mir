package com.open.iot.netdevicemgr.dao;

import com.open.iot.netdevicemgr.entity.SiteContactsInfo;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 站点与联系人对应关系，多对多 Mapper 接口
 * </p>
 *
 * @author huy
 * @since 2019-12-02
 */
@Mapper
public interface SiteContactsInfoMapper extends BaseMapper<SiteContactsInfo> {

}
