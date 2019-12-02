package com.open.iot.netdevicemgr.dto;
import com.open.iot.modelandutils.base.PageRequest;
import lombok.Data;

/**
 * <p>
 * 站点资源详情
 * </p>
 *
 * @author huy
 * @since 2019-12-02
 */
@Data
public class SiteResourcesDto extends PageRequest {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
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


}
