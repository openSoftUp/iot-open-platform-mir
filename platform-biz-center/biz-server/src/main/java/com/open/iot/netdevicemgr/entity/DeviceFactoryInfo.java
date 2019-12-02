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
 * 设备厂家信息
 * </p>
 *
 * @author huy
 * @since 2019-12-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class DeviceFactoryInfo extends Model<DeviceFactoryInfo> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
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
