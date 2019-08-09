package com.open.iot.modelandutils.base;

/**
 * 公共错误码
 * @author Administrator
 *
 */
public enum CommonErrorCode {
	
	
	OPERATION_SUCCESS(0, "操作成功"),

	/**
	 * 操作失败
	 */
	 OPERATION_FAILED(1, "操作失败"),

	/**
	 * 参数错误
	 */
	ILLEGAL_ARGUMENT(10000400, "参数错误"),
	/**
	 * 业务错误
	 */
	SERVICE_EXCEPTION(10000002, "业务错误"),
	/**
	 * 非法的数据格式，参数没有经过校验
	 */
	ILLEGAL_DATA(10000003, "数据错误"),

	/**
	 * 文件太大
	 */
	MULTIPART_TOO_LARGE(10000004, "文件太大"),
	/**
	 * 非法状态
	 */
	ILLEGAL_STATE(10000005, "非法状态"),
	/**
	 * 缺少参数
	 */
	MISSING_ARGUMENT(10000006, "缺少参数"),
//	/**
//	 * 非法访问
//	 */
//	ILLEGAL_ACCESS(100007, "非法访问,没有认证"),
	/**
	 * 权限不足
	 */
	UNAUTHORIZED(10000403, "没有权限访问"),

	/**
	 * 错误的请求
	 */
	METHOD_NOT_ALLOWED(10000009, "不支持的方法"),

	/**
	 * 参数类型错误
	 */
	ILLEGAL_ARGUMENT_TYPE(1000010, "参数类型错误"),

	// 参数长度限制
	ARGUMENT_LENGTH_LIMIT(1000011, "参数长度超过限制"),
	
//	/**
//	 * 用户或密码错误
//	 */
//	USER_PWD_ERROR(10000401, "用户或密码错误"),
	/**
	 * 凭证无效
	 */
	USER_PWD_ERROR(10000401, "无效凭证"),

	/**
	 * 系统内部错误
	 */
	INTERNAL_SERVER_ERROR(10000000, "系统错误"),
	
	
	DEVICE_NOT_FOUND(20001, "设备不存在");

	private final int value;

	private final String message;

	CommonErrorCode(int value, String message) {
		this.value = value;
		this.message = message;
	}

	/**
	 * Return the integer value of this status code.
	 */
	public int value() {
		return this.value;
	}

	/**
	 * Return the reason phrase of this status code.
	 */
	public String getMessage() {
		return this.message;
	}
}
