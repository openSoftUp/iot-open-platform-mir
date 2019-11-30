package com.open.iot.device.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.open.iot.common.service.impl.AbstractJdbcService;
import com.open.iot.device.dao.ChestDao;
import com.open.iot.device.entity.Chest;
import com.open.iot.device.service.ChestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author miaosc
 * @date 11/30/2019
 */
@Service
public class ChestServiceImpl extends AbstractJdbcService<Chest> implements ChestService {

    @Autowired
    ChestDao chestDao;

    @Override
    protected BaseMapper<Chest> getDao() {
        return chestDao;
    }
}
