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
import com.open.iot.netdevicemgr.dto.DeviceCabinetBodyDto;
import com.open.iot.netdevicemgr.entity.DeviceCabinetBody;
import com.open.iot.netdevicemgr.service.DeviceCabinetBodyService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * 柜体 前端控制器
 * </p>
 *
 * @author huy
 * @since 2019-12-02
 */
@RestController
@Api(tags = "柜体 前端控制器api")
@RequestMapping("/device/cabinet/body")
public class DeviceCabinetBodyController {

	@Autowired
	private DeviceCabinetBodyService deviceCabinetBodyService;


	/**
	 * 查询
	 * @param params
	 * @return
	 * @throws JsonProcessingException 
	 */
	@ApiOperation(value = "分页列表")
	@GetMapping("/page/list")
	public Result<?> findPage(DeviceCabinetBodyDto dto) throws JsonProcessingException {
		PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
		QueryWrapper<DeviceCabinetBody> queryWrapper = new QueryWrapper<DeviceCabinetBody>();
		List<DeviceCabinetBody> allList = deviceCabinetBodyService.list(queryWrapper);
		PageInfo<DeviceCabinetBody> pageInfo = new PageInfo<>(allList);
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
	public Result<?> save(@RequestBody DeviceCabinetBody deviceCabinetBody) throws JsonProcessingException {
		Date curDate = new Date();
		deviceCabinetBody.setCreateTime(curDate);
		deviceCabinetBody.setUpdateTime(curDate);
		boolean flag = deviceCabinetBodyService.save(deviceCabinetBody);
		if(flag) {
			return Result.succeed(deviceCabinetBody, CommonErrorCode.OPERATION_SUCCESS.getMessage());
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
	public Result<?> update(@RequestBody DeviceCabinetBody deviceCabinetBody) throws JsonProcessingException {
		Date curDate = new Date();
		deviceCabinetBody.setUpdateTime(curDate);
		boolean flag = deviceCabinetBodyService.updateById(deviceCabinetBody);
		if(flag) {
			return Result.succeed(deviceCabinetBody, CommonErrorCode.OPERATION_SUCCESS.getMessage());
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
		boolean flag = deviceCabinetBodyService.removeById(clientId);
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
		DeviceCabinetBody deviceCabinetBody = deviceCabinetBodyService.getById(clientId);
		return Result.succeed(deviceCabinetBody, CommonErrorCode.OPERATION_SUCCESS.getMessage());
	}
}

