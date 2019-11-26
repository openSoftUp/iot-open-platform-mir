package com.open.iot.device.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author miaosc
 * @date 11/26/2019
 */
@Setter
@Getter
@ToString
@TableName("tb_chest")
public class Chest extends BaseEntity{

    @TableId
    private String chestId;

    @TableField
    private String chestName;

    @TableField
    private String siteId;
}
