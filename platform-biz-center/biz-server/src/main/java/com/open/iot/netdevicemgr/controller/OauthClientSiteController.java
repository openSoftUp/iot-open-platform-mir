package com.open.iot.netdevicemgr.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.open.iot.annotation.log.LogAnnotation;
import com.open.iot.modelandutils.base.CommonErrorCode;
import com.open.iot.modelandutils.base.PageRequest;
import com.open.iot.modelandutils.base.Result;
import com.open.iot.netdevicemgr.entity.OauthClientSite;
import com.open.iot.netdevicemgr.service.OauthClientSiteService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

@RestController
@Api(tags = "站点权限管理控制器api")
@RequestMapping("/client/sit/config")
public class OauthClientSiteController {

	@Autowired
	private OauthClientSiteService oauthClientSiteService;


	/**
	 * 查询
	 * @param params
	 * @return
	 * @throws JsonProcessingException 
	 */
	@ApiOperation(value = "分页列表")
	@GetMapping("/page/list")
	@LogAnnotation(module="biz-center",recordRequestParam=false)
	public Result<?> findPage(PageRequest pageRequest,String clientId) throws JsonProcessingException {
		PageHelper.startPage(pageRequest.getPageNum(), pageRequest.getPageSize());
		QueryWrapper<OauthClientSite> queryWrapper = new QueryWrapper<OauthClientSite>();
		if(StringUtils.isNotBlank(clientId)) queryWrapper.lambda().eq(OauthClientSite::getClientId, clientId);
		List<OauthClientSite> allList = oauthClientSiteService.list(queryWrapper);
		PageInfo<OauthClientSite> pageInfo = new PageInfo<>(allList);
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
	public Result<?> save(@RequestBody OauthClientSite oauthClientSite) throws JsonProcessingException {
		boolean flag = oauthClientSiteService.save(oauthClientSite);
		if(flag) {
			return Result.succeed(oauthClientSite, CommonErrorCode.OPERATION_SUCCESS.getMessage());
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
	public Result<?> update(@RequestBody OauthClientSite oauthClientSite) throws JsonProcessingException {
		boolean flag = oauthClientSiteService.updateById(oauthClientSite);
		if(flag) {
			return Result.succeed(oauthClientSite, CommonErrorCode.OPERATION_SUCCESS.getMessage());
		}
		return Result.failed();
	}

	/**
	 * 删除
	 * @param id
	 */
	@ApiOperation(value = "删除")
	@PostMapping("/delete/{id}")
	@LogAnnotation(module="user-center",recordRequestParam=false)
	public Result<?> delete(@PathVariable Integer id) {
		boolean flag = oauthClientSiteService.removeById(id);
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
	@LogAnnotation(module="user-center",recordRequestParam=false)
	public Result<?> get(@PathVariable Integer id) {
		OauthClientSite oauthClientSite = oauthClientSiteService.getById(id);
		return Result.succeed(oauthClientSite, CommonErrorCode.OPERATION_SUCCESS.getMessage());
	}

}

