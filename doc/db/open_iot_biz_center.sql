CREATE DATABASE IF NOT EXISTS `open_iot_biz_center` DEFAULT CHARACTER SET = utf8mb4;
Use `open_iot_biz_center`;
#
# Structure for table "oauth_client_details"
#

DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `client_id` varchar(48) NOT NULL COMMENT '应用标识',
  `resource_ids` varchar(256) DEFAULT NULL COMMENT '资源限定串(逗号分割)',
  `client_secret` varchar(256) DEFAULT NULL COMMENT '应用密钥(bcyt) 加密',
  `client_secret_str` varchar(256) DEFAULT NULL COMMENT '应用密钥(明文)',
  `scope` varchar(256) DEFAULT NULL COMMENT '范围',
  `authorized_grant_types` varchar(256) DEFAULT NULL COMMENT '5种oauth授权方式(authorization_code,password,refresh_token,client_credentials)',
  `web_server_redirect_uri` varchar(256) DEFAULT NULL COMMENT '回调地址 ',
  `authorities` varchar(256) DEFAULT NULL COMMENT '权限',
  `access_token_validity` int(11) DEFAULT NULL COMMENT 'access_token有效期',
  `refresh_token_validity` int(11) DEFAULT NULL COMMENT 'refresh_token有效期',
  `additional_information` varchar(4096) DEFAULT '{}' COMMENT '{}',
  `autoapprove` varchar(256) DEFAULT NULL COMMENT '是否自动授权 是-true',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4;

#
# Data for table "oauth_client_details"
#

INSERT INTO `oauth_client_details` (`id`, `client_id`, `resource_ids`, `client_secret`, `client_secret_str`, `scope`, `authorized_grant_types`, `web_server_redirect_uri`, `authorities`, `access_token_validity`, `refresh_token_validity`, `additional_information`, `autoapprove`) VALUES ('1', 'app', NULL, '$2a$10$i3F515wEDiB4Gvj9ym9Prui0dasRttEUQ9ink4Wpgb4zEDCAlV8zO', 'app', 'app', 'password,refresh_token', NULL, NULL, '180000', NULL, '{}', 'true');
INSERT INTO `oauth_client_details` (`id`, `client_id`, `resource_ids`, `client_secret`, `client_secret_str`, `scope`, `authorized_grant_types`, `web_server_redirect_uri`, `authorities`, `access_token_validity`, `refresh_token_validity`, `additional_information`, `autoapprove`) VALUES ('2', 'back', 'back,test', '$2a$10$ULxRssv/4NWOc388lZFbyus3IFfsbcpG/BZOq4TRxDhsx5HHIR7Jm', 'app', 'all', 'password,refresh_token', NULL, NULL, '180000', NULL, '{}', 'true');
INSERT INTO `oauth_client_details` (`id`, `client_id`, `resource_ids`, `client_secret`, `client_secret_str`, `scope`, `authorized_grant_types`, `web_server_redirect_uri`, `authorities`, `access_token_validity`, `refresh_token_validity`, `additional_information`, `autoapprove`) VALUES ('3', 'webApp', NULL, '$2a$10$06msMGYRH8nrm4iVnKFNKOoddB8wOwymVhbUzw/d3ZixD7Nq8ot72', 'webApp', 'app', 'authorization_code,password,refresh_token,client_credentials', NULL, NULL, '180000', NULL, '{}', 'true');


DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parentId` int(11) NOT NULL,
  `name` varchar(64) NOT NULL,
  `url` varchar(1024) DEFAULT NULL,
  `path` varchar(1024) DEFAULT NULL,
  `css` varchar(32) DEFAULT NULL,
  `sort` int(11) NOT NULL,
  `createTime` datetime NOT NULL,
  `updateTime` datetime NOT NULL,
  `isMenu` int(11) DEFAULT NULL COMMENT '是否菜单 1 是 2 不是',
  `hidden` int(11) DEFAULT NULL COMMENT '是否隐藏,0 false 1 true',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100000 DEFAULT CHARSET=utf8mb4;

#
# Data for table "sys_menu"
#

INSERT INTO `sys_menu` (`id`, `parentId`, `name`, `url`, `path`, `css`, `sort`, `createTime`, `updateTime`, `isMenu`, `hidden`) VALUES ('11', '20', '用户管理', '#!user', 'system/user.html', 'layui-icon-friends', '2', '2019-08-06 16:42:53', '2019-08-06 16:42:53', '1', '0');
INSERT INTO `sys_menu` (`id`, `parentId`, `name`, `url`, `path`, `css`, `sort`, `createTime`, `updateTime`, `isMenu`, `hidden`) VALUES ('12', '20', '角色管理', '#!role', 'system/role.html', 'layui-icon-friends', '3', '2019-08-06 16:42:53', '2019-08-06 16:42:53', '1', '0');
INSERT INTO `sys_menu` (`id`, `parentId`, `name`, `url`, `path`, `css`, `sort`, `createTime`, `updateTime`, `isMenu`, `hidden`) VALUES ('13', '20', '菜单管理', '#!menus', 'system/menus.html', 'layui-icon-menu-fill', '4', '2019-08-06 16:42:53', '2019-08-06 16:42:53', '1', '0');
INSERT INTO `sys_menu` (`id`, `parentId`, `name`, `url`, `path`, `css`, `sort`, `createTime`, `updateTime`, `isMenu`, `hidden`) VALUES ('14', '20', '权限管理', '#!permissions', 'system/permissions.html', 'layui-icon-password', '5', '2019-08-06 16:42:53', '2019-08-06 16:42:53', '1', '0');
INSERT INTO `sys_menu` (`id`, `parentId`, `name`, `url`, `path`, `css`, `sort`, `createTime`, `updateTime`, `isMenu`, `hidden`) VALUES ('20', '-1', '认证中心', 'javascript:;', '', 'layui-icon-set', '1', '2019-08-06 16:42:53', '2019-08-06 16:42:53', '1', '0');



#
# Structure for table "sys_permission"
#

DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `permission` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `createTime` datetime NOT NULL,
  `updateTime` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `permission` (`permission`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=100000 DEFAULT CHARSET=utf8mb4;

#
# Data for table "sys_permission"
#


INSERT INTO `sys_permission` (`id`, `permission`, `name`, `createTime`, `updateTime`) VALUES ('11', 'user:post/users/{id}/roles', '给用户分配角色', '2019-08-06 16:40:51', '2019-08-06 16:40:51');
INSERT INTO `sys_permission` (`id`, `permission`, `name`, `createTime`, `updateTime`) VALUES ('12', 'user:post/users/{id}/resetPassword', '用户重置密码', '2019-08-06 16:40:51', '2019-08-06 16:40:51');
INSERT INTO `sys_permission` (`id`, `permission`, `name`, `createTime`, `updateTime`) VALUES ('13', 'user:get/users', '用户查询', '2019-08-06 16:40:51', '2019-08-06 16:40:51');
INSERT INTO `sys_permission` (`id`, `permission`, `name`, `createTime`, `updateTime`) VALUES ('14', 'user:get/users/updateEnabled', '用户状态修改', '2019-08-06 16:40:51', '2019-08-06 16:40:51');
INSERT INTO `sys_permission` (`id`, `permission`, `name`, `createTime`, `updateTime`) VALUES ('15', 'user:put/users/password', '修改密码', '2019-08-06 16:40:51', '2019-08-06 16:40:51');



#
# Structure for table "sys_role"
#

DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(32) NOT NULL COMMENT '角色code',
  `name` varchar(50) NOT NULL COMMENT '角色名',
  `createTime` datetime NOT NULL,
  `updateTime` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=100000 DEFAULT CHARSET=utf8mb4;

#
# Data for table "sys_role"
#

INSERT INTO `sys_role` VALUES (1,'ADMIN','管理员','2019-08-06 16:40:51','2019-08-06 16:40:51'),(10,'NOMAL','普通用户','2019-08-06 16:40:51','2019-08-06 16:40:51');

#
# Structure for table "sys_role_menu"
#

DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `roleId` int(11) NOT NULL,
  `menuId` int(11) NOT NULL,
  PRIMARY KEY (`roleId`,`menuId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

