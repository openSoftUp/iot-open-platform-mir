package com.open.iot.common.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.lang.Nullable;

import java.io.Serializable;
import java.util.Optional;

/**
 * @author miaosc
 * @date 11/28/2019
 */
public interface JdbcService<T extends Serializable> {

    /**
     * 根据id删除
     *
     * @param id
     */
    void deleteById(Serializable id);

    /**
     * 插入新的
     *
     * @param entity
     */
    void insert(T entity);

    /**
     * 按主键查询
     *
     * @param id
     * @return
     */
    Optional<T> findById(Serializable id);

    /**
     * 分页查询
     *
     * @param page
     * @param wrapper
     * @return
     */
    IPage<T> selectPage(IPage<T> page,@Nullable Wrapper<T> wrapper);
}
