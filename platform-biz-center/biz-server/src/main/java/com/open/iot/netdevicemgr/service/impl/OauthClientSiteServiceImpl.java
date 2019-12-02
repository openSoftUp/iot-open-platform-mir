package com.open.iot.netdevicemgr.service.impl;

import com.open.iot.netdevicemgr.entity.OauthClientSite;
import com.open.iot.netdevicemgr.dao.OauthClientSiteMapper;
import com.open.iot.netdevicemgr.service.OauthClientSiteService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 站点与客户端权限配置，多对多 服务实现类
 * </p>
 *
 * @author huy
 * @since 2019-12-02
 */
@Service
public class OauthClientSiteServiceImpl extends ServiceImpl<OauthClientSiteMapper, OauthClientSite> implements OauthClientSiteService {

}
