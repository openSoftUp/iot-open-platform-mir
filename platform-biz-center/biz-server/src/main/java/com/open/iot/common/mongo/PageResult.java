package com.open.iot.common.mongo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
* @ClassName: PageResult  
* @Description: mongoDB的分页结果.  
* @author huy  
* @date 2019年8月9日  
*  
* @param <T>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class PageResult<T> {

    @ApiModelProperty("页码，从1开始")
    private Integer pageNum;

    @ApiModelProperty("页面大小")
    private Integer pageSize;

    @ApiModelProperty("总数")
    private Long total;

    @ApiModelProperty("总页数")
    private Integer pages;

    @ApiModelProperty("数据")
    private List<T> list;
    
    public static final String SORT_ASC="asc";
    public static final String SORT_DESC="desc";

}
