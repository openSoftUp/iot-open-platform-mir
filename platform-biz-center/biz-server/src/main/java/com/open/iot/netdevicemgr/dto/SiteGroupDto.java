package com.open.iot.netdevicemgr.dto;

import com.open.iot.modelandutils.base.PageRequest;
import lombok.Data;

/**
 * <p>
 * 站点分组模型
 * </p>
 *
 * @author huy
 * @since 2019-12-02
 */
@Data
public class SiteGroupDto extends PageRequest {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Integer id;

    /**
     * 分组
     */
    private String groupName;

    /**
     * 分组备注
     */
    private String groupRemark;

    /**
     * 父分组ID
     */
    private Integer parentId;

    /**
     * 分组状态：1:启用;2:停用;3:删除
     */
    private Integer state;


}
