package com.open.iot.common.mongo;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 
* @ClassName: MongoPageHelper  
* @Description: MongoDB分页查询工具类.
* @author huy  
* @date 2019年8月9日  
*
 */
@Component
public class MongoPageHelper {

    public static final int FIRST_PAGE_NUM = 1;
    public static final String ID = "id";
    private final MongoTemplate mongoTemplate;

    @Autowired
    public MongoPageHelper(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }


    /**
     * 分页查询，直接返回集合类型的结果.
     *
     * @see MongoPageHelper#pageQuery(org.springframework.data.mongodb.core.query.Query,
     * java.lang.Class, java.util.function.Function, java.lang.Integer, java.lang.Integer,
     * java.lang.String)
     */
    public <T> PageResult<T> pageQuery(Query query, Class<T> entityClass, Integer pageSize,
        Integer pageNum) {
        return pageQuery(query, entityClass, Function.identity(), pageSize, pageNum, null);
    }
    
    public <T> PageResult<T> pageQuery(Query query,Criteria idCriteria, Class<T> entityClass, Integer pageSize,
            Integer pageNum,String sort) {
            return pageQuery(query,idCriteria, entityClass, Function.identity(), pageSize, pageNum, null,sort);
        }

    /**
     * 分页查询，不考虑条件分页，直接使用skip-limit来分页.
     *
     * @see MongoPageHelper#pageQuery(org.springframework.data.mongodb.core.query.Query,
     * java.lang.Class, java.util.function.Function, java.lang.Integer, java.lang.Integer,
     * java.lang.String)
     */
    public <T, R> PageResult<R> pageQuery(Query query, Class<T> entityClass, Function<T, R> mapper,
        Integer pageSize, Integer pageNum) {
        return pageQuery(query, entityClass, mapper, pageSize, pageNum, null);
    }

    /**
     * 分页查询.
     *
     * @param query Mongo Query对象，构造你自己的查询条件.
     * @param entityClass Mongo collection定义的entity class，用来确定查询哪个集合.
     * @param mapper 映射器，你从db查出来的list的元素类型是entityClass, 如果你想要转换成另一个对象，比如去掉敏感字段等，可以使用mapper来决定如何转换.
     * @param pageSize 分页的大小.
     * @param pageNum 当前页.
     * @param lastId 条件分页参数, 区别于skip-limit，采用find(_id>lastId).limit分页.
     * 如果不跳页，像朋友圈，微博这样下拉刷新的分页需求，需要传递上一页的最后一条记录的ObjectId。 如果是null，则返回pageNum那一页.
     * @param <T> collection定义的class类型.
     * @param <R> 最终返回时，展现给页面时的一条记录的类型。
     * @return PageResult，一个封装page信息的对象.
     */
    public <T, R> PageResult<R> pageQuery(Query query, Class<T> entityClass, Function<T, R> mapper,
        Integer pageSize, Integer pageNum, String lastId) {
        //分页逻辑
        long total = mongoTemplate.count(query, entityClass);
        final Integer pages = (int) Math.ceil(total / (double) pageSize);
        if (pageNum <= 0 || pageNum > pages) {
            pageNum = FIRST_PAGE_NUM;
        }
        final Criteria criteria = new Criteria();
        if (StringUtils.isNotBlank(lastId)) {
            if (pageNum != FIRST_PAGE_NUM) {
                criteria.and(ID).gt(lastId);
            }
            query.limit(pageSize);
        } else {
            int skip = pageSize * (pageNum - 1);
            query.skip(skip).limit(pageSize);
        }

        final List<T> entityList = mongoTemplate
            .find(query.addCriteria(criteria)
                    .with(Sort.by(Lists.newArrayList(new Order(Direction.ASC, ID)))),
                entityClass);

        final PageResult<R> pageResult = new PageResult<>();
        pageResult.setTotal(total);
        pageResult.setPages(pages);
        pageResult.setPageSize(pageSize);
        pageResult.setPageNum(pageNum);
        pageResult.setList(entityList.stream().map(mapper).collect(Collectors.toList()));
        return pageResult;
    }
    
    public <T, R> PageResult<R> pageQuery(Query query,Criteria idCriteria, Class<T> entityClass, Function<T, R> mapper,
            Integer pageSize, Integer pageNum, String lastId,String sort) {
            //分页逻辑
            long total = mongoTemplate.count(query, entityClass);
            query = new Query();
            final Integer pages = (int) Math.ceil(total / (double) pageSize);
            if (pageNum <= 0 || pageNum > pages) {
                pageNum = FIRST_PAGE_NUM;
            }
            final Criteria criteria = new Criteria();
            if (StringUtils.isNotBlank(lastId)) {
                if (pageNum != FIRST_PAGE_NUM) {
//                    criteria.and(ID).gt(lastId/*new ObjectId(lastId)*/);
//                    criteria.andOperator(idCriteria);
                    criteria.andOperator(idCriteria,Criteria.where("id").gt(lastId));
                }
                query.limit(pageSize);
            } else {
            	criteria.andOperator(idCriteria);
                int skip = pageSize * (pageNum - 1);
                query.skip(skip).limit(pageSize);
            }
            Order order = new Order(Direction.ASC, ID);
            if(sort.equals(PageResult.SORT_DESC)) {
            	order = new Order(Direction.DESC, ID);
            }
            
            final List<T> entityList = mongoTemplate
                .find(query.addCriteria(criteria)
                        .with(Sort.by(Lists.newArrayList(order))),
                    entityClass);

            final PageResult<R> pageResult = new PageResult<>();
            pageResult.setTotal(total);
            pageResult.setPages(pages);
            pageResult.setPageSize(pageSize);
            pageResult.setPageNum(pageNum);
            pageResult.setList(entityList.stream().map(mapper).collect(Collectors.toList()));
            return pageResult;
        }

}
