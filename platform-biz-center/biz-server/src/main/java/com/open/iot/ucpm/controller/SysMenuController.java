package com.open.iot.ucpm.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.open.iot.annotation.log.LogAnnotation;
import com.open.iot.model.system.LoginAppUser;
import com.open.iot.model.system.SysMenu;
import com.open.iot.model.system.SysRole;
import com.open.iot.modelandutils.base.CommonErrorCode;
import com.open.iot.modelandutils.base.Result;
import com.open.iot.ucpm.service.SysMenuService;
import com.open.iot.utils.SysUserUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "菜单模块api")
@RequestMapping("/menus")
public class SysMenuController {

	@Autowired
	private SysMenuService menuService;

	/**
	 * 删除菜单
	 * 
	 * @param id
	 */
	@PreAuthorize("hasAuthority('menu:delete/menus/{id}')")
	@ApiOperation(value = "删除菜单")
	@DeleteMapping("/{id}")
	@LogAnnotation(module="user-center",recordRequestParam=false)
	public Result<?> delete(@PathVariable Long id) {
		try {
			menuService.delete(id);
			return Result.succeed();
		} catch (Exception ex) {
			ex.printStackTrace();
			return Result.failed();
		}

	}

	/**
	 * 给角色分配菜单
	 */
	@PreAuthorize("hasAuthority('menu:post/menus/granted')")
	@ApiOperation(value = "角色分配菜单")
	@PostMapping("/granted")
	@LogAnnotation(module="user-center",recordRequestParam=false)
	public Result<?> setMenuToRole(@RequestBody SysMenu sysMenu) {
		menuService.setMenuToRole(sysMenu.getRoleId(), sysMenu.getMenuIds());
		return Result.succeed();
	}

	//两种方式控制权限都可以。@pms是自定义的
//	@PreAuthorize("@pms.hasPermission('menu:get/menus/findAlls')")
	@PreAuthorize("hasAuthority('menu:get/menus/findAlls')")
	@ApiOperation(value = "查询所有菜单")
	@GetMapping("/findAlls")
	@LogAnnotation(module="user-center",recordRequestParam=false)
	public Result<?> findAlls() {
		List<SysMenu> list = menuService.findAll();

		return Result.succeed(list, CommonErrorCode.OPERATION_SUCCESS.getMessage());
	}

	@ApiOperation(value = "获取菜单以及顶级菜单")
	@GetMapping("/findOnes")
	@PreAuthorize("hasAuthority('menu:get/menus/findOnes')")
	public Result<?> findOnes() {
		List<SysMenu> list = menuService.findOnes();
		return Result.succeed(list, CommonErrorCode.OPERATION_SUCCESS.getMessage());
	}

	/**
	 * 添加菜单 或者 更新
	 * 
	 * @param menu
	 * @return
	 */
	@PreAuthorize("hasAnyAuthority('menu:post/menus','menu:put/menus')")
	@ApiOperation(value = "新增菜单")
	@PostMapping("saveOrUpdate")
	@LogAnnotation(module="user-center",recordRequestParam=false)
	public Result<?> saveOrUpdate(@RequestBody SysMenu menu) {

		try {
			if (menu.getId() != null) {
				menuService.update(menu);
			} else {
				menuService.save(menu);
			}
			return Result.succeed();
		} catch (Exception ex) {
			ex.printStackTrace();
			return Result.failed();
		}

	}

	/**
	 * 当前登录用户的菜单
	 * 
	 * @return
	 */
	@PreAuthorize("hasAuthority('menu:get/menus/current')")
	@GetMapping("/current")
	@ApiOperation(value = "查询当前用户菜单")
	@LogAnnotation(module="user-center",recordRequestParam=false)
	public Result<?> findMyMenu() {

		LoginAppUser loginAppUser = SysUserUtil.getLoginAppUser();
		Set<SysRole> roles = loginAppUser.getSysRoles();
		if (CollectionUtils.isEmpty(roles)) {
			return Result.succeed();
		}

		List<SysMenu> menus = menuService
				.findByRoles(roles.parallelStream().map(SysRole::getId).collect(Collectors.toSet()));

		List<SysMenu> sysMenus = TreeBuilder(menus);

		return Result.succeed(sysMenus, CommonErrorCode.OPERATION_SUCCESS.getMessage());
	}

	/**
	 * 两层循环实现建树
	 * 
	 * @param sysMenus
	 * @return
	 */
	public static List<SysMenu> TreeBuilder(List<SysMenu> sysMenus) {

		List<SysMenu> menus = new ArrayList<SysMenu>();
		for (SysMenu sysMenu : sysMenus) {
			if (ObjectUtils.equals(-1L, sysMenu.getParentId())) {
				menus.add(sysMenu);
			}
			for (SysMenu menu : sysMenus) {
				if (menu.getParentId().equals(sysMenu.getId())) {
					if (sysMenu.getSubMenus() == null) {
						sysMenu.setSubMenus(new ArrayList<>());
					}
					sysMenu.getSubMenus().add(menu);
				}
			}
		}
		return menus;
	}

}
