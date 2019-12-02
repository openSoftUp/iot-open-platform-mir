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
import com.open.iot.netdevicemgr.dto.DeviceLockControlPanelDto;
import com.open.iot.netdevicemgr.entity.DeviceLockControlPanel;
import com.open.iot.netdevicemgr.service.DeviceLockControlPanelService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * 锁控板 前端控制器
 * </p>
 *
 * @author huy
 * @since 2019-12-02
 */
@RestController
@Api(tags = "锁控板 前端控制器api")
@RequestMapping("/device/lock/control/panel")
public class DeviceLockControlPanelController {

	@Autowired
	private DeviceLockControlPanelService deviceLockControlPanelService;


	/**
	 * 查询
	 * @param params
	 * @return
	 * @throws JsonProcessingException 
	 */
	@ApiOperation(value = "分页列表")
	@GetMapping("/page/list")
	public Result<?> findPage(DeviceLockControlPanelDto dto) throws JsonProcessingException {
		PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
		QueryWrapper<DeviceLockControlPanel> queryWrapper = new QueryWrapper<DeviceLockControlPanel>();
		List<DeviceLockControlPanel> allList = deviceLockControlPanelService.list(queryWrapper);
		PageInfo<DeviceLockControlPanel> pageInfo = new PageInfo<>(allList);
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
	public Result<?> save(@RequestBody DeviceLockControlPanel deviceLockControlPanel) throws JsonProcessingException {
		Date curDate = new Date();
		deviceLockControlPanel.setCreateTime(curDate);
		deviceLockControlPanel.setUpdateTime(curDate);
		boolean flag = deviceLockControlPanelService.save(deviceLockControlPanel);
		if(flag) {
			return Result.succeed(deviceLockControlPanel, CommonErrorCode.OPERATION_SUCCESS.getMessage());
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
	public Result<?> update(@RequestBody DeviceLockControlPanel deviceLockControlPanel) throws JsonProcessingException {
		Date curDate = new Date();
		deviceLockControlPanel.setUpdateTime(curDate);
		boolean flag = deviceLockControlPanelService.updateById(deviceLockControlPanel);
		if(flag) {
			return Result.succeed(deviceLockControlPanel, CommonErrorCode.OPERATION_SUCCESS.getMessage());
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
		boolean flag = deviceLockControlPanelService.removeById(clientId);
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
		DeviceLockControlPanel deviceLockControlPanel = deviceLockControlPanelService.getById(clientId);
		return Result.succeed(deviceLockControlPanel, CommonErrorCode.OPERATION_SUCCESS.getMessage());
	}
}

