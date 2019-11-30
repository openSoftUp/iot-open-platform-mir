package com.open.iot.device.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.open.iot.common.service.impl.AbstractJdbcService;
import com.open.iot.device.dao.DtuControlDao;
import com.open.iot.device.entity.DtuControl;
import com.open.iot.device.service.DtuControlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author miaosc
 * @date 11/30/2019
 */
@Service
public class DtuControlServiceImpl extends AbstractJdbcService<DtuControl> implements DtuControlService {

    @Autowired
    DtuControlDao dtuControlDao;

    @Override
    protected BaseMapper<DtuControl> getDao() {
        return dtuControlDao;
    }
}
