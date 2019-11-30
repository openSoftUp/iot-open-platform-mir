package com.open.iot.device.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.open.iot.common.service.impl.AbstractJdbcService;
import com.open.iot.device.dao.SiteDao;
import com.open.iot.device.entity.Site;
import com.open.iot.device.service.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author miaosc
 * @date 11/30/2019
 */
@Service
public class SiteServiceImpl extends AbstractJdbcService<Site> implements SiteService {

    @Autowired
    SiteDao siteDao;

    @Override
    protected BaseMapper<Site> getDao() {
        return siteDao;
    }
}
