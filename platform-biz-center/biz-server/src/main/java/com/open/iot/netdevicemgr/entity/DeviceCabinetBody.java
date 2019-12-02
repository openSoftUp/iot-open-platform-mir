package com.open.iot.netdevicemgr.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.util.Date;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 柜体
 * </p>
 *
 * @author huy
 * @since 2019-12-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class DeviceCabinetBody extends Model<DeviceCabinetBody> {

    private static final long serialVersionUID = 1L;

    /**
     * 设备序列号
     */
    @TableId(value = "serial_no", type = IdType.INPUT)
    private String serialNo;

    /**
     * 设备mac
     */
    private String mac;

    /**
     * 柜门数量,单个数字（0-9任一）
     */
    private Integer doorNum;

    /**
     * 配件信息,根据业务所增加的配件信息
     */
    private String configurationInfo;

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

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 更新人
     */
    private String updateUser;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;


    @Override
    protected Serializable pkVal() {
        return this.serialNo;
    }

}
