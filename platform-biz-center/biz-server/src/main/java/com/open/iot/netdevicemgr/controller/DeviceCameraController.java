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
import com.open.iot.netdevicemgr.dto.DeviceCameraDto;
import com.open.iot.netdevicemgr.entity.DeviceCamera;
import com.open.iot.netdevicemgr.service.DeviceCameraService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * 摄像头 前端控制器
 * </p>
 *
 * @author huy
 * @since 2019-12-02
 */
@RestController
@Api(tags = "摄像头 前端控制器api")
@RequestMapping("/device/camera")
public class DeviceCameraController {

	@Autowired
	private DeviceCameraService deviceCameraService;


	/**
	 * 查询
	 * @param params
	 * @return
	 * @throws JsonProcessingException 
	 */
	@ApiOperation(value = "分页列表")
	@GetMapping("/page/list")
	public Result<?> findPage(DeviceCameraDto dto) throws JsonProcessingException {
		PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
		QueryWrapper<DeviceCamera> queryWrapper = new QueryWrapper<DeviceCamera>();
		List<DeviceCamera> allList = deviceCameraService.list(queryWrapper);
		PageInfo<DeviceCamera> pageInfo = new PageInfo<>(allList);
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
	public Result<?> save(@RequestBody DeviceCamera deviceCamera) throws JsonProcessingException {
		Date curDate = new Date();
		deviceCamera.setCreateTime(curDate);
		deviceCamera.setUpdateTime(curDate);
		boolean flag = deviceCameraService.save(deviceCamera);
		if(flag) {
			return Result.succeed(deviceCamera, CommonErrorCode.OPERATION_SUCCESS.getMessage());
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
	public Result<?> update(@RequestBody DeviceCamera deviceCamera) throws JsonProcessingException {
		Date curDate = new Date();
		deviceCamera.setUpdateTime(curDate);
		boolean flag = deviceCameraService.updateById(deviceCamera);
		if(flag) {
			return Result.succeed(deviceCamera, CommonErrorCode.OPERATION_SUCCESS.getMessage());
		}
		return Result.failed();
	}

	/**
	 * 删除
	 * @param id
	 */
	@ApiOperation(value = "删除")
	@PostMapping("/delete/{serialNo}")
	@LogAnnotation(module="biz-center",recordRequestParam=false)
	public Result<?> delete(@PathVariable String serialNo) {
		boolean flag = deviceCameraService.removeById(serialNo);
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
	@PostMapping("/get/{serialNo}")
	public Result<?> get(@PathVariable String serialNo) {
		DeviceCamera deviceCamera = deviceCameraService.getById(serialNo);
		return Result.succeed(deviceCamera, CommonErrorCode.OPERATION_SUCCESS.getMessage());
	}
}

