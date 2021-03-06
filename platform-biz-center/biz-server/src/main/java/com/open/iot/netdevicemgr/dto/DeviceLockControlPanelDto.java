package com.open.iot.netdevicemgr.dto;

import com.open.iot.modelandutils.base.PageRequest;

import lombok.Data;

/**
 * <p>
 * 锁控板
 * </p>
 *
 * @author huy
 * @since 2019-12-02
 */
@Data
public class DeviceLockControlPanelDto extends PageRequest {

    private static final long serialVersionUID = 1L;

    /**
     * 设备序列号
     */
    private String serialNo;

    /**
     * 设备mac
     */
    private String mac;

    /**
     * 拨码,单个数字（0-9任一）
     */
    private Integer dialNum;

    /**
     * 设备名称
     */
    private String deviceName;

    /**
     * 设备的软件版本
     */
    private String devVersion;

    /**
     * 所属站点
     */
    private Integer siteId;

    /**
     * 备注信息
     */
    private String remarks;

    /**
     * 设备状态：1:启用;2:停用;3:删除
     */
    private Integer state;

    /**
     * 设备类型
     */
    private Integer classType;

    /**
     * 设备型号
     */
    private Integer productType;

}
