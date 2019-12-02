package com.open.iot.netdevicemgr.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.util.Date;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 设备型号信息
 * </p>
 *
 * @author huy
 * @since 2019-12-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class DeviceProductTypeInfo extends Model<DeviceProductTypeInfo> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
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
    private Integer productTypeStatus;

    /**
     * 对应的设备类型ID
     */
    private Integer classTypeId;

    /**
     * 设备厂家
     */
    private Integer factoryId;

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
        return this.id;
    }

}
