package com.open.iot.netdevicemgr.dto;

import com.open.iot.modelandutils.base.PageRequest;
import lombok.Data;

/**
 * <p>
 * 设备型号信息
 * </p>
 *
 * @author huy
 * @since 2019-12-02
 */
@Data
public class DeviceProductTypeInfoDto extends PageRequest{

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Integer id;

    /**
     * 设备型号，设备供应商提供的设备序列号
     */
    private String productTypeName;

    /**
     * 设备型号参数
     */
    private String productTypeParams;

    /**
     * 备注信息
     */
    private String productTypeRemark;

    /**
     * 1:启用;2:停用;3:删除
     */
    private Integer state;

    /**
     * 对应的设备类型ID
     */
    private Integer classTypeId;

    /**
     * 设备厂家
     */
    private Integer factoryId;


}
