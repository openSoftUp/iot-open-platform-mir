package com.open.iot.common.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.open.iot.common.service.JdbcService;

import java.io.Serializable;
import java.util.Optional;

/**
 * 抽象的Jdbc服务,封装了一下简单的方法实现
 *
 * @author miaosc
 * @date 11/28/2019
 */
public abstract class AbstractJdbcService<T extends Serializable> implements JdbcService<T> {

    @Override
    public void deleteById(Serializable id) {
        getDao().deleteById(id);
    }

    @Override
    public void insert(T entity) {
        getDao().insert(entity);
    }

    @Override
    public Optional<T> findById(Serializable id) {
        return Optional.ofNullable(getDao().selectById(id));
    }

    @Override
    public IPage<T> selectPage(IPage<T> page, Wrapper<T> wrapper) {
        return getDao().selectPage(page,wrapper);
    }

    /**
     * 获取Dao
     *
     * @return
     */
    protected abstract BaseMapper<T> getDao();

}
