package com.open.iot.device.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import com.open.iot.device.dao.SleepDao;
import com.open.iot.modelandutils.base.CommonErrorCode;
import com.open.iot.modelandutils.base.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@Api(tags = "示例api")
@RequestMapping("/demo")
public class DemoController {

	/*
	 * @Autowired private SleepDao sleepDao;
	 * 
	 * @ApiOperation(value = "用户查询列表")
	 * 
	 * @GetMapping("/sleep/get/{id}") public Result<?> get(@PathVariable String id){
	 * log.info("示例demo{}",id); return Result.succeed(sleepDao.get(id),
	 * CommonErrorCode.OPERATION_SUCCESS.getMessage()); }
	 */
}
