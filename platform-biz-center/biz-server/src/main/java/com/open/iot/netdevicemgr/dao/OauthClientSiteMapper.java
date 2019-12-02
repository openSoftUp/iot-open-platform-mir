package com.open.iot.netdevicemgr.dao;

import com.open.iot.netdevicemgr.entity.OauthClientSite;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 站点与客户端权限配置，多对多 Mapper 接口
 * </p>
 *
 * @author huy
 * @since 2019-12-02
 */
@Mapper
public interface OauthClientSiteMapper extends BaseMapper<OauthClientSite> {

}
