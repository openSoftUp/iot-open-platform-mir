package com.open.iot.auth.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Maps;
import com.open.iot.auth.service.ClientService;
import com.open.iot.modelandutils.base.CommonErrorCode;
import com.open.iot.modelandutils.base.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 
* @ClassName: ClientController  
* @Description: APP相关接口  
* @author huy  
* @date 2019年8月6日  
*
 */
@Api(tags = "应用")
@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping("/list")
    @ApiOperation(value = "应用列表")
    public Result<?> listAlls(@RequestParam Map<String, Object> params) {
        return Result.succeed(clientService.findList(Maps.newHashMap()), CommonErrorCode.OPERATION_SUCCESS.getMessage());
    }

    @GetMapping("/get/{id}")
    @ApiOperation(value = "根据id获取应用")
    public Result<?> get(@PathVariable Long id) {
        return Result.succeed(clientService.getById(id), CommonErrorCode.OPERATION_SUCCESS.getMessage());
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "删除应用")
    public Result<?> delete(@PathVariable Long id) {
    	clientService.deleteClient(id);
    	return Result.succeed();
    }

}
