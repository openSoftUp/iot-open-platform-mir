package com.open.iot.device.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 硬件分组
 *
 * @author miaosc
 * @date 11/26/2019
 */
@Getter
@Setter
@ToString
@TableName("tb_team")
public class Team extends BaseEntity {

    /**
     * 分组id
     */
    @TableId
    private String teamId;

    /**
     * 分组名称
     */
    @TableField
    private String teamName;

}
