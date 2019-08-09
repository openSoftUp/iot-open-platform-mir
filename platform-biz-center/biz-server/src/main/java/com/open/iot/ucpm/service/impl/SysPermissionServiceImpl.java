package com.open.iot.ucpm.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.open.iot.model.system.SysPermission;
import com.open.iot.ucpm.dao.SysPermissionDao;
import com.open.iot.ucpm.dao.SysRolePermissionDao;
import com.open.iot.ucpm.service.SysPermissionService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SysPermissionServiceImpl implements SysPermissionService {

	@Autowired
	private SysPermissionDao sysPermissionDao;
	@Autowired
	private SysRolePermissionDao rolePermissionDao;

	@Override
	public Set<SysPermission> findByRoleIds(Set<Long> roleIds) {
		return rolePermissionDao.findPermissionsByRoleIds(roleIds);
	}

	@Transactional
	@Override
	public void save(SysPermission sysPermission) {
		SysPermission permission = sysPermissionDao.findByPermission(sysPermission.getPermission());
		if (permission != null) {
			throw new IllegalArgumentException("权限标识已存在");
		}
		sysPermission.setCreateTime(new Date());
		sysPermission.setUpdateTime(sysPermission.getCreateTime());

		sysPermissionDao.insert(sysPermission);
		log.info("保存权限标识：{}", sysPermission);
	}

	@Transactional
	@Override
	public void update(SysPermission sysPermission) {
		sysPermission.setUpdateTime(new Date());
		sysPermissionDao.updateByOps(sysPermission);
		log.info("修改权限标识：{}", sysPermission);
	}

	@Transactional
	@Override
	public void delete(Long id) {
		SysPermission permission = sysPermissionDao.findById(id);
		if (permission == null) {
			throw new IllegalArgumentException("权限标识不存在");
		}

		sysPermissionDao.deleteOps(id);
		rolePermissionDao.deleteRolePermission(null, id);
		log.info("删除权限标识：{}", permission);
	}

	@Override
	public PageInfo findPermissions(Map<String, Object> params) {
		PageHelper.startPage(MapUtils.getInteger(params, "pageNum"), MapUtils.getInteger(params, "pageSize"));
		List<SysPermission> list  = sysPermissionDao.findList(params);
		PageInfo<SysPermission> pageInfo = new PageInfo(list);
		return pageInfo;
	}

	@Override
	public void setAuthToRole(Long roleId, Set<Long> authIds) {
		rolePermissionDao.deleteRolePermission(roleId,null);

		if (!CollectionUtils.isEmpty(authIds)) {
			authIds.forEach(authId -> {
				rolePermissionDao.saveRolePermission(roleId, authId);
			});
		}

	}
}
