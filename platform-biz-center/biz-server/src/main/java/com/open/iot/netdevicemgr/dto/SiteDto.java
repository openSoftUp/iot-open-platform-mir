package com.open.iot.netdevicemgr.dto;

import com.open.iot.modelandutils.base.PageRequest;
import lombok.Data;

/**
 * <p>
 * 站点模型
 * </p>
 *
 * @author huy
 * @since 2019-12-02
 */
@Data
public class SiteDto extends PageRequest {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Integer id;

    /**
     * 站点名称
     */
    private String siteName;

    /**
     * 站点位置
     */
    private String siteAddr;

    /**
     * 分组备注
     */
    private String siteRemark;

    /**
     * 所属站点分组
     */
    private Integer groupId;

    /**
     * 分组状态：1:启用;2:停用;3:删除
     */
    private Integer state;


}
