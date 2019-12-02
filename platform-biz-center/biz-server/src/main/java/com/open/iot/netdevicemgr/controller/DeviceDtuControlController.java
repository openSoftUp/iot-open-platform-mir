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
import com.open.iot.netdevicemgr.dto.DeviceDtuControlDto;
import com.open.iot.netdevicemgr.entity.DeviceDtuControl;
import com.open.iot.netdevicemgr.service.DeviceDtuControlService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * DTU控制器 前端控制器
 * </p>
 *
 * @author huy
 * @since 2019-12-02
 */
@RestController
@Api(tags = "DTU控制器 前端控制器api")
@RequestMapping("/device/dtu/control")
public class DeviceDtuControlController {

	@Autowired
	private DeviceDtuControlService deviceDtuControlService;


	/**
	 * 查询
	 * @param params
	 * @return
	 * @throws JsonProcessingException 
	 */
	@ApiOperation(value = "分页列表")
	@GetMapping("/page/list")
	public Result<?> findPage(DeviceDtuControlDto dto) throws JsonProcessingException {
		PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
		QueryWrapper<DeviceDtuControl> queryWrapper = new QueryWrapper<DeviceDtuControl>();
		List<DeviceDtuControl> allList = deviceDtuControlService.list(queryWrapper);
		PageInfo<DeviceDtuControl> pageInfo = new PageInfo<>(allList);
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
	public Result<?> save(@RequestBody DeviceDtuControl deviceDtuControl) throws JsonProcessingException {
		Date curDate = new Date();
		deviceDtuControl.setCreateTime(curDate);
		deviceDtuControl.setUpdateTime(curDate);
		boolean flag = deviceDtuControlService.save(deviceDtuControl);
		if(flag) {
			return Result.succeed(deviceDtuControl, CommonErrorCode.OPERATION_SUCCESS.getMessage());
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
	public Result<?> update(@RequestBody DeviceDtuControl deviceDtuControl) throws JsonProcessingException {
		Date curDate = new Date();
		deviceDtuControl.setUpdateTime(curDate);
		boolean flag = deviceDtuControlService.updateById(deviceDtuControl);
		if(flag) {
			return Result.succeed(deviceDtuControl, CommonErrorCode.OPERATION_SUCCESS.getMessage());
		}
		return Result.failed();
	}

	/**
	 * 删除
	 * @param id
	 */
	@ApiOperation(value = "删除")
	@PostMapping("/delete/{clientId}")
	@LogAnnotation(module="user-center",recordRequestParam=false)
	public Result<?> delete(@PathVariable String clientId) {
		boolean flag = deviceDtuControlService.removeById(clientId);
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
	@PostMapping("/get/{clientId}")
	public Result<?> get(@PathVariable String clientId) {
		DeviceDtuControl deviceDtuControl = deviceDtuControlService.getById(clientId);
		return Result.succeed(deviceDtuControl, CommonErrorCode.OPERATION_SUCCESS.getMessage());
	}
}

