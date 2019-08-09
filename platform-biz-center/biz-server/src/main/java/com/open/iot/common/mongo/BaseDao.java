package com.open.iot.common.mongo;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;


/**
 * 
* @ClassName: BaseDao  
* @Description: 模板方法模式 
* @author huy  
* @date 2018年12月19日  
*  
* @param <PK>
* @param <T>
 */
public abstract class BaseDao<PK extends Serializable, T> {

    @Autowired
    protected MongoTemplate mongoTemplate;

    @Autowired
	protected MongoPageHelper mongoPageHelper;
    /**
     * 获取实体类的Class
     *
     * @return
     */
    protected abstract Class<T> getEntityClass();

    public T get(PK pk) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(pk));
        return mongoTemplate.findOne(query, getEntityClass());
    }

    public void insert(T entity) {
        mongoTemplate.insert(entity);
    }

    public void insertByBatch(Collection<T> coll) {
        mongoTemplate.insert(coll, getEntityClass());
    }

    public void save(T entity) {
        mongoTemplate.save(entity);
    }

    public void remove(PK pk) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(pk));
        mongoTemplate.remove(query, getEntityClass());
    }

    protected List<T> listEnityWithPageOrderById(Query query, boolean asc, PK offset, int limit) {
        if (asc) {
            query.with(new Sort(Direction.ASC, "id"));
            if (offset != null) {
                query.addCriteria(Criteria.where("id").gt(offset));
            }
        } else {
            query.with(new Sort(Direction.DESC, "id"));
            if (offset != null) {
                query.addCriteria(Criteria.where("id").lt(offset));
            }
        }
        query.limit(limit);
        return mongoTemplate.find(query, getEntityClass());
    }

    protected List<T> listEnityWithPageOrderById(Query query, PK start, PK end, PK offset, boolean asc, int limit) {
        if (asc) {
            query.with(new Sort(Direction.ASC, "id"));
            if (null != offset) {
                query.addCriteria(Criteria.where("id").gt(offset).lte(end));
            } else {
                query.addCriteria(Criteria.where("id").gte(start).lte(end));
            }
        } else {
            query.with(new Sort(Direction.DESC, "id"));
            if (null != offset) {
                query.addCriteria(Criteria.where("id").gte(start).lt(offset));
            } else {
                query.addCriteria(Criteria.where("id").gte(start).lte(end));
            }
        }
        query.limit(limit);
        return mongoTemplate.find(query, getEntityClass());
    }
    
    protected List<T> listAllEnityWithPageOrderById(Query query, PK start, PK end, boolean asc) {
        if (asc) {
            query.with(new Sort(Direction.ASC, "id"));
            query.addCriteria(Criteria.where("id").gte(start).lte(end));
        } else {
            query.with(new Sort(Direction.DESC, "id"));
            query.addCriteria(Criteria.where("id").gte(start).lte(end));
        }
        return mongoTemplate.find(query, getEntityClass());
    }
}
