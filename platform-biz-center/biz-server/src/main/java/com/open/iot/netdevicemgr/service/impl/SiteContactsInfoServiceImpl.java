package com.open.iot.netdevicemgr.service.impl;

import com.open.iot.netdevicemgr.entity.SiteContactsInfo;
import com.open.iot.netdevicemgr.dao.SiteContactsInfoMapper;
import com.open.iot.netdevicemgr.service.SiteContactsInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 站点与联系人对应关系，多对多 服务实现类
 * </p>
 *
 * @author huy
 * @since 2019-12-02
 */
@Service
public class SiteContactsInfoServiceImpl extends ServiceImpl<SiteContactsInfoMapper, SiteContactsInfo> implements SiteContactsInfoService {

}
