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
import com.open.iot.modelandutils.base.Result;
import com.open.iot.netdevicemgr.dto.DeviceProductTypeInfoDto;
import com.open.iot.netdevicemgr.entity.DeviceProductTypeInfo;
import com.open.iot.netdevicemgr.service.DeviceProductTypeInfoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * 设备型号信息 前端控制器
 * </p>
 *
 * @author huy
 * @since 2019-12-02
 */
@RestController
@Api(tags = "设备型号信息 前端控制器api")
@RequestMapping("/device/productType")
public class DeviceProductTypeInfoController {

	@Autowired
	private DeviceProductTypeInfoService deviceProductTypeInfoService;


	/**
	 * 查询
	 * @param params
	 * @return
	 * @throws JsonProcessingException 
	 */
	@ApiOperation(value = "分页列表")
	@GetMapping("/page/list")
	public Result<?> findPage(DeviceProductTypeInfoDto dto) throws JsonProcessingException {
		PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
		QueryWrapper<DeviceProductTypeInfo> queryWrapper = new QueryWrapper<DeviceProductTypeInfo>();
		List<DeviceProductTypeInfo> allList = deviceProductTypeInfoService.list(queryWrapper);
		PageInfo<DeviceProductTypeInfo> pageInfo = new PageInfo<>(allList);
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
	public Result<?> save(@RequestBody DeviceProductTypeInfo deviceProductTypeInfo) throws JsonProcessingException {
		Date curDate = new Date();
		deviceProductTypeInfo.setCreateTime(curDate);
		deviceProductTypeInfo.setUpdateTime(curDate);
		boolean flag = deviceProductTypeInfoService.save(deviceProductTypeInfo);
		if(flag) {
			return Result.succeed(deviceProductTypeInfo, CommonErrorCode.OPERATION_SUCCESS.getMessage());
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
	public Result<?> update(@RequestBody DeviceProductTypeInfo deviceProductTypeInfo) throws JsonProcessingException {
		Date curDate = new Date();
		deviceProductTypeInfo.setUpdateTime(curDate);
		boolean flag = deviceProductTypeInfoService.updateById(deviceProductTypeInfo);
		if(flag) {
			return Result.succeed(deviceProductTypeInfo, CommonErrorCode.OPERATION_SUCCESS.getMessage());
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
		boolean flag = deviceProductTypeInfoService.removeById(id);
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
		DeviceProductTypeInfo deviceProductTypeInfo = deviceProductTypeInfoService.getById(id);
		return Result.succeed(deviceProductTypeInfo, CommonErrorCode.OPERATION_SUCCESS.getMessage());
	}
}

