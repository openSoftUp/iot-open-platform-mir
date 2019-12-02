//package com.open.iot.device.entity;
//
//import com.baomidou.mybatisplus.annotation.TableField;
//import com.baomidou.mybatisplus.annotation.TableId;
//import com.baomidou.mybatisplus.annotation.TableName;
//import lombok.Getter;
//import lombok.Setter;
//import lombok.ToString;
//
///**
// * 储物柜
// *
// * @author miaosc
// * @date 11/26/2019
// */
//@Setter
//@Getter
//@ToString
//@TableName("tb_chest")
//public class Chest extends BaseEntity{
//
//    /**
//     * 主键
//     */
//    @TableId
//    private String chestId;
//
//    /**
//     * 名称
//     */
//    @TableField
//    private String chestName;
//
//    /**
//     * 所属站点
//     */
//    @TableField
//    private String siteId;
//
//    /**
//     * 容量(有几个舱)
//     */
//    @TableField
//    private Integer capacity;
//
//    /**
//     * 所处位置
//     */
//    @TableField
//    private String position;
//
//    /**
//     * 当前是否可用
//     */
//    @TableField
//    private Boolean enabled;
//}
