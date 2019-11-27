package com.open.iot.device.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.open.iot.modelandutils.base.State;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * 舱口
 *
 * @author miaosc
 * @date 11/26/2019
 */
@Setter
@Getter
@ToString
@TableName("tb_hole")
public class Hole extends BaseEntity {

    /**
     * 主键
     */
    @TableId
    private String holeId;

    /**
     * 编号
     */
    @NotNull
    @TableField
    private Integer num;

    /**
     * 名称
     */
    @TableField
    private String holeName;

    /**
     * 所属柜体
     */
    @TableField
    private String chestId;

    /**
     * 当前状态
     */
    @TableField
    private State state;

    /**
     * 是否可用
     *
     * @return
     */
    public boolean available() {
        return State.IDLE == state;
    }
}
