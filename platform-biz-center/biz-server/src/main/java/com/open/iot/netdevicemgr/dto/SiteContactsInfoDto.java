package com.open.iot.netdevicemgr.dto;

import com.open.iot.modelandutils.base.PageRequest;

import lombok.Data;

/**
 * <p>
 * 站点与联系人对应关系，多对多
 * </p>
 *
 * @author huy
 * @since 2019-12-02
 */
@Data
public class SiteContactsInfoDto extends PageRequest {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Integer id;

    /**
     * 站点ID
     */
    private Integer siteId;

    /**
     * 联系人ID
     */
    private Integer contactId;
    

}
