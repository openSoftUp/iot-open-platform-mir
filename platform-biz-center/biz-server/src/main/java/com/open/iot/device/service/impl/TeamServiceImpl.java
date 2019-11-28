package com.open.iot.device.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.open.iot.device.dao.TeamDao;
import com.open.iot.device.entity.Team;
import com.open.iot.device.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author miaosc
 * @date 11/28/2019
 */
@Service
public class TeamServiceImpl extends AbstractJdbcService<Team> implements TeamService {

    @Autowired
    TeamDao teamDao;

    @Override
    protected BaseMapper<Team> getDao() {
        return teamDao;
    }
}
