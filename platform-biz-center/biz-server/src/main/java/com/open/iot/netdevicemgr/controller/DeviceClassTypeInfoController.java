package com.open.iot.netdevicemgr.controller;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.open.iot.annotation.log.LogAnnotation;
import com.open.iot.modelandutils.base.CommonErrorCode;
import com.open.iot.modelandutils.base.PageRequest;
import com.open.iot.modelandutils.base.Result;
import com.open.iot.netdevicemgr.entity.DeviceClassTypeInfo;
import com.open.iot.netdevicemgr.service.DeviceClassTypeInfoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * 设备类型信息 前端控制器
 * </p>
 *
 * @author huy
 * @since 2019-12-02
 */
@RestController
@Api(tags = "设备类型信息 前端控制器api")
@RequestMapping("/device/classType")
public class DeviceClassTypeInfoController {

	@Autowired
	private DeviceClassTypeInfoService deviceClassTypeInfoService;


	/**
	 * 查询
	 * @param params
	 * @return
	 * @throws JsonProcessingException 
	 */
	@ApiOperation(value = "分页列表")
	@GetMapping("/page/list")
	public Result<?> findPage(PageRequest pageRequest) throws JsonProcessingException {
		PageHelper.startPage(pageRequest.getPageNum(), pageRequest.getPageSize());
		QueryWrapper<DeviceClassTypeInfo> queryWrapper = new QueryWrapper<DeviceClassTypeInfo>();
		List<DeviceClassTypeInfo> allList = deviceClassTypeInfoService.list(queryWrapper);
		PageInfo<DeviceClassTypeInfo> pageInfo = new PageInfo<>(allList);
		return Result.succeed(pageInfo, CommonErrorCode.OPERATION_SUCCESS.getMessage());
	}

	/**
	 * 新增
	 * @return
	 * @throws JsonProcessingException 
	 */
	@ApiOperation(value = "新增")
	@PostMapping("/save")
	@LogAnnotation(module="biz-center",recordRequestParam=false)
	public Result<?> save(@RequestBody DeviceClassTypeInfo deviceClassTypeInfo) throws JsonProcessingException {
		Date curDate = new Date();
		deviceClassTypeInfo.setCreateTime(curDate);
		deviceClassTypeInfo.setUpdateTime(curDate);
		boolean flag = deviceClassTypeInfoService.save(deviceClassTypeInfo);
		if(flag) {
			return Result.succeed(deviceClassTypeInfo, CommonErrorCode.OPERATION_SUCCESS.getMessage());
		}
		return Result.failed();
	}
	
	/**
	 * 更新
	 * @return
	 * @throws JsonProcessingException 
	 */
	@ApiOperation(value = "更新")
	@PostMapping("/update")
	@LogAnnotation(module="biz-center",recordRequestParam=false)
	public Result<?> update(@RequestBody DeviceClassTypeInfo deviceClassTypeInfo) throws JsonProcessingException {
		Date curDate = new Date();
		deviceClassTypeInfo.setUpdateTime(curDate);
		boolean flag = deviceClassTypeInfoService.updateById(deviceClassTypeInfo);
		if(flag) {
			return Result.succeed(deviceClassTypeInfo, CommonErrorCode.OPERATION_SUCCESS.getMessage());
		}
		return Result.failed();
	}

	/**
	 * 删除
	 * @param id
	 */
	@ApiOperation(value = "删除")
	@PostMapping("/delete/{id}")
	@LogAnnotation(module="biz-center",recordRequestParam=false)
	public Result<?> delete(@PathVariable Integer id) {
		boolean flag = deviceClassTypeInfoService.removeById(id);
		if(flag) {
			return Result.succeed();
		}
		return Result.failed();
	}

	/**
	 * 获取
	 * @param id
	 */
	@ApiOperation(value = "获取")
	@PostMapping("/get/{id}")
	public Result<?> get(@PathVariable Integer id) {
		DeviceClassTypeInfo deviceClassTypeInfo = deviceClassTypeInfoService.getById(id);
		return Result.succeed(deviceClassTypeInfo, CommonErrorCode.OPERATION_SUCCESS.getMessage());
	}
}

