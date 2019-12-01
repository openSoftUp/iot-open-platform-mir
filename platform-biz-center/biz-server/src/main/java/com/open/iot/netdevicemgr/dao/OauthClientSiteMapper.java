package com.open.iot.netdevicemgr.dao;


import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.open.iot.netdevicemgr.entity.OauthClientSite;


/**
 * 
* @ClassName: OauthClientSiteMapper 
* @Description: 客户端与站点关系管理) 
* @author huy
* @date 2019年12月1日 上午10:58:03 
*
 */
@Mapper
public interface OauthClientSiteMapper extends BaseMapper<OauthClientSite> {

}
