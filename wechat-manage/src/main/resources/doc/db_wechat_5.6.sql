/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50610
Source Host           : localhost:3306
Source Database       : db_wechat

Target Server Type    : MYSQL
Target Server Version : 50610
File Encoding         : 65001

Date: 2017-06-08 08:00:55
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for cms_access
-- ----------------------------
DROP TABLE IF EXISTS `cms_access`;
CREATE TABLE `cms_access` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限表';

-- ----------------------------
-- Table structure for cms_auth_rule
-- ----------------------------
DROP TABLE IF EXISTS `cms_auth_rule`;
CREATE TABLE `cms_auth_rule` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='管理员权限规则表';

-- ----------------------------
-- Table structure for cms_manageruser
-- ----------------------------
DROP TABLE IF EXISTS `cms_manageruser`;
CREATE TABLE `cms_manageruser` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(50) NOT NULL COMMENT '登录名，用户名',
  `password` varchar(128) NOT NULL COMMENT '密码(真正的密码与用户名MD5加密)',
  `userType` int(11) DEFAULT NULL COMMENT '用户类型(字典表)',
  `realName` varchar(50) DEFAULT NULL COMMENT '姓名',
  `email` varchar(100) DEFAULT NULL COMMENT '用户邮箱',
  `emailstatus` tinyint(1) DEFAULT '0' COMMENT 'email是否经过验证',
  `userSex` tinyint(2) DEFAULT NULL COMMENT '性别',
  `mobile` varchar(50) DEFAULT NULL COMMENT '用户手机号码',
  `telephone` varchar(50) DEFAULT NULL COMMENT '用户电话号码',
  `signature` varchar(200) DEFAULT NULL COMMENT '个性签名',
  `isLockedOut` tinyint(2) NOT NULL DEFAULT '1' COMMENT '是否锁定(1:不锁定;0：锁定)',
  `isVisible` tinyint(2) NOT NULL DEFAULT '1' COMMENT '是否有效(是否审核);1:可见;0:不可见,默认值',
  `loginFlag` tinyint(2) NOT NULL DEFAULT '1' COMMENT '是否允许登陆;1:允许,默认值;0:不允许',
  `isEnabled` tinyint(2) NOT NULL DEFAULT '0' COMMENT '是否有效;-1:删除;0:不可用,默认值;1:可用',
  `remark` varchar(255) DEFAULT NULL COMMENT '描述',
  `create` int(11) DEFAULT NULL COMMENT '最初创建者',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建日期',
  `modifier` int(11) DEFAULT NULL COMMENT '最后修改人',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改日期',
  `sequence` int(11) DEFAULT '1' COMMENT '排顺字段：越大越靠前',
  PRIMARY KEY (`id`),
  UNIQUE KEY `userName` (`userName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统管理员用户表';

-- ----------------------------
-- Table structure for cms_menu
-- ----------------------------
DROP TABLE IF EXISTS `cms_menu`;
CREATE TABLE `cms_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `menuName` varchar(15) NOT NULL COMMENT '菜单名称',
  `menuUrl` varchar(255) DEFAULT NULL COMMENT '菜单链接地址',
  `parentCode` int(11) NOT NULL COMMENT '父菜单编号',
  `isVisible` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否可见(启用/禁用),不为空',
  `isEnabled` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否有效;-1:删除;0:不可用,默认值;1:可用',
  `iconClass` varchar(255) DEFAULT NULL COMMENT '菜单图标样式',
  `level` int(11) DEFAULT NULL COMMENT '菜单等级',
  `sequence` int(11) DEFAULT '1' COMMENT '排序',
  `creater` int(11) DEFAULT NULL COMMENT '创建人',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建日期',
  `modifier` int(11) DEFAULT NULL COMMENT '最后修改人',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改日期',
  `allowEdit` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否允许编辑;1:允许,默认值;0:不允许',
  `allowDelete` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否允许删除;1:允许删除,默认值,0:不允许删除',
  `remark` varchar(255) DEFAULT NULL COMMENT '描述说明',
  PRIMARY KEY (`id`),
  KEY `parentCode` (`parentCode`),
  KEY `isEnabled` (`isEnabled`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统管理员菜单权限表';

-- ----------------------------
-- Table structure for cms_menu_btn
-- ----------------------------
DROP TABLE IF EXISTS `cms_menu_btn`;
CREATE TABLE `cms_menu_btn` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `menuId` int(11) DEFAULT NULL COMMENT '所属菜单',
  `btnName` varchar(100) DEFAULT NULL COMMENT '按钮名称',
  `className` varchar(100) DEFAULT NULL COMMENT '页面class名称',
  `methodName` varchar(100) DEFAULT NULL COMMENT '后台method名称',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单按钮';

-- ----------------------------
-- Table structure for cms_office
-- ----------------------------
DROP TABLE IF EXISTS `cms_office`;
CREATE TABLE `cms_office` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `parentId` int(11) NOT NULL COMMENT '父级编号',
  `name` varchar(100) COLLATE utf8_bin NOT NULL COMMENT '名称',
  `sequence` int(11) NOT NULL COMMENT '排序',
  `areaId` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '归属区域',
  `code` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '区域编码',
  `type` tinyint(1) NOT NULL COMMENT '机构类型',
  `grade` tinyint(1) NOT NULL COMMENT '机构等级',
  `address` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '联系地址',
  `zipCode` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '邮政编码',
  `master` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '负责人',
  `phone` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '电话',
  `fax` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '传真',
  `email` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '邮箱',
  `isEnabled` tinyint(1) DEFAULT NULL,
  `isVisible` tinyint(1) DEFAULT NULL COMMENT '是否启用',
  `primary_person` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '主负责人',
  `deputy_person` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '副负责人',
  `create` int(11) NOT NULL COMMENT '创建者',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` int(11) NOT NULL COMMENT '更新者',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `remark` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`id`),
  KEY `parentId` (`parentId`),
  KEY `isEnabled` (`isEnabled`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='机构表';

-- ----------------------------
-- Table structure for cms_role
-- ----------------------------
DROP TABLE IF EXISTS `cms_role`;
CREATE TABLE `cms_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roleCode` varchar(50) NOT NULL COMMENT '角色编号',
  `roleName` varchar(200) NOT NULL COMMENT '角色名称',
  `categoryCode` varchar(50) NOT NULL COMMENT '角色分类',
  `allowEdit` tinyint(2) NOT NULL DEFAULT '1' COMMENT '是否允许编辑(1:允许，默认值;0:不允许)',
  `allowDelete` tinyint(2) NOT NULL DEFAULT '1' COMMENT '是否允许删除(1:允许，默认值;0:不允许)',
  `sequence` tinyint(2) DEFAULT '0' COMMENT '排序码',
  `isVisible` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否可见;1:可见,默认值;0:不可见',
  `isEnabled` tinyint(2) NOT NULL DEFAULT '0' COMMENT '是否有效:默认0无效；',
  `creater` int(11) NOT NULL,
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建日期',
  `modifier` varchar(255) DEFAULT NULL,
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改日期',
  `remark` varchar(255) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`),
  UNIQUE KEY `roleCode` (`roleCode`,`isEnabled`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Table structure for cms_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `cms_role_menu`;
CREATE TABLE `cms_role_menu` (
  `roleID` int(11) NOT NULL COMMENT '角色ID',
  `authID` int(11) NOT NULL COMMENT '角色菜单ID',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`roleID`,`authID`),
  UNIQUE KEY `roleID` (`roleID`,`authID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色-菜单';

-- ----------------------------
-- Table structure for cms_role_office
-- ----------------------------
DROP TABLE IF EXISTS `cms_role_office`;
CREATE TABLE `cms_role_office` (
  `roleId` int(11) NOT NULL COMMENT '角色编号',
  `officeId` int(11) NOT NULL COMMENT '机构编号',
  PRIMARY KEY (`roleId`,`officeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='角色-机构';

-- ----------------------------
-- Table structure for cms_user_role
-- ----------------------------
DROP TABLE IF EXISTS `cms_user_role`;
CREATE TABLE `cms_user_role` (
  `userId` int(11) NOT NULL COMMENT '用户主键',
  `roleId` int(11) NOT NULL COMMENT '角色主键',
  PRIMARY KEY (`userId`,`roleId`),
  UNIQUE KEY `RoleInfo` (`userId`,`roleId`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户-角色关联表';

-- ----------------------------
-- Table structure for crawler_image
-- ----------------------------
DROP TABLE IF EXISTS `crawler_image`;
CREATE TABLE `crawler_image` (
  `userCode` varchar(64) DEFAULT NULL,
  `boardId` varchar(64) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL COMMENT '地址',
  `type` varchar(32) DEFAULT NULL COMMENT '数据类型',
  `remark` text COMMENT '对内容的描述 ',
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '入库时间',
  `source` varchar(32) DEFAULT NULL,
  UNIQUE KEY `url` (`url`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='网络爬虫-图片';

-- ----------------------------
-- Table structure for os_api_interface
-- ----------------------------
DROP TABLE IF EXISTS `os_api_interface`;
CREATE TABLE `os_api_interface` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `url` varchar(100) NOT NULL COMMENT '地址',
  `method` varchar(30) NOT NULL COMMENT '请求方法',
  `param` text COMMENT '请求参数：json格式存储',
  `requestExam` text COMMENT '请求样例',
  `responseParam` text COMMENT '返回参数：json格式存储',
  `errorList` text COMMENT '错误列表',
  `trueExam` text COMMENT '正确返回样例',
  `falseExam` text COMMENT '错误返回样例',
  `isEnabled` tinyint(1) NOT NULL DEFAULT '1' COMMENT '状态',
  `moduleId` varchar(50) NOT NULL COMMENT '模块ID',
  `interfaceName` varchar(100) NOT NULL COMMENT '接口名',
  `remark` text COMMENT '备注',
  `errors` text COMMENT '错误信息：json格式',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `updateBy` varchar(100) NOT NULL COMMENT '更新人',
  `version` varchar(20) NOT NULL COMMENT '版本号',
  `sequence` int(11) NOT NULL DEFAULT '0' COMMENT '排序：排序，越大越靠前',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='CrapApi—接口表（interfa）';

-- ----------------------------
-- Table structure for os_api_parameter
-- ----------------------------
DROP TABLE IF EXISTS `os_api_parameter`;
CREATE TABLE `os_api_parameter` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL COMMENT '参数名',
  `value` varchar(64) DEFAULT NULL COMMENT '参数值',
  `create` int(11) DEFAULT NULL COMMENT '创建者',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` int(11) DEFAULT NULL COMMENT '更新者',
  `updateTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='接口参数表';

-- ----------------------------
-- Table structure for os_email_records
-- ----------------------------
DROP TABLE IF EXISTS `os_email_records`;
CREATE TABLE `os_email_records` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tomail` int(11) DEFAULT NULL COMMENT '收件人',
  `frommail` int(11) DEFAULT NULL COMMENT '发件人',
  `subject` varchar(128) DEFAULT NULL COMMENT '邮件标题',
  `message` text COMMENT '邮件内容',
  `charset` int(11) DEFAULT NULL COMMENT '邮件编码（字典表）',
  `emailType` tinyint(4) DEFAULT NULL COMMENT '邮箱类型：0.找回密码；1.注册；2.改变邮箱；3.通知',
  `level` int(11) DEFAULT NULL COMMENT '邮件紧急级别',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '记录插入时间',
  `appid` int(11) DEFAULT NULL COMMENT '来源应用id',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '链接是否已失效：0.失效；1.未失效；',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='邮件发送记录';

-- ----------------------------
-- Table structure for sys_button
-- ----------------------------
DROP TABLE IF EXISTS `sys_button`;
CREATE TABLE `sys_button` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '按钮编码(主键)',
  `buttonName` varchar(128) DEFAULT NULL COMMENT '按钮名称',
  `buttonIcon` varchar(128) DEFAULT NULL COMMENT '按钮图标',
  `buttonDesc` varchar(255) DEFAULT NULL COMMENT '备注说明',
  `buttonSeq` int(11) DEFAULT NULL COMMENT '排序',
  `creater` int(11) DEFAULT NULL COMMENT '创建人',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建日期',
  `modifier` int(11) DEFAULT NULL COMMENT '最后修改人',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统管理-按钮库';

-- ----------------------------
-- Table structure for sys_datasource
-- ----------------------------
DROP TABLE IF EXISTS `sys_datasource`;
CREATE TABLE `sys_datasource` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sourceName` varchar(30) NOT NULL COMMENT '连接名称',
  `dbUrl` varchar(128) NOT NULL COMMENT '数据库连接url',
  `dbUserName` varchar(30) NOT NULL COMMENT '数据库登陆用户名',
  `dbPassword` varchar(64) NOT NULL COMMENT '数据库登陆密码',
  `jdbcName` varchar(128) NOT NULL COMMENT ' 数据库连接驱动',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据源配置';

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `itemName` varchar(128) NOT NULL COMMENT '字典类型的中文名称:同种类型下唯一性',
  `itemCode` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '字典类型的编码:同种类型下唯一性',
  `sequence` int(11) NOT NULL COMMENT '排顺字段：越大越靠前',
  `parentId` int(11) NOT NULL,
  `isEnabled` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否有效;-1:删除;0:不可用,默认值;1:可用',
  `allowEdit` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否允许编辑;1:允许,默认值;0:不允许',
  `allowDelete` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否允许删除;1:允许删除,默认值,0:不允许删除',
  `create` int(11) NOT NULL COMMENT ' 最初创建者',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '记录创建时间',
  `modifier` int(11) NOT NULL COMMENT '最后修改人',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '数据最后更新时间',
  `remark` text,
  PRIMARY KEY (`id`),
  UNIQUE KEY `itemCode` (`itemCode`,`isEnabled`) USING BTREE,
  KEY `isEnabled` (`isEnabled`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据字典';

-- ----------------------------
-- Table structure for sys_dict_area
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_area`;
CREATE TABLE `sys_dict_area` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `areaName` varchar(255) DEFAULT NULL COMMENT '地区名',
  `parentID` int(11) DEFAULT NULL COMMENT '父节点ID',
  `	

isLastNode` tinyint(2) DEFAULT '1' COMMENT '是否是最后一个节点(1:true;0:false)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='地区表';

-- ----------------------------
-- Table structure for sys_dict_china_city
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_china_city`;
CREATE TABLE `sys_dict_china_city` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `areaCode` varchar(20) NOT NULL COMMENT '行政区划代码',
  `areaName` varchar(50) NOT NULL COMMENT '所辖行政区',
  `areaType` varchar(10) DEFAULT NULL COMMENT '城乡分类',
  `parentId` int(11) NOT NULL COMMENT '父ID',
  `areaLevel` int(11) DEFAULT NULL COMMENT '行政区级别(类型   0：国家    1：省、直辖市   2：市  3：区   4：街道 、办事处  5：村委会、村)',
  `locationCode` varchar(20) DEFAULT NULL COMMENT '地理位置(省级行政区划分)',
  `economyCode` varchar(20) DEFAULT NULL COMMENT '经济位置(省级行政区划分)',
  `shortName` varchar(50) DEFAULT NULL COMMENT '简称',
  `areaUrl` varchar(258) DEFAULT NULL COMMENT '点击访问地址',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `areaCode` (`areaCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='行政区域：省-市-县-镇-村';

-- ----------------------------
-- Table structure for sys_icon
-- ----------------------------
DROP TABLE IF EXISTS `sys_icon`;
CREATE TABLE `sys_icon` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `displayName` varchar(255) NOT NULL COMMENT '显示名称',
  `className` varchar(255) NOT NULL COMMENT 'class 名字',
  `sourceType` varchar(255) NOT NULL COMMENT '来源',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='图标资源';

-- ----------------------------
-- Table structure for sys_log_login
-- ----------------------------
DROP TABLE IF EXISTS `sys_log_login`;
CREATE TABLE `sys_log_login` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userCode` int(11) NOT NULL COMMENT '登录人',
  `ipAddress` varchar(20) NOT NULL COMMENT '登录的IP地址',
  `loginStatus` tinyint(2) NOT NULL DEFAULT '1' COMMENT '登录是否成功',
  `browser` varchar(255) DEFAULT NULL COMMENT '登录浏览器',
  `logLeavel` int(11) DEFAULT NULL COMMENT '日志级别',
  `opType` int(11) DEFAULT NULL COMMENT '日志类型',
  `logContent` text COMMENT '日志内容',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '记录产生时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='登录历史日志表';

-- ----------------------------
-- Table structure for sys_log_operate
-- ----------------------------
DROP TABLE IF EXISTS `sys_log_operate`;
CREATE TABLE `sys_log_operate` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号(主键)',
  `userCode` int(11) NOT NULL COMMENT '操作人的信息',
  `macAddress` varchar(100) DEFAULT NULL COMMENT 'Mac地址',
  `opType` int(11) NOT NULL COMMENT '日志描述类型(操作代码)',
  `broswer` varchar(128) DEFAULT NULL COMMENT '浏览器',
  `tableName` varchar(100) DEFAULT NULL COMMENT '日志操作表',
  `content` varchar(200) DEFAULT NULL COMMENT '操作日日志内容操作了什么内容，越具体越好（修改前、修改后）',
  `remark` text COMMENT '备注信息，一些其他的需要说明的信息',
  `createTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '日志记录创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='操作日志表';

-- ----------------------------
-- Table structure for sys_member_info
-- ----------------------------
DROP TABLE IF EXISTS `sys_member_info`;
CREATE TABLE `sys_member_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT '登录名，用户名',
  `userPassword` varchar(100) NOT NULL COMMENT '用户密码',
  `userpic` varchar(50) DEFAULT NULL COMMENT '头像',
  `realname` varchar(50) DEFAULT NULL COMMENT '姓名',
  `mobile` varchar(255) DEFAULT NULL COMMENT '手机号',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `nickname` varchar(50) DEFAULT NULL COMMENT '呢称',
  `birthday` date DEFAULT NULL COMMENT '出生日期',
  `userSex` tinyint(2) DEFAULT NULL COMMENT '性别',
  `userFrom` varchar(50) NOT NULL COMMENT '用户来源',
  `firstVisit` datetime NOT NULL COMMENT '第一次访问时间(注册时间)',
  `previousVisit` datetime NOT NULL COMMENT '上一次访问时间',
  `lastVisit` datetime DEFAULT NULL COMMENT '最后访问时间',
  `logOnCount` int(11) DEFAULT NULL COMMENT '登录次数',
  `passwordErrorCount` int(11) DEFAULT NULL COMMENT '密码连续错误次数',
  `ipAddress` varchar(50) DEFAULT NULL COMMENT '登录IP地址',
  `mACAddress` varchar(50) DEFAULT NULL,
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `homeAddress` varchar(255) DEFAULT NULL COMMENT '家庭住址',
  `signature` varchar(255) DEFAULT NULL COMMENT '个性签名',
  `auditStatus` tinyint(2) DEFAULT NULL COMMENT '审核状态(1为已审核，0为未审核)',
  `isLockedOut` tinyint(2) NOT NULL DEFAULT '1' COMMENT '是否锁定(1:false:0:true)',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `isEnabled` tinyint(2) NOT NULL DEFAULT '1' COMMENT '有效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统用户表';

-- ----------------------------
-- Table structure for sys_proxy_info
-- ----------------------------
DROP TABLE IF EXISTS `sys_proxy_info`;
CREATE TABLE `sys_proxy_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(128) DEFAULT NULL COMMENT '地址',
  `status` tinyint(2) NOT NULL DEFAULT '0' COMMENT '是否可用',
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `url` (`url`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='代理IP网站';

-- ----------------------------
-- Table structure for sys_sysconfig
-- ----------------------------
DROP TABLE IF EXISTS `sys_sysconfig`;
CREATE TABLE `sys_sysconfig` (
  `name` varchar(255) NOT NULL,
  `value` varchar(255) DEFAULT NULL,
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统配置表';

-- ----------------------------
-- Table structure for sys_version
-- ----------------------------
DROP TABLE IF EXISTS `sys_version`;
CREATE TABLE `sys_version` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `versionName` varchar(50) DEFAULT NULL COMMENT '版本名称',
  `versionCode` varchar(30) DEFAULT NULL COMMENT '版本编码',
  `loginPage` varchar(255) DEFAULT NULL COMMENT '登陆入口页面',
  `versionnum` varchar(20) DEFAULT NULL COMMENT '版本号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='版本';
