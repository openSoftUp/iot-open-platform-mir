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
public class SiteResourcesTypeDto extends PageRequest {

    private static final long serialVersionUID = 1L;

    /**
     * 资源类型code
     */
    private String code;

    /**
     * 备注
     */
    private String resourcesTypeRemark;

    /**
     * 状态：1:启用;2:停用;3:删除
     */
    private Integer state;


}
