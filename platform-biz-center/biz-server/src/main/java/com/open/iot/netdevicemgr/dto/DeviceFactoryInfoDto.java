package com.open.iot.netdevicemgr.dto;

import com.open.iot.modelandutils.base.PageRequest;

import lombok.Data;

/**
 * <p>
 * 设备厂家信息
 * </p>
 *
 * @author huy
 * @since 2019-12-02
 */
@Data
public class DeviceFactoryInfoDto extends PageRequest {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Integer id;

    /**
     * 厂家名称
     */
    private String factoryName;

    /**
     * 厂家税号
     */
    private String factoryTaxNum;

    /**
     * 地址
     */
    private String factoryAddr;

    /**
     * 厂家开户行
     */
    private String bankName;

    /**
     * 厂家银行账号
     */
    private String bankAccount;

    /**
     * 厂家联系人姓名
     */
    private String contactMan;

    /**
     * 厂家联系电话
     */
    private String contactTel;

    /**
     * 厂家联系邮件
     */
    private String contactEmail;

    /**
     * 1:启用;2:停用;3:删除
     */
    private Integer state;


}
