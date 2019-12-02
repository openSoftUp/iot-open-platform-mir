package com.open.iot.netdevicemgr.dto;

import com.open.iot.modelandutils.base.PageRequest;
import lombok.Data;

/**
 * <p>
 * 设备类型信息
 * </p>
 *
 * @author huy
 * @since 2019-12-02
 */
@Data
public class DeviceClassTypeInfoDto extends PageRequest {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Integer id;

    /**
     * 设备类型名称,设备供应商提供的设备序列号
     */
    private String classTypeName;

    /**
     * 备注信息
     */
    private String classTypeRemark;

    /**
     * 1:启用;2:停用;3:删除
     */
    private Integer state;


}
