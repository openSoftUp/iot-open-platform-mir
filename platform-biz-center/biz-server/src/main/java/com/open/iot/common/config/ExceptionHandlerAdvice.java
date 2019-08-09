package com.open.iot.common.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MultipartException;

import com.open.iot.modelandutils.base.CommonErrorCode;
import com.open.iot.modelandutils.base.Result;
import com.open.iot.modelandutils.ex.ServiceException;

/**
 * 
* @ClassName: ExceptionHandlerAdvice  
* @Description: 异常通用处理  
* @author huy  
* @date 2019年8月6日  
*
 */
@RestControllerAdvice
public class ExceptionHandlerAdvice {

	/**
	 * IllegalArgumentException异常处理返回json 状态码:400
	 * 
	 * @param exception
	 * @return
	 */
	@ExceptionHandler({ IllegalArgumentException.class })
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Result<?> badRequestException(IllegalArgumentException exception) {
		return failure(CommonErrorCode.ILLEGAL_ARGUMENT);
	}

	/**
	 * AccessDeniedException异常处理返回json 状态码:403
	 * 
	 * @param exception
	 * @return
	 */
	@ExceptionHandler({ AccessDeniedException.class })
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public Result<?> badMethodExpressException(AccessDeniedException exception) {
		return failure(CommonErrorCode.UNAUTHORIZED);
	}

	@ResponseBody
	@ExceptionHandler(MissingServletRequestParameterException.class)
	@ResponseStatus(HttpStatus.OK)
	public Result<?> handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
		return failure(CommonErrorCode.MISSING_ARGUMENT, e);
	}

	@ResponseBody
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	@ResponseStatus(HttpStatus.OK)
	public Result<?> handleMethodArgumentTypeMismatchExceptionException(MethodArgumentTypeMismatchException e) {

		return failure(CommonErrorCode.ILLEGAL_ARGUMENT_TYPE, e);
	}

	@ResponseBody
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public Result<?> handleException(Exception e) {
		e.printStackTrace();
		return failure(CommonErrorCode.INTERNAL_SERVER_ERROR, e);
	}

	@ResponseBody
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	public Result<?> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
		return failure(CommonErrorCode.METHOD_NOT_ALLOWED, e);
	}

	@ResponseBody
	@ExceptionHandler(ServiceException.class)
	@ResponseStatus(HttpStatus.OK)
	public Result<?> handleServiceException(ServiceException e) {
		if (e.getCode() == null) {
			return failure(CommonErrorCode.SERVICE_EXCEPTION, e);
		} else {
			return Result.failedWith(null, e.getCode(), e.getMessage());
		}
	}

	@ResponseBody
	@ExceptionHandler(IllegalStateException.class)
	@ResponseStatus(HttpStatus.OK)
	public Result<?> handleIllegalStateException(IllegalStateException e) {
		return failure(CommonErrorCode.ILLEGAL_STATE, e);
	}

	private String getMessage(Exception exception, CommonErrorCode status) {
		return exception.getMessage() != null ? exception.getMessage() : status.getMessage();
	}

	/**
	 * 构建返回错误
	 * 
	 * @param status
	 * @param exception
	 * @param data
	 * @return
	 */
	private Result<?> failure(CommonErrorCode status, Exception exception, Object data) {
		String meesage = null;
		if (exception != null) {
			meesage = this.getMessage(exception, status);
		}
		return Result.failedWith(data, status.value(), meesage);
	}

	/**
	 * 构建返回错误
	 * 
	 * @param status
	 * @param data
	 * @return
	 */
	private Result<?> failure(CommonErrorCode status, Object data) {
		return failure(status, null, data);
	}

	/**
	 * 构建返回错误
	 * 
	 * @param status
	 * @param exception
	 * @return
	 */
	private Result<?> failure(CommonErrorCode status, Exception exception) {
		return failure(status, exception, null);
	}

	/**
	 * 构建返回错误
	 * 
	 * @param status
	 * @return
	 */
	private Result<?> failure(CommonErrorCode status) {
		return Result.failedWith(null, status.value(), status.getMessage());
	}

	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@ExceptionHandler(ConstraintViolationException.class)
	public Result<?> handleValidationException(ConstraintViolationException e) {
		List<Map<String, Object>> fields = new ArrayList<>();
		for (ConstraintViolation<?> cv : e.getConstraintViolations()) {
			String fieldName = ((PathImpl) cv.getPropertyPath()).getLeafNode().asString();
			String message = cv.getMessage();
			Map<String, Object> field = new HashMap<>();
			field.put("field", fieldName);
			field.put("message", message);
			fields.add(field);
		}
		return failure(CommonErrorCode.ILLEGAL_DATA, fields);
	}

	@ResponseBody
	@ExceptionHandler(BindException.class)
	@ResponseStatus(HttpStatus.OK)
	public Result<?> handleBindException(BindException e) {
		List<Map<String, Object>> fields = new ArrayList<>();
		for (FieldError error : e.getFieldErrors()) {
			Map<String, Object> field = new HashMap<>();
			field.put("field", error.getField());
			field.put("message", error.getDefaultMessage());
			fields.add(field);
		}
		return failure(CommonErrorCode.ILLEGAL_DATA, fields);
	}

	@ResponseBody
	@ExceptionHandler(MultipartException.class)
	@ResponseStatus(HttpStatus.OK)
	public Result<?> handleMultipartException() {
		return failure(CommonErrorCode.MULTIPART_TOO_LARGE);
	}

}
