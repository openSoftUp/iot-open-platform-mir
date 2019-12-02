package com.open.iot.netdevicemgr.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.util.Date;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 站点资源详情
 * </p>
 *
 * @author huy
 * @since 2019-12-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SiteResources extends Model<SiteResources> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 资源名称
     */
    private String resourcesName;

    /**
     * 资源备注
     */
    private String resourcesRemark;

    /**
     * 资源类型编码
     */
    private String resourcesTypeCode;

    /**
     * 资源类型JSON参数，可根据资源类型动态变化，对应每种资源对象需定义清楚
     */
    private String resourcesParams;

    /**
     * 工作状态：1:空闲;2:占用;3:使用
     */
    private Integer workState;

    /**
     * 状态：1:启用;2:停用;3:删除
     */
    private Integer state;

    /**
     * 所属站点
     */
    private Integer siteId;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 更新人
     */
    private String updateUser;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
