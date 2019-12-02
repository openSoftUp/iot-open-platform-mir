package com.open.iot.netdevicemgr.dto;

import com.open.iot.modelandutils.base.PageRequest;

import lombok.Data;

/**
 * <p>
 * 站点与客户端权限配置，多对多
 * </p>
 *
 * @author huy
 * @since 2019-12-02
 */
@Data
public class OauthClientSiteDto extends PageRequest {

    private static final long serialVersionUID = 1L;


    /**
     * 应用标识
     */
    private String clientId;

    /**
     * 站点ID
     */
    private Integer siteId;
    

}
