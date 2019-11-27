package com.open.iot.device.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 站点
 *
 * @author miaosc
 * @date 11/26/2019
 */
@Setter
@Getter
@ToString
@TableName(value = "tb_site")
public class Site extends BaseEntity {

    /**
     * 主键
     */
    @TableId
    private String siteId;

    /**
     * 站点名称
     */
    @TableField
    private String siteName;

    /**
     * 所属分组
     */
    @TableField
    private String teamId;

    /**
     * 地址
     */
    @TableField
    private String address;
}
