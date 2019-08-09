package com.open.iot.modelandutils.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> implements Serializable {

    /**  
	* @Fields field:field:{todo}(用一句话描述这个变量表示什么)  
	* @date 2019年8月1日  
	*/
	private static final long serialVersionUID = 1L;
	
	
	private T datas;
    private Integer code;
    private String msg;

    public static <T> Result<T> succeed(String msg) {
        return succeedWith(null, CodeEnum.SUCCESS.getCode(),msg);
    }

    public static <T> Result<T> succeed(T model, String msg) {
        return succeedWith(model, CodeEnum.SUCCESS.getCode(),msg);
    }
    
    public static <T> Result<T> succeed() {
        return succeedWith(null, CommonErrorCode.OPERATION_SUCCESS.value(),CommonErrorCode.OPERATION_SUCCESS.getMessage());
    }

    public static <T> Result<T> succeedWith(T datas, Integer code,String msg) {
        return new Result<T>(datas, code, msg);
    }

    public static <T> Result<T> failed() {
        return failedWith(null, CommonErrorCode.OPERATION_FAILED.value(),CommonErrorCode.OPERATION_FAILED.getMessage());
    }
    
    public static <T> Result<T> failed(CommonErrorCode commonErrorCode) {
        return failedWith(null, commonErrorCode.value(),commonErrorCode.getMessage());
    }
    
    public static <T> Result<T> failed(String msg) {
        return failedWith(null, CodeEnum.ERROR.getCode(), msg);
    }

    public static <T> Result<T> failed(T model,String msg) {
        return failedWith(model, CodeEnum.ERROR.getCode(), msg);
    }

    public static <T> Result<T> failedWith(T datas, Integer code, String msg) {
        return new Result<T>( datas, code, msg);
    }

}
