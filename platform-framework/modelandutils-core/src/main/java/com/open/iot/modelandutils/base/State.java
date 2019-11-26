package com.open.iot.modelandutils.base;

/**
 * 使用状态
 *
 * @author miaosc
 * @date 11/26/2019
 */
public enum State {

    /**
     * 已用
     */
    USED,

    /**
     * 预定状态,介于已用和空闲之间的中间状态
     */
    READY,

    /**
     * 停用状态
     */
    DISABLED,

    /**
     * 空闲
     */
    IDLE
}
