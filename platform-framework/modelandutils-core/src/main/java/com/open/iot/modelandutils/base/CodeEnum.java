package com.open.iot.modelandutils.base;

/**
 * 响应码枚举
 * @author miaosc
 * @date 2019/11/26
 */
public enum CodeEnum {
    /**
     * 成功
     */
    SUCCESS(0),
    /**
     * 失败
     */
    ERROR(1);

    private Integer code;

    CodeEnum(Integer code){
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