#
# Data for table "sys_role_menu"
#

INSERT INTO `sys_role_menu` (`roleId`, `menuId`) VALUES ('1', '11');
INSERT INTO `sys_role_menu` (`roleId`, `menuId`) VALUES ('1', '12');
INSERT INTO `sys_role_menu` (`roleId`, `menuId`) VALUES ('1', '13');
INSERT INTO `sys_role_menu` (`roleId`, `menuId`) VALUES ('1', '14');
INSERT INTO `sys_role_menu` (`roleId`, `menuId`) VALUES ('1', '20');

#
# Structure for table "sys_role_permission"
#

DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission` (
  `roleId` int(11) NOT NULL,
  `permissionId` bigint(20) NOT NULL,
  PRIMARY KEY (`roleId`,`permissionId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

#
# Data for table "sys_role_permission"
#

INSERT INTO `sys_role_permission` (`roleId`, `permissionId`) VALUES ('1', '11');
INSERT INTO `sys_role_permission` (`roleId`, `permissionId`) VALUES ('1', '12');
INSERT INTO `sys_role_permission` (`roleId`, `permissionId`) VALUES ('1', '13');
INSERT INTO `sys_role_permission` (`roleId`, `permissionId`) VALUES ('1', '14');
INSERT INTO `sys_role_permission` (`roleId`, `permissionId`) VALUES ('1', '15');


#
# Structure for table "sys_role_user"
#

