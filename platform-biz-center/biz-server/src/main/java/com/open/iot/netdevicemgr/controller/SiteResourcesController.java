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
import com.open.iot.netdevicemgr.dto.SiteResourcesDto;
import com.open.iot.netdevicemgr.entity.SiteResources;
import com.open.iot.netdevicemgr.service.SiteResourcesService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * 站点资源详情 前端控制器
 * </p>
 *
 * @author huy
 * @since 2019-12-02
 */
@RestController
@Api(tags = "站点资源详情 前端控制器api")
@RequestMapping("/site/resources")
public class SiteResourcesController {

	@Autowired
	private SiteResourcesService siteResourcesService;


	/**
	 * 查询
	 * @param params
	 * @return
	 * @throws JsonProcessingException 
	 */
	@ApiOperation(value = "分页列表")
	@GetMapping("/page/list")
	public Result<?> findPage(SiteResourcesDto dto) throws JsonProcessingException {
		PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
		QueryWrapper<SiteResources> queryWrapper = new QueryWrapper<SiteResources>();
		List<SiteResources> allList = siteResourcesService.list(queryWrapper);
		PageInfo<SiteResources> pageInfo = new PageInfo<>(allList);
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
	public Result<?> save(@RequestBody SiteResources siteResources) throws JsonProcessingException {
		Date curDate = new Date();
		siteResources.setCreateTime(curDate);
		siteResources.setUpdateTime(curDate);
		boolean flag = siteResourcesService.save(siteResources);
		if(flag) {
			return Result.succeed(siteResources, CommonErrorCode.OPERATION_SUCCESS.getMessage());
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
	public Result<?> update(@RequestBody SiteResources siteResources) throws JsonProcessingException {
		Date curDate = new Date();
		siteResources.setUpdateTime(curDate);
		boolean flag = siteResourcesService.updateById(siteResources);
		if(flag) {
			return Result.succeed(siteResources, CommonErrorCode.OPERATION_SUCCESS.getMessage());
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
		boolean flag = siteResourcesService.removeById(id);
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
		SiteResources siteResources = siteResourcesService.getById(id);
		return Result.succeed(siteResources, CommonErrorCode.OPERATION_SUCCESS.getMessage());
	}
}