DROP TABLE IF EXISTS `sys_role_user`;
CREATE TABLE `sys_role_user` (
  `userId` bigint(20) NOT NULL,
  `roleId` int(11) NOT NULL,
  PRIMARY KEY (`userId`,`roleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

#
# Data for table "sys_role_user"
#

INSERT INTO `sys_role_user` VALUES (1,1),(2,10);

#
# Structure for table "sys_user"
#

DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(60) NOT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `headImgUrl` varchar(1024) DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `sex` tinyint(1) DEFAULT NULL,
  `enabled` tinyint(1) NOT NULL DEFAULT '1',
  `type` varchar(16) NOT NULL,
  `createTime` datetime NOT NULL,
  `updateTime` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10000000 DEFAULT CHARSET=utf8mb4;

#
# Data for table "sys_user"
#

INSERT INTO `sys_user` (`id`, `username`, `password`, `nickname`, `headImgUrl`, `phone`, `sex`, `enabled`, `type`, `createTime`, `updateTime`) VALUES ('1', 'admin', '$2a$10$i3F515wEDiB4Gvj9ym9Prui0dasRttEUQ9ink4Wpgb4zEDCAlV8zO', '管理员', 'http://image.jpg', '13106975707', '1', '1', 'BACKEND', '2019-08-06 16:47:55', '2019-08-06 16:47:55');
INSERT INTO `sys_user` (`id`, `username`, `password`, `nickname`, `headImgUrl`, `phone`, `sex`, `enabled`, `type`, `createTime`, `updateTime`) VALUES ('2', 'test', '$2a$10$i3F515wEDiB4Gvj9ym9Prui0dasRttEUQ9ink4Wpgb4zEDCAlV8zO', '测试账户', 'http://baidu.image.jpg', '13851539156', '0', '0', 'APP', '2019-08-06 16:47:55', '2019-08-06 16:47:55');




# --netdevice mgr start
#
# Structure for table "oauth_client_site"
#

DROP TABLE IF EXISTS `device_class_type_info`;
CREATE TABLE `device_class_type_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `class_type_name` varchar(48) DEFAULT NULL COMMENT '设备类型名称',
  `class_type_remark` varchar(200) DEFAULT NULL COMMENT '备注信息',
  `state` int(2) DEFAULT 1 COMMENT '状态：1:启用;2:停用;3:删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT '设备类型信息';

DROP TABLE IF EXISTS `device_product_type_info`;
CREATE TABLE `device_product_type_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `product_type_name` varchar(48) DEFAULT NULL COMMENT '设备型号',
  `product_type_params` varchar(200) DEFAULT NULL COMMENT '设备型号参数',
  `product_type_remark` varchar(200) DEFAULT NULL COMMENT '备注信息',
  `state` int(2) DEFAULT 1 COMMENT '状态：1:启用;2:停用;3:删除',
  `class_type_id` int(11) DEFAULT NULL COMMENT '对应的设备类型ID',
  `factory_id` int(11) DEFAULT NULL COMMENT '设备厂家',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT '设备型号信息';

DROP TABLE IF EXISTS `device_factory_info`;
CREATE TABLE `device_factory_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `factory_name` varchar(48) DEFAULT NULL COMMENT '厂家名称',
  `factory_tax_num` varchar(48) DEFAULT NULL COMMENT '厂家税号',
  `factory_addr` varchar(200) DEFAULT NULL COMMENT '地址',
  `bank_name` varchar(200) DEFAULT NULL COMMENT '厂家开户行',
  `bank_account` varchar(200) DEFAULT NULL COMMENT '厂家银行账号',
  `contact_man` varchar(48) DEFAULT NULL COMMENT '厂家联系人姓名',
  `contact_tel` varchar(48) DEFAULT NULL COMMENT '厂家联系电话',
  `contact_email` varchar(48) DEFAULT NULL COMMENT '厂家联系邮件',
  `state` int(2) DEFAULT 1 COMMENT '状态：1:启用;2:停用;3:删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT '设备厂家信息';

DROP TABLE IF EXISTS site_group;
CREATE TABLE  site_group (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `group_name` varchar(255) NOT NULL COMMENT '分组',
  `group_remark` varchar(255) DEFAULT NULL COMMENT '分组备注',
  `parent_id` int(11) DEFAULT NULL COMMENT '父分组ID',
  `state` int(2) DEFAULT 1 COMMENT '状态：1:启用;2:停用;3:删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
   PRIMARY KEY (id) 
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='站点分组模型';
INSERT INTO `site_group` (`id`, `group_name`, `group_remark`, `parent_id`, `state`, `create_time`, `update_time`) VALUES ('1', '全部','全部', '-1', '1', now(),  now());


DROP TABLE IF EXISTS site;
CREATE TABLE  site (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `site_name` varchar(100) NOT NULL COMMENT '站点名称',
  `site_addr` varchar(255) DEFAULT NULL COMMENT '站点位置',
  `site_remark` varchar(255) DEFAULT NULL COMMENT '分组备注',  
  `group_id` int(11) DEFAULT NULL COMMENT '所属站点分组',
  `state` int(2) DEFAULT 1 COMMENT '状态：1:启用;2:停用;3:删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
   PRIMARY KEY (id) 
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='站点模型';


DROP TABLE IF EXISTS contacts_info;
CREATE TABLE  contacts_info (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `contact_name` varchar(100) NOT NULL COMMENT '联系人名称',
  `contact_tel` varchar(255) NOT NULL COMMENT '联系人电话',
  `contact_email` varchar(255) DEFAULT NULL COMMENT '联系人邮件',  
  `contact_group_code` varchar(50) DEFAULT NULL COMMENT '所属站点分组',
  `state` int(2) DEFAULT 1 COMMENT '状态：1:启用;2:停用;3:删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
   PRIMARY KEY (id) 
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='联系人信息';


DROP TABLE IF EXISTS `site_contacts_info`;
CREATE TABLE `site_contacts_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `site_id` int(11) NOT NULL  COMMENT '站点ID',
  `contact_id` int(11) NOT NULL COMMENT '联系人ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (id) 
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT '站点与联系人对应关系，多对多';

DROP TABLE IF EXISTS `oauth_client_site`;
CREATE TABLE `oauth_client_site` (
  `client_id` varchar(48) NOT NULL COMMENT '应用标识',
  `site_ids` varchar(255) NOT NULL COMMENT '站点IDs,多个用逗号隔开',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT '站点与客户端权限配置，多对多';


DROP TABLE IF EXISTS `device_dtu_control`;
CREATE TABLE `device_dtu_control` (
  `serial_no` varchar(32) NOT NULL COMMENT '设备序列号',
  `mac` varchar(32) DEFAULT NULL COMMENT '设备mac',
  `sim_card` varchar(50) DEFAULT NULL COMMENT 'sim卡',
  `device_name` varchar(50) DEFAULT NULL COMMENT '设备名称',
  `dev_version` varchar(100) DEFAULT NULL COMMENT '设备的软件版本',
  `site_id` int(11) DEFAULT NULL COMMENT '所属站点',
  `remarks` varchar(200) DEFAULT NULL COMMENT '备注信息',
  `state` int(2) DEFAULT 1 COMMENT '状态：1:启用;2:停用;3:删除',
  `class_type` int(11)  DEFAULT NULL COMMENT '设备类型',
  `product_type` int(11) DEFAULT NULL COMMENT '设备型号',
  `create_user` varchar(50) DEFAULT NULL COMMENT '创建人',
  `update_user` varchar(50) DEFAULT NULL COMMENT '更新人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL  COMMENT '更新时间',
  PRIMARY KEY (`serial_no`),
  KEY `DEV_DTU_CONTROL_INX_SITE_ID` (`site_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='DTU控制器';

DROP TABLE IF EXISTS `device_lock_control_panel`;
CREATE TABLE `device_lock_control_panel` (
  `serial_no` varchar(32) NOT NULL COMMENT '设备序列号',
  `mac` varchar(32) DEFAULT NULL COMMENT '设备mac',
  `dial_num` int(1) NOT NULL  COMMENT '拨码,单个数字（0-9任一）',
  `device_name` varchar(50) DEFAULT NULL COMMENT '设备名称',
  `dev_version` varchar(100) DEFAULT NULL COMMENT '设备的软件版本',
  `site_id` int(11) DEFAULT NULL COMMENT '所属站点',
  `remarks` varchar(200) DEFAULT NULL COMMENT '备注信息',
  `state` int(2) DEFAULT 1 COMMENT '状态：1:启用;2:停用;3:删除',
  `class_type` int(11)  DEFAULT NULL COMMENT '设备类型',
  `product_type` int(11) DEFAULT NULL COMMENT '设备型号',
  `create_user` varchar(50) DEFAULT NULL COMMENT '创建人',
  `update_user` varchar(50) DEFAULT NULL COMMENT '更新人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL  COMMENT '更新时间',
  PRIMARY KEY (`serial_no`),
  KEY `DEV_LOCAL_CONTROL_PANEL_INX_SITE_ID` (`site_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='锁控板';

DROP TABLE IF EXISTS `device_cabinet_body`;
CREATE TABLE `device_cabinet_body` (
  `serial_no` varchar(32) NOT NULL COMMENT '设备序列号',
  `mac` varchar(32) DEFAULT NULL COMMENT '设备mac',
  `door_num` int(2) NOT NULL  COMMENT '柜门数量,单个数字（0-9任一）',
  `configuration_info` varchar(50) DEFAULT NULL COMMENT '配件信息,根据业务所增加的配件信息',
  `device_name` varchar(50) DEFAULT NULL COMMENT '设备名称',
  `dev_version` varchar(100) DEFAULT NULL COMMENT '设备的软件版本',
  `site_id` int(11) DEFAULT NULL COMMENT '所属站点',
  `remarks` varchar(200) DEFAULT NULL COMMENT '备注信息',
  `state` int(2) DEFAULT 1 COMMENT '状态：1:启用;2:停用;3:删除',
  `class_type` int(11)  DEFAULT NULL COMMENT '设备类型',
  `product_type` int(11) DEFAULT NULL COMMENT '设备型号',
  `create_user` varchar(50) DEFAULT NULL COMMENT '创建人',
  `update_user` varchar(50) DEFAULT NULL COMMENT '更新人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL  COMMENT '更新时间',
  PRIMARY KEY (`serial_no`),
  KEY `DEV_CABINET_BODY_INX_SITE_ID` (`site_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='柜体';

DROP TABLE IF EXISTS `device_nvr`;
CREATE TABLE `device_nvr` (
  `serial_no` varchar(32) NOT NULL COMMENT '设备序列号',
  `mac` varchar(32) DEFAULT NULL COMMENT '设备mac',
  `product_time` datetime DEFAULT NULL COMMENT '生产日期',
  `verification_code` varchar(50) DEFAULT NULL COMMENT '验证码,产品自带的验证码',
  `dev_version` varchar(100) DEFAULT NULL COMMENT '设备的软件版本', 
  `device_name` varchar(50) DEFAULT NULL COMMENT '设备名称',
  `site_id` int(11) DEFAULT NULL COMMENT '所属站点',
  `remarks` varchar(200) DEFAULT NULL COMMENT '备注信息',
  `state` int(2) DEFAULT 1 COMMENT '状态：1:启用;2:停用;3:删除',
  `class_type` int(11)  DEFAULT NULL COMMENT '设备类型',
  `product_type` int(11) DEFAULT NULL COMMENT '设备型号',
  `create_user` varchar(50) DEFAULT NULL COMMENT '创建人',
  `update_user` varchar(50) DEFAULT NULL COMMENT '更新人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL  COMMENT '更新时间',
  PRIMARY KEY (`serial_no`),
  KEY `DEV_NVR_INX_SITE_ID` (`site_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='NVR';


DROP TABLE IF EXISTS `device_camera`;
CREATE TABLE `device_camera` (
  `serial_no` varchar(32) NOT NULL COMMENT '设备序列号',
  `mac` varchar(32) DEFAULT NULL COMMENT '设备mac',  
  `product_time` datetime DEFAULT NULL COMMENT '生产日期',
  `verification_code` varchar(50) DEFAULT NULL COMMENT '验证码,产品自带的验证码',
  `dev_version` varchar(100) DEFAULT NULL COMMENT '设备的软件版本', 
  `dev_user` varchar(50) DEFAULT NULL COMMENT '设备账号', 
  `dev_password` varchar(50) DEFAULT NULL COMMENT '设备密码', 
  `device_name` varchar(50) DEFAULT NULL COMMENT '设备名称',
  `site_id` int(11) DEFAULT NULL COMMENT '所属站点',
  `remarks` varchar(200) DEFAULT NULL COMMENT '备注信息',
  `state` int(2) DEFAULT 1 COMMENT '状态：1:启用;2:停用;3:删除',
  `class_type` int(11)  DEFAULT NULL COMMENT '设备类型',
  `product_type` int(11) DEFAULT NULL COMMENT '设备型号',
  `create_user` varchar(50) DEFAULT NULL COMMENT '创建人',
  `update_user` varchar(50) DEFAULT NULL COMMENT '更新人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL  COMMENT '更新时间',
  PRIMARY KEY (`serial_no`),
  KEY `DEV_CAMERA_INX_SITE_ID` (`site_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='摄像头';

DROP TABLE IF EXISTS site_resources_type;
CREATE TABLE  site_resources_type (
  `code` varchar(50) NOT NULL COMMENT '资源类型code',
  `resources_type_remark` varchar(255) DEFAULT NULL COMMENT '备注', 
  `state` int(2) DEFAULT 1 COMMENT '状态：1:启用;2:停用;3:删除',
  `create_user` varchar(50) DEFAULT NULL COMMENT '创建人',
  `update_user` varchar(50) DEFAULT NULL COMMENT '更新人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
   PRIMARY KEY (code) 
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='站点资源详情';

DROP TABLE IF EXISTS site_resources;
CREATE TABLE  site_resources (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `resources_name` varchar(255) NOT NULL COMMENT '资源名称',
  `resources_remark` varchar(255) DEFAULT NULL COMMENT '资源备注', 
  `resources_type_code` varchar(50) NOT NULL COMMENT '资源类型编码',
  `resources_params` JSON DEFAULT NULL COMMENT '资源类型JSON参数，可根据资源类型动态变化，对应每种资源对象需定义清楚',
  `work_state` int(1) DEFAULT 1 COMMENT '工作状态：1:空闲;2:占用;3:使用',
  `state` int(2) DEFAULT 1 COMMENT '状态：1:启用;2:停用;3:删除',
  `site_id` int(11) DEFAULT NULL COMMENT '所属站点',
  `create_user` varchar(50) DEFAULT NULL COMMENT '创建人',
  `update_user` varchar(50) DEFAULT NULL COMMENT '更新人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
   PRIMARY KEY (id) 
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='站点资源详情';
