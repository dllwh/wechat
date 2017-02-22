/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50622
Source Host           : localhost:3306
Source Database       : db_rolemanagement

Target Server Type    : MYSQL
Target Server Version : 50622
File Encoding         : 65001

Date: 2015-10-29 11:00:28
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for administrative
-- ----------------------------
DROP TABLE IF EXISTS `administrative`;
CREATE TABLE `administrative` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL COMMENT '俗称',
  `title` varchar(255) DEFAULT NULL COMMENT '官方名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of administrative
-- ----------------------------
INSERT INTO `administrative` VALUES ('1', '一级行政区', '省级行政区');
INSERT INTO `administrative` VALUES ('2', '二级行政区', '地级行政区');
INSERT INTO `administrative` VALUES ('3', '三级行政区', '县级行政区');
INSERT INTO `administrative` VALUES ('4', '四级行政区', '乡级行政区');
INSERT INTO `administrative` VALUES ('5', '五级行政区', '村级行政区');
INSERT INTO `administrative` VALUES ('6', '六级行政区', '组级行政区');

-- ----------------------------
-- Table structure for area_geography
-- ----------------------------
DROP TABLE IF EXISTS `area_geography`;
CREATE TABLE `area_geography` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `areanName` varchar(255) NOT NULL COMMENT '地理区域名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='中国行政地理区域';

-- ----------------------------
-- Records of area_geography
-- ----------------------------
INSERT INTO `area_geography` VALUES ('1', '华北地区');
INSERT INTO `area_geography` VALUES ('2', '东北地区');
INSERT INTO `area_geography` VALUES ('3', '华东地区');
INSERT INTO `area_geography` VALUES ('4', '华中地区');
INSERT INTO `area_geography` VALUES ('5', '华南地区');
INSERT INTO `area_geography` VALUES ('6', '西南地区');
INSERT INTO `area_geography` VALUES ('7', '西北地区');
INSERT INTO `area_geography` VALUES ('8', '港澳台地区');

-- ----------------------------
-- Table structure for concept
-- ----------------------------
DROP TABLE IF EXISTS `concept`;
CREATE TABLE `concept` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tree_id` int(11) NOT NULL DEFAULT '1' COMMENT '本体树ID',
  `concept_id` varchar(255) NOT NULL DEFAULT '0' COMMENT '概念ID',
  `concept_parent_id` varchar(255) NOT NULL DEFAULT '0' COMMENT '概念父节点ID',
  `concept_name` varchar(255) NOT NULL DEFAULT '' COMMENT '概念名称',
  `concept_level` int(11) NOT NULL DEFAULT '0' COMMENT '级别',
  `concept_error` int(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `concept_name` (`concept_name`) USING BTREE,
  UNIQUE KEY `tree_id` (`tree_id`,`concept_id`,`concept_parent_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=13814 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of concept
-- ----------------------------
INSERT INTO `concept` VALUES ('130', '1', '0', '-1', 'Dinfo概念', '-1', '0');
INSERT INTO `concept` VALUES ('183', '1', '29', '0', '布置类', '0', '0');
INSERT INTO `concept` VALUES ('238', '1', '29_001', '29', '布置合理', '1', '0');
INSERT INTO `concept` VALUES ('239', '1', '29_002', '29', '一般', '1', '0');
INSERT INTO `concept` VALUES ('240', '1', '30', '0', '员工素质', '0', '0');
INSERT INTO `concept` VALUES ('243', '1', '30_003', '30', '专业', '1', '0');
INSERT INTO `concept` VALUES ('244', '1', '30_004', '30', '不专业', '1', '0');
INSERT INTO `concept` VALUES ('245', '1', '30_005', '30', '素质差', '1', '0');
INSERT INTO `concept` VALUES ('247', '1', '31', '0', '产品介绍', '0', '0');
INSERT INTO `concept` VALUES ('248', '1', '31_001', '31', '不多', '1', '0');
INSERT INTO `concept` VALUES ('251', '1', '31_003', '31', '实事求是', '1', '0');
INSERT INTO `concept` VALUES ('253', '1', '31_005', '31', '夸大其词', '1', '0');
INSERT INTO `concept` VALUES ('254', '1', '30_008', '30', '误导', '1', '0');
INSERT INTO `concept` VALUES ('255', '1', '32', '0', '服务速度', '0', '0');
INSERT INTO `concept` VALUES ('262', '1', '33', '0', '服务规范', '0', '0');
INSERT INTO `concept` VALUES ('264', '1', '33_002', '33', '位置', '1', '0');
INSERT INTO `concept` VALUES ('265', '1', '33_003', '33', '搬运', '1', '0');
INSERT INTO `concept` VALUES ('267', '1', '33_005', '33', '送货', '1', '0');
INSERT INTO `concept` VALUES ('269', '1', '33_007', '33', '开箱验机', '1', '0');
INSERT INTO `concept` VALUES ('271', '1', '33_009', '33', '要求', '1', '0');
INSERT INTO `concept` VALUES ('272', '1', '34', '0', '服务技能', '0', '0');
INSERT INTO `concept` VALUES ('275', '1', '34_001', '34', '到位', '1', '0');
INSERT INTO `concept` VALUES ('276', '1', '35', '0', '讲解指导', '0', '0');
INSERT INTO `concept` VALUES ('277', '1', '35_001', '35', '讲解', '1', '0');
INSERT INTO `concept` VALUES ('281', '1', '33_010', '33', '清理', '1', '0');
INSERT INTO `concept` VALUES ('282', '1', '33_011', '33', '未清理', '1', '0');
INSERT INTO `concept` VALUES ('285', '1', '36_001', '36', '简单易懂', '1', '0');
INSERT INTO `concept` VALUES ('286', '1', '36_002', '36', '便捷性', '1', '0');
INSERT INTO `concept` VALUES ('288', '1', '36_004', '36', '不清晰', '1', '0');
INSERT INTO `concept` VALUES ('289', '1', '36_005', '36', '是否人性化', '1', '0');
INSERT INTO `concept` VALUES ('291', '1', '36_007', '36', '复杂难懂', '1', '0');
INSERT INTO `concept` VALUES ('292', '1', '36_008', '36', '不方便 ', '1', '0');
INSERT INTO `concept` VALUES ('293', '1', '37', '0', '产品质量', '0', '0');
INSERT INTO `concept` VALUES ('294', '1', '37_001', '37', '稳定可靠', '1', '0');
INSERT INTO `concept` VALUES ('295', '1', '38', '0', '使用效果', '0', '0');
INSERT INTO `concept` VALUES ('297', '1', '38_002', '38', '好', '1', '0');
INSERT INTO `concept` VALUES ('303', '1', '39', '0', '耗能', '0', '0');
INSERT INTO `concept` VALUES ('304', '1', '39_001', '39', '节能', '1', '0');
INSERT INTO `concept` VALUES ('305', '1', '40', '0', '安全性', '0', '0');
INSERT INTO `concept` VALUES ('306', '1', '40_001', '40', '安全', '1', '0');
INSERT INTO `concept` VALUES ('308', '1', '39_003', '39', '能耗高', '1', '0');
INSERT INTO `concept` VALUES ('309', '1', '41', '0', '换货 ', '0', '0');
INSERT INTO `concept` VALUES ('310', '1', '41_001', '41', '考虑', '1', '0');
INSERT INTO `concept` VALUES ('311', '1', '42', '0', '拨通难易', '0', '0');
INSERT INTO `concept` VALUES ('312', '1', '42_001', '42', '一拨就通', '1', '0');
INSERT INTO `concept` VALUES ('314', '1', '42_003', '42', '不容易打通', '1', '0');
INSERT INTO `concept` VALUES ('317', '1', '43', '0', '态度', '0', '0');
INSERT INTO `concept` VALUES ('319', '1', '43_001', '43', '冷淡', '1', '0');
INSERT INTO `concept` VALUES ('320', '1', '43_002', '43', '热情', '1', '0');
INSERT INTO `concept` VALUES ('324', '1', '44', '0', '预约合理性', '0', '0');
INSERT INTO `concept` VALUES ('325', '1', '44_001', '44', '不合理 ', '1', '0');
INSERT INTO `concept` VALUES ('326', '1', '44_002', '44', '合理 ', '1', '0');
INSERT INTO `concept` VALUES ('329', '1', '45', '0', '准确性', '0', '0');
INSERT INTO `concept` VALUES ('330', '1', '45_001', '45', '准确', '1', '0');
INSERT INTO `concept` VALUES ('331', '1', '45_002', '45', '不准确', '1', '0');
INSERT INTO `concept` VALUES ('337', '1', '32_012', '32', '与用户改约', '1', '0');
INSERT INTO `concept` VALUES ('341', '1', '43_006', '43', '耐心', '1', '0');
INSERT INTO `concept` VALUES ('353', '1', '34_008', '34', '较低', '1', '0');
INSERT INTO `concept` VALUES ('355', '1', '34_010', '34', '时间长', '1', '0');
INSERT INTO `concept` VALUES ('362', '1', '34_017', '34', '用户优惠', '1', '0');
INSERT INTO `concept` VALUES ('363', '1', '46', '0', '优惠活动', '0', '0');
INSERT INTO `concept` VALUES ('365', '1', '46_002', '46', '会员活动', '1', '0');
INSERT INTO `concept` VALUES ('366', '1', '47', '0', '关怀度', '0', '0');
INSERT INTO `concept` VALUES ('369', '1', '47_003', '47', '关心 ', '1', '0');
INSERT INTO `concept` VALUES ('370', '1', '47_004', '47', '关注', '1', '0');
INSERT INTO `concept` VALUES ('373', '1', '47_007', '47', '无人问津', '1', '0');
INSERT INTO `concept` VALUES ('374', '1', '48', '0', '产品保养', '0', '0');
INSERT INTO `concept` VALUES ('375', '1', '48_001', '48', '是否保养', '1', '0');
INSERT INTO `concept` VALUES ('376', '1', '49', '0', '购买', '0', '0');
INSERT INTO `concept` VALUES ('381', '1', '49_005', '49', '是否会选择', '1', '0');
INSERT INTO `concept` VALUES ('387', '1', '51', '0', '推荐', '0', '0');
INSERT INTO `concept` VALUES ('388', '1', '51_001', '51', '会推荐', '1', '0');
INSERT INTO `concept` VALUES ('391', '1', '51_004', '51', '不一定', '1', '0');
INSERT INTO `concept` VALUES ('395', '1', '37_006', '37', '满意', '1', '0');
INSERT INTO `concept` VALUES ('396', '1', '37_007', '37', '不满意', '1', '0');
INSERT INTO `concept` VALUES ('408', '1', '32_015', '32', '按照', '1', '0');
INSERT INTO `concept` VALUES ('409', '1', '32_016', '32', '约定', '1', '0');
INSERT INTO `concept` VALUES ('414', '1', '36_009', '36', '操作', '1', '0');
INSERT INTO `concept` VALUES ('415', '1', '36_010', '36', '加强', '1', '0');
INSERT INTO `concept` VALUES ('420', '1', '37_009', '37', '反复维修', '1', '0');
INSERT INTO `concept` VALUES ('421', '1', '41_002', '41', '退换货', '1', '0');
INSERT INTO `concept` VALUES ('424', '1', '32_019', '32', '时间是否一致', '1', '0');
INSERT INTO `concept` VALUES ('425', '1', '32_020', '32', '未按要求', '1', '0');
INSERT INTO `concept` VALUES ('429', '1', '33_014', '33', '规范', '1', '0');
INSERT INTO `concept` VALUES ('430', '1', '43_013', '43', '言辞不当', '1', '0');
INSERT INTO `concept` VALUES ('431', '1', '30_014', '30', '差', '1', '0');
INSERT INTO `concept` VALUES ('433', '1', '34_020', '34', '不能正常使用', '1', '0');
INSERT INTO `concept` VALUES ('434', '1', '45_003', '45', '确保', '1', '0');
INSERT INTO `concept` VALUES ('435', '1', '34_021', '34', '修复', '1', '0');
INSERT INTO `concept` VALUES ('436', '1', '47_009', '47', '能够', '1', '0');
INSERT INTO `concept` VALUES ('437', '1', '47_010', '47', '接到', '1', '0');
INSERT INTO `concept` VALUES ('438', '1', '46_003', '46', '优惠推介', '1', '0');
INSERT INTO `concept` VALUES ('439', '1', '47_011', '47', '感到', '1', '0');
INSERT INTO `concept` VALUES ('441', '1', '49_006', '49', '购买产品', '1', '0');
INSERT INTO `concept` VALUES ('442', '1', '47_013', '47', '用不上', '1', '0');
INSERT INTO `concept` VALUES ('443', '1', '47_014', '47', '是否需要联系', '1', '0');
INSERT INTO `concept` VALUES ('448', '1', '40_002', '40', '不安全', '1', '0');
INSERT INTO `concept` VALUES ('449', '1', '29_003', '29', '环境一般', '1', '0');
INSERT INTO `concept` VALUES ('451', '1', '30_017', '30', '提高', '1', '0');
INSERT INTO `concept` VALUES ('452', '1', '30_018', '30', '冲撞', '1', '0');
INSERT INTO `concept` VALUES ('453', '1', '51_007', '51', '都不', '1', '0');
INSERT INTO `concept` VALUES ('455', '1', '32_021', '32', '时间', '1', '0');
INSERT INTO `concept` VALUES ('456', '1', '32_022', '32', '是否按时上门', '1', '0');
INSERT INTO `concept` VALUES ('457', '1', '32_023', '32', '及时性', '1', '0');
INSERT INTO `concept` VALUES ('458', '1', '32_024', '32', '响应速度', '1', '0');
INSERT INTO `concept` VALUES ('460', '1', '34_022', '34', '不熟练', '1', '0');
INSERT INTO `concept` VALUES ('461', '1', '38_003', '38', '不好', '1', '0');
INSERT INTO `concept` VALUES ('462', '1', '34_023', '34', '培训', '1', '0');
INSERT INTO `concept` VALUES ('463', '1', '34_024', '34', '会', '1', '0');
INSERT INTO `concept` VALUES ('464', '1', '34_025', '34', '不会', '1', '0');
INSERT INTO `concept` VALUES ('465', '1', '33_015', '33', '价格', '1', '0');
INSERT INTO `concept` VALUES ('466', '1', '33_016', '33', '定期', '1', '0');
INSERT INTO `concept` VALUES ('469', '1', '36_013', '36', '净化', '1', '0');
INSERT INTO `concept` VALUES ('470', '1', '38_004', '38', '有噪音', '1', '0');
INSERT INTO `concept` VALUES ('471', '1', '34_026', '34', '熟练', '1', '0');
INSERT INTO `concept` VALUES ('472', '1', '51_008', '51', '推出', '1', '0');
INSERT INTO `concept` VALUES ('473', '1', '39_004', '39', '损耗高低性', '1', '0');
INSERT INTO `concept` VALUES ('474', '1', '31_006', '31', '容积大', '1', '0');
INSERT INTO `concept` VALUES ('475', '1', '37_010', '37', '性能高', '1', '0');
INSERT INTO `concept` VALUES ('476', '1', '52', '0', '整体评价', '0', '0');
INSERT INTO `concept` VALUES ('477', '1', '52_001', '52', '好评', '1', '0');
INSERT INTO `concept` VALUES ('478', '1', '52_002', '52', '中评', '1', '0');
INSERT INTO `concept` VALUES ('479', '1', '52_003', '52', '差评', '1', '0');
INSERT INTO `concept` VALUES ('480', '1', '37_011', '37', '性能低', '1', '0');
INSERT INTO `concept` VALUES ('481', '1', '31_007', '31', '容积小', '1', '0');
INSERT INTO `concept` VALUES ('482', '1', '31_008', '31', '多', '1', '0');
INSERT INTO `concept` VALUES ('483', '1', '43_014', '43', '责任', '1', '0');
INSERT INTO `concept` VALUES ('484', '1', '36', '0', '产品便利性', '0', '0');
INSERT INTO `concept` VALUES ('584', '1', '34_027', '34', '正常使用', '1', '0');
INSERT INTO `concept` VALUES ('585', '1', '38_005', '38', '无噪音', '1', '0');
INSERT INTO `concept` VALUES ('13772', '2', '0', '-1', '导入单级节点', '-1', '0');
INSERT INTO `concept` VALUES ('13783', '3', '0', '-1', '异步表达式', '-1', '0');
INSERT INTO `concept` VALUES ('13785', '4', '0', '-1', '导入节点数据', '-1', '0');
INSERT INTO `concept` VALUES ('13786', '4', '10', '0', '惨绝人寰', '0', '0');
INSERT INTO `concept` VALUES ('13787', '4', '11', '0', '惨无人道', '0', '0');
INSERT INTO `concept` VALUES ('13788', '4', '12', '0', '豺狼成性', '0', '0');
INSERT INTO `concept` VALUES ('13789', '4', '13', '0', '恶贯满盈', '0', '0');
INSERT INTO `concept` VALUES ('13790', '4', '14', '0', '赶尽杀绝', '0', '0');
INSERT INTO `concept` VALUES ('13791', '4', '15', '0', '狼心狗肺', '0', '0');
INSERT INTO `concept` VALUES ('13792', '4', '16', '0', '灭绝人性', '0', '0');
INSERT INTO `concept` VALUES ('13793', '4', '17', '0', '人面兽心', '0', '0');
INSERT INTO `concept` VALUES ('13794', '4', '18', '0', '如狼似虎', '0', '0');
INSERT INTO `concept` VALUES ('13795', '4', '19', '0', '丧尽天良', '0', '0');
INSERT INTO `concept` VALUES ('13796', '4', '20', '0', '丧心病狂', '0', '0');
INSERT INTO `concept` VALUES ('13797', '4', '21', '0', '杀人不见血', '0', '0');
INSERT INTO `concept` VALUES ('13798', '4', '22', '0', '杀人不眨眼', '0', '0');
INSERT INTO `concept` VALUES ('13799', '4', '23', '0', '杀人如麻', '0', '0');
INSERT INTO `concept` VALUES ('13800', '4', '24', '0', '伤天害理', '0', '0');
INSERT INTO `concept` VALUES ('13801', '4', '25', '0', '嗜杀成性', '0', '0');
INSERT INTO `concept` VALUES ('13802', '4', '26', '0', '死有余辜', '0', '0');
INSERT INTO `concept` VALUES ('13803', '4', '27', '0', '五毒俱全', '0', '0');
INSERT INTO `concept` VALUES ('13804', '4', '28', '0', '一落千丈', '0', '0');
INSERT INTO `concept` VALUES ('13805', '4', '29', '0', '遗臭万年', '0', '0');
INSERT INTO `concept` VALUES ('13806', '4', '30', '0', '愚不可及', '0', '0');
INSERT INTO `concept` VALUES ('13807', '4', '31', '0', '罪不容诛', '0', '0');
INSERT INTO `concept` VALUES ('13808', '4', '32', '0', '罪大恶极', '0', '0');
INSERT INTO `concept` VALUES ('13809', '4', '33', '0', '罪恶滔天', '0', '0');
INSERT INTO `concept` VALUES ('13810', '4', '34', '0', '罪恶昭著', '0', '0');
INSERT INTO `concept` VALUES ('13811', '4', '35', '0', '罪该万死', '0', '0');
INSERT INTO `concept` VALUES ('13812', '4', '36', '0', '罪孽深重', '0', '0');
INSERT INTO `concept` VALUES ('13813', '4', '37', '0', '作恶多端', '0', '0');

-- ----------------------------
-- Table structure for concept_resource
-- ----------------------------
DROP TABLE IF EXISTS `concept_resource`;
CREATE TABLE `concept_resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tree_id` int(11) NOT NULL DEFAULT '0' COMMENT '本体树ID',
  `concept_id` varchar(255) NOT NULL DEFAULT '0' COMMENT '概念ID',
  `resource` varchar(255) NOT NULL DEFAULT '0' COMMENT '概念父节点ID',
  `type` int(11) NOT NULL DEFAULT '0' COMMENT '级别  0 代表一般的资源， 1 代表正则表达式',
  `langue_type` int(11) NOT NULL COMMENT '语言类型，0=中文简体，1=中文繁体，2=英文',
  PRIMARY KEY (`id`),
  UNIQUE KEY `tree_id` (`tree_id`,`concept_id`,`resource`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=532 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of concept_resource
-- ----------------------------
INSERT INTO `concept_resource` VALUES ('1', '1', '29_001', '布置.*合理', '1', '1');
INSERT INTO `concept_resource` VALUES ('4', '1', '29_003', '环境.*一般', '1', '1');
INSERT INTO `concept_resource` VALUES ('7', '1', '29_002', '一般', '0', '1');
INSERT INTO `concept_resource` VALUES ('10', '1', '30_003', '专业', '0', '1');
INSERT INTO `concept_resource` VALUES ('13', '1', '30_004', '不专业', '0', '1');
INSERT INTO `concept_resource` VALUES ('16', '1', '30_005', '素质差', '0', '1');
INSERT INTO `concept_resource` VALUES ('19', '1', '30_008', '误导', '0', '1');
INSERT INTO `concept_resource` VALUES ('28', '1', '30_003', '特别专业', '0', '1');
INSERT INTO `concept_resource` VALUES ('34', '1', '30_004', '特别不专业', '0', '1');
INSERT INTO `concept_resource` VALUES ('37', '1', '30_004', '没有特别专业', '0', '1');
INSERT INTO `concept_resource` VALUES ('43', '1', '30_017', '提高', '0', '1');
INSERT INTO `concept_resource` VALUES ('52', '1', '30_018', '冲撞', '0', '1');
INSERT INTO `concept_resource` VALUES ('55', '1', '30_014', '差', '0', '1');
INSERT INTO `concept_resource` VALUES ('58', '1', '30_004', '不是很专业', '0', '1');
INSERT INTO `concept_resource` VALUES ('61', '1', '31_001', '不多', '0', '1');
INSERT INTO `concept_resource` VALUES ('64', '1', '31_003', '实事求是', '0', '1');
INSERT INTO `concept_resource` VALUES ('73', '1', '51_001', '会.*推荐', '1', '1');
INSERT INTO `concept_resource` VALUES ('76', '1', '51_004', '不一定.*推荐', '1', '1');
INSERT INTO `concept_resource` VALUES ('82', '1', '51_007', '都不选', '0', '1');
INSERT INTO `concept_resource` VALUES ('85', '1', '32_021', '按约定.*时间', '1', '1');
INSERT INTO `concept_resource` VALUES ('88', '1', '32_022', '按时.*上门', '1', '1');
INSERT INTO `concept_resource` VALUES ('91', '1', '32_023', '未准时', '0', '1');
INSERT INTO `concept_resource` VALUES ('94', '1', '32_023', '催促.*到达', '1', '1');
INSERT INTO `concept_resource` VALUES ('97', '1', '32_023', '未在要求时间内', '0', '1');
INSERT INTO `concept_resource` VALUES ('100', '1', '32_024', '响应速度.*很快', '1', '1');
INSERT INTO `concept_resource` VALUES ('103', '1', '32_020', '未按要求', '0', '1');
INSERT INTO `concept_resource` VALUES ('106', '1', '32_020', '不按要求', '0', '1');
INSERT INTO `concept_resource` VALUES ('109', '1', '32_019', '短期', '0', '1');
INSERT INTO `concept_resource` VALUES ('112', '1', '32_019', '时间一致', '0', '1');
INSERT INTO `concept_resource` VALUES ('115', '1', '32_015', '按照', '0', '1');
INSERT INTO `concept_resource` VALUES ('118', '1', '32_019', '提前', '0', '1');
INSERT INTO `concept_resource` VALUES ('122', '1', '32_016', '约定', '0', '2');
INSERT INTO `concept_resource` VALUES ('123', '1', '32_016', '指定', '0', '2');
INSERT INTO `concept_resource` VALUES ('124', '1', '32_012', '与用户改约', '0', '2');
INSERT INTO `concept_resource` VALUES ('125', '1', '33_014', '规范', '0', '2');
INSERT INTO `concept_resource` VALUES ('126', '1', '33_014', '过程规范', '0', '2');
INSERT INTO `concept_resource` VALUES ('127', '1', '33_002', '指定位置', '0', '2');
INSERT INTO `concept_resource` VALUES ('128', '1', '33_002', '规定位置', '0', '2');
INSERT INTO `concept_resource` VALUES ('129', '1', '33_003', '搬运', '0', '2');
INSERT INTO `concept_resource` VALUES ('130', '1', '33_010', '清理现场', '0', '2');
INSERT INTO `concept_resource` VALUES ('131', '1', '33_005', '未送货', '0', '2');
INSERT INTO `concept_resource` VALUES ('132', '1', '33_005', '送货', '0', '2');
INSERT INTO `concept_resource` VALUES ('133', '1', '33_007', '开箱验机', '0', '2');
INSERT INTO `concept_resource` VALUES ('134', '1', '33_007', '主动开箱验机', '0', '2');
INSERT INTO `concept_resource` VALUES ('135', '1', '33_009', '要求', '0', '2');
INSERT INTO `concept_resource` VALUES ('136', '1', '33_011', '未清理', '0', '2');
INSERT INTO `concept_resource` VALUES ('137', '1', '33_010', '清理服务现场', '0', '2');
INSERT INTO `concept_resource` VALUES ('138', '1', '34_001', '一次到位', '0', '2');
INSERT INTO `concept_resource` VALUES ('139', '1', '34_001', '服务到位', '0', '2');
INSERT INTO `concept_resource` VALUES ('140', '1', '34_001', '安装到位', '0', '2');
INSERT INTO `concept_resource` VALUES ('141', '1', '34_021', '修复', '0', '2');
INSERT INTO `concept_resource` VALUES ('142', '1', '34_021', '一次修复', '0', '2');
INSERT INTO `concept_resource` VALUES ('143', '1', '34_022', '未能一次就好', '0', '2');
INSERT INTO `concept_resource` VALUES ('144', '1', '34_022', '不是很熟练', '0', '2');
INSERT INTO `concept_resource` VALUES ('146', '1', '34_010', '修复时间长', '0', '2');
INSERT INTO `concept_resource` VALUES ('147', '1', '34_010', '安装时间长', '0', '2');
INSERT INTO `concept_resource` VALUES ('148', '1', '34_008', '较低', '0', '2');
INSERT INTO `concept_resource` VALUES ('149', '1', '34_022', '不能一次判断出故障', '0', '2');
INSERT INTO `concept_resource` VALUES ('150', '1', '34_022', '无法一次判断出故障', '0', '2');
INSERT INTO `concept_resource` VALUES ('151', '1', '34_022', '不能判断出故障', '0', '2');
INSERT INTO `concept_resource` VALUES ('152', '1', '34_022', '修了多次', '0', '2');
INSERT INTO `concept_resource` VALUES ('153', '1', '34_020', '正常使用', '0', '2');
INSERT INTO `concept_resource` VALUES ('154', '1', '34_020', '不能正常使用', '0', '2');
INSERT INTO `concept_resource` VALUES ('155', '1', '34_020', '也不能正常使用', '0', '2');
INSERT INTO `concept_resource` VALUES ('156', '1', '34_022', '二次上门', '0', '2');
INSERT INTO `concept_resource` VALUES ('157', '1', '35_001', '讲解', '0', '2');
INSERT INTO `concept_resource` VALUES ('158', '1', '35_001', '主动讲解', '0', '2');
INSERT INTO `concept_resource` VALUES ('159', '1', '35_001', '未讲解', '0', '2');
INSERT INTO `concept_resource` VALUES ('160', '1', '36_001', '清晰简单', '0', '2');
INSERT INTO `concept_resource` VALUES ('161', '1', '36_001', '简单易懂', '0', '2');
INSERT INTO `concept_resource` VALUES ('162', '1', '36_002', '便捷性也一般', '0', '2');
INSERT INTO `concept_resource` VALUES ('163', '1', '36_001', '容易理解', '0', '2');
INSERT INTO `concept_resource` VALUES ('164', '1', '36_002', '便捷性一般', '0', '2');
INSERT INTO `concept_resource` VALUES ('165', '1', '36_004', '不清晰', '0', '2');
INSERT INTO `concept_resource` VALUES ('166', '1', '36_004', '不明了', '0', '2');
INSERT INTO `concept_resource` VALUES ('167', '1', '36_004', '不易懂', '0', '2');
INSERT INTO `concept_resource` VALUES ('168', '1', '36_005', '缺乏人性化', '0', '2');
INSERT INTO `concept_resource` VALUES ('169', '1', '36_005', '人性化', '0', '2');
INSERT INTO `concept_resource` VALUES ('172', '1', '36_008', '不方便', '0', '2');
INSERT INTO `concept_resource` VALUES ('173', '1', '36_008', '不是很方便', '0', '2');
INSERT INTO `concept_resource` VALUES ('174', '1', '36_008', '非常不方便', '0', '2');
INSERT INTO `concept_resource` VALUES ('175', '1', '36_009', '操作', '0', '2');
INSERT INTO `concept_resource` VALUES ('176', '1', '36_010', '需要加强', '0', '2');
INSERT INTO `concept_resource` VALUES ('177', '1', '37_001', '稳定可靠', '0', '2');
INSERT INTO `concept_resource` VALUES ('178', '1', '37_006', '满意', '0', '2');
INSERT INTO `concept_resource` VALUES ('179', '1', '37_006', '非常满意', '0', '2');
INSERT INTO `concept_resource` VALUES ('180', '1', '37_006', '非常好', '0', '2');
INSERT INTO `concept_resource` VALUES ('181', '1', '37_007', '非常不满意', '0', '2');
INSERT INTO `concept_resource` VALUES ('182', '1', '37_007', '不满意', '0', '2');
INSERT INTO `concept_resource` VALUES ('183', '1', '37_009', '维修', '0', '2');
INSERT INTO `concept_resource` VALUES ('184', '1', '37_009', '反复维修', '0', '2');
INSERT INTO `concept_resource` VALUES ('186', '1', '38_002', '非常好', '0', '2');
INSERT INTO `concept_resource` VALUES ('187', '1', '39_001', '节能', '0', '2');
INSERT INTO `concept_resource` VALUES ('188', '1', '39_001', '能耗小', '0', '2');
INSERT INTO `concept_resource` VALUES ('189', '1', '39_003', '能耗偏高', '0', '2');
INSERT INTO `concept_resource` VALUES ('190', '1', '39_003', '能耗高', '0', '2');
INSERT INTO `concept_resource` VALUES ('191', '1', '40_001', '安全', '0', '2');
INSERT INTO `concept_resource` VALUES ('192', '1', '40_002', '不安全', '0', '2');
INSERT INTO `concept_resource` VALUES ('193', '1', '40_002', '危险', '0', '2');
INSERT INTO `concept_resource` VALUES ('194', '1', '41_001', '考虑', '0', '2');
INSERT INTO `concept_resource` VALUES ('195', '1', '41_001', '打算', '0', '2');
INSERT INTO `concept_resource` VALUES ('196', '1', '41_002', '退换货', '0', '2');
INSERT INTO `concept_resource` VALUES ('197', '1', '41_002', '退货', '0', '2');
INSERT INTO `concept_resource` VALUES ('198', '1', '41_002', '换货', '0', '2');
INSERT INTO `concept_resource` VALUES ('199', '1', '42_001', '一拨就通', '0', '2');
INSERT INTO `concept_resource` VALUES ('200', '1', '42_001', '容易拨通', '0', '2');
INSERT INTO `concept_resource` VALUES ('201', '1', '42_003', '不容易打通', '0', '2');
INSERT INTO `concept_resource` VALUES ('202', '1', '42_003', '几次才接通', '0', '2');
INSERT INTO `concept_resource` VALUES ('203', '1', '42_003', '非常难打', '0', '2');
INSERT INTO `concept_resource` VALUES ('204', '1', '42_003', '几率很小', '0', '2');
INSERT INTO `concept_resource` VALUES ('205', '1', '43_001', '很冷淡', '0', '2');
INSERT INTO `concept_resource` VALUES ('206', '1', '43_001', '态度冷淡', '0', '2');
INSERT INTO `concept_resource` VALUES ('207', '1', '43_001', '态度一般', '0', '2');
INSERT INTO `concept_resource` VALUES ('208', '1', '43_001', '态度差', '0', '2');
INSERT INTO `concept_resource` VALUES ('212', '1', '43_001', '态度恶劣', '0', '2');
INSERT INTO `concept_resource` VALUES ('213', '1', '43_001', '说话生硬', '0', '2');
INSERT INTO `concept_resource` VALUES ('214', '1', '43_013', '没有言辞不当', '0', '2');
INSERT INTO `concept_resource` VALUES ('215', '1', '43_013', '言辞不当', '0', '2');
INSERT INTO `concept_resource` VALUES ('216', '1', '43_002', '热情', '0', '2');
INSERT INTO `concept_resource` VALUES ('217', '1', '43_002', '态度很好', '0', '2');
INSERT INTO `concept_resource` VALUES ('218', '1', '43_006', '耐心', '0', '2');
INSERT INTO `concept_resource` VALUES ('219', '1', '43_006', '很有耐心', '0', '2');
INSERT INTO `concept_resource` VALUES ('220', '1', '43_006', '有耐心', '0', '2');
INSERT INTO `concept_resource` VALUES ('221', '1', '43_002', '很热情', '0', '2');
INSERT INTO `concept_resource` VALUES ('223', '1', '43_002', '很不错', '0', '2');
INSERT INTO `concept_resource` VALUES ('224', '1', '43_002', '态度不错', '0', '2');
INSERT INTO `concept_resource` VALUES ('226', '1', '44_001', '不漂亮', '0', '2');
INSERT INTO `concept_resource` VALUES ('227', '1', '44_001', '不按要求', '0', '2');
INSERT INTO `concept_resource` VALUES ('228', '1', '44_002', '合理', '0', '2');
INSERT INTO `concept_resource` VALUES ('229', '1', '44_002', '比较合理', '0', '2');
INSERT INTO `concept_resource` VALUES ('230', '1', '44_001', '不安用户要求', '0', '2');
INSERT INTO `concept_resource` VALUES ('231', '1', '45_001', '准确', '0', '2');
INSERT INTO `concept_resource` VALUES ('232', '1', '45_001', '正确', '0', '2');
INSERT INTO `concept_resource` VALUES ('233', '1', '45_002', '不准确', '0', '2');
INSERT INTO `concept_resource` VALUES ('234', '1', '45_002', '不标准', '0', '2');
INSERT INTO `concept_resource` VALUES ('235', '1', '45_003', '确保', '0', '2');
INSERT INTO `concept_resource` VALUES ('236', '1', '45_003', '确定', '0', '2');
INSERT INTO `concept_resource` VALUES ('237', '1', '46_002', '会员活动', '0', '2');
INSERT INTO `concept_resource` VALUES ('238', '1', '46_003', '优惠推介信息', '0', '2');
INSERT INTO `concept_resource` VALUES ('239', '1', '46_003', '优惠推介', '0', '2');
INSERT INTO `concept_resource` VALUES ('240', '1', '47_004', '关注', '0', '2');
INSERT INTO `concept_resource` VALUES ('241', '1', '47_004', '注意|留意', '0', '2');
INSERT INTO `concept_resource` VALUES ('242', '1', '47_003', '关心', '0', '2');
INSERT INTO `concept_resource` VALUES ('243', '1', '47_003', '感受到关心', '0', '2');
INSERT INTO `concept_resource` VALUES ('244', '1', '47_003', '感受到.*关心', '1', '2');
INSERT INTO `concept_resource` VALUES ('245', '1', '47_004', '仍有人关注', '0', '2');
INSERT INTO `concept_resource` VALUES ('246', '1', '47_007', '无人问津', '0', '2');
INSERT INTO `concept_resource` VALUES ('247', '1', '47_007', '没有任何联系 ', '0', '2');
INSERT INTO `concept_resource` VALUES ('249', '1', '47_007', '没有后续联系沟通', '0', '2');
INSERT INTO `concept_resource` VALUES ('250', '1', '47_009', '能够', '0', '2');
INSERT INTO `concept_resource` VALUES ('251', '1', '47_009', '能够站在', '0', '2');
INSERT INTO `concept_resource` VALUES ('252', '1', '47_010', '会接到', '0', '2');
INSERT INTO `concept_resource` VALUES ('253', '1', '47_010', '经常接到', '0', '2');
INSERT INTO `concept_resource` VALUES ('254', '1', '47_011', '感到', '0', '2');
INSERT INTO `concept_resource` VALUES ('255', '1', '47_011', '感受到', '0', '2');
INSERT INTO `concept_resource` VALUES ('256', '1', '47_011', '感觉到', '0', '2');
INSERT INTO `concept_resource` VALUES ('257', '1', '47_013', '都用不上', '0', '2');
INSERT INTO `concept_resource` VALUES ('258', '1', '47_013', '用不上', '0', '2');
INSERT INTO `concept_resource` VALUES ('259', '1', '47_014', '有问题需要联系', '0', '2');
INSERT INTO `concept_resource` VALUES ('260', '1', '47_014', '需要联系', '0', '2');
INSERT INTO `concept_resource` VALUES ('261', '1', '48_001', '清洗保养', '0', '2');
INSERT INTO `concept_resource` VALUES ('263', '1', '49_006', '购买产品', '0', '2');
INSERT INTO `concept_resource` VALUES ('264', '1', '49_006', '还会', '0', '2');
INSERT INTO `concept_resource` VALUES ('265', '1', '49_006', '还选', '0', '2');
INSERT INTO `concept_resource` VALUES ('266', '1', '49_006', '首选', '0', '2');
INSERT INTO `concept_resource` VALUES ('267', '1', '49_005', '不会选择', '0', '2');
INSERT INTO `concept_resource` VALUES ('268', '1', '49_005', '会仔细考虑', '0', '2');
INSERT INTO `concept_resource` VALUES ('269', '1', '49_005', '不会购买', '0', '2');
INSERT INTO `concept_resource` VALUES ('270', '1', '49_005', '不会买', '0', '2');
INSERT INTO `concept_resource` VALUES ('271', '1', '37_006', '都满意', '0', '2');
INSERT INTO `concept_resource` VALUES ('272', '1', '37_007', '都不满意', '0', '2');
INSERT INTO `concept_resource` VALUES ('273', '1', '37_007', '很不满意', '0', '2');
INSERT INTO `concept_resource` VALUES ('274', '1', '29_002', '很一般', '0', '2');
INSERT INTO `concept_resource` VALUES ('275', '1', '29_002', '都很一般', '0', '2');
INSERT INTO `concept_resource` VALUES ('278', '1', '38_003', '不好|差', '0', '1');
INSERT INTO `concept_resource` VALUES ('282', '1', '36_001', '无需查阅', '0', '1');
INSERT INTO `concept_resource` VALUES ('283', '1', '36_009', '进行操作', '0', '1');
INSERT INTO `concept_resource` VALUES ('284', '1', '36_007', '复杂难懂', '0', '1');
INSERT INTO `concept_resource` VALUES ('285', '1', '34_021', '调试', '0', '1');
INSERT INTO `concept_resource` VALUES ('286', '1', '34_008', '较差', '0', '1');
INSERT INTO `concept_resource` VALUES ('287', '1', '37_007', '退机', '0', '1');
INSERT INTO `concept_resource` VALUES ('288', '1', '32_024', '提高', '0', '1');
INSERT INTO `concept_resource` VALUES ('289', '1', '32_024', '加强', '0', '1');
INSERT INTO `concept_resource` VALUES ('290', '1', '32_024', '增强', '0', '1');
INSERT INTO `concept_resource` VALUES ('291', '1', '43_001', '态度很差', '0', '1');
INSERT INTO `concept_resource` VALUES ('292', '1', '31_005', '夸大其词', '0', '1');
INSERT INTO `concept_resource` VALUES ('293', '1', '34_008', '极差', '0', '1');
INSERT INTO `concept_resource` VALUES ('294', '1', '34_022', '不太娴熟', '0', '1');
INSERT INTO `concept_resource` VALUES ('295', '1', '34_022', '不娴熟', '0', '1');
INSERT INTO `concept_resource` VALUES ('297', '1', '34_022', '不熟练', '0', '1');
INSERT INTO `concept_resource` VALUES ('299', '1', '38_003', '极差', '0', '1');
INSERT INTO `concept_resource` VALUES ('302', '1', '34_022', '有所欠缺', '0', '1');
INSERT INTO `concept_resource` VALUES ('305', '1', '34_023', '培训', '0', '1');
INSERT INTO `concept_resource` VALUES ('306', '1', '43_002', '态度认真', '0', '1');
INSERT INTO `concept_resource` VALUES ('307', '1', '36_002', '清楚', '0', '1');
INSERT INTO `concept_resource` VALUES ('309', '1', '34_024', '会安装', '0', '1');
INSERT INTO `concept_resource` VALUES ('310', '1', '34_025', '不会安装', '0', '1');
INSERT INTO `concept_resource` VALUES ('311', '1', '34_025', '不会', '0', '1');
INSERT INTO `concept_resource` VALUES ('312', '1', '34_017', '优惠', '0', '1');
INSERT INTO `concept_resource` VALUES ('313', '1', '33_015', '价格高', '0', '1');
INSERT INTO `concept_resource` VALUES ('314', '1', '33_015', '价钱', '0', '1');
INSERT INTO `concept_resource` VALUES ('317', '1', '33_016', '定期', '0', '1');
INSERT INTO `concept_resource` VALUES ('318', '1', '33_016', '定时', '0', '1');
INSERT INTO `concept_resource` VALUES ('319', '1', '33_016', '按时', '0', '1');
INSERT INTO `concept_resource` VALUES ('320', '1', '33_016', '定期提醒', '0', '1');
INSERT INTO `concept_resource` VALUES ('321', '1', '35_001', '无.*介绍', '1', '1');
INSERT INTO `concept_resource` VALUES ('322', '1', '36_008', '没有*.方便', '1', '1');
INSERT INTO `concept_resource` VALUES ('325', '1', '36_013', '净化', '0', '1');
INSERT INTO `concept_resource` VALUES ('327', '1', '33', '推介', '0', '1');
INSERT INTO `concept_resource` VALUES ('328', '1', '51_001', '产品.*推介', '1', '1');
INSERT INTO `concept_resource` VALUES ('329', '1', '37_006', '产品.*特别满意', '1', '1');
INSERT INTO `concept_resource` VALUES ('330', '1', '47_004', '对.*关注', '1', '1');
INSERT INTO `concept_resource` VALUES ('331', '1', '43_002', '很认真', '0', '1');
INSERT INTO `concept_resource` VALUES ('332', '1', '43_002', '感觉.*不错', '1', '1');
INSERT INTO `concept_resource` VALUES ('333', '1', '38_003', '未能.*好', '0', '1');
INSERT INTO `concept_resource` VALUES ('334', '1', '38_002', '相当好', '0', '1');
INSERT INTO `concept_resource` VALUES ('335', '1', '38_002', '.*好', '1', '1');
INSERT INTO `concept_resource` VALUES ('336', '1', '37_006', '100分', '1', '1');
INSERT INTO `concept_resource` VALUES ('337', '1', '43_002', '认真负责', '0', '1');
INSERT INTO `concept_resource` VALUES ('338', '1', '33_015', '价格低', '0', '1');
INSERT INTO `concept_resource` VALUES ('339', '1', '33_015', '贵', '0', '1');
INSERT INTO `concept_resource` VALUES ('340', '1', '33_015', '便宜', '0', '1');
INSERT INTO `concept_resource` VALUES ('341', '1', '33_015', '价钱低', '0', '1');
INSERT INTO `concept_resource` VALUES ('342', '1', '33_015', '价钱高', '0', '1');
INSERT INTO `concept_resource` VALUES ('343', '1', '33_015', '费用高', '0', '1');
INSERT INTO `concept_resource` VALUES ('344', '1', '33_015', '费用低', '0', '1');
INSERT INTO `concept_resource` VALUES ('346', '1', '34_024', '安装熟练', '0', '1');
INSERT INTO `concept_resource` VALUES ('347', '1', '34_021', '检测', '0', '1');
INSERT INTO `concept_resource` VALUES ('348', '1', '32_020', '未按照要求', '0', '1');
INSERT INTO `concept_resource` VALUES ('349', '1', '32_024', '响应速度快', '0', '1');
INSERT INTO `concept_resource` VALUES ('350', '1', '32_024', '响应速度慢', '0', '1');
INSERT INTO `concept_resource` VALUES ('351', '1', '32_024', '响应速度很慢', '0', '1');
INSERT INTO `concept_resource` VALUES ('352', '1', '45_001', '非常准确', '0', '1');
INSERT INTO `concept_resource` VALUES ('353', '1', '45_001', '很准确', '0', '1');
INSERT INTO `concept_resource` VALUES ('354', '1', '45_003', '保证', '0', '1');
INSERT INTO `concept_resource` VALUES ('355', '1', '46_003', '优惠活动', '0', '1');
INSERT INTO `concept_resource` VALUES ('356', '1', '47_009', '可以', '0', '1');
INSERT INTO `concept_resource` VALUES ('357', '1', '48_001', '不保养', '0', '1');
INSERT INTO `concept_resource` VALUES ('358', '1', '38_004', '噪音', '0', '1');
INSERT INTO `concept_resource` VALUES ('360', '1', '38_004', '噪音大', '0', '1');
INSERT INTO `concept_resource` VALUES ('361', '1', '38_004', '有点噪音', '0', '1');
INSERT INTO `concept_resource` VALUES ('362', '1', '43_001', '态度不好', '0', '1');
INSERT INTO `concept_resource` VALUES ('363', '1', '31_001', '储备不足', '0', '1');
INSERT INTO `concept_resource` VALUES ('364', '1', '31_001', '不足', '0', '1');
INSERT INTO `concept_resource` VALUES ('365', '1', '31_001', '不够用', '0', '1');
INSERT INTO `concept_resource` VALUES ('368', '1', '47', '维护关系', '0', '1');
INSERT INTO `concept_resource` VALUES ('369', '1', '49_005', '还选', '0', '1');
INSERT INTO `concept_resource` VALUES ('370', '1', '41_001', '会仔细考虑', '0', '1');
INSERT INTO `concept_resource` VALUES ('371', '1', '30_014', '极差', '0', '1');
INSERT INTO `concept_resource` VALUES ('372', '1', '30_005', '低俗', '0', '1');
INSERT INTO `concept_resource` VALUES ('373', '1', '34_022', '到位', '0', '1');
INSERT INTO `concept_resource` VALUES ('374', '1', '36_002', '使用方便', '0', '1');
INSERT INTO `concept_resource` VALUES ('375', '1', '36_010', '有待加强', '0', '1');
INSERT INTO `concept_resource` VALUES ('376', '1', '37_001', '可靠', '0', '1');
INSERT INTO `concept_resource` VALUES ('377', '1', '43_002', '还不错', '0', '1');
INSERT INTO `concept_resource` VALUES ('378', '1', '41_001', '考虑也周到', '0', '1');
INSERT INTO `concept_resource` VALUES ('380', '1', '32_022', '不及时', '0', '1');
INSERT INTO `concept_resource` VALUES ('381', '1', '32_022', '不能及时', '0', '1');
INSERT INTO `concept_resource` VALUES ('382', '1', '47_014', '没有任何联系', '0', '1');
INSERT INTO `concept_resource` VALUES ('383', '1', '47_014', '没有后续联系沟通', '0', '1');
INSERT INTO `concept_resource` VALUES ('386', '1', '29_001', '安排.*合理', '1', '1');
INSERT INTO `concept_resource` VALUES ('387', '1', '29_001', '摆放.*合理', '1', '1');
INSERT INTO `concept_resource` VALUES ('388', '1', '29_001', '布置.*漂亮', '1', '1');
INSERT INTO `concept_resource` VALUES ('389', '1', '29_001', '安排.*漂亮', '1', '1');
INSERT INTO `concept_resource` VALUES ('390', '1', '29_001', '摆放.*漂亮', '1', '1');
INSERT INTO `concept_resource` VALUES ('391', '1', '29_003', '环境.*差不多', '1', '1');
INSERT INTO `concept_resource` VALUES ('392', '1', '29_003', '环境.*还可以', '1', '1');
INSERT INTO `concept_resource` VALUES ('393', '1', '29_002', '差不多', '0', '1');
INSERT INTO `concept_resource` VALUES ('394', '1', '29_002', '还可以', '0', '1');
INSERT INTO `concept_resource` VALUES ('395', '1', '30_004', '不标准', '0', '1');
INSERT INTO `concept_resource` VALUES ('396', '1', '30_004', '非常不专业', '0', '1');
INSERT INTO `concept_resource` VALUES ('397', '1', '30_004', '很不专业', '0', '1');
INSERT INTO `concept_resource` VALUES ('398', '1', '30_005', '素质低', '0', '1');
INSERT INTO `concept_resource` VALUES ('399', '1', '30_005', '没文化', '0', '1');
INSERT INTO `concept_resource` VALUES ('400', '1', '30_008', '误解', '0', '1');
INSERT INTO `concept_resource` VALUES ('401', '1', '30_014', '烂', '0', '1');
INSERT INTO `concept_resource` VALUES ('402', '1', '30_014', '不好', '0', '1');
INSERT INTO `concept_resource` VALUES ('403', '1', '30_014', '太差', '0', '1');
INSERT INTO `concept_resource` VALUES ('404', '1', '30_014', '非常差', '0', '1');
INSERT INTO `concept_resource` VALUES ('405', '1', '30_017', '有待提高', '0', '1');
INSERT INTO `concept_resource` VALUES ('406', '1', '30_017', '有待增加', '0', '1');
INSERT INTO `concept_resource` VALUES ('407', '1', '30_017', '有待增长', '0', '1');
INSERT INTO `concept_resource` VALUES ('408', '1', '30_018', '顶撞', '0', '1');
INSERT INTO `concept_resource` VALUES ('409', '1', '30_018', '吵架', '0', '1');
INSERT INTO `concept_resource` VALUES ('410', '1', '30_018', '大骂', '0', '1');
INSERT INTO `concept_resource` VALUES ('411', '1', '31_001', '少', '0', '1');
INSERT INTO `concept_resource` VALUES ('412', '1', '31_001', '太少', '0', '1');
INSERT INTO `concept_resource` VALUES ('413', '1', '31_003', '按事实', '0', '1');
INSERT INTO `concept_resource` VALUES ('414', '1', '31_003', '不夸大其词', '0', '1');
INSERT INTO `concept_resource` VALUES ('415', '1', '32_015', '根据', '0', '1');
INSERT INTO `concept_resource` VALUES ('416', '1', '32_015', '遵循', '0', '1');
INSERT INTO `concept_resource` VALUES ('417', '1', '32_021', '未按约定.*时间', '0', '1');
INSERT INTO `concept_resource` VALUES ('418', '1', '32_022', '不按约定时间.*上门', '1', '1');
INSERT INTO `concept_resource` VALUES ('419', '1', '32_022', '未按照约定的时间.*上门', '1', '1');
INSERT INTO `concept_resource` VALUES ('420', '1', '32_022', '按改约时间.*上门', '1', '1');
INSERT INTO `concept_resource` VALUES ('421', '1', '32_023', '催促下.*到达', '1', '1');
INSERT INTO `concept_resource` VALUES ('422', '1', '32_023', '多次催促也未.*到达', '1', '1');
INSERT INTO `concept_resource` VALUES ('423', '1', '32_023', '按时.*到达', '1', '1');
INSERT INTO `concept_resource` VALUES ('424', '1', '32_023', '提前.*到达', '1', '1');
INSERT INTO `concept_resource` VALUES ('425', '1', '32_023', '准时', '0', '1');
INSERT INTO `concept_resource` VALUES ('426', '1', '32_024', '响应速度.*较快', '1', '1');
INSERT INTO `concept_resource` VALUES ('427', '1', '33_003', '协助搬运', '0', '1');
INSERT INTO `concept_resource` VALUES ('428', '1', '33_009', '规定', '0', '1');
INSERT INTO `concept_resource` VALUES ('429', '1', '33_011', '未打扫', '0', '1');
INSERT INTO `concept_resource` VALUES ('430', '1', '33_014', '标准', '0', '1');
INSERT INTO `concept_resource` VALUES ('431', '1', '37_006', '服务.*特别满意', '1', '1');
INSERT INTO `concept_resource` VALUES ('432', '1', '51_001', '也会.*推荐', '1', '1');
INSERT INTO `concept_resource` VALUES ('433', '1', '51_001', '主动.*推荐', '1', '1');
INSERT INTO `concept_resource` VALUES ('434', '1', '51_004', '无人.*推荐', '1', '1');
INSERT INTO `concept_resource` VALUES ('435', '1', '51_004', '不会.*推荐', '1', '1');
INSERT INTO `concept_resource` VALUES ('436', '1', '51_007', '都不买', '0', '1');
INSERT INTO `concept_resource` VALUES ('437', '1', '51_007', '都不推荐', '0', '1');
INSERT INTO `concept_resource` VALUES ('438', '1', '34_026', '快速', '0', '1');
INSERT INTO `concept_resource` VALUES ('439', '1', '34_026', '娴熟', '0', '1');
INSERT INTO `concept_resource` VALUES ('440', '1', '34_026', '熟练', '0', '1');
INSERT INTO `concept_resource` VALUES ('441', '1', '51_008', '推出', '0', '1');
INSERT INTO `concept_resource` VALUES ('442', '1', '51_008', '应该推出', '0', '1');
INSERT INTO `concept_resource` VALUES ('443', '1', '35_001', '讲明白', '0', '1');
INSERT INTO `concept_resource` VALUES ('444', '1', '30_004', '专业度不够', '0', '1');
INSERT INTO `concept_resource` VALUES ('447', '1', '33_015', '收费价格', '0', '1');
INSERT INTO `concept_resource` VALUES ('448', '1', '36_010', '加强', '0', '1');
INSERT INTO `concept_resource` VALUES ('449', '1', '30_004', '不是专业的', '0', '1');
INSERT INTO `concept_resource` VALUES ('450', '1', '34_026', '熟悉', '0', '1');
INSERT INTO `concept_resource` VALUES ('451', '1', '39_004', '损耗.*太高', '1', '1');
INSERT INTO `concept_resource` VALUES ('452', '1', '39_004', '降低.损耗', '1', '1');
INSERT INTO `concept_resource` VALUES ('453', '1', '39_004', '降低.成本', '1', '1');
INSERT INTO `concept_resource` VALUES ('454', '1', '34_001', '一次安装好', '0', '1');
INSERT INTO `concept_resource` VALUES ('455', '1', '44_001', '不合理', '0', '1');
INSERT INTO `concept_resource` VALUES ('456', '1', '38_003', '没有.*好', '1', '1');
INSERT INTO `concept_resource` VALUES ('457', '1', '32_024', '速度快', '0', '1');
INSERT INTO `concept_resource` VALUES ('458', '1', '38_003', '过滤.*不理想', '1', '1');
INSERT INTO `concept_resource` VALUES ('459', '1', '38_002', '也好', '0', '1');
INSERT INTO `concept_resource` VALUES ('460', '1', '30_003', '非常专业', '0', '1');
INSERT INTO `concept_resource` VALUES ('461', '1', '30_003', '很专业', '0', '1');
INSERT INTO `concept_resource` VALUES ('462', '1', '30_003', '标准', '0', '1');
INSERT INTO `concept_resource` VALUES ('463', '1', '51_004', '无人推介', '0', '1');
INSERT INTO `concept_resource` VALUES ('465', '1', '43_002', '态度好', '0', '1');
INSERT INTO `concept_resource` VALUES ('466', '1', '31_006', '容积大', '0', '1');
INSERT INTO `concept_resource` VALUES ('469', '1', '31_006', '体积大', '0', '1');
INSERT INTO `concept_resource` VALUES ('470', '1', '31_006', '面积大', '0', '1');
INSERT INTO `concept_resource` VALUES ('472', '1', '37_010', '超高', '0', '1');
INSERT INTO `concept_resource` VALUES ('473', '1', '37_010', '高', '0', '1');
INSERT INTO `concept_resource` VALUES ('475', '1', '37_010', '超低', '0', '1');
INSERT INTO `concept_resource` VALUES ('476', '1', '32_024', '超快', '0', '1');
INSERT INTO `concept_resource` VALUES ('477', '1', '37_006', '超满意', '0', '1');
INSERT INTO `concept_resource` VALUES ('478', '1', '37_007', '超不满意', '0', '1');
INSERT INTO `concept_resource` VALUES ('479', '1', '32_024', '神速', '0', '1');
INSERT INTO `concept_resource` VALUES ('480', '1', '49_005', '下次还会', '0', '1');
INSERT INTO `concept_resource` VALUES ('481', '1', '38_002', '很好', '0', '1');
INSERT INTO `concept_resource` VALUES ('482', '1', '38_002', '一级棒', '0', '1');
INSERT INTO `concept_resource` VALUES ('483', '1', '32_024', '速度很快', '0', '1');
INSERT INTO `concept_resource` VALUES ('484', '1', '32_024', '很快', '0', '1');
INSERT INTO `concept_resource` VALUES ('488', '1', '36_002', '方便', '0', '1');
INSERT INTO `concept_resource` VALUES ('489', '1', '43_002', '态度也很好', '0', '1');
INSERT INTO `concept_resource` VALUES ('490', '1', '32_024', '挺快', '0', '1');
INSERT INTO `concept_resource` VALUES ('491', '1', '38_002', '太好', '0', '1');
INSERT INTO `concept_resource` VALUES ('496', '1', '52', '凑合', '0', '1');
INSERT INTO `concept_resource` VALUES ('497', '1', '52', '还行', '0', '1');
INSERT INTO `concept_resource` VALUES ('498', '1', '52', '马马虎虎', '0', '1');
INSERT INTO `concept_resource` VALUES ('499', '1', '52_001', '好评', '0', '1');
INSERT INTO `concept_resource` VALUES ('500', '1', '52_002', '中评', '0', '1');
INSERT INTO `concept_resource` VALUES ('501', '1', '52_003', '差评', '0', '1');
INSERT INTO `concept_resource` VALUES ('502', '1', '52_002', '凑合', '0', '1');
INSERT INTO `concept_resource` VALUES ('503', '1', '52_002', '还行', '0', '1');
INSERT INTO `concept_resource` VALUES ('504', '1', '52_002', '马马虎虎', '0', '1');
INSERT INTO `concept_resource` VALUES ('505', '1', '37_001', '稳定', '0', '1');
INSERT INTO `concept_resource` VALUES ('506', '1', '37_011', '低', '0', '1');
INSERT INTO `concept_resource` VALUES ('507', '1', '37_011', '超低', '0', '1');
INSERT INTO `concept_resource` VALUES ('508', '1', '37_011', '不稳定', '0', '1');
INSERT INTO `concept_resource` VALUES ('509', '1', '31_006', '正合适', '0', '1');
INSERT INTO `concept_resource` VALUES ('510', '1', '31_007', '容积小', '0', '1');
INSERT INTO `concept_resource` VALUES ('511', '1', '31_007', '体积小', '0', '1');
INSERT INTO `concept_resource` VALUES ('512', '1', '31_007', '面积小', '0', '1');
INSERT INTO `concept_resource` VALUES ('513', '1', '31_007', '太小', '0', '1');
INSERT INTO `concept_resource` VALUES ('514', '1', '31_006', '太大', '0', '1');
INSERT INTO `concept_resource` VALUES ('515', '1', '31_008', '多', '0', '1');
INSERT INTO `concept_resource` VALUES ('516', '1', '31_008', '很多', '0', '1');
INSERT INTO `concept_resource` VALUES ('517', '1', '31_008', '足够多', '0', '1');
INSERT INTO `concept_resource` VALUES ('518', '1', '32_023', '快', '0', '1');
INSERT INTO `concept_resource` VALUES ('519', '1', '32_023', '块', '0', '1');
INSERT INTO `concept_resource` VALUES ('520', '1', '32_024', '给力', '0', '1');
INSERT INTO `concept_resource` VALUES ('522', '1', '43_014', '责任', '0', '1');
INSERT INTO `concept_resource` VALUES ('523', '1', '43_014', '责任性强', '0', '1');
INSERT INTO `concept_resource` VALUES ('524', '1', '31_006', '空间大', '0', '1');
INSERT INTO `concept_resource` VALUES ('525', '1', '31_007', '空间小', '0', '1');
INSERT INTO `concept_resource` VALUES ('526', '1', '39_001', '省电', '0', '1');
INSERT INTO `concept_resource` VALUES ('527', '1', '38_002', '不错', '0', '1');
INSERT INTO `concept_resource` VALUES ('529', '1', '34_027', '正常使用', '0', '1');
INSERT INTO `concept_resource` VALUES ('530', '1', '38_005', '静音', '0', '1');
INSERT INTO `concept_resource` VALUES ('531', '1', '38_005', '声音很小', '0', '1');

-- ----------------------------
-- Table structure for dept
-- ----------------------------
DROP TABLE IF EXISTS `dept`;
CREATE TABLE `dept` (
  `deptNo` int(11) NOT NULL AUTO_INCREMENT COMMENT '表示部门编号，是唯一编号',
  `deptName` varchar(255) NOT NULL COMMENT '部门名称',
  `deptDesc` mediumtext COMMENT '部门备注',
  PRIMARY KEY (`deptNo`),
  UNIQUE KEY `deptName` (`deptName`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dept
-- ----------------------------
INSERT INTO `dept` VALUES ('1', '技术研发部', '相识是缘，被爱是份！只有两个人在一起了才算是缘分！无论你爱或不爱，我都在这里；无论你爱或不爱,我都等你！');
INSERT INTO `dept` VALUES ('2', '财务部', '【初恋】在最美好的季节开始，你经过一阵子，我却记得一辈子。谢谢不完美的你，给予我完美的青春！！');
INSERT INTO `dept` VALUES ('3', '人事管理部', '我以为小鸟飞不过沧海，是因为小鸟没有飞过沧海的勇气。十年以后我才发现，不是小鸟飞不过去，而是沧海的那一头，早已没有了等待……		');
INSERT INTO `dept` VALUES ('4', '市场开发部', '有人说，你结婚的那个人，一定不是你最爱的，我不信，我不信了十几年，可是，我输了。');
INSERT INTO `dept` VALUES ('5', '生产部', '【追梦】梦想即便遥不可及，我亦不放弃追梦，追梦追梦，追逐我梦，哪怕倾尽一生，我亦无悔！');
INSERT INTO `dept` VALUES ('6', '安全监察部', '有人，骗我、欺我、笑我、辱我、害我，何以处置？唯有，敬他、容他、让他、忍他、随他，看准时机弄死他');
INSERT INTO `dept` VALUES ('7', '计划营销部', '别去打扰不愿理你的人,因为他心里那最重要的不是你。 真心等你的，总会真心等下去；不愿等你的，总是一转身就牵了别人的手。我相信，如果真心爱你，无论他多忙，多累，多辛苦，总会有时间给你发短信，打电话，只是愿不愿意。如果一个人真的在乎你，那么总能挤出时间来陪你，没有借口，没有谎言');
INSERT INTO `dept` VALUES ('8', '行政部', null);
INSERT INTO `dept` VALUES ('9', '质量管理部', null);
INSERT INTO `dept` VALUES ('10', '营销部', null);
INSERT INTO `dept` VALUES ('11', '营运部', null);
INSERT INTO `dept` VALUES ('12', '维修部门', null);
INSERT INTO `dept` VALUES ('13', '人力资源部', null);
INSERT INTO `dept` VALUES ('14', '客户服务部', null);

-- ----------------------------
-- Table structure for element
-- ----------------------------
DROP TABLE IF EXISTS `element`;
CREATE TABLE `element` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tree_id` int(11) NOT NULL DEFAULT '1' COMMENT '本体树ID',
  `element_id` varchar(255) NOT NULL DEFAULT '0' COMMENT '要素ID',
  `element_parent_id` varchar(255) NOT NULL DEFAULT '0' COMMENT '要素父节点ID',
  `element_name` varchar(255) NOT NULL DEFAULT '' COMMENT '要素名称',
  `element_level` int(11) NOT NULL DEFAULT '0' COMMENT '级别',
  `element_weight` int(11) NOT NULL DEFAULT '50' COMMENT '要素权值',
  PRIMARY KEY (`id`),
  KEY `element_name` (`element_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4603 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of element
-- ----------------------------
INSERT INTO `element` VALUES ('135', '1', '0', '-1', '海尔要素', '-1', '0');
INSERT INTO `element` VALUES ('150', '1', '10', '0', '产品购买体验评价', '0', '50');
INSERT INTO `element` VALUES ('156', '1', '11', '0', '送货评价', '0', '50');
INSERT INTO `element` VALUES ('157', '1', '11_001', '11', '送货人员', '1', '50');
INSERT INTO `element` VALUES ('158', '1', '11_002', '11', '用户', '1', '50');
INSERT INTO `element` VALUES ('159', '1', '11_003', '11', '机器', '1', '50');
INSERT INTO `element` VALUES ('160', '1', '12', '0', '安装评价', '0', '50');
INSERT INTO `element` VALUES ('161', '1', '12_001', '12', '安装人员', '1', '50');
INSERT INTO `element` VALUES ('162', '1', '12_002', '12', '使用方法', '1', '50');
INSERT INTO `element` VALUES ('163', '1', '13', '0', '产品使用便利性评价', '0', '50');
INSERT INTO `element` VALUES ('166', '1', '13_003', '13', '设计', '1', '50');
INSERT INTO `element` VALUES ('167', '1', '13_004', '13', '说明书', '1', '50');
INSERT INTO `element` VALUES ('168', '1', '14', '0', '产品质量可靠性', '0', '50');
INSERT INTO `element` VALUES ('169', '1', '14_001', '14', '质量', '1', '50');
INSERT INTO `element` VALUES ('170', '1', '14_002', '14', '使用效果', '1', '50');
INSERT INTO `element` VALUES ('171', '1', '14_003', '14', '节能', '1', '50');
INSERT INTO `element` VALUES ('172', '1', '14_004', '14', '安全', '1', '50');
INSERT INTO `element` VALUES ('173', '1', '15', '0', '电话接听安排处理评价', '0', '50');
INSERT INTO `element` VALUES ('174', '1', '15_001', '15', '电话', '1', '50');
INSERT INTO `element` VALUES ('175', '1', '15_002', '15', '接听人员', '1', '50');
INSERT INTO `element` VALUES ('176', '1', '15_003', '15', '信息记录', '1', '50');
INSERT INTO `element` VALUES ('177', '1', '16', '0', '服务速度评价', '0', '50');
INSERT INTO `element` VALUES ('178', '1', '16_001', '16', '响应速度', '1', '50');
INSERT INTO `element` VALUES ('179', '1', '16_002', '16', '用户要求', '1', '50');
INSERT INTO `element` VALUES ('180', '1', '17', '0', '服务规范及态度评价', '0', '50');
INSERT INTO `element` VALUES ('183', '1', '18', '0', '服务人员技能水平评价', '0', '50');
INSERT INTO `element` VALUES ('184', '1', '18_001', '18', '上门', '1', '50');
INSERT INTO `element` VALUES ('185', '1', '18_002', '18', '技术水平', '1', '50');
INSERT INTO `element` VALUES ('186', '1', '19', '0', '购买后关怀服务评价', '0', '50');
INSERT INTO `element` VALUES ('187', '1', '19_007', '19', '节假日', '1', '50');
INSERT INTO `element` VALUES ('188', '1', '19_002', '19', '商品', '1', '50');
INSERT INTO `element` VALUES ('189', '1', '19_003', '19', '会员', '1', '50');
INSERT INTO `element` VALUES ('190', '1', '20', '0', '再购买或推荐亲戚朋友购买评价', '0', '50');
INSERT INTO `element` VALUES ('193', '1', '20_003', '20', '亲戚朋友', '1', '50');
INSERT INTO `element` VALUES ('211', '1', '11_004', '11', '用户家', '1', '50');
INSERT INTO `element` VALUES ('214', '1', '13_006', '13', '日常使用', '1', '50');
INSERT INTO `element` VALUES ('215', '1', '13_007', '13', '保养', '1', '50');
INSERT INTO `element` VALUES ('216', '1', '15_004', '15', '时间', '1', '50');
INSERT INTO `element` VALUES ('217', '1', '16_003', '16', '用户改约', '1', '50');
INSERT INTO `element` VALUES ('218', '1', '17_003', '17', '服务', '1', '50');
INSERT INTO `element` VALUES ('219', '1', '17_004', '17', '态度', '1', '50');
INSERT INTO `element` VALUES ('223', '1', '19_004', '19', '信息', '1', '50');
INSERT INTO `element` VALUES ('224', '1', '20_005', '20', '选购家电', '1', '50');
INSERT INTO `element` VALUES ('226', '1', '18_003', '18', '技术人员', '1', '50');
INSERT INTO `element` VALUES ('227', '1', '20_006', '20', '自己', '1', '50');
INSERT INTO `element` VALUES ('229', '1', '18_004', '18', '服务人员', '1', '50');
INSERT INTO `element` VALUES ('231', '1', '14_005', '14', '性价比', '1', '50');
INSERT INTO `element` VALUES ('232', '1', '16_004', '16', '运送方式', '1', '50');
INSERT INTO `element` VALUES ('233', '1', '19_005', '19', '品牌', '1', '50');
INSERT INTO `element` VALUES ('238', '1', '10_017_001', '10_017', '门体', '2', '50');
INSERT INTO `element` VALUES ('240', '1', '21', '0', '产品功能', '0', '50');
INSERT INTO `element` VALUES ('241', '1', '21_001', '21', '照明', '1', '50');
INSERT INTO `element` VALUES ('242', '1', '21_002', '21', '制冷', '1', '50');
INSERT INTO `element` VALUES ('243', '1', '21_003', '21', '抽屉', '1', '50');
INSERT INTO `element` VALUES ('244', '1', '21_004', '21', '搁物架', '1', '50');
INSERT INTO `element` VALUES ('245', '1', '21_005', '21', '温控', '1', '50');
INSERT INTO `element` VALUES ('246', '1', '19_006', '19', '发票', '1', '50');
INSERT INTO `element` VALUES ('247', '1', '11_005', '11', '物流', '1', '50');
INSERT INTO `element` VALUES ('248', '1', '19_008', '19', '冰箱', '1', '50');
INSERT INTO `element` VALUES ('249', '1', '19_009', '19', '洗衣机', '1', '50');
INSERT INTO `element` VALUES ('251', '1', '22', '0', 'QQ', '0', '50');
INSERT INTO `element` VALUES ('252', '1', '22_001', '22', 'QQ12', '1', '50');
INSERT INTO `element` VALUES ('4601', '2', '0', '-1', '导入单级节点', '-1', '0');
INSERT INTO `element` VALUES ('4602', '3', '0', '-1', '异步表达式', '-1', '0');

-- ----------------------------
-- Table structure for element_resource
-- ----------------------------
DROP TABLE IF EXISTS `element_resource`;
CREATE TABLE `element_resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tree_id` int(11) NOT NULL DEFAULT '1' COMMENT '本体树ID',
  `element_id` varchar(255) NOT NULL DEFAULT '0' COMMENT '要素ID',
  `resource` varchar(255) NOT NULL DEFAULT '0' COMMENT '要素父节点ID',
  `type` int(11) NOT NULL DEFAULT '0' COMMENT '资源类型，0=词，1=正则',
  `element_type` int(11) NOT NULL COMMENT '要素类型，分为要素动作=1,要素属性=0,错误状态=2',
  `langue_type` int(11) NOT NULL COMMENT '语言类型，0=中文简体，1=中文繁体，2=英文',
  PRIMARY KEY (`id`),
  UNIQUE KEY `key` (`resource`,`tree_id`,`element_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=234 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of element_resource
-- ----------------------------
INSERT INTO `element_resource` VALUES ('23', '1', '10_011', '销售现场', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('39', '1', '11_001', '送货人员', '0', '0', '2');
INSERT INTO `element_resource` VALUES ('40', '1', '11_001', '送货员', '0', '0', '2');
INSERT INTO `element_resource` VALUES ('41', '1', '11_002', '用户', '0', '0', '2');
INSERT INTO `element_resource` VALUES ('42', '1', '11_002', '客户', '0', '0', '2');
INSERT INTO `element_resource` VALUES ('43', '1', '11_003', '机器', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('44', '1', '11_004', '用户家', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('45', '1', '11_004', '用户家里', '0', '0', '2');
INSERT INTO `element_resource` VALUES ('47', '1', '12_001', '安装员', '0', '0', '2');
INSERT INTO `element_resource` VALUES ('48', '1', '12_001', '维修员', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('49', '1', '12_001', '维修人员', '0', '0', '2');
INSERT INTO `element_resource` VALUES ('50', '1', '12_002', '使用方法', '0', '0', '2');
INSERT INTO `element_resource` VALUES ('51', '1', '12_002', '使用知识', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('52', '1', '12_002', '使用说明', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('56', '1', '13_003', '设计', '0', '1', '1');
INSERT INTO `element_resource` VALUES ('57', '1', '13_003', '策划', '0', '1', '1');
INSERT INTO `element_resource` VALUES ('58', '1', '13_004', '说明书', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('59', '1', '13_004', '说明文档', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('60', '1', '13_006', '日常使用', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('61', '1', '13_006', '使用', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('62', '1', '13_007', '保养', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('63', '1', '14_001', '质量', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('64', '1', '14_002', '使用效果', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('65', '1', '14_002', '效果', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('66', '1', '14_003', '节能', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('67', '1', '14_003', '能耗', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('68', '1', '14_004', '安全', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('69', '1', '15_001', '电话', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('70', '1', '15_001', '手机', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('71', '1', '15_001', '座机', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('72', '1', '15_002', '接听人员', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('73', '1', '15_002', '接听员', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('74', '1', '15_002', '服务员', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('75', '1', '15_002', '服务人员', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('76', '1', '15_003', '信息记录', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('77', '1', '15_003', '信息', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('78', '1', '15_004', '时间', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('79', '1', '15_004', '预约时间', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('80', '1', '15_004', '预定时间', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('81', '1', '16_001', '响应速度', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('82', '1', '16_001', '响应', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('83', '1', '16_001', '速度', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('84', '1', '16_002', '用户要求', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('85', '1', '16_002', '用户提出', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('86', '1', '16_003', '用户改约', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('87', '1', '16_003', '用户换个时间', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('89', '1', '17_003', '服务态度', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('90', '1', '17_003', '服务人员谈吐举止', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('91', '1', '17_004', '态度', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('92', '1', '17_003', '服务人员素质', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('93', '1', '17_003', '服务过程', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('94', '1', '17_004', '语气', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('95', '1', '18_001', '上门', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('96', '1', '18_001', '到家', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('97', '1', '18_002', '技术', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('98', '1', '18_002', '水平', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('99', '1', '19_001', '节假日', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('100', '1', '19_001', '春节', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('101', '1', '19_001', '元旦', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('102', '1', '19_001', '国庆节', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('103', '1', '19_001', '清明节', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('104', '1', '19_001', '五一劳动节', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('105', '1', '19_001', '双十一', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('106', '1', '19_001', '双十二', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('107', '1', '19_002', '海尔', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('108', '1', '19_003', '会员', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('109', '1', '19_004', '相关信息', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('110', '1', '19_004', '信息', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('111', '1', '20_003', '亲戚', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('112', '1', '20_003', '亲戚朋友', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('113', '1', '20_003', '朋友', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('115', '1', '20_005', '选购家电', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('116', '1', '20_005', '选购产品', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('117', '1', '11_002', '消费者', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('118', '1', '11_003', '电器', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('119', '1', '11_003', '家电', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('120', '1', '11_004', '用户家庭', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('121', '1', '12_001', '安装人员', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('122', '1', '17_003', '售后', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('123', '1', '17_003', '售前', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('124', '1', '18_001', '上门维修', '0', '1', '1');
INSERT INTO `element_resource` VALUES ('125', '1', '15_003', '咨询', '0', '1', '1');
INSERT INTO `element_resource` VALUES ('126', '1', '19_003', '活动', '0', '1', '1');
INSERT INTO `element_resource` VALUES ('127', '1', '19_003', '会员活动', '0', '1', '1');
INSERT INTO `element_resource` VALUES ('128', '1', '19_002', '净水机', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('129', '1', '19_002', '商品', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('130', '1', '19_002', '净化桶', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('131', '1', '10_011', '专卖店', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('134', '1', '19_002', '海尔净水器', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('135', '1', '19_002', '净水器', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('136', '1', '12_001', '安装师傅', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('138', '1', '18_003', '技术员', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('139', '1', '18_003', '技术人员', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('140', '1', '10_010', '实物', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('142', '1', '12_002', '维护方法', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('143', '1', '20_003', '邻居', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('145', '1', '20_006', '自己', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('146', '1', '20_006', '本人', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('147', '1', '10_012', '购买', '0', '1', '1');
INSERT INTO `element_resource` VALUES ('151', '1', '10_010', '样品', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('152', '1', '12_001', '维修师傅', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('153', '1', '13_003', '外观设计', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('154', '1', '13_007', '定期保养', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('155', '1', '15_002', '客服', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('156', '1', '17_003', '服务', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('157', '1', '12_001', '安装工人', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('158', '1', '18_004', '服务人员', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('159', '1', '18_004', '售后人员', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('160', '1', '16_002', '用户建议', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('163', '1', '10_010', '产品', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('164', '1', '13_003', '产品标识', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('165', '1', '13_003', '产品设计', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('166', '1', '10_013', '配件', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('167', '1', '10_013', '水龙头', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('168', '1', '10_013', '测试笔', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('169', '1', '10_013', '螺丝钉', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('170', '1', '10_013', '耗材', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('171', '1', '10_013', '换件', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('172', '1', '10_013', '管子', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('173', '1', '10_013', '皮垫', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('174', '1', '10_013', '水管', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('175', '1', '10_013', '小桶', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('176', '1', '10_013', '水桶', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('177', '1', '10_013', '装置', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('178', '1', '10_013', '包装箱', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('179', '1', '10_013', '芯', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('180', '1', '15_004', '时间预约', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('181', '1', '10_013', '增压泵', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('182', '1', '10_011', '商场', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('183', '1', '10_011', '商店', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('184', '1', '18_002', '安装技术', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('185', '1', '18_004', '人员', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('186', '1', '10_013', '线', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('187', '1', '10_013', '监测仪', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('188', '1', '19_002', '热水器', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('189', '1', '19_002', '饮水机', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('190', '1', '11_001', '送货师傅', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('191', '1', '11_001', '送货師傅', '0', '0', '2');
INSERT INTO `element_resource` VALUES ('192', '1', '10_010', '图片', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('194', '1', '14_005', '性价比', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('195', '1', '14_005', '性能', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('196', '1', '16_004', '物流', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('197', '1', '16_004', '快递', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('198', '1', '16_004', 'EMS', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('199', '1', '10_010', '东西', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('200', '1', '20_006', '我', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('201', '1', '10_010', '宝贝', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('202', '1', '19_002', '洗衣机', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('203', '1', '19_005', 'FPA', '1', '0', '1');
INSERT INTO `element_resource` VALUES ('204', '1', '19_005', 'JE', '1', '0', '1');
INSERT INTO `element_resource` VALUES ('205', '1', '19_005', 'SIO', '1', '0', '1');
INSERT INTO `element_resource` VALUES ('206', '1', '19_005', 'TCL', '1', '0', '1');
INSERT INTO `element_resource` VALUES ('207', '1', '19_005', 'LG', '1', '0', '1');
INSERT INTO `element_resource` VALUES ('208', '1', '18_004', '工作人员', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('209', '1', '10_014', '颜色', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('210', '1', '10_015', '款式', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('211', '1', '18_004', '员工', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('212', '1', '10_016', '板材', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('213', '1', '10_017', '把手', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('214', '1', '10_017', '扶手', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('218', '1', '10_014', '酒红色', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('219', '1', '10_014', '白色', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('220', '1', '10_014', '灰色', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('221', '1', '10_013', '压缩机', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('222', '1', '21_001', '照明', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('223', '1', '21_002', '制冷', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('224', '1', '21_003', '抽屉', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('225', '1', '21_004', '搁物架', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('226', '1', '21_005', '温控', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('227', '1', '19_006', '发票', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('228', '1', '17_003', '全国联保', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('229', '1', '11_005', '物流', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('230', '1', '18_004', '配送员', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('231', '1', '19_008', '冰箱', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('232', '1', '19_009', '洗衣机', '0', '0', '1');
INSERT INTO `element_resource` VALUES ('233', '1', '11_001', '派送人员', '0', '1', '1');

-- ----------------------------
-- Table structure for employer
-- ----------------------------
DROP TABLE IF EXISTS `employer`;
CREATE TABLE `employer` (
  `empid` int(11) NOT NULL AUTO_INCREMENT,
  `empNo` int(11) NOT NULL COMMENT '表示职员编号，是唯一编号',
  `empName` varchar(255) NOT NULL COMMENT '职员姓名',
  `sex` varchar(255) NOT NULL,
  `hireDate` varchar(50) NOT NULL,
  `deptNo` int(11) NOT NULL COMMENT '部门编号',
  `email` varchar(255) NOT NULL,
  `empDesc` mediumtext COMMENT '职员备注',
  PRIMARY KEY (`empid`),
  UNIQUE KEY `EmpNo` (`empNo`) USING BTREE,
  KEY `DeptNo` (`deptNo`) USING BTREE,
  KEY `empName` (`empName`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of employer
-- ----------------------------

-- ----------------------------
-- Table structure for exam_data
-- ----------------------------
DROP TABLE IF EXISTS `exam_data`;
CREATE TABLE `exam_data` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `catlog` varchar(255) DEFAULT NULL,
  `content` text,
  `wstatus` int(11) DEFAULT '0',
  `cstatus` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of exam_data
-- ----------------------------
INSERT INTO `exam_data` VALUES ('2', '1_10', '今天买了海尔的纯净水，体验完美', '0', '0');
INSERT INTO `exam_data` VALUES ('4', '1_10', '电联客江r**********，要求申请电子账单自动发送到邮箱的业务，客称今天月份就开始申请这个业务了，一直没有开通，客称业务员上交了但是就是没有申请下来，已告知会尽快安排申请，要求发一份*月份的账单到jiang.taofoxtar.cn 这个邮箱，月结卡号：*', '0', '0');
INSERT INTO `exam_data` VALUES ('5', '1_10', '电联客江r**********，要求申请电子账单自动发送到邮箱的业务，客称今天月份就开始申请这个业务了，一直没有开通，客称业务员上交了但是就是没有申请下来，已告知会尽快安排申请，要求发一份*月份的账单到jiang.taofoxtar.cn 这个邮箱，月结卡号：*', '0', '0');
INSERT INTO `exam_data` VALUES ('6', '1_10', '电联客江r**********，要求申请电子账单自动发送到邮箱的业务，客称今天月份就开始申请这个业务了，一直没有开通，客称业务员上交了但是就是没有申请下来，已告知会尽快安排申请，要求发一份*月份的账单到jiang.taofoxtar.cn 这个邮箱，月结卡号：*', '0', '0');
INSERT INTO `exam_data` VALUES ('7', '1_10', '电联客江r**********，要求申请电子账单自动发送到邮箱的业务，客称今天月份就开始申请这个业务了，一直没有开通，客称业务员上交了但是就是没有申请下来，已告知会尽快安排申请，要求发一份*月份的账单到jiang.taofoxtar.cn 这个邮箱，月结卡号：*', '0', '0');
INSERT INTO `exam_data` VALUES ('8', '1_10', '电联客江r**********，要求申请电子账单自动发送到邮箱的业务，客称今天月份就开始申请这个业务了，一直没有开通，客称业务员上交了但是就是没有申请下来，已告知会尽快安排申请，要求发一份*月份的账单到jiang.taofoxtar.cn 这个邮箱，月结卡号：*', '0', '0');
INSERT INTO `exam_data` VALUES ('9', '1_10', '电联客江r**********，要求申请电子账单自动发送到邮箱的业务，客称今天月份就开始申请这个业务了，一直没有开通，客称业务员上交了但是就是没有申请下来，已告知会尽快安排申请，要求发一份*月份的账单到jiang.taofoxtar.cn 这个邮箱，月结卡号：*', '0', '0');
INSERT INTO `exam_data` VALUES ('10', '1_10', '电联客江r**********，要求申请电子账单自动发送到邮箱的业务，客称今天月份就开始申请这个业务了，一直没有开通，客称业务员上交了但是就是没有申请下来，已告知会尽快安排申请，要求发一份*月份的账单到jiang.taofoxtar.cn 这个邮箱，月结卡号：*', '0', '0');
INSERT INTO `exam_data` VALUES ('11', '1_10', '电联客江r**********，要求申请电子账单自动发送到邮箱的业务，客称今天月份就开始申请这个业务了，一直没有开通，客称业务员上交了但是就是没有申请下来，已告知会尽快安排申请，要求发一份*月份的账单到jiang.taofoxtar.cn 这个邮箱，月结卡号：*', '0', '0');
INSERT INTO `exam_data` VALUES ('12', '1_10', '电联客江r**********，要求申请电子账单自动发送到邮箱的业务，客称今天月份就开始申请这个业务了，一直没有开通，客称业务员上交了但是就是没有申请下来，已告知会尽快安排申请，要求发一份*月份的账单到jiang.taofoxtar.cn 这个邮箱，月结卡号：*', '0', '0');
INSERT INTO `exam_data` VALUES ('13', '1_10', '电联客江r**********，要求申请电子账单自动发送到邮箱的业务，客称今天月份就开始申请这个业务了，一直没有开通，客称业务员上交了但是就是没有申请下来，已告知会尽快安排申请，要求发一份*月份的账单到jiang.taofoxtar.cn 这个邮箱，月结卡号：*', '0', '0');
INSERT INTO `exam_data` VALUES ('14', '1_10', '电联客江r**********，要求申请电子账单自动发送到邮箱的业务，客称今天月份就开始申请这个业务了，一直没有开通，客称业务员上交了但是就是没有申请下来，已告知会尽快安排申请，要求发一份*月份的账单到jiang.taofoxtar.cn 这个邮箱，月结卡号：*', '0', '0');
INSERT INTO `exam_data` VALUES ('15', '1_10', '电联客江r**********，要求申请电子账单自动发送到邮箱的业务，客称今天月份就开始申请这个业务了，一直没有开通，客称业务员上交了但是就是没有申请下来，已告知会尽快安排申请，要求发一份*月份的账单到jiang.taofoxtar.cn 这个邮箱，月结卡号：*', '0', '0');
INSERT INTO `exam_data` VALUES ('16', '1_10', '电联客江r**********，要求申请电子账单自动发送到邮箱的业务，客称今天月份就开始申请这个业务了，一直没有开通，客称业务员上交了但是就是没有申请下来，已告知会尽快安排申请，要求发一份*月份的账单到jiang.taofoxtar.cn 这个邮箱，月结卡号：*', '0', '0');
INSERT INTO `exam_data` VALUES ('17', '1_10', '电联客江r**********，要求申请电子账单自动发送到邮箱的业务，客称今天月份就开始申请这个业务了，一直没有开通，客称业务员上交了但是就是没有申请下来，已告知会尽快安排申请，要求发一份*月份的账单到jiang.taofoxtar.cn 这个邮箱，月结卡号：*', '0', '0');
INSERT INTO `exam_data` VALUES ('18', '1_10', '电联客江r**********，要求申请电子账单自动发送到邮箱的业务，客称今天月份就开始申请这个业务了，一直没有开通，客称业务员上交了但是就是没有申请下来，已告知会尽快安排申请，要求发一份*月份的账单到jiang.taofoxtar.cn 这个邮箱，月结卡号：*', '0', '0');
INSERT INTO `exam_data` VALUES ('19', '1_10', '电联客江r**********，要求申请电子账单自动发送到邮箱的业务，客称今天月份就开始申请这个业务了，一直没有开通，客称业务员上交了但是就是没有申请下来，已告知会尽快安排申请，要求发一份*月份的账单到jiang.taofoxtar.cn 这个邮箱，月结卡号：*', '0', '0');
INSERT INTO `exam_data` VALUES ('20', '1_10', '电联客江r**********，要求申请电子账单自动发送到邮箱的业务，客称今天月份就开始申请这个业务了，一直没有开通，客称业务员上交了但是就是没有申请下来，已告知会尽快安排申请，要求发一份*月份的账单到jiang.taofoxtar.cn 这个邮箱，月结卡号：*', '0', '0');
INSERT INTO `exam_data` VALUES ('21', '1_10', '电联客江r**********，要求申请电子账单自动发送到邮箱的业务，客称今天月份就开始申请这个业务了，一直没有开通，客称业务员上交了但是就是没有申请下来，已告知会尽快安排申请，要求发一份*月份的账单到jiang.taofoxtar.cn 这个邮箱，月结卡号：*', '0', '0');
INSERT INTO `exam_data` VALUES ('22', '1_10', '电联客江r**********，要求申请电子账单自动发送到邮箱的业务，客称今天月份就开始申请这个业务了，一直没有开通，客称业务员上交了但是就是没有申请下来，已告知会尽快安排申请，要求发一份*月份的账单到jiang.taofoxtar.cn 这个邮箱，月结卡号：*', '0', '0');
INSERT INTO `exam_data` VALUES ('23', '1_10', '电联客江r**********，要求申请电子账单自动发送到邮箱的业务，客称今天月份就开始申请这个业务了，一直没有开通，客称业务员上交了但是就是没有申请下来，已告知会尽快安排申请，要求发一份*月份的账单到jiang.taofoxtar.cn 这个邮箱，月结卡号：*', '0', '0');
INSERT INTO `exam_data` VALUES ('24', '1_10', '电联客江r**********，要求申请电子账单自动发送到邮箱的业务，客称今天月份就开始申请这个业务了，一直没有开通，客称业务员上交了但是就是没有申请下来，已告知会尽快安排申请，要求发一份*月份的账单到jiang.taofoxtar.cn 这个邮箱，月结卡号：*', '0', '0');
INSERT INTO `exam_data` VALUES ('25', '1_10', '电联客江r**********，要求申请电子账单自动发送到邮箱的业务，客称今天月份就开始申请这个业务了，一直没有开通，客称业务员上交了但是就是没有申请下来，已告知会尽快安排申请，要求发一份*月份的账单到jiang.taofoxtar.cn 这个邮箱，月结卡号：*', '0', '0');
INSERT INTO `exam_data` VALUES ('26', '1_10', '电联客江r**********，要求申请电子账单自动发送到邮箱的业务，客称今天月份就开始申请这个业务了，一直没有开通，客称业务员上交了但是就是没有申请下来，已告知会尽快安排申请，要求发一份*月份的账单到jiang.taofoxtar.cn 这个邮箱，月结卡号：*', '0', '0');
INSERT INTO `exam_data` VALUES ('27', '1_10', '电联客江r**********，要求申请电子账单自动发送到邮箱的业务，客称今天月份就开始申请这个业务了，一直没有开通，客称业务员上交了但是就是没有申请下来，已告知会尽快安排申请，要求发一份*月份的账单到jiang.taofoxtar.cn 这个邮箱，月结卡号：*', '0', '0');
INSERT INTO `exam_data` VALUES ('28', '1_10', '电联客江r**********，要求申请电子账单自动发送到邮箱的业务，客称今天月份就开始申请这个业务了，一直没有开通，客称业务员上交了但是就是没有申请下来，已告知会尽快安排申请，要求发一份*月份的账单到jiang.taofoxtar.cn 这个邮箱，月结卡号：*', '0', '0');
INSERT INTO `exam_data` VALUES ('29', '1_10', '电联客江r**********，要求申请电子账单自动发送到邮箱的业务，客称今天月份就开始申请这个业务了，一直没有开通，客称业务员上交了但是就是没有申请下来，已告知会尽快安排申请，要求发一份*月份的账单到jiang.taofoxtar.cn 这个邮箱，月结卡号：*', '0', '0');
INSERT INTO `exam_data` VALUES ('30', '1_10', '电联客江r**********，要求申请电子账单自动发送到邮箱的业务，客称今天月份就开始申请这个业务了，一直没有开通，客称业务员上交了但是就是没有申请下来，已告知会尽快安排申请，要求发一份*月份的账单到jiang.taofoxtar.cn 这个邮箱，月结卡号：*', '0', '0');

-- ----------------------------
-- Table structure for exam_expression
-- ----------------------------
DROP TABLE IF EXISTS `exam_expression`;
CREATE TABLE `exam_expression` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `exam_id` int(11) DEFAULT NULL,
  `expression` varchar(255) DEFAULT NULL,
  `catlog` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=88 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of exam_expression
-- ----------------------------
INSERT INTO `exam_expression` VALUES ('1', '2', ' c_及时性', '1_10');
INSERT INTO `exam_expression` VALUES ('2', '2', ' k_没有', '1_10');
INSERT INTO `exam_expression` VALUES ('3', '2', ' k_请', '1_10');
INSERT INTO `exam_expression` VALUES ('4', '3', ' c_及时性', '1_10');
INSERT INTO `exam_expression` VALUES ('5', '3', ' k_没有', '1_10');
INSERT INTO `exam_expression` VALUES ('6', '3', ' k_请', '1_10');
INSERT INTO `exam_expression` VALUES ('7', '4', ' c_及时性', '1_10');
INSERT INTO `exam_expression` VALUES ('8', '4', ' k_没有', '1_10');
INSERT INTO `exam_expression` VALUES ('9', '4', ' k_请', '1_10');
INSERT INTO `exam_expression` VALUES ('10', '5', ' c_及时性', '1_10');
INSERT INTO `exam_expression` VALUES ('11', '5', ' k_没有', '1_10');
INSERT INTO `exam_expression` VALUES ('12', '5', ' k_请', '1_10');
INSERT INTO `exam_expression` VALUES ('13', '6', ' c_及时性', '1_10');
INSERT INTO `exam_expression` VALUES ('14', '6', ' k_没有', '1_10');
INSERT INTO `exam_expression` VALUES ('15', '6', ' k_请', '1_10');
INSERT INTO `exam_expression` VALUES ('16', '7', ' c_及时性', '1_10');
INSERT INTO `exam_expression` VALUES ('17', '7', ' k_没有', '1_10');
INSERT INTO `exam_expression` VALUES ('18', '7', ' k_请', '1_10');
INSERT INTO `exam_expression` VALUES ('19', '8', ' c_及时性', '1_10');
INSERT INTO `exam_expression` VALUES ('20', '8', ' k_没有', '1_10');
INSERT INTO `exam_expression` VALUES ('21', '8', ' k_请', '1_10');
INSERT INTO `exam_expression` VALUES ('22', '9', ' c_及时性', '1_10');
INSERT INTO `exam_expression` VALUES ('23', '9', ' k_没有', '1_10');
INSERT INTO `exam_expression` VALUES ('24', '9', ' k_请', '1_10');
INSERT INTO `exam_expression` VALUES ('25', '10', ' c_及时性', '1_10');
INSERT INTO `exam_expression` VALUES ('26', '10', ' k_没有', '1_10');
INSERT INTO `exam_expression` VALUES ('27', '10', ' k_请', '1_10');
INSERT INTO `exam_expression` VALUES ('28', '11', ' c_及时性', '1_10');
INSERT INTO `exam_expression` VALUES ('29', '11', ' k_没有', '1_10');
INSERT INTO `exam_expression` VALUES ('30', '11', ' k_请', '1_10');
INSERT INTO `exam_expression` VALUES ('31', '12', ' c_及时性', '1_10');
INSERT INTO `exam_expression` VALUES ('32', '12', ' k_没有', '1_10');
INSERT INTO `exam_expression` VALUES ('33', '12', ' k_请', '1_10');
INSERT INTO `exam_expression` VALUES ('34', '13', ' c_及时性', '1_10');
INSERT INTO `exam_expression` VALUES ('35', '13', ' k_没有', '1_10');
INSERT INTO `exam_expression` VALUES ('36', '13', ' k_请', '1_10');
INSERT INTO `exam_expression` VALUES ('37', '14', ' c_及时性', '1_10');
INSERT INTO `exam_expression` VALUES ('38', '14', ' k_没有', '1_10');
INSERT INTO `exam_expression` VALUES ('39', '14', ' k_请', '1_10');
INSERT INTO `exam_expression` VALUES ('40', '15', ' c_及时性', '1_10');
INSERT INTO `exam_expression` VALUES ('41', '15', ' k_没有', '1_10');
INSERT INTO `exam_expression` VALUES ('42', '15', ' k_请', '1_10');
INSERT INTO `exam_expression` VALUES ('43', '16', ' c_及时性', '1_10');
INSERT INTO `exam_expression` VALUES ('44', '16', ' k_没有', '1_10');
INSERT INTO `exam_expression` VALUES ('45', '16', ' k_请', '1_10');
INSERT INTO `exam_expression` VALUES ('46', '17', ' c_及时性', '1_10');
INSERT INTO `exam_expression` VALUES ('47', '17', ' k_没有', '1_10');
INSERT INTO `exam_expression` VALUES ('48', '17', ' k_请', '1_10');
INSERT INTO `exam_expression` VALUES ('49', '18', ' c_及时性', '1_10');
INSERT INTO `exam_expression` VALUES ('50', '18', ' k_没有', '1_10');
INSERT INTO `exam_expression` VALUES ('51', '18', ' k_请', '1_10');
INSERT INTO `exam_expression` VALUES ('52', '19', ' c_及时性', '1_10');
INSERT INTO `exam_expression` VALUES ('53', '19', ' k_没有', '1_10');
INSERT INTO `exam_expression` VALUES ('54', '19', ' k_请', '1_10');
INSERT INTO `exam_expression` VALUES ('55', '20', ' c_及时性', '1_10');
INSERT INTO `exam_expression` VALUES ('56', '20', ' k_没有', '1_10');
INSERT INTO `exam_expression` VALUES ('57', '20', ' k_请', '1_10');
INSERT INTO `exam_expression` VALUES ('58', '21', ' c_及时性', '1_10');
INSERT INTO `exam_expression` VALUES ('59', '21', ' k_没有', '1_10');
INSERT INTO `exam_expression` VALUES ('60', '21', ' k_请', '1_10');
INSERT INTO `exam_expression` VALUES ('61', '22', ' c_及时性', '1_10');
INSERT INTO `exam_expression` VALUES ('62', '22', ' k_没有', '1_10');
INSERT INTO `exam_expression` VALUES ('63', '22', ' k_请', '1_10');
INSERT INTO `exam_expression` VALUES ('64', '23', ' c_及时性', '1_10');
INSERT INTO `exam_expression` VALUES ('65', '23', ' k_没有', '1_10');
INSERT INTO `exam_expression` VALUES ('66', '23', ' k_请', '1_10');
INSERT INTO `exam_expression` VALUES ('67', '24', ' c_及时性', '1_10');
INSERT INTO `exam_expression` VALUES ('68', '24', ' k_没有', '1_10');
INSERT INTO `exam_expression` VALUES ('69', '24', ' k_请', '1_10');
INSERT INTO `exam_expression` VALUES ('70', '25', ' c_及时性', '1_10');
INSERT INTO `exam_expression` VALUES ('71', '25', ' k_没有', '1_10');
INSERT INTO `exam_expression` VALUES ('72', '25', ' k_请', '1_10');
INSERT INTO `exam_expression` VALUES ('73', '26', ' c_及时性', '1_10');
INSERT INTO `exam_expression` VALUES ('74', '26', ' k_没有', '1_10');
INSERT INTO `exam_expression` VALUES ('75', '26', ' k_请', '1_10');
INSERT INTO `exam_expression` VALUES ('76', '27', ' c_及时性', '1_10');
INSERT INTO `exam_expression` VALUES ('77', '27', ' k_没有', '1_10');
INSERT INTO `exam_expression` VALUES ('78', '27', ' k_请', '1_10');
INSERT INTO `exam_expression` VALUES ('79', '28', ' c_及时性', '1_10');
INSERT INTO `exam_expression` VALUES ('80', '28', ' k_没有', '1_10');
INSERT INTO `exam_expression` VALUES ('81', '28', ' k_请', '1_10');
INSERT INTO `exam_expression` VALUES ('82', '29', ' c_及时性', '1_10');
INSERT INTO `exam_expression` VALUES ('83', '29', ' k_没有', '1_10');
INSERT INTO `exam_expression` VALUES ('84', '29', ' k_请', '1_10');
INSERT INTO `exam_expression` VALUES ('85', '30', ' c_及时性', '1_10');
INSERT INTO `exam_expression` VALUES ('86', '30', ' k_没有', '1_10');
INSERT INTO `exam_expression` VALUES ('87', '30', ' k_请', '1_10');

-- ----------------------------
-- Table structure for ext_datagrid
-- ----------------------------
DROP TABLE IF EXISTS `ext_datagrid`;
CREATE TABLE `ext_datagrid` (
  `ext_id` int(11) NOT NULL AUTO_INCREMENT,
  `ext_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ext_id`),
  KEY `postName` (`ext_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ext_datagrid
-- ----------------------------
INSERT INTO `ext_datagrid` VALUES ('1', '正式员工');
INSERT INTO `ext_datagrid` VALUES ('2', '正式员工');
INSERT INTO `ext_datagrid` VALUES ('3', '正式员工');
INSERT INTO `ext_datagrid` VALUES ('4', '正式员工');
INSERT INTO `ext_datagrid` VALUES ('5', '正式员工');
INSERT INTO `ext_datagrid` VALUES ('6', '正式员工');
INSERT INTO `ext_datagrid` VALUES ('7', '正式员工');
INSERT INTO `ext_datagrid` VALUES ('8', '正式员工');
INSERT INTO `ext_datagrid` VALUES ('9', '正式员工');
INSERT INTO `ext_datagrid` VALUES ('10', '正式员工');
INSERT INTO `ext_datagrid` VALUES ('11', '正式员工');
INSERT INTO `ext_datagrid` VALUES ('12', '正式员工');
INSERT INTO `ext_datagrid` VALUES ('13', '正式员工');
INSERT INTO `ext_datagrid` VALUES ('14', '正式员工');
INSERT INTO `ext_datagrid` VALUES ('15', '正式员工');
INSERT INTO `ext_datagrid` VALUES ('16', '正式员工');
INSERT INTO `ext_datagrid` VALUES ('17', '正式员工');
INSERT INTO `ext_datagrid` VALUES ('18', '正式员工');

-- ----------------------------
-- Table structure for e_educate
-- ----------------------------
DROP TABLE IF EXISTS `e_educate`;
CREATE TABLE `e_educate` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `text` varchar(255) NOT NULL COMMENT '内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of e_educate
-- ----------------------------
INSERT INTO `e_educate` VALUES ('1', '小学');
INSERT INTO `e_educate` VALUES ('2', '初中');
INSERT INTO `e_educate` VALUES ('3', '中专');
INSERT INTO `e_educate` VALUES ('4', '高中');
INSERT INTO `e_educate` VALUES ('5', '大学专科');
INSERT INTO `e_educate` VALUES ('6', '大学本科');
INSERT INTO `e_educate` VALUES ('7', '硕士研究生');
INSERT INTO `e_educate` VALUES ('8', '博士研究生');

-- ----------------------------
-- Table structure for langue
-- ----------------------------
DROP TABLE IF EXISTS `langue`;
CREATE TABLE `langue` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `text` varchar(255) NOT NULL,
  `code` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `text` (`text`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=84 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of langue
-- ----------------------------
INSERT INTO `langue` VALUES ('1', '中文简体', 'zh-CN');
INSERT INTO `langue` VALUES ('2', '中文繁体', 'zh-TW');
INSERT INTO `langue` VALUES ('3', '英语', 'en');
INSERT INTO `langue` VALUES ('4', '阿尔巴尼亚语', 'sq');
INSERT INTO `langue` VALUES ('5', '阿拉伯语', 'ar');
INSERT INTO `langue` VALUES ('6', '阿塞拜疆语', 'az');
INSERT INTO `langue` VALUES ('7', '爱尔兰语', 'ga');
INSERT INTO `langue` VALUES ('8', '爱沙尼亚语', 'et');
INSERT INTO `langue` VALUES ('9', '巴斯克语', 'eu');
INSERT INTO `langue` VALUES ('10', '白俄罗斯语', 'be');
INSERT INTO `langue` VALUES ('11', '保加利亚语', 'bg');
INSERT INTO `langue` VALUES ('12', '冰岛语', 'is');
INSERT INTO `langue` VALUES ('13', '波兰语', 'pl');
INSERT INTO `langue` VALUES ('14', '波斯尼亚语', 'bs');
INSERT INTO `langue` VALUES ('15', '波斯', 'fa');
INSERT INTO `langue` VALUES ('16', '布尔语(南非荷兰语)', 'af');
INSERT INTO `langue` VALUES ('17', '丹麦语', 'da');
INSERT INTO `langue` VALUES ('18', '德语', 'de');
INSERT INTO `langue` VALUES ('19', '俄语', 'ru');
INSERT INTO `langue` VALUES ('20', '法语', 'fr');
INSERT INTO `langue` VALUES ('21', '菲律宾语', 'tl');
INSERT INTO `langue` VALUES ('22', '芬兰语', 'fi');
INSERT INTO `langue` VALUES ('23', '高棉语', 'km');
INSERT INTO `langue` VALUES ('24', '格鲁吉亚语', 'ka');
INSERT INTO `langue` VALUES ('25', '古吉拉特语', 'gu');
INSERT INTO `langue` VALUES ('26', '海地克里奥尔语', 'ht');
INSERT INTO `langue` VALUES ('27', '韩语', 'ko');
INSERT INTO `langue` VALUES ('28', '豪萨语', 'ha');
INSERT INTO `langue` VALUES ('29', '荷兰语', 'nl');
INSERT INTO `langue` VALUES ('30', '加利西亚语', 'gl');
INSERT INTO `langue` VALUES ('31', '加泰罗尼亚语', 'ca');
INSERT INTO `langue` VALUES ('32', '捷克语', 'cs');
INSERT INTO `langue` VALUES ('33', '卡纳达语', 'kn');
INSERT INTO `langue` VALUES ('34', '克罗地亚语', 'hr');
INSERT INTO `langue` VALUES ('35', '拉丁语', 'la');
INSERT INTO `langue` VALUES ('36', '拉脱维亚语', 'lv');
INSERT INTO `langue` VALUES ('37', '老挝语', 'la');
INSERT INTO `langue` VALUES ('38', '立陶宛语', 'lt');
INSERT INTO `langue` VALUES ('39', '罗马尼亚语', 'ro');
INSERT INTO `langue` VALUES ('40', '马耳他语', 'mt');
INSERT INTO `langue` VALUES ('41', '马来地语', 'mr');
INSERT INTO `langue` VALUES ('42', '马来语', 'ms');
INSERT INTO `langue` VALUES ('43', '马其顿语', 'mk');
INSERT INTO `langue` VALUES ('44', '毛利语', 'mi');
INSERT INTO `langue` VALUES ('45', '蒙古语', 'mn');
INSERT INTO `langue` VALUES ('46', '孟加拉语', 'bn');
INSERT INTO `langue` VALUES ('47', '苗语', 'hmn');
INSERT INTO `langue` VALUES ('48', '南非祖鲁语', 'zu');
INSERT INTO `langue` VALUES ('49', '尼泊尔语', 'ne');
INSERT INTO `langue` VALUES ('50', '挪威语', 'no');
INSERT INTO `langue` VALUES ('51', '旁遮普语', 'pa');
INSERT INTO `langue` VALUES ('52', '葡萄牙语', 'pt');
INSERT INTO `langue` VALUES ('53', '日语', 'ja');
INSERT INTO `langue` VALUES ('54', '瑞典语', 'sv');
INSERT INTO `langue` VALUES ('55', '塞尔维亚语', 'sr');
INSERT INTO `langue` VALUES ('56', '世界语', 'eo');
INSERT INTO `langue` VALUES ('57', '斯洛伐克语', 'sk');
INSERT INTO `langue` VALUES ('58', '斯洛文尼亚语', 'sl');
INSERT INTO `langue` VALUES ('59', '斯瓦西里语', 'sw');
INSERT INTO `langue` VALUES ('60', '宿务语', 'ceb');
INSERT INTO `langue` VALUES ('61', '索马里语', 'so');
INSERT INTO `langue` VALUES ('62', '泰卢固语', 'te');
INSERT INTO `langue` VALUES ('63', '泰米尔语', 'ta');
INSERT INTO `langue` VALUES ('64', '泰语', 'th');
INSERT INTO `langue` VALUES ('65', '土耳其语', 'tr');
INSERT INTO `langue` VALUES ('66', '威尔士语', 'cy');
INSERT INTO `langue` VALUES ('67', '乌尔都语', 'ur');
INSERT INTO `langue` VALUES ('68', '乌克兰语', 'uk');
INSERT INTO `langue` VALUES ('69', '希伯来语', 'iw');
INSERT INTO `langue` VALUES ('70', '希腊语', 'el');
INSERT INTO `langue` VALUES ('71', '西班牙语', 'es');
INSERT INTO `langue` VALUES ('72', '匈牙利语', 'hu');
INSERT INTO `langue` VALUES ('73', '亚美尼亚语', 'hy');
INSERT INTO `langue` VALUES ('74', '伊博语', 'ig');
INSERT INTO `langue` VALUES ('75', '意大利语', 'it');
INSERT INTO `langue` VALUES ('76', '意地绪语', 'yi');
INSERT INTO `langue` VALUES ('77', '印地语', 'hi');
INSERT INTO `langue` VALUES ('78', '印尼语', 'id');
INSERT INTO `langue` VALUES ('79', '印尼爪哇语', 'jw');
INSERT INTO `langue` VALUES ('82', '约鲁巴语', 'yo');
INSERT INTO `langue` VALUES ('83', '越南语', 'vi');

-- ----------------------------
-- Table structure for local_city
-- ----------------------------
DROP TABLE IF EXISTS `local_city`;
CREATE TABLE `local_city` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `city_code` int(11) DEFAULT NULL COMMENT '城市代码',
  `city` varchar(255) DEFAULT NULL COMMENT '城市',
  `province_id` int(11) DEFAULT NULL COMMENT '所属省、自治区、直辖市',
  `1evel` int(11) DEFAULT NULL COMMENT '级别',
  `plate_code` varchar(255) DEFAULT NULL COMMENT '车牌代码',
  `area` varchar(255) DEFAULT NULL COMMENT '面积（km²）',
  `area_code` int(11) DEFAULT NULL COMMENT '区号',
  PRIMARY KEY (`id`),
  KEY `province_id` (`province_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of local_city
-- ----------------------------

-- ----------------------------
-- Table structure for local_province
-- ----------------------------
DROP TABLE IF EXISTS `local_province`;
CREATE TABLE `local_province` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `province` varchar(255) DEFAULT NULL COMMENT '省份',
  `referred` varchar(255) DEFAULT NULL COMMENT '简称',
  `Nickname` varchar(255) DEFAULT NULL COMMENT '别称',
  `type` int(11) DEFAULT NULL COMMENT '所属行政级别',
  `capital` varchar(255) DEFAULT NULL COMMENT '省会（或首府）名',
  `regional_id` int(11) DEFAULT NULL,
  `area` varchar(255) DEFAULT NULL COMMENT '面积（万平方千米',
  `people_number` float DEFAULT NULL COMMENT '人口（万人）',
  `zip` varchar(255) DEFAULT NULL COMMENT '邮编',
  `government_code` varchar(255) DEFAULT NULL COMMENT '行政代码',
  `jurisdiction` mediumtext COMMENT '管辖',
  PRIMARY KEY (`id`),
  UNIQUE KEY `province` (`province`,`referred`) USING BTREE,
  KEY `type` (`type`) USING BTREE,
  KEY `regional_id` (`regional_id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8 COMMENT='中国行政区域——省、自治区、直辖市 ';

-- ----------------------------
-- Records of local_province
-- ----------------------------
INSERT INTO `local_province` VALUES ('1', '河北省', '冀', '', '1', '石家庄', '1', '19', '7287.51', '050052', '130000', '共有11个地级行政区划单位（其中：11个地级市，包括3个较大的市：石家庄、唐山、邯郸），172个县级行政区划单位（其中：36个市辖区、22个县级市、108个县、6个自治县），2233个乡级行政区划单位（其中：1个区公所、273个街道、1013个镇、894个乡、52个民族乡）');
INSERT INTO `local_province` VALUES ('2', '山西省', '晋', '', '1', '太原', '1', '16', '3474', '030072', '140000', '共有11个地级行政区划单位（其中：11个地级市，包括2个较大的市：太原、大同），119个县级行政区划单位（其中：23个市辖区、11个县级市、85个县），1397个乡级行政区划单位（其中：201个街道、564个镇、632个乡）');
INSERT INTO `local_province` VALUES ('3', '辽宁省', '辽', '', '1', '沈阳', '2', '15', '4252', '110032', '210000', '共有14个地级行政区划单位（其中：14个地级市，包括2个副省级市：沈阳、大连、3个较大的市：鞍山、抚顺、本溪），100个县级行政区划单位（其中：56个市辖区、17个县级市、19个县、8个自治县），6972个乡级行政区划单位（其中：611个街道、6071个镇、221个乡、69个民族乡）');
INSERT INTO `local_province` VALUES ('4', '吉林省', '吉', '', '1', '长春', '2', '19', '2724', '130051', '220000', '共有9个地级行政区划单位（其中：8个地级市包括1个副省级市：长春、1个较大的市：吉林、1个自治州），60个县级行政区划单位（其中：20个市辖区、20个县级市、17个县、3个自治县），898个乡级行政区划单位（其中：280个街道、428个镇、162个乡、28个民族乡）');
INSERT INTO `local_province` VALUES ('5', '黑龙江省', '黑', '', '1', '哈尔滨', '2', '46', '3843', '150001', '230000', '共有13个地级行政区划单位（其中：12个地级市包括1个副省级市：哈尔滨、1个较大的市：齐齐哈尔、1个地区），128个县级行政区划单位（其中：64个市辖区、18个县级市、45个县、1个自治县），1195个乡级行政区划单位（其中：383个街道、478个镇、276个乡、58个民族乡）');
INSERT INTO `local_province` VALUES ('6', '江苏省', '苏', '', '1', '南京', '3', '10', '7467', '210024', '320000', '共有13个地级行政区划单位（其中：13个地级市包括1个副省级市：南京、3个较大的市：苏州、无锡、徐州），104个县级行政区划单位（其中：55个市辖区、25个县级市、24个县），1300个乡级行政区划单位（其中：344个街道、860个镇、95个乡、1个民族乡）');
INSERT INTO `local_province` VALUES ('7', '浙江省', '浙', '', '1', '杭州', '3', '10', '4748', '310025', '330000', '共有11个地级行政区划单位（其中：11个地级市包括2个副省级市：杭州、宁波），90个县级行政区划单位（其中：32个市辖区、22个县级市、35个县、1个自治县），1499个乡级行政区划单位（其中：402个街道、654个镇、429个乡、14个民族乡）');
INSERT INTO `local_province` VALUES ('8', '安徽省', '皖', '', '1', '合肥', '3', '14', '6825', '230001', '340000', '共有16个地级行政区划单位（其中：16个地级市包括2个较大的市：合肥、淮南），105个县级行政区划单位（其中：43个市辖区、6个县级市、56个县），1513个乡级行政区划单位（其中：265个街道、914个镇、334个乡、9个民族乡）');
INSERT INTO `local_province` VALUES ('9', '福建省', '闽', '', '1', '福州', '3', '12', '3530', '350003', '350000', '共有10个地级行政区划单位（其中：9个地级市包括1副省级市：厦门、1个较大的市：福州、1个综合实验区（对台特区）：平潭）），85个县级行政区划单位（其中：26个市辖区、14个县级市、45个县），1102个乡级行政区划单位（其中：173个街道、600个镇、310个乡、19个民族乡）');
INSERT INTO `local_province` VALUES ('10', '江西省', '赣', '', '1', '南昌', '3', '17', '4694', '330046', '360000', '共有11个地级行政区划单位（其中：11个地级市包括1个较大的市：南昌），100个县级行政区划单位（其中：19个市辖区、11个县级市、70个县），2827个乡级行政区划单位（其中：1431个街道、794个镇、594个乡、8个民族乡）');
INSERT INTO `local_province` VALUES ('11', '山东省', '鲁', '', '1', '济南', '3', '16', '9536', '250011', '370000', '共有17个地级行政区划单位（其中：17个地级市包括2个副省级市：济南、青岛、1个较大的市：淄博），140个县级行政区划单位（其中：49个市辖区、31个县级市、60个县），1857个乡级行政区划单位（其中：611个街道、1118个镇、128个乡）');
INSERT INTO `local_province` VALUES ('12', '河南省', '豫', '', '1', '郑州', '4', '17', '10800', '450003', '410000', '共有17个地级行政区划单位（其中：17个地级市包括2个较大的市：郑州、洛阳），159个县级行政区划单位（其中：50个市辖区、21个县级市、88个县），2381个乡级行政区划单位（其中：518个街道、1011个镇、840个乡、12个民族乡）');
INSERT INTO `local_province` VALUES ('13', '湖北省', '鄂', '', '1', '武汉', '4', '19', '6149', '430071', '420000', '共有13个地级行政区划单位（其中：12个地级市包括1个副省级市：武汉、1个自治州），103个县级行政区划单位（其中：38个市辖区、24个县级市、38个县、2个自治县、1个林区），1233个乡级行政区划单位（其中：297个街道、742个镇、184个乡、10个民族乡）');
INSERT INTO `local_province` VALUES ('14', '湖南省', '湘', '', '1', '长沙', '4', '21', '7069', '410000', '430000', '共有14个地级行政区划单位（其中：13个地级市包括1个较大的市：长沙、1个自治州），122个县级行政区划单位（其中：35个市辖区、16个县级市、64个县、7个自治县），2426个乡级行政区划单位（其中：267个街道、1121个镇、941个乡、97个民族乡）');
INSERT INTO `local_province` VALUES ('15', '广东省', '粤', '', '1', '广州', '5', '18', '8522', '510031', '440000', '共有21个地级行政区划单位（其中：21个地级市包括2个副省级市：广州、深圳、2个经济特区市：珠海、汕头），121个县级行政区划单位（其中：54个市辖区、23个县级市、41个县、3个自治县），1585个乡级行政区划单位（其中：442个街道、1132个镇、4个乡、7个民族乡）');
INSERT INTO `local_province` VALUES ('16', '海南省', '琼', '', '1', '海口', '5', '3.4', '896', '570203', '460000', '共有3个地级行政区划单位（其中：3个地级市包括1个较大的市：海口），20个县级行政区划单位（其中：4个市辖区、6个县级市、4个县、6个自治县），222个乡级行政区划单位（其中：18个街道、183个镇、21个乡）');
INSERT INTO `local_province` VALUES ('17', '四川省', '川', '蜀', '1', '成都', '6', '49', '9001', '610016', '510000', '共有21个地级行政区划单位（其中：18个地级市包括1个副省级市：成都、3个自治州），181个县级行政区划单位（其中：44个市辖区、14个县级市、119个县、4个自治县），4672个乡级行政区划单位（其中：277个街道、1816个镇、2481个乡、98个民族乡）');
INSERT INTO `local_province` VALUES ('18', '贵州省', '贵', '黔', '1', '贵阳', '6', '18', '4189', '550004', '520000', '共有9个地级行政区划单位（其中：6个地级市包括1个较大的市：贵阳、3个自治州），88个县级行政区划单位（其中：13个市辖区、7个县级市、56个县、11个自治县、1个特区），1558个乡级行政区划单位（其中：113个街道、694个镇、501个乡、250个民族乡）');
INSERT INTO `local_province` VALUES ('19', '云南省', '云', '滇', '1', '昆明', '6', '39', '4528', '650021', '530000', '共有16个地级行政区划单位（其中：8个地级市包括1个较大的市：昆明、8个自治州），129个县级行政区划单位（其中：13个市辖区、11个县级市、76个县、29个自治县），1362个乡级行政区划单位（其中：118个街道、577个镇、522个乡、145个民族乡）');
INSERT INTO `local_province` VALUES ('20', '陕西省', '陕', '秦', '1', '西安', '7', '21', '3874', '710004', '610000', '共有10个地级行政区划单位（其中：10个地级市包括1个副省级市：西安、1个省级示范区杨凌示范区），107个县级行政区划单位（其中：24个市辖区、3个县级市、80个县），1418个乡级行政区划单位（其中：199个街道、1137个镇、82个乡）');
INSERT INTO `local_province` VALUES ('21', '甘肃省', '甘', '陇', '1', '兰州', '7', '43', '2712', '730030', '620000', '共有14个地级行政区划单位（其中：12个地级市包括1个较大的市：兰州、2个自治州），86个县级行政区划单位（其中：17个市辖区、4个县级市、58个县、7个自治县），1353个乡级行政区划单位（其中：126个街道、468个镇、725个乡、34个民族乡）');
INSERT INTO `local_province` VALUES ('22', '青海省', '青', '', '1', '西宁', '7', '72', '550', '810000', '630000', '共有8个地级行政区划单位（其中：1个地级市即较大的市：西宁、1个地区、6个自治州），43个县级行政区划单位（其中：4个市辖区、2个县级市、30个县、7个自治县），396个乡级行政区划单位（其中：30个街道、137个镇、201个乡、28个民族乡）');
INSERT INTO `local_province` VALUES ('23', '台湾省', '台', '', '1', '台北', '8', '3.6', '2337.35', null, null, null);
INSERT INTO `local_province` VALUES ('24', '内蒙古自治区', '蒙', '', '2', '呼和浩特', '1', '118', '2470', '010055', '150000', '共有12个地级行政区划单位（其中：9个地级市（包括2个较大的市：呼和浩特、包头）、3个盟），101个县级行政区划单位（其中：21个市辖区、11个县级市、17个县、49个旗、3个自治旗），909个乡级行政区划单位（其中：240个街道、477个镇、68个乡、17个民族乡、106个苏木、1个民族苏木）');
INSERT INTO `local_province` VALUES ('25', '广西壮族自治区', '桂', '', '2', '南宁', '5', '24', '5331', '530012', '450000', '共有14个地级行政区划单位（其中：14个地级市包括1个较大的市：南宁），109个县级行政区划单位（其中：34个市辖区、7个县级市、56个县、12个自治县），1235个乡级行政区划单位（其中：109个街道、702个镇、366个乡、58个民族乡）');
INSERT INTO `local_province` VALUES ('26', '西藏自治区', '藏', '', '2', '拉萨', '6', '123', '294', '850000', '540000', '共有7个地级行政区划单位（其中：1个地级市即较大的市：拉萨、6个地区），73个县级行政区划单位（其中：1个市辖区、1个县级市、71个县），692个乡级行政区划单位（其中：10个街道、140个镇、533个乡、9个民族乡）');
INSERT INTO `local_province` VALUES ('27', '宁夏回族自治区', '宁', '', '2', '银川', '7', '6.6', '643', '750001', '640000', '共有5个地级行政区划单位（其中：5个地级市包括1个较大的市：银川），22个县级行政区划单位（其中：9个市辖区、2个县级市、11个县），237个乡级行政区划单位（其中：44个街道、101个镇、92个乡');
INSERT INTO `local_province` VALUES ('28', '新疆维吾尔自治区', '新', '', '2', '乌鲁木齐', '7', '166', '2164', '830041', '650000', '共有14个地级行政区划单位（其中：2个地级市包括1个较大的市：乌鲁木齐、7个地区、5个自治州），99个县级行政区划单位（其中：11个市辖区、20个县级市、62个县、6个自治县），1020个乡级行政区划单位（其中：1个区公所、162个街道、242个镇、573个乡、42个民族乡）');
INSERT INTO `local_province` VALUES ('29', '北京市', '京', '', '3', '北京', '1', '1.7', '1262', '100001', '110000', '共有16个县级行政区划单位（其中：14个市辖区、2个县），322个乡级行政区划单位（其中：140个街道、144个镇、33个乡、5个民族乡）');
INSERT INTO `local_province` VALUES ('30', '天津市', '津', '', '3', '天津', '1', '1.2', '990', '300040', '120000', '共有16个县级行政区划单位（其中：13个市辖区、3个县），244个乡级行政区划单位（其中：110个街道、123个镇、10个乡、1个民族乡）');
INSERT INTO `local_province` VALUES ('31', '上海市', '沪', '申', '3', '上海', '3', '0.634', '1412', '200003', '310000', '共有17个县级行政区划单位（其中：16个市辖区、1个县），209个乡级行政区划单位（其中：99个街道、108个镇、2个乡');
INSERT INTO `local_province` VALUES ('32', '重庆市', '渝', '', '3', '重庆', '6', '8.2', '3303', '400015', '500000', '共有38个县级行政区划单位（其中：19个市辖区、15个县、4个自治县），1012个乡级行政区划单位（其中：189个街道、598个镇、211个乡、14个民族乡）');
INSERT INTO `local_province` VALUES ('33', '香港特别行政区', '香港', '', '4', '香港', '8', '0.1104', '703.35', null, null, null);
INSERT INTO `local_province` VALUES ('34', '澳门特别行政区', '澳门', '', '4', '澳门', '8', '0.00292', '54.22', null, null, null);

-- ----------------------------
-- Table structure for local_province_type
-- ----------------------------
DROP TABLE IF EXISTS `local_province_type`;
CREATE TABLE `local_province_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL COMMENT '行政区域级别',
  `level` int(11) NOT NULL COMMENT '行政区级别',
  PRIMARY KEY (`id`),
  KEY `level` (`level`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='中国行政区域类型:[省、自治区、直辖市]';

-- ----------------------------
-- Records of local_province_type
-- ----------------------------
INSERT INTO `local_province_type` VALUES ('1', '省', '1');
INSERT INTO `local_province_type` VALUES ('2', '自治区', '1');
INSERT INTO `local_province_type` VALUES ('3', '直辖市', '1');
INSERT INTO `local_province_type` VALUES ('4', '特别行政区', '1');

-- ----------------------------
-- Table structure for loggerinfo
-- ----------------------------
DROP TABLE IF EXISTS `loggerinfo`;
CREATE TABLE `loggerinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `log_type` varchar(255) DEFAULT NULL COMMENT '类型',
  `user_name` varchar(255) DEFAULT NULL COMMENT '操作人用户名/别名',
  `host_name` varchar(255) DEFAULT NULL COMMENT '主机',
  `operating_time` varchar(255) DEFAULT NULL COMMENT '操作时间',
  `log_content` varchar(255) DEFAULT NULL COMMENT '日志内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of loggerinfo
-- ----------------------------
INSERT INTO `loggerinfo` VALUES ('1', '登陆系统', 'admin', '192.168.2.139', '2014-10-27 14:40:27', '用户【admin】登陆成功!');
INSERT INTO `loggerinfo` VALUES ('2', '登陆系统', 'admin', '192.168.2.139', '2014-10-27 14:43:42', '用户【admin】登陆成功!');
INSERT INTO `loggerinfo` VALUES ('3', '登陆系统', 'admin', '192.168.2.164', '2014-11-10 11:07:59', '用户【admin】登陆成功!');
INSERT INTO `loggerinfo` VALUES ('4', '退出系统', 'admin', '192.168.2.164', '2014-11-10 11:09:23', '用户【admin】退出系统!');
INSERT INTO `loggerinfo` VALUES ('5', '登陆系统', 'admin', '192.168.2.164', '2014-11-18 16:48:11', '用户【admin】登陆成功!');
INSERT INTO `loggerinfo` VALUES ('6', '退出系统', 'admin', '192.168.2.164', '2014-11-18 16:56:11', '用户【admin】退出系统!');

-- ----------------------------
-- Table structure for log_type
-- ----------------------------
DROP TABLE IF EXISTS `log_type`;
CREATE TABLE `log_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of log_type
-- ----------------------------
INSERT INTO `log_type` VALUES ('1', '用户名错误或不存在');
INSERT INTO `log_type` VALUES ('2', '登录密码错误');
INSERT INTO `log_type` VALUES ('3', '登录系统');
INSERT INTO `log_type` VALUES ('4', '修改登录密码');
INSERT INTO `log_type` VALUES ('5', '退出系统');

-- ----------------------------
-- Table structure for m_echarts
-- ----------------------------
DROP TABLE IF EXISTS `m_echarts`;
CREATE TABLE `m_echarts` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of m_echarts
-- ----------------------------

-- ----------------------------
-- Table structure for m_fusioncharts
-- ----------------------------
DROP TABLE IF EXISTS `m_fusioncharts`;
CREATE TABLE `m_fusioncharts` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '节点id',
  `chartName` varchar(255) NOT NULL COMMENT '节点名称',
  `href` varchar(255) NOT NULL COMMENT '节点路径',
  `parentId` int(11) NOT NULL COMMENT '节点父id',
  `desc` varchar(255) NOT NULL COMMENT '节点描述',
  `iconCls` varchar(255) NOT NULL COMMENT '节点图标样式',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of m_fusioncharts
-- ----------------------------
INSERT INTO `m_fusioncharts` VALUES ('1', '柱状图和直方图(2D、3D)', '', '-1', 'Column & Bar Charts', 'icon-chart_bar');
INSERT INTO `m_fusioncharts` VALUES ('2', '直线图和面积图(2D、3D)', '', '-1', 'Line & Area Charts', 'icon-chart_line ');
INSERT INTO `m_fusioncharts` VALUES ('3', '饼图和圆环图(2D、3D)', '', '-1', '', 'icon-Pie_Chart');
INSERT INTO `m_fusioncharts` VALUES ('4', '散点图和气泡图(2D)', '', '-1', '', 'icon-scatter');
INSERT INTO `m_fusioncharts` VALUES ('5', '堆栈图表(2D、3D)', '', '-1', '', 'icon-stack');
INSERT INTO `m_fusioncharts` VALUES ('6', '双轴图表(2D、3D)', '', '-1', '', 'icon-biaxial');
INSERT INTO `m_fusioncharts` VALUES ('7', '分层柱状图(2D、3D)', '', '-1', '', 'icon-Layer_Columnar');
INSERT INTO `m_fusioncharts` VALUES ('8', '分层条形图(2D、3D)', '', '-1', '', 'icon-Layer_bar');
INSERT INTO `m_fusioncharts` VALUES ('9', '分层面积图(2D)', '', '-1', '', '');
INSERT INTO `m_fusioncharts` VALUES ('10', '多序列分层柱状图(2D)', '', '-1', '', '');
INSERT INTO `m_fusioncharts` VALUES ('11', '组合图表(2D、3D)', '', '-1', '', '');
INSERT INTO `m_fusioncharts` VALUES ('12', '滚动图', '', '-1', '', '');
INSERT INTO `m_fusioncharts` VALUES ('13', '真3D图表', '', '-1', '', '');

-- ----------------------------
-- Table structure for ontology
-- ----------------------------
DROP TABLE IF EXISTS `ontology`;
CREATE TABLE `ontology` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tree_id` int(11) NOT NULL DEFAULT '0' COMMENT '本体树ID',
  `ontology_id` varchar(255) NOT NULL DEFAULT '0' COMMENT '本体ID',
  `ontology_parent_id` varchar(255) NOT NULL DEFAULT '0' COMMENT '本体父节点ID',
  `ontology_name` varchar(255) NOT NULL DEFAULT '' COMMENT '本体名称',
  `ontology_level` int(11) NOT NULL DEFAULT '0' COMMENT '级别',
  `weight` int(11) DEFAULT '65535',
  `learn_status` int(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `tree_id` (`tree_id`,`ontology_id`,`ontology_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=871 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ontology
-- ----------------------------
INSERT INTO `ontology` VALUES ('1', '1', '0', '-1', '海尔TCE环节', '-1', '65535', '2');
INSERT INTO `ontology` VALUES ('2', '1', '10', '0', '购买体验环节', '0', '65535', '0');
INSERT INTO `ontology` VALUES ('3', '1', '10_001', '10', '产品选择购买体验', '1', '65535', '0');
INSERT INTO `ontology` VALUES ('4', '1', '11', '0', '送货安装环节', '0', '65535', '0');
INSERT INTO `ontology` VALUES ('5', '1', '11_001', '11', '送货', '1', '65535', '0');
INSERT INTO `ontology` VALUES ('6', '1', '11_002', '11', '安装', '1', '65535', '2');
INSERT INTO `ontology` VALUES ('7', '1', '12', '0', '产品使用环节', '0', '65535', '0');
INSERT INTO `ontology` VALUES ('8', '1', '13', '0', '服务环节', '0', '65535', '0');
INSERT INTO `ontology` VALUES ('9', '1', '14', '0', '老用户关怀环节', '0', '65535', '0');
INSERT INTO `ontology` VALUES ('17', '1', '14_001', '14', '购买产品后对海尔关怀服务', '1', '65535', '0');
INSERT INTO `ontology` VALUES ('18', '1', '14_002', '14', '再购买或推荐亲戚朋友购买', '1', '65535', '0');
INSERT INTO `ontology` VALUES ('20', '1', '12_001', '12', '产品使用便利性', '1', '65535', '0');
INSERT INTO `ontology` VALUES ('21', '1', '12_003', '12', '产品质量可靠性', '1', '65535', '0');
INSERT INTO `ontology` VALUES ('22', '1', '13_001', '13', '服务规范及态度', '1', '65535', '0');
INSERT INTO `ontology` VALUES ('23', '1', '13_002', '13', '服务技能', '1', '65535', '0');
INSERT INTO `ontology` VALUES ('25', '1', '13_004', '13', '服务速度', '1', '65535', '0');
INSERT INTO `ontology` VALUES ('26', '1', '13_005', '13', '电话接听安排处理', '1', '65535', '0');
INSERT INTO `ontology` VALUES ('347', '2', '0', '-1', '产品评价', '-1', '65535', '2');
INSERT INTO `ontology` VALUES ('348', '3', '0', '-1', '服务评价', '-1', '65535', '2');
INSERT INTO `ontology` VALUES ('349', '2', '36', '0', '产品使用便利性', '0', '65535', '0');
INSERT INTO `ontology` VALUES ('350', '2', '37', '0', '产品噪音', '0', '65535', '0');
INSERT INTO `ontology` VALUES ('351', '2', '38', '0', '产品外观', '0', '65535', '0');
INSERT INTO `ontology` VALUES ('352', '2', '58_001', '58', '产品质量可靠性', '1', '65535', '0');
INSERT INTO `ontology` VALUES ('355', '2', '42', '0', '安全隐患', '0', '65535', '0');
INSERT INTO `ontology` VALUES ('356', '2', '43', '0', '新技术', '0', '65535', '0');
INSERT INTO `ontology` VALUES ('357', '2', '44', '0', '配件问题', '0', '65535', '0');
INSERT INTO `ontology` VALUES ('358', '3', '45', '0', '讲解指导', '0', '65535', '0');
INSERT INTO `ontology` VALUES ('359', '3', '46', '0', '安排调度', '0', '65535', '0');
INSERT INTO `ontology` VALUES ('361', '3', '65_001', '65', '服务技能', '1', '65535', '0');
INSERT INTO `ontology` VALUES ('362', '3', '52_001', '52', '服务流程', '1', '65535', '0');
INSERT INTO `ontology` VALUES ('363', '3', '65_003', '65', '服务规范', '1', '65535', '0');
INSERT INTO `ontology` VALUES ('364', '3', '50', '0', '服务速度', '0', '65535', '0');
INSERT INTO `ontology` VALUES ('365', '3', '51', '0', '电话接听安排处理', '0', '65535', '0');
INSERT INTO `ontology` VALUES ('366', '3', '52', '0', '收费服务', '0', '65535', '0');
INSERT INTO `ontology` VALUES ('367', '3', '53', '0', '关怀服务满意度', '0', '65535', '0');
INSERT INTO `ontology` VALUES ('368', '3', '54', '0', '继续购买并推荐产品', '0', '65535', '0');
INSERT INTO `ontology` VALUES ('410', '4', '0', '-1', '评价类型', '-1', '65535', '2');
INSERT INTO `ontology` VALUES ('451', '4', '55', '0', '意见', '0', '65535', '0');
INSERT INTO `ontology` VALUES ('453', '5', '0', '-1', '海尔产品', '-1', '65535', '0');
INSERT INTO `ontology` VALUES ('454', '5', '15', '0', '卡萨帝', '0', '65535', '0');
INSERT INTO `ontology` VALUES ('455', '5', '16', '0', 'FPA', '0', '65535', '0');
INSERT INTO `ontology` VALUES ('456', '5', '17', '0', 'GE', '0', '65535', '0');
INSERT INTO `ontology` VALUES ('457', '5', '18', '0', 'SIO', '0', '65535', '0');
INSERT INTO `ontology` VALUES ('458', '5', '19', '0', '富可视', '0', '65535', '0');
INSERT INTO `ontology` VALUES ('459', '5', '20', '0', '睿侠', '0', '65535', '0');
INSERT INTO `ontology` VALUES ('460', '5', '21', '0', 'LG', '0', '65535', '0');
INSERT INTO `ontology` VALUES ('461', '5', '22', '0', 'TCL', '0', '65535', '0');
INSERT INTO `ontology` VALUES ('462', '5', '23', '0', '惠康', '0', '65535', '0');
INSERT INTO `ontology` VALUES ('463', '5', '24', '0', '康宝', '0', '65535', '0');
INSERT INTO `ontology` VALUES ('464', '5', '25', '0', '七喜', '0', '65535', '0');
INSERT INTO `ontology` VALUES ('465', '5', '26', '0', '日日顺', '0', '65535', '0');
INSERT INTO `ontology` VALUES ('466', '5', '27', '0', '三菱', '0', '65535', '0');
INSERT INTO `ontology` VALUES ('467', '5', '28', '0', '山水', '0', '65535', '0');
INSERT INTO `ontology` VALUES ('468', '5', '29', '0', '索尼', '0', '65535', '0');
INSERT INTO `ontology` VALUES ('469', '5', '30', '0', '统帅', '0', '65535', '0');
INSERT INTO `ontology` VALUES ('470', '5', '31', '0', '小鸭', '0', '65535', '0');
INSERT INTO `ontology` VALUES ('471', '5', '32', '0', '新宝', '0', '65535', '0');
INSERT INTO `ontology` VALUES ('472', '5', '33', '0', '樱奇', '0', '65535', '0');
INSERT INTO `ontology` VALUES ('473', '5', '34', '0', '月兔', '0', '65535', '0');
INSERT INTO `ontology` VALUES ('474', '5', '35', '0', '海尔', '0', '65535', '0');
INSERT INTO `ontology` VALUES ('475', '5', '15_001', '15', '冰箱/冷柜', '1', '65535', '0');
INSERT INTO `ontology` VALUES ('476', '5', '15_002', '15', '洗衣机', '1', '65535', '0');
INSERT INTO `ontology` VALUES ('477', '5', '15_003', '15', '热水器', '1', '65535', '0');
INSERT INTO `ontology` VALUES ('478', '5', '15_004', '15', '彩电', '1', '65535', '0');
INSERT INTO `ontology` VALUES ('479', '5', '15_005', '15', '空调', '1', '65535', '0');
INSERT INTO `ontology` VALUES ('480', '5', '15_006', '15', '厨房电器', '1', '65535', '0');
INSERT INTO `ontology` VALUES ('481', '5', '15_007', '15', '生活家电', '1', '65535', '0');
INSERT INTO `ontology` VALUES ('482', '5', '16_001', '16', '冰箱', '1', '65535', '0');
INSERT INTO `ontology` VALUES ('483', '5', '16_002', '16', '洗衣机', '1', '65535', '0');
INSERT INTO `ontology` VALUES ('484', '5', '16_003', '16', '厨房电器', '1', '65535', '0');
INSERT INTO `ontology` VALUES ('485', '5', '17_001', '17', '冰箱/冷柜', '1', '65535', '0');
INSERT INTO `ontology` VALUES ('486', '5', '17_002', '17', '洗衣机', '1', '65535', '0');
INSERT INTO `ontology` VALUES ('487', '5', '17_003', '17', '空调', '1', '65535', '0');
INSERT INTO `ontology` VALUES ('488', '5', '18_001', '18', '彩电', '1', '65535', '0');
INSERT INTO `ontology` VALUES ('489', '5', '19_001', '19', '彩电', '1', '65535', '0');
INSERT INTO `ontology` VALUES ('490', '5', '20_001', '20', '彩电', '1', '65535', '0');
INSERT INTO `ontology` VALUES ('491', '5', '21_001', '21', '冰箱/冷柜', '1', '65535', '0');
INSERT INTO `ontology` VALUES ('492', '5', '21_002', '21', '洗衣机', '1', '65535', '0');
INSERT INTO `ontology` VALUES ('493', '5', '21_003', '21', '彩电', '1', '65535', '0');
INSERT INTO `ontology` VALUES ('494', '5', '22_001', '22', '冰箱/冷柜', '1', '65535', '0');
INSERT INTO `ontology` VALUES ('495', '5', '22_002', '22', '洗衣机', '1', '65535', '0');
INSERT INTO `ontology` VALUES ('496', '5', '22_003', '22', '彩电', '1', '65535', '0');
INSERT INTO `ontology` VALUES ('497', '5', '22_004', '22', '空调', '1', '65535', '0');
INSERT INTO `ontology` VALUES ('498', '5', '22_005', '22', '挂架', '1', '65535', '0');
INSERT INTO `ontology` VALUES ('499', '5', '23_001', '23', '冰箱/冷柜', '1', '65535', '0');
INSERT INTO `ontology` VALUES ('500', '5', '23_002', '23', '洗衣机', '1', '65535', '0');
INSERT INTO `ontology` VALUES ('501', '5', '23_003', '23', '空调', '1', '65535', '0');
INSERT INTO `ontology` VALUES ('502', '5', '24_001', '24', '热水器', '1', '65535', '0');
INSERT INTO `ontology` VALUES ('503', '5', '24_002', '24', '厨房电器', '1', '65535', '0');
INSERT INTO `ontology` VALUES ('504', '5', '24_003', '24', '生活家电', '1', '65535', '0');
INSERT INTO `ontology` VALUES ('505', '5', '25_001', '25', '彩电', '1', '65535', '0');
INSERT INTO `ontology` VALUES ('506', '5', '26_001', '26', '冰箱/冷柜', '1', '65535', '0');
INSERT INTO `ontology` VALUES ('507', '5', '26_002', '26', '洗衣机', '1', '65535', '0');
INSERT INTO `ontology` VALUES ('508', '5', '27_001', '27', '空调', '1', '65535', '0');
INSERT INTO `ontology` VALUES ('509', '5', '28_001', '28', '彩电', '1', '65535', '0');
INSERT INTO `ontology` VALUES ('510', '5', '29_001', '29', '视讯产品', '1', '65535', '0');
INSERT INTO `ontology` VALUES ('511', '5', '30_001', '30', '冰箱/冷柜', '1', '65535', '0');
INSERT INTO `ontology` VALUES ('512', '5', '30_002', '30', '洗衣机', '1', '65535', '0');
INSERT INTO `ontology` VALUES ('513', '5', '30_003', '30', '热水器', '1', '65535', '0');
INSERT INTO `ontology` VALUES ('514', '5', '30_004', '30', '彩电', '1', '65535', '0');
INSERT INTO `ontology` VALUES ('515', '5', '30_005', '30', '空调', '1', '65535', '0');
INSERT INTO `ontology` VALUES ('516', '5', '30_006', '30', '厨房电器', '1', '65535', '0');
INSERT INTO `ontology` VALUES ('517', '5', '30_007', '30', '生活家电', '1', '65535', '0');
INSERT INTO `ontology` VALUES ('518', '5', '31_001', '31', '冰箱/冷柜', '1', '65535', '0');
INSERT INTO `ontology` VALUES ('519', '5', '31_002', '31', '洗衣机', '1', '65535', '0');
INSERT INTO `ontology` VALUES ('520', '5', '32_001', '32', '冰箱/冷柜', '1', '65535', '0');
INSERT INTO `ontology` VALUES ('521', '5', '32_002', '32', '洗衣机', '1', '65535', '0');
INSERT INTO `ontology` VALUES ('522', '5', '32_003', '32', '热水器', '1', '65535', '0');
INSERT INTO `ontology` VALUES ('523', '5', '32_004', '32', '彩电', '1', '65535', '0');
INSERT INTO `ontology` VALUES ('524', '5', '32_005', '32', '空调', '1', '65535', '0');
INSERT INTO `ontology` VALUES ('525', '5', '32_006', '32', '厨房电器', '1', '65535', '0');
INSERT INTO `ontology` VALUES ('526', '5', '33_001', '33', '厨房电器', '1', '65535', '0');
INSERT INTO `ontology` VALUES ('527', '5', '34_001', '34', '空调', '1', '65535', '0');
INSERT INTO `ontology` VALUES ('528', '5', '35_001', '35', '洗衣机', '1', '65535', '0');
INSERT INTO `ontology` VALUES ('529', '5', '35_002', '35', '冰箱/冷柜', '1', '65535', '0');
INSERT INTO `ontology` VALUES ('530', '5', '35_003', '35', '空调', '1', '65535', '0');
INSERT INTO `ontology` VALUES ('531', '5', '35_004', '35', '热水器', '1', '65535', '0');
INSERT INTO `ontology` VALUES ('532', '5', '35_005', '35', '电视机', '1', '65535', '0');
INSERT INTO `ontology` VALUES ('533', '5', '35_006', '35', '厨电电器', '1', '65535', '0');
INSERT INTO `ontology` VALUES ('534', '5', '35_007', '35', '生活家电', '1', '65535', '0');
INSERT INTO `ontology` VALUES ('535', '5', '35_008', '35', '计算机', '1', '65535', '0');
INSERT INTO `ontology` VALUES ('536', '5', '35_009', '35', '手机、数码', '1', '65535', '0');
INSERT INTO `ontology` VALUES ('537', '5', '35_010', '35', '整体厨房', '1', '65535', '0');
INSERT INTO `ontology` VALUES ('538', '5', '15_001_001', '15_001', 'CASARTE冰箱', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('539', '5', '15_001_002', '15_001', 'CASARTE冷柜', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('540', '5', '15_002_001', '15_002', 'CASARTE波轮', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('541', '5', '15_002_002', '15_002', 'CASARTE滚筒', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('542', '5', '15_003_001', '15_003', 'CASARTE燃气热水器', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('543', '5', '15_003_002', '15_003', 'CASARTE电热水器', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('544', '5', '15_003_003', '15_003', 'CASARTE太阳热水器', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('545', '5', '15_004_001', '15_004', 'CASARTE平板电视', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('546', '5', '15_005_001', '15_005', 'CASARTE家用空调', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('547', '5', '15_006_001', '15_006', 'CASARTE消毒柜', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('548', '5', '15_006_002', '15_006', 'CASARTE燃气灶', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('549', '5', '15_006_003', '15_006', 'CASARTE吸油烟机', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('550', '5', '15_006_004', '15_006', 'CASARTE家用电烤箱', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('551', '5', '15_007_001', '15_007', 'CASARTE生活家电', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('552', '5', '16_001_001', '16_001', 'FPA冰箱', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('553', '5', '16_002_001', '16_002', 'FPA洗衣机', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('554', '5', '16_003_001', '16_003', 'FPA灶具', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('555', '5', '16_003_002', '16_003', 'FPA吸油烟机', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('556', '5', '16_003_003', '16_003', 'FPA烤箱', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('557', '5', '16_003_004', '16_003', 'FPA洗碗机', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('558', '5', '17_001_001', '17_001', 'GE冰箱', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('559', '5', '17_001_002', '17_001', 'GE特种冰箱', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('560', '5', '17_001_003', '17_001', 'GE冷柜', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('561', '5', '17_002_001', '17_002', 'GE波轮', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('562', '5', '17_002_002', '17_002', 'GE滚筒', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('563', '5', '17_003_001', '17_003', 'GE空调', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('564', '5', '18_001_001', '18_001', 'SIO彩电', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('565', '5', '19_001_001', '19_001', 'InFocus富可视彩电', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('566', '5', '20_001_001', '20_001', 'RadioShack睿侠彩电', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('567', '5', '21_001_001', '21_001', 'LG冰箱', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('568', '5', '21_002_001', '21_002', 'LG滚筒', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('569', '5', '21_003_001', '21_003', 'LG电视', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('570', '5', '22_001_001', '22_001', 'TCL冰箱', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('571', '5', '22_002_001', '22_002', 'TCL波轮', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('572', '5', '22_002_002', '22_002', 'TCL滚筒', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('573', '5', '22_003_001', '22_003', 'TCL彩电', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('574', '5', '22_003_002', '22_003', 'TCL电视', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('575', '5', '22_004_001', '22_004', 'TCL空调', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('576', '5', '22_005_001', '22_005', 'TCL挂架', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('577', '5', '23_001_001', '23_001', '惠康冰箱', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('578', '5', '23_001_002', '23_001', '惠康冷柜', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('579', '5', '23_002_001', '23_002', '惠康波轮', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('580', '5', '23_003_001', '23_003', '惠康空调', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('581', '5', '24_001_001', '24_001', '康宝电热水器', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('582', '5', '24_001_002', '24_001', '康宝燃气热水器', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('583', '5', '24_002_001', '24_002', '康宝消毒柜', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('584', '5', '24_002_002', '24_002', '康宝吸油烟机', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('585', '5', '24_002_003', '24_002', '康宝燃气灶', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('586', '5', '24_003_001', '24_003', '康宝电磁炉', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('587', '5', '24_003_002', '24_003', '康宝电饭煲', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('588', '5', '24_003_003', '24_003', '康宝电饼铛', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('589', '5', '24_003_004', '24_003', '康宝电压力锅', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('590', '5', '24_003_005', '24_003', '康宝电火锅', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('591', '5', '24_003_006', '24_003', '康宝电水壶', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('592', '5', '24_003_007', '24_003', '康宝果疏清理机', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('593', '5', '24_003_008', '24_003', '康宝豆浆机', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('594', '5', '25_001_001', '25_001', '七喜彩电', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('595', '5', '26_001_001', '26_001', '日日顺冰箱', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('596', '5', '26_001_002', '26_001', '日日顺冷柜', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('597', '5', '26_002_001', '26_002', '日日顺波轮', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('598', '5', '27_001_001', '27_001', '三菱空调', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('599', '5', '28_001_001', '28_001', '山水彩电', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('600', '5', '29_001_001', '29_001', '索尼DVD', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('601', '5', '29_001_002', '29_001', '索尼家庭影院', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('602', '5', '30_001_001', '30_001', '统帅冰箱', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('603', '5', '30_001_002', '30_001', '统帅特种冰箱', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('604', '5', '30_001_003', '30_001', '统帅冷柜', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('605', '5', '30_002_001', '30_002', '统帅波轮', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('606', '5', '30_002_002', '30_002', '统帅滚筒', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('607', '5', '30_003_001', '30_003', '统帅电热水器', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('608', '5', '30_003_002', '30_003', '统帅燃气热水器', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('609', '5', '30_003_003', '30_003', '统帅太阳热水器', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('610', '5', '30_004_001', '30_004', '统帅电视', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('611', '5', '30_005_001', '30_005', '统帅空调', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('612', '5', '30_006_001', '30_006', '统帅吸油烟机', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('613', '5', '30_006_002', '30_006', '统帅燃气灶', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('614', '5', '30_007_001', '30_007', '统帅手持式挂烫机', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('615', '5', '30_007_002', '30_007', '统帅电饭煲', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('616', '5', '30_007_003', '30_007', '统帅蒸汽拖把', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('617', '5', '30_007_004', '30_007', '统帅电水壶', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('618', '5', '30_007_005', '30_007', '统帅电火锅', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('619', '5', '30_007_006', '30_007', '统帅饮水机', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('620', '5', '30_007_007', '30_007', '统帅电饼铛', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('621', '5', '30_007_008', '30_007', '统帅加湿器', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('622', '5', '30_007_009', '30_007', '统帅电压力锅', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('623', '5', '30_007_010', '30_007', '统帅电磁炉', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('624', '5', '30_007_011', '30_007', '统帅豆浆机', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('625', '5', '31_001_001', '31_001', '小鸭冷柜', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('626', '5', '31_002_001', '31_002', '小鸭波轮', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('627', '5', '31_002_002', '31_002', '小鸭滚筒', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('628', '5', '32_001_001', '32_001', '新宝冰箱', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('629', '5', '32_002_001', '32_002', '新宝波轮', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('630', '5', '32_002_002', '32_002', '新宝滚筒', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('631', '5', '32_003_001', '32_003', '新宝电热水器', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('632', '5', '32_003_002', '32_003', '新宝燃气热水器', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('633', '5', '32_004_001', '32_004', '新宝彩电', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('634', '5', '32_005_001', '32_005', '新宝空调', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('635', '5', '32_006_001', '32_006', '新宝吸油烟机', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('636', '5', '32_006_002', '32_006', '新宝消毒柜', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('637', '5', '32_006_003', '32_006', '新宝燃气灶', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('638', '5', '33_001_001', '33_001', '樱奇消毒柜', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('639', '5', '33_001_002', '33_001', '樱奇吸油烟机', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('640', '5', '33_001_003', '33_001', '樱奇燃气灶', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('641', '5', '34_001_001', '34_001', '月兔空调', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('642', '5', '35_001_002_001', '35_001_002', '波轮（上开门）/甩干机', '3', '65535', '0');
INSERT INTO `ontology` VALUES ('643', '5', '35_001_002', '35_001', '滚筒（侧开门）/干衣机', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('644', '5', '35_002_001', '35_002', '单/两/三门冰箱', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('645', '5', '35_002_002', '35_002', '冷柜/冰吧', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('646', '5', '35_002_003', '35_002', '对开门冰箱', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('647', '5', '35_002_004', '35_002', '多门冰箱', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('648', '5', '35_002_005', '35_002', '医用冷柜', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('649', '5', '35_002_006', '35_002', '定制冷柜（雀巢、和路雪、蒙牛、可口可乐等）', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('650', '5', '35_002_007', '35_002', '展示柜', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('651', '5', '35_003_001', '35_003', '壁挂式空调', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('652', '5', '35_003_002', '35_003', '柜式空调', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('653', '5', '35_003_003', '35_003', '无氟空调', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('654', '5', '35_003_004', '35_003', '商用空调', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('655', '5', '35_004_001', '35_004', '电热水器', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('656', '5', '35_004_002', '35_004', '燃气热水器', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('657', '5', '35_004_003', '35_004', '太阳热水器', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('658', '5', '35_004_004', '35_004', '采暖炉', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('659', '5', '35_004_005', '35_004', '热泵热水器', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('660', '5', '35_004_006', '35_004', '软水机器', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('661', '5', '35_005_001', '35_005', '平板电视', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('662', '5', '35_005_002', '35_005', '彩电', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('663', '5', '35_005_003', '35_005', '机顶盒', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('664', '5', '35_005_004', '35_005', '分频器', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('665', '5', '35_006_001', '35_006', '吸油烟机', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('666', '5', '35_006_002', '35_006', '燃气灶', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('667', '5', '35_006_003', '35_006', '洗碗机', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('668', '5', '35_006_004', '35_006', '消毒柜', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('669', '5', '35_006_005', '35_006', '家用电烤箱', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('670', '5', '35_007_001', '35_007', '微波炉', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('671', '5', '35_007_002', '35_007', '吸尘器', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('672', '5', '35_007_003', '35_007', '净水机', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('673', '5', '35_007_004', '35_007', '电磁炉', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('674', '5', '35_007_005', '35_007', '电暖器', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('675', '5', '35_007_006', '35_007', '电饭煲', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('676', '5', '35_007_007', '35_007', '电压力锅', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('677', '5', '35_007_008', '35_007', '饮水机', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('678', '5', '35_007_009', '35_007', '豆浆机', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('679', '5', '35_007_010', '35_007', '挂熨机、电熨斗', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('680', '5', '35_007_011', '35_007', '电风扇', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('681', '5', '35_007_012', '35_007', '剃须刀', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('682', '5', '35_007_013', '35_007', '电水煲', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('683', '5', '35_007_014', '35_007', '空气清新机', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('684', '5', '35_007_015', '35_007', '其他', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('685', '5', '35_008_001', '35_008', '台式机/一体机', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('686', '5', '35_008_002', '35_008', '笔记本电脑', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('687', '5', '35_008_003', '35_008', '商用计算机', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('688', '5', '35_009_001', '35_009', '移动电话', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('689', '5', '35_009_002', '35_009', '视讯产品', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('690', '5', '35_009_003', '35_009', '数码产品', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('691', '5', '35_009_004', '35_009', '数码相机', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('692', '5', '35_009_005', '35_009', '投影机', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('693', '5', '35_009_006', '35_009', 'DVD', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('694', '5', '35_010_001', '35_010', '整体厨房', '2', '65535', '0');
INSERT INTO `ontology` VALUES ('695', '4', '56', '0', '建议', '0', '65535', '0');
INSERT INTO `ontology` VALUES ('696', '3', '65_002', '65', '服务态度', '1', '65535', '0');
INSERT INTO `ontology` VALUES ('700', '2', '58', '0', '可靠性', '0', '65535', '0');
INSERT INTO `ontology` VALUES ('701', '2', '38_009', '38', '板材', '1', '65535', '0');
INSERT INTO `ontology` VALUES ('702', '2', '38_002', '38', '把手', '1', '65535', '0');
INSERT INTO `ontology` VALUES ('703', '2', '38_010', '38', '显示', '1', '65535', '0');
INSERT INTO `ontology` VALUES ('704', '2', '38_004', '38', '控制', '1', '65535', '0');
INSERT INTO `ontology` VALUES ('705', '2', '38_005', '38', '颜色', '1', '65535', '0');
INSERT INTO `ontology` VALUES ('706', '2', '38_006', '38', '设计', '1', '65535', '0');
INSERT INTO `ontology` VALUES ('707', '2', '38_007', '38', '门体', '1', '65535', '0');
INSERT INTO `ontology` VALUES ('708', '2', '38_008', '38', '其他', '1', '65535', '0');
INSERT INTO `ontology` VALUES ('709', '2', '59', '0', '使用体验', '0', '65535', '0');
INSERT INTO `ontology` VALUES ('710', '2', '59_001', '59', '产品整体使用评价', '1', '65535', '0');
INSERT INTO `ontology` VALUES ('711', '2', '59_002', '59', '使用体验', '1', '65535', '0');
INSERT INTO `ontology` VALUES ('712', '2', '59_003', '59', '做工品质', '1', '65535', '0');
INSERT INTO `ontology` VALUES ('713', '2', '59_004', '59', '其他', '1', '65535', '0');
INSERT INTO `ontology` VALUES ('714', '2', '60', '0', '功能', '0', '65535', '0');
INSERT INTO `ontology` VALUES ('715', '2', '60_001', '60', '健康', '1', '65535', '0');
INSERT INTO `ontology` VALUES ('716', '2', '60_002', '60', '人性化', '1', '65535', '0');
INSERT INTO `ontology` VALUES ('717', '2', '60_003', '60', '其他', '1', '65535', '0');
INSERT INTO `ontology` VALUES ('718', '2', '60_004', '60', '温控', '1', '65535', '0');
INSERT INTO `ontology` VALUES ('719', '2', '60_005', '60', '制冷', '1', '65535', '0');
INSERT INTO `ontology` VALUES ('720', '2', '60_006', '60', '容积', '1', '65535', '0');
INSERT INTO `ontology` VALUES ('721', '2', '61', '0', '内饰', '0', '65535', '0');
INSERT INTO `ontology` VALUES ('722', '2', '61_001', '61', '照明', '1', '65535', '0');
INSERT INTO `ontology` VALUES ('723', '2', '61_002', '61', '抽屉', '1', '65535', '0');
INSERT INTO `ontology` VALUES ('724', '2', '61_003', '61', '搁物架', '1', '65535', '0');
INSERT INTO `ontology` VALUES ('725', '2', '61_004', '61', '内部(空间)结构', '1', '65535', '0');
INSERT INTO `ontology` VALUES ('726', '2', '61_005', '61', '其他', '1', '65535', '0');
INSERT INTO `ontology` VALUES ('727', '2', '62', '0', '性能', '0', '65535', '0');
INSERT INTO `ontology` VALUES ('728', '2', '62_001', '62', '压缩机', '1', '65535', '0');
INSERT INTO `ontology` VALUES ('729', '2', '62_005', '62', '性能值', '1', '65535', '0');
INSERT INTO `ontology` VALUES ('730', '2', '62_003', '62', '其他', '1', '65535', '0');
INSERT INTO `ontology` VALUES ('731', '2', '59_005', '59', '说明书', '1', '65535', '0');
INSERT INTO `ontology` VALUES ('732', '3', '63', '0', '发票', '0', '65535', '0');
INSERT INTO `ontology` VALUES ('733', '3', '64', '0', '物流', '0', '65535', '0');
INSERT INTO `ontology` VALUES ('734', '3', '64_001', '64', '物流及时性', '1', '65535', '0');
INSERT INTO `ontology` VALUES ('735', '3', '64_002', '64', '包装', '1', '65535', '0');
INSERT INTO `ontology` VALUES ('736', '3', '64_003', '64', '其他', '1', '65535', '0');
INSERT INTO `ontology` VALUES ('737', '1', '13_006', '13', '发票', '1', '65535', '0');
INSERT INTO `ontology` VALUES ('738', '3', '65', '0', '安装人员', '0', '65535', '0');
INSERT INTO `ontology` VALUES ('739', '3', '66', '0', '送货人员', '0', '65535', '0');
INSERT INTO `ontology` VALUES ('740', '3', '66_001', '66', '服务规范', '1', '65535', '0');
INSERT INTO `ontology` VALUES ('741', '3', '66_002', '66', '服务态度', '1', '65535', '0');
INSERT INTO `ontology` VALUES ('742', '3', '67', '0', '现场布置', '0', '65535', '0');
INSERT INTO `ontology` VALUES ('743', '3', '68', '0', '服务人员', '0', '65535', '0');
INSERT INTO `ontology` VALUES ('744', '3', '68_001', '68', '服务态度', '1', '65535', '0');
INSERT INTO `ontology` VALUES ('745', '3', '68_002', '68', '服务技能', '1', '65535', '0');
INSERT INTO `ontology` VALUES ('867', '6', '0', '-1', '测试同步表达式', '-1', '65535', '0');
INSERT INTO `ontology` VALUES ('870', '7', '0', '-1', '单节点导入数据', '-1', '65535', '0');

-- ----------------------------
-- Table structure for ontology_resource
-- ----------------------------
DROP TABLE IF EXISTS `ontology_resource`;
CREATE TABLE `ontology_resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tree_id` int(11) NOT NULL DEFAULT '0' COMMENT '本体树ID',
  `ontology_id` varchar(255) NOT NULL DEFAULT '0' COMMENT '本体ID',
  `ontology_expression` varchar(255) NOT NULL DEFAULT '0' COMMENT '本体表达式',
  `langue_type` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `tree_id` (`tree_id`,`ontology_id`,`ontology_expression`) USING BTREE,
  KEY `ontology_expression` (`ontology_expression`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1593 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ontology_resource
-- ----------------------------
INSERT INTO `ontology_resource` VALUES ('842', '1', '11_002', 'e_清理', '0');
INSERT INTO `ontology_resource` VALUES ('861', '1', '13_001', 'e_服务', '0');
INSERT INTO `ontology_resource` VALUES ('870', '1', '13_004', 'c_是否', '0');
INSERT INTO `ontology_resource` VALUES ('880', '1', '14_001', '#c_无人问津#', '0');
INSERT INTO `ontology_resource` VALUES ('887', '4', '56', 'k_有待提高', '0');
INSERT INTO `ontology_resource` VALUES ('888', '4', '56', 'k_仍需要', '0');
INSERT INTO `ontology_resource` VALUES ('889', '4', '56', 'k_应该', '0');
INSERT INTO `ontology_resource` VALUES ('890', '4', '56', 'k_希望', '0');
INSERT INTO `ontology_resource` VALUES ('891', '4', '56', 'k_建议', '0');
INSERT INTO `ontology_resource` VALUES ('896', '4', '56', 'k_感觉比较浪费', '0');
INSERT INTO `ontology_resource` VALUES ('897', '4', '56', 'k_有待加强', '0');
INSERT INTO `ontology_resource` VALUES ('898', '4', '56', 'k_请', '0');
INSERT INTO `ontology_resource` VALUES ('899', '4', '56', 'k_请说明', '0');
INSERT INTO `ontology_resource` VALUES ('900', '4', '56', 'k_见意', '0');
INSERT INTO `ontology_resource` VALUES ('901', '4', '56', 'k_加强', '0');
INSERT INTO `ontology_resource` VALUES ('902', '4', '56', 'k_最好', '0');
INSERT INTO `ontology_resource` VALUES ('903', '4', '56', 'k_可以考虑', '0');
INSERT INTO `ontology_resource` VALUES ('904', '4', '56', 'k_还要', '0');
INSERT INTO `ontology_resource` VALUES ('905', '4', '56', 'k_避免', '0');
INSERT INTO `ontology_resource` VALUES ('906', '4', '56', 'k_继续加强', '0');
INSERT INTO `ontology_resource` VALUES ('907', '4', '56', 'k_继续', '0');
INSERT INTO `ontology_resource` VALUES ('908', '4', '56', 'k_继续发扬', '0');
INSERT INTO `ontology_resource` VALUES ('909', '4', '56', 'k_需要', '0');
INSERT INTO `ontology_resource` VALUES ('910', '4', '56', 'k_也需要', '0');
INSERT INTO `ontology_resource` VALUES ('911', '4', '56', 'k_如果', '0');
INSERT INTO `ontology_resource` VALUES ('912', '4', '56', 'k_如果能', '0');
INSERT INTO `ontology_resource` VALUES ('913', '4', '56', 'k_应', '0');
INSERT INTO `ontology_resource` VALUES ('914', '4', '56', 'k_应加强', '0');
INSERT INTO `ontology_resource` VALUES ('915', '4', '56', 'k_应考虑', '0');
INSERT INTO `ontology_resource` VALUES ('916', '4', '56', 'k_以便', '0');
INSERT INTO `ontology_resource` VALUES ('917', '4', '56', 'k_请及时', '0');
INSERT INTO `ontology_resource` VALUES ('918', '4', '56', 'k_提供', '0');
INSERT INTO `ontology_resource` VALUES ('919', '4', '56', 'k_还有待提高', '0');
INSERT INTO `ontology_resource` VALUES ('920', '4', '56', 'k_可', '0');
INSERT INTO `ontology_resource` VALUES ('921', '4', '56', 'k_并', '0');
INSERT INTO `ontology_resource` VALUES ('922', '4', '56', 'k_扩大宣传', '0');
INSERT INTO `ontology_resource` VALUES ('923', '4', '55', 'k_没人清楚', '0');
INSERT INTO `ontology_resource` VALUES ('924', '4', '55', 'k_缺乏', '0');
INSERT INTO `ontology_resource` VALUES ('925', '4', '55', 'k_有误差', '0');
INSERT INTO `ontology_resource` VALUES ('926', '4', '55', 'k_不了解', '0');
INSERT INTO `ontology_resource` VALUES ('927', '4', '55', 'k_没有', '0');
INSERT INTO `ontology_resource` VALUES ('928', '4', '56', 'k_确认', '0');
INSERT INTO `ontology_resource` VALUES ('929', '4', '55', 'k_太恶心', '0');
INSERT INTO `ontology_resource` VALUES ('930', '4', '55', 'k_太麻烦', '0');
INSERT INTO `ontology_resource` VALUES ('931', '4', '55', 'k_不过关', '0');
INSERT INTO `ontology_resource` VALUES ('932', '4', '55', 'k_投诉', '0');
INSERT INTO `ontology_resource` VALUES ('933', '4', '55', 'k_不够用', '0');
INSERT INTO `ontology_resource` VALUES ('934', '4', '55', 'k_未装', '0');
INSERT INTO `ontology_resource` VALUES ('935', '4', '55', 'k_未检测', '0');
INSERT INTO `ontology_resource` VALUES ('936', '4', '55', 'k_不能', '0');
INSERT INTO `ontology_resource` VALUES ('937', '4', '55', 'k_没法比', '0');
INSERT INTO `ontology_resource` VALUES ('938', '4', '55', 'k_不足', '0');
INSERT INTO `ontology_resource` VALUES ('939', '4', '55', 'k_太难太上火还惹不起', '0');
INSERT INTO `ontology_resource` VALUES ('940', '4', '55', 'k_还惹不起', '0');
INSERT INTO `ontology_resource` VALUES ('941', '4', '55', 'k_太上火', '0');
INSERT INTO `ontology_resource` VALUES ('942', '4', '55', 'k_没安装好', '0');
INSERT INTO `ontology_resource` VALUES ('943', '4', '55', 'k_怀疑', '0');
INSERT INTO `ontology_resource` VALUES ('944', '4', '55', 'k_不太专业', '0');
INSERT INTO `ontology_resource` VALUES ('945', '4', '55', 'k_也太不', '0');
INSERT INTO `ontology_resource` VALUES ('946', '4', '55', 'k_太慢', '0');
INSERT INTO `ontology_resource` VALUES ('947', '4', '55', 'k_太短', '0');
INSERT INTO `ontology_resource` VALUES ('948', '4', '55', 'k_未解释', '0');
INSERT INTO `ontology_resource` VALUES ('949', '4', '55', 'k_安装不对', '0');
INSERT INTO `ontology_resource` VALUES ('950', '4', '55', 'k_不理想', '0');
INSERT INTO `ontology_resource` VALUES ('951', '4', '55', 'k_不合格', '0');
INSERT INTO `ontology_resource` VALUES ('952', '4', '55', 'k_不要', '0');
INSERT INTO `ontology_resource` VALUES ('953', '4', '55', 'k_不负责', '0');
INSERT INTO `ontology_resource` VALUES ('954', '4', '55', 'k_难打开', '0');
INSERT INTO `ontology_resource` VALUES ('955', '4', '55', 'k_不可能', '0');
INSERT INTO `ontology_resource` VALUES ('956', '4', '55', 'k_较低', '0');
INSERT INTO `ontology_resource` VALUES ('957', '4', '55', 'k_不能下载', '0');
INSERT INTO `ontology_resource` VALUES ('958', '4', '55', 'k_未安排', '0');
INSERT INTO `ontology_resource` VALUES ('959', '4', '55', 'k_没人提及', '0');
INSERT INTO `ontology_resource` VALUES ('960', '4', '56', 'k_多开一些', '0');
INSERT INTO `ontology_resource` VALUES ('961', '4', '55', 'k_差太大', '0');
INSERT INTO `ontology_resource` VALUES ('962', '4', '55', 'k_权力太小', '0');
INSERT INTO `ontology_resource` VALUES ('963', '4', '55', 'k_不重视', '0');
INSERT INTO `ontology_resource` VALUES ('964', '4', '55', 'k_水量太小', '0');
INSERT INTO `ontology_resource` VALUES ('969', '2', '36', '#(e_日常使用+c_是否保养+c_方)#', '0');
INSERT INTO `ontology_resource` VALUES ('970', '2', '36', 'c_不方便', '0');
INSERT INTO `ontology_resource` VALUES ('971', '2', '36', 'e_商品+e_产品+k_有待提高', '0');
INSERT INTO `ontology_resource` VALUES ('973', '2', '36', '#k_没有开关#', '0');
INSERT INTO `ontology_resource` VALUES ('974', '2', '36', '#(e_日常使用+k_过于浪费+k_能否循环)#', '0');
INSERT INTO `ontology_resource` VALUES ('977', '2', '36', '#(e_机器|e_产品)+c_不方便+k_没有开关#', '0');
INSERT INTO `ontology_resource` VALUES ('978', '2', '37', '#e_产品+c_有噪音#', '0');
INSERT INTO `ontology_resource` VALUES ('979', '2', '37', '#k_减少+k_噪音#', '0');
INSERT INTO `ontology_resource` VALUES ('980', '2', '37', '#e_产品+k_无噪音#', '0');
INSERT INTO `ontology_resource` VALUES ('986', '2', '58_001', '#(e_质量+c_稳定可靠)#', '0');
INSERT INTO `ontology_resource` VALUES ('987', '2', '58_001', '#(e_日常使用|e_商品)+c_感到#', '0');
INSERT INTO `ontology_resource` VALUES ('988', '2', '58_001', '#(e_质量+c_好)#', '0');
INSERT INTO `ontology_resource` VALUES ('989', '2', '58_001', '#(e_日常使用|e_商品)+c_安全+c_感到#', '0');
INSERT INTO `ontology_resource` VALUES ('991', '2', '58_001', '#e_商品+k_惊人#', '0');
INSERT INTO `ontology_resource` VALUES ('992', '2', '58_001', '#(e_机器|e_质量|e_商品)+k_没得说#', '0');
INSERT INTO `ontology_resource` VALUES ('993', '2', '58_001', 'e_质量+k_信任', '0');
INSERT INTO `ontology_resource` VALUES ('994', '2', '58_001', '#(e_质量+c_一般)#', '0');
INSERT INTO `ontology_resource` VALUES ('996', '2', '58_001', '#c_不能正常使用+c_短期+c_反复维修#', '0');
INSERT INTO `ontology_resource` VALUES ('997', '2', '58_001', 'e_质量+k_断了', '0');
INSERT INTO `ontology_resource` VALUES ('998', '2', '58_001', 'k_异味+e_商品', '0');
INSERT INTO `ontology_resource` VALUES ('999', '2', '58_001', '#(e_质量+c_差)#', '0');
INSERT INTO `ontology_resource` VALUES ('1000', '2', '58_001', '#c_考虑+c_退换货#', '0');
INSERT INTO `ontology_resource` VALUES ('1001', '2', '58_001', 'c_反复维修+e_质量', '0');
INSERT INTO `ontology_resource` VALUES ('1002', '2', '58_001', 'k_水质不合格', '0');
INSERT INTO `ontology_resource` VALUES ('1003', '2', '58_001', '#(e_导购+e_质量+k_掩饰+k_测试)#', '0');
INSERT INTO `ontology_resource` VALUES ('1004', '2', '59_002', '#(e_使用效果+c_好+e_节能+e_安全)#', '0');
INSERT INTO `ontology_resource` VALUES ('1005', '2', '59_002', '#(c_好+e_节能+e_安全)#', '0');
INSERT INTO `ontology_resource` VALUES ('1006', '2', '59_002', '#(c_好+e_节能)#', '0');
INSERT INTO `ontology_resource` VALUES ('1007', '2', '59_002', '#(c_节能+e_使用效果+c_好)#', '0');
INSERT INTO `ontology_resource` VALUES ('1008', '2', '59_002', '#e_商品+e_使用方法+e_服务#', '0');
INSERT INTO `ontology_resource` VALUES ('1009', '2', '59_002', '#(c_能耗高+e_使用效果+c_一般)#', '0');
INSERT INTO `ontology_resource` VALUES ('1010', '2', '59_002', '#e_产品+k_不好评价#', '0');
INSERT INTO `ontology_resource` VALUES ('1013', '2', '42', '#e_产品+c_安全+k_重视#', '0');
INSERT INTO `ontology_resource` VALUES ('1014', '2', '42', '#e_产品+c_安全+k_不重视#', '0');
INSERT INTO `ontology_resource` VALUES ('1015', '2', '44', '#(k_装水+e_配件+c_好)#', '0');
INSERT INTO `ontology_resource` VALUES ('1016', '2', '44', '#(e_配件+c_不多)#', '0');
INSERT INTO `ontology_resource` VALUES ('1018', '2', '44', '#(e_配件+k_套餐)#', '0');
INSERT INTO `ontology_resource` VALUES ('1019', '2', '44', '#(e_商品|e_配件)+k_麻烦#', '0');
INSERT INTO `ontology_resource` VALUES ('1020', '2', '44', '#(e_配件+k_明晰应该标注)#', '0');
INSERT INTO `ontology_resource` VALUES ('1021', '2', '44', '#(e_配件+k_更换)#', '0');
INSERT INTO `ontology_resource` VALUES ('1022', '2', '44', '#(e_配件+k_太短)#', '0');
INSERT INTO `ontology_resource` VALUES ('1023', '2', '44', '#(k_配备+e_配件)#', '0');
INSERT INTO `ontology_resource` VALUES ('1024', '2', '44', '#(e_配件+k_包含)#', '0');
INSERT INTO `ontology_resource` VALUES ('1025', '2', '44', '#(k_改变+e_配件)#', '0');
INSERT INTO `ontology_resource` VALUES ('1026', '2', '44', '#(k_配+e_配件)#', '0');
INSERT INTO `ontology_resource` VALUES ('1032', '3', '45', '#(e_安装人员+c_讲解)#', '0');
INSERT INTO `ontology_resource` VALUES ('1033', '3', '45', '#(c_讲解+e_使用方法)#', '0');
INSERT INTO `ontology_resource` VALUES ('1034', '3', '45', '#(e_导购+c_一般)#', '0');
INSERT INTO `ontology_resource` VALUES ('1035', '3', '45', '#(e_导购+c_不专业)#', '0');
INSERT INTO `ontology_resource` VALUES ('1036', '3', '45', '#(e_导购+c_素质差)#', '0');
INSERT INTO `ontology_resource` VALUES ('1037', '3', '45', '#(c_无人推荐+e_产品)#', '0');
INSERT INTO `ontology_resource` VALUES ('1038', '3', '45', '#(e_产品+c_夸大其词)#', '0');
INSERT INTO `ontology_resource` VALUES ('1039', '3', '45', '#(c_误导+e_用户)#', '0');
INSERT INTO `ontology_resource` VALUES ('1040', '3', '45', '#(e_导购|e_产品)+c_讲解#', '0');
INSERT INTO `ontology_resource` VALUES ('1041', '3', '45', '#(c_未讲解+e_使用知识)#', '0');
INSERT INTO `ontology_resource` VALUES ('1042', '3', '45', 'k_讲解太少', '0');
INSERT INTO `ontology_resource` VALUES ('1043', '3', '45', '#(e_导购+(c_热情|c_专业))#', '0');
INSERT INTO `ontology_resource` VALUES ('1044', '3', '45', '#(e_用户角度+c_推荐)#', '0');
INSERT INTO `ontology_resource` VALUES ('1045', '3', '45', '#c_讲解+k_很详细#', '0');
INSERT INTO `ontology_resource` VALUES ('1046', '3', '45', '#(e_导购讲解+c_清晰简单)#', '0');
INSERT INTO `ontology_resource` VALUES ('1047', '3', '45', '#e_导购讲解+(c_实事求是+c_推荐)#', '0');
INSERT INTO `ontology_resource` VALUES ('1048', '3', '46', '#e_上门+k_安排#', '0');
INSERT INTO `ontology_resource` VALUES ('1050', '3', '65_001', '#(e_安装人员+c_到位+k_哈哈)#', '0');
INSERT INTO `ontology_resource` VALUES ('1051', '3', '65_001', '#(k_特别满意+k_很关注)#', '0');
INSERT INTO `ontology_resource` VALUES ('1052', '3', '65_001', '#e_安装人员+k_考虑也周到#', '0');
INSERT INTO `ontology_resource` VALUES ('1053', '3', '65_001', '#(e_安装人员|e_使用方法)+(c_能够+c_到位)#', '0');
INSERT INTO `ontology_resource` VALUES ('1054', '3', '65_001', 'k_装置+c_好', '0');
INSERT INTO `ontology_resource` VALUES ('1055', '3', '65_001', '#c_好#', '0');
INSERT INTO `ontology_resource` VALUES ('1056', '3', '65_001', '#(e_上门+c_能够+c_到位)#', '0');
INSERT INTO `ontology_resource` VALUES ('1057', '3', '65_001', '#(e_上门+c_能够+c_不能正常使用)#', '0');
INSERT INTO `ontology_resource` VALUES ('1058', '3', '65_001', '#(e_上门+c_能够+c_修复)#', '0');
INSERT INTO `ontology_resource` VALUES ('1061', '3', '65_001', 'e_安装人员+k_难给好评', '0');
INSERT INTO `ontology_resource` VALUES ('1062', '3', '65_001', '#(e_信息|e_上门|e_商品|e_信息记录|e_时间)+c_操作+c_熟练性#', '0');
INSERT INTO `ontology_resource` VALUES ('1063', '3', '65_001', '#(e_安装人员+c_培训)#', '0');
INSERT INTO `ontology_resource` VALUES ('1064', '3', '65_001', '#e_产品+c_培训#', '0');
INSERT INTO `ontology_resource` VALUES ('1065', '3', '65_001', '#(e_安装人员+c_不熟练+k_汽车)#', '0');
INSERT INTO `ontology_resource` VALUES ('1066', '3', '65_001', 'e_安装人员+k_太慢', '0');
INSERT INTO `ontology_resource` VALUES ('1067', '3', '65_001', 'c_能够+e_安装人员+e_机器', '0');
INSERT INTO `ontology_resource` VALUES ('1068', '3', '65_001', 'k_生疏+e_机器', '0');
INSERT INTO `ontology_resource` VALUES ('1069', '3', '65_001', '#(e_安装人员|e_说明书)+c_不会+k_装错了+k_才装好#', '0');
INSERT INTO `ontology_resource` VALUES ('1071', '3', '65_001', '#(e_产品+c_培训+k_不到位)#', '0');
INSERT INTO `ontology_resource` VALUES ('1072', '3', '65_001', '#(e_技术水平+c_不满意)#', '0');
INSERT INTO `ontology_resource` VALUES ('1073', '3', '65_001', '#(e_技术水平+c_熟练性)#', '0');
INSERT INTO `ontology_resource` VALUES ('1074', '3', '65_001', '#(e_技术水平+c_修复)#', '0');
INSERT INTO `ontology_resource` VALUES ('1075', '3', '65_001', '#(e_技术水平+c_时间长)#', '0');
INSERT INTO `ontology_resource` VALUES ('1076', '3', '65_001', '#(e_安装人员+k_技能+k_加强)#', '0');
INSERT INTO `ontology_resource` VALUES ('1077', '3', '65_001', '#(e_安装人员+c_培训+k_建议)#', '0');
INSERT INTO `ontology_resource` VALUES ('1078', '3', '65_001', '#(e_技术水平+c_较低)#', '0');
INSERT INTO `ontology_resource` VALUES ('1080', '3', '65_001', '#(e_安装人员|e_产品|e_商品|e_日常使用)+c_熟练性#', '0');
INSERT INTO `ontology_resource` VALUES ('1081', '3', '65_001', '#(e_安装人员|e_产品|e_商品|e_日常使用)+c_培训#', '0');
INSERT INTO `ontology_resource` VALUES ('1082', '3', '65_001', '#(e_安装人员|e_机器)+c_不会#', '0');
INSERT INTO `ontology_resource` VALUES ('1083', '3', '65_001', '#e_技术水平+k_有待提高#', '0');
INSERT INTO `ontology_resource` VALUES ('1084', '3', '65_001', '#(e_设计|e_商品)+k_全套安装#', '0');
INSERT INTO `ontology_resource` VALUES ('1086', '3', '65_001', '#(e_技术水平+c_差)#', '0');
INSERT INTO `ontology_resource` VALUES ('1087', '3', '65_001', '#(e_技术水平+c_正常使用)#', '0');
INSERT INTO `ontology_resource` VALUES ('1088', '3', '65_001', '#(e_安装人员+e_技术水平+k_不过关)#', '0');
INSERT INTO `ontology_resource` VALUES ('1089', '3', '65_001', '#(e_安装人员+c_反复维修+c_培训)#', '0');
INSERT INTO `ontology_resource` VALUES ('1090', '3', '65_001', '#(e_安装人员+k_没有洗手+k_太不专业)#', '0');
INSERT INTO `ontology_resource` VALUES ('1092', '2', '36', '#e_产品标识+c_简单易懂#', '0');
INSERT INTO `ontology_resource` VALUES ('1093', '2', '36', '#e_产品标识+c_便捷性#', '0');
INSERT INTO `ontology_resource` VALUES ('1094', '2', '36', '#(e_产品+c_便捷性)+c_加强#', '0');
INSERT INTO `ontology_resource` VALUES ('1095', '2', '36', '#(e_商品+c_净化)#', '0');
INSERT INTO `ontology_resource` VALUES ('1096', '2', '36', '#(e_产品|e_技术水平)+c_响应速度#', '0');
INSERT INTO `ontology_resource` VALUES ('1098', '2', '36', '#(e_设计+k_旋转)#', '0');
INSERT INTO `ontology_resource` VALUES ('1099', '2', '36', '#(e_导购|e_商品)+c_便捷性#', '0');
INSERT INTO `ontology_resource` VALUES ('1100', '2', '36', '#(e_使用方法+k_清楚)#', '0');
INSERT INTO `ontology_resource` VALUES ('1101', '2', '36', '#(e_日常使用|e_上门)+c_便捷性#', '0');
INSERT INTO `ontology_resource` VALUES ('1103', '3', '52_001', '#(e_安装人员+c_清理)#', '0');
INSERT INTO `ontology_resource` VALUES ('1104', '3', '52_001', 'e_安装人员+e_服务+k_全面', '0');
INSERT INTO `ontology_resource` VALUES ('1105', '3', '52_001', '#e_导购+c_会+k_应该推出+k_一并#', '0');
INSERT INTO `ontology_resource` VALUES ('1106', '3', '52_001', '#c_能够+k_水管或线整理整齐#', '0');
INSERT INTO `ontology_resource` VALUES ('1107', '3', '52_001', '#e_安装人员+k_自觉洗手#', '0');
INSERT INTO `ontology_resource` VALUES ('1108', '3', '52_001', '#e_安装人员+k_水质检测#', '0');
INSERT INTO `ontology_resource` VALUES ('1109', '3', '52_001', '#(e_用户+k_后续+k_检查)#', '0');
INSERT INTO `ontology_resource` VALUES ('1110', '3', '52_001', '#(e_安装人员+c_未清理)#', '0');
INSERT INTO `ontology_resource` VALUES ('1111', '3', '52_001', 'e_商品+k_介绍+k_没有', '0');
INSERT INTO `ontology_resource` VALUES ('1112', '3', '52_001', '#(k_未检测+k_未装+k_离开)#', '0');
INSERT INTO `ontology_resource` VALUES ('1120', '3', '65_003', '#(e_用户+k_物流配送)#', '0');
INSERT INTO `ontology_resource` VALUES ('1121', '3', '65_003', '#(e_产品|e_商品)+c_满意+k_一直#', '0');
INSERT INTO `ontology_resource` VALUES ('1122', '3', '65_003', 'e_安装人员+k_直接联系', '0');
INSERT INTO `ontology_resource` VALUES ('1123', '3', '65_003', '#e_安装人员+c_专业#', '0');
INSERT INTO `ontology_resource` VALUES ('1124', '3', '65_003', 'k_收安同步', '0');
INSERT INTO `ontology_resource` VALUES ('1125', '3', '65_003', '#(e_导购+k_建议+k_一体服务)#', '0');
INSERT INTO `ontology_resource` VALUES ('1126', '3', '65_003', '#e_技术人员+k_紧缺#', '0');
INSERT INTO `ontology_resource` VALUES ('1127', '3', '65_003', '#(e_服务+c_规范)#', '0');
INSERT INTO `ontology_resource` VALUES ('1128', '3', '65_003', '#e_服务+c_满意#', '0');
INSERT INTO `ontology_resource` VALUES ('1129', '3', '65_003', '#c_操作+k_一如既往的好#', '0');
INSERT INTO `ontology_resource` VALUES ('1130', '3', '65_003', '#e_服务+k_很满意#', '0');
INSERT INTO `ontology_resource` VALUES ('1131', '3', '65_003', '#(e_产品|e_商品)+c_满意+c_关注+k_服务#', '0');
INSERT INTO `ontology_resource` VALUES ('1132', '3', '65_003', '#(e_服务+k_好)#', '0');
INSERT INTO `ontology_resource` VALUES ('1133', '3', '65_003', '#(e_服务人员素质|e_服务过程+c_有待提高)#', '0');
INSERT INTO `ontology_resource` VALUES ('1134', '3', '65_003', '#(e_商品+c_定期+k_应该)#', '0');
INSERT INTO `ontology_resource` VALUES ('1135', '3', '65_003', '#e_产品+c_服务规范+c_会+k_建议做好#', '0');
INSERT INTO `ontology_resource` VALUES ('1136', '3', '65_003', 'k_建议+k_做好产品的推介', '0');
INSERT INTO `ontology_resource` VALUES ('1142', '3', '65_003', '#(e_时间+e_保养+e_服务)#', '0');
INSERT INTO `ontology_resource` VALUES ('1143', '3', '65_003', '#k_建议+k_水质检测+k_最好#', '0');
INSERT INTO `ontology_resource` VALUES ('1144', '3', '65_003', '#(e_商品|e_日常使用)+k_希望了解+k_咨询#', '0');
INSERT INTO `ontology_resource` VALUES ('1145', '3', '65_003', 'e_服务+k_差+k_没来', '0');
INSERT INTO `ontology_resource` VALUES ('1146', '3', '65_003', '#(e_服务人员素质+c_差)#', '0');
INSERT INTO `ontology_resource` VALUES ('1147', '3', '65_003', '#(e_商品|e_售后)+c_较低+c_不好+k_极差#', '0');
INSERT INTO `ontology_resource` VALUES ('1148', '3', '65_003', '#(k_服务+k_差+k_联系+k_没来)#', '0');
INSERT INTO `ontology_resource` VALUES ('1149', '3', '50', '#(e_送货人员+c_及时性+e_用户家)#', '0');
INSERT INTO `ontology_resource` VALUES ('1150', '3', '50', 'c_响应速度+k_高效', '0');
INSERT INTO `ontology_resource` VALUES ('1151', '3', '50', '#(e_安装人员+e_用户+c_约定)#', '0');
INSERT INTO `ontology_resource` VALUES ('1152', '3', '50', 'c_响应速度+c_好+e_服务', '0');
INSERT INTO `ontology_resource` VALUES ('1154', '3', '50', '#(e_服务|e_用户)+c_响应速度#', '0');
INSERT INTO `ontology_resource` VALUES ('1155', '3', '50', '#(c_响应速度+e_用户要求+c_及时性)#', '0');
INSERT INTO `ontology_resource` VALUES ('1156', '3', '50', '#(c_响应速度+e_用户要求+c_时间是否一致)#', '0');
INSERT INTO `ontology_resource` VALUES ('1157', '3', '50', '(e_用户|e_安装人员)+k_不能及时', '0');
INSERT INTO `ontology_resource` VALUES ('1159', '3', '50', '#(e_送货人员+c_时间)#', '0');
INSERT INTO `ontology_resource` VALUES ('1160', '3', '50', '#(e_送货人员+c_及时性)#', '0');
INSERT INTO `ontology_resource` VALUES ('1161', '3', '50', '#(e_信息|e_上门|e_商品|e_信息记录|e_时间|e_服务)+c_操作+c_响应速度#', '0');
INSERT INTO `ontology_resource` VALUES ('1162', '3', '50', '#(e_安装人员+c_及时性)#', '0');
INSERT INTO `ontology_resource` VALUES ('1163', '3', '50', 'e_响应速度+c_导购', '0');
INSERT INTO `ontology_resource` VALUES ('1164', '3', '50', 'e_响应速度+k_慢', '0');
INSERT INTO `ontology_resource` VALUES ('1166', '3', '50', '#e_时间+k_不及时#', '0');
INSERT INTO `ontology_resource` VALUES ('1167', '3', '50', '#(c_响应速度+k_加强+k_道德)#', '0');
INSERT INTO `ontology_resource` VALUES ('1168', '3', '50', '#(e_服务人员+c_较低+c_响应速度)#', '0');
INSERT INTO `ontology_resource` VALUES ('1169', '3', '50', '#(e_服务+c_较低+c_响应速度)#', '0');
INSERT INTO `ontology_resource` VALUES ('1170', '3', '50', '#(e_技术人员+c_响应速度)#', '0');
INSERT INTO `ontology_resource` VALUES ('1171', '3', '50', '#(c_未按要求+c_时间是否一致)#', '0');
INSERT INTO `ontology_resource` VALUES ('1172', '3', '50', '#(c_与用户改约+c_是否按时上门)#', '0');
INSERT INTO `ontology_resource` VALUES ('1173', '3', '50', '#(c_未按要求+c_与用户改约)#', '0');
INSERT INTO `ontology_resource` VALUES ('1174', '3', '50', '#(c_未按要求+c_是否按时上门)#', '0');
INSERT INTO `ontology_resource` VALUES ('1175', '3', '50', '#(e_上门|e_日常使用)+c_便捷性#', '0');
INSERT INTO `ontology_resource` VALUES ('1176', '3', '50', '#(e_商品|e_上门)+c_响应速度#', '0');
INSERT INTO `ontology_resource` VALUES ('1178', '3', '50', 'c_是否按时上门', '0');
INSERT INTO `ontology_resource` VALUES ('1179', '3', '50', '#e_电话+e_机器+k_催#', '0');
INSERT INTO `ontology_resource` VALUES ('1180', '3', '50', '#c_未按要求+e_用户改约+c_是否按时上门#', '0');
INSERT INTO `ontology_resource` VALUES ('1181', '3', '50', '#(e_信息|e_商品|e_信息记录|e_时间)+c_操作+c_送货#', '0');
INSERT INTO `ontology_resource` VALUES ('1182', '3', '50', '#e_响应速度+k_慢#', '0');
INSERT INTO `ontology_resource` VALUES ('1183', '3', '51', '#(e_电话+c_不容易打通)#', '0');
INSERT INTO `ontology_resource` VALUES ('1184', '3', '51', '#(c_未按要求+e_时间)#', '0');
INSERT INTO `ontology_resource` VALUES ('1185', '3', '51', '#(e_接听人员+c_不专业)#', '0');
INSERT INTO `ontology_resource` VALUES ('1187', '3', '51', '#(e_时间+c_不合理 )#', '0');
INSERT INTO `ontology_resource` VALUES ('1188', '3', '51', 'e_电话+e_服务+c_不满意', '0');
INSERT INTO `ontology_resource` VALUES ('1189', '3', '51', '#(e_电话+c_一拨就通)#', '0');
INSERT INTO `ontology_resource` VALUES ('1190', '3', '51', '#(e_预约时间+c_合理)#', '0');
INSERT INTO `ontology_resource` VALUES ('1191', '3', '51', '#(e_电话+c_一拨就通) #', '0');
INSERT INTO `ontology_resource` VALUES ('1192', '3', '51', '#(e_信息记录+c_准确)#', '0');
INSERT INTO `ontology_resource` VALUES ('1193', '3', '51', '#(e_时间+c_合理)#', '0');
INSERT INTO `ontology_resource` VALUES ('1194', '3', '52', 'c_用户+k_价格差太大', '0');
INSERT INTO `ontology_resource` VALUES ('1195', '3', '52', '#(e_日常使用|e_上门)+c_价格#', '0');
INSERT INTO `ontology_resource` VALUES ('1196', '3', '52', '#k_打孔+k_贵#', '0');
INSERT INTO `ontology_resource` VALUES ('1197', '3', '52', '#c_操作+k_贵#', '0');
INSERT INTO `ontology_resource` VALUES ('1198', '3', '52', '#c_价格#', '0');
INSERT INTO `ontology_resource` VALUES ('1199', '3', '52', 'k_安装费+e_服务人员', '0');
INSERT INTO `ontology_resource` VALUES ('1200', '3', '52', 'k_乱收费', '0');
INSERT INTO `ontology_resource` VALUES ('1201', '3', '52', 'k_免费+e_服务人员', '0');
INSERT INTO `ontology_resource` VALUES ('1202', '3', '53', '#(e_用户+c_满意+k_答复)#', '0');
INSERT INTO `ontology_resource` VALUES ('1203', '3', '53', '#(e_节假日+c_接到+c_是否保养+e_会员活动)#', '0');
INSERT INTO `ontology_resource` VALUES ('1204', '3', '53', '#k_感受到+(e_用户|e_产品)+c_关心#', '0');
INSERT INTO `ontology_resource` VALUES ('1205', '3', '53', '#(c_会接到+c_会推荐)#', '0');
INSERT INTO `ontology_resource` VALUES ('1206', '3', '53', '#(c_感到+c_购买产品+c_关注)#', '0');
INSERT INTO `ontology_resource` VALUES ('1207', '3', '53', '#(e_用户|e_会员)+c_用户优惠#', '0');
INSERT INTO `ontology_resource` VALUES ('1208', '3', '53', '#(c_会接到+k_相关信息+c_用不上 )#', '0');
INSERT INTO `ontology_resource` VALUES ('1209', '3', '53', '#(e_产品|e_商品)+k_宣传+k_回访提醒#', '0');
INSERT INTO `ontology_resource` VALUES ('1210', '3', '53', '#(e_产品+e_服务+c_满意)#', '0');
INSERT INTO `ontology_resource` VALUES ('1211', '3', '53', '#c_没有任何联系#', '0');
INSERT INTO `ontology_resource` VALUES ('1212', '3', '53', '#(c_是否需要联系+e_自己+c_无人问津)#', '0');
INSERT INTO `ontology_resource` VALUES ('1213', '3', '53', '#(e_产品+e_服务+c_一般)#', '0');
INSERT INTO `ontology_resource` VALUES ('1214', '3', '53', '#(e_产品+e_服务+c_不满意)#', '0');
INSERT INTO `ontology_resource` VALUES ('1215', '3', '54', '#e_节假日+c_购买产品#', '0');
INSERT INTO `ontology_resource` VALUES ('1216', '3', '54', '#(c_是否会选择+e_海尔)#', '0');
INSERT INTO `ontology_resource` VALUES ('1217', '3', '54', '#(e_自己+c_是否会选择)#', '0');
INSERT INTO `ontology_resource` VALUES ('1218', '3', '54', '#(e_亲戚朋友+k_准备买)#', '0');
INSERT INTO `ontology_resource` VALUES ('1219', '3', '54', '#(e_亲戚朋友+c_会推荐)#', '0');
INSERT INTO `ontology_resource` VALUES ('1220', '3', '54', '#(e_用户|e_服务)+c_会推荐#', '0');
INSERT INTO `ontology_resource` VALUES ('1221', '3', '54', 'e_用户+e_亲戚朋友', '0');
INSERT INTO `ontology_resource` VALUES ('1222', '3', '54', '#(e_导购|e_安装评价)+c_会推荐#', '0');
INSERT INTO `ontology_resource` VALUES ('1223', '3', '54', '#(e_选购家电+c_是否会选择)#', '0');
INSERT INTO `ontology_resource` VALUES ('1224', '3', '54', '#(c_不一定+c_会+c_首选)#', '0');
INSERT INTO `ontology_resource` VALUES ('1225', '3', '54', '#(e_产品+e_服务)+c_是否会选择#', '0');
INSERT INTO `ontology_resource` VALUES ('1226', '3', '54', '#(e_产品+e_服务+c_不满意)#', '0');
INSERT INTO `ontology_resource` VALUES ('1227', '3', '54', '#(e_亲戚朋友+c_都不)#', '0');
INSERT INTO `ontology_resource` VALUES ('1229', '2', '43', '#e_产品+k_技术+k_加强#', '0');
INSERT INTO `ontology_resource` VALUES ('1230', '3', '65_002', '#e_安装人员+e_上门+c_热情#', '0');
INSERT INTO `ontology_resource` VALUES ('1231', '3', '65_002', '#e_安装人员+e_态度+k_很+c_热情#', '0');
INSERT INTO `ontology_resource` VALUES ('1233', '3', '65_002', 'e_安装人员+k_主动', '0');
INSERT INTO `ontology_resource` VALUES ('1234', '3', '65_002', '#k_继续发扬#', '0');
INSERT INTO `ontology_resource` VALUES ('1236', '3', '65_002', '#(e_安装人员+c_态度认真+c_热情)#', '0');
INSERT INTO `ontology_resource` VALUES ('1237', '3', '65_002', '#(c_会+c_热情)#', '0');
INSERT INTO `ontology_resource` VALUES ('1240', '3', '65_002', '#(e_态度+e_安装人员+k_可以给)#', '0');
INSERT INTO `ontology_resource` VALUES ('1241', '3', '65_002', '#(e_安装人员+c_热情+k_很)#', '0');
INSERT INTO `ontology_resource` VALUES ('1243', '3', '65_002', '#(e_接听人员+c_热情)#', '0');
INSERT INTO `ontology_resource` VALUES ('1244', '3', '65_002', '#(e_用户+c_热情)#', '0');
INSERT INTO `ontology_resource` VALUES ('1245', '3', '65_002', '#(e_接听人员+c_热情+c_专业)#', '0');
INSERT INTO `ontology_resource` VALUES ('1249', '3', '65_002', '#c_冷淡+c_冲撞#', '0');
INSERT INTO `ontology_resource` VALUES ('1251', '3', '65_002', '#(e_接听人员+c_冷淡)#', '0');
INSERT INTO `ontology_resource` VALUES ('1252', '3', '65_002', '#e_电话+c_冷淡#', '0');
INSERT INTO `ontology_resource` VALUES ('1253', '1', '13_001', '#e_态度#', '0');
INSERT INTO `ontology_resource` VALUES ('1254', '2', '42', '#(c_好+e_安全)#', '0');
INSERT INTO `ontology_resource` VALUES ('1258', '1', '10_001', 'e_导购', '0');
INSERT INTO `ontology_resource` VALUES ('1259', '1', '10_001', 'e_现场', '0');
INSERT INTO `ontology_resource` VALUES ('1260', '1', '11_001', 'e_送货人员', '0');
INSERT INTO `ontology_resource` VALUES ('1261', '1', '11_001', 'c_送货', '0');
INSERT INTO `ontology_resource` VALUES ('1262', '1', '11_001', 'c_开箱验机', '0');
INSERT INTO `ontology_resource` VALUES ('1263', '1', '11_001', '#(c_搬运+e_机器)#', '0');
INSERT INTO `ontology_resource` VALUES ('1264', '1', '11_001', '#(c_开箱验机+c_不专业)#', '0');
INSERT INTO `ontology_resource` VALUES ('1265', '1', '11_002', 'e_安装人员', '0');
INSERT INTO `ontology_resource` VALUES ('1266', '1', '11_002', 'c_到位', '0');
INSERT INTO `ontology_resource` VALUES ('1267', '1', '11_002', 'c_不熟练', '0');
INSERT INTO `ontology_resource` VALUES ('1268', '1', '11_002', '#(e_安装人员+c_及时性)#', '0');
INSERT INTO `ontology_resource` VALUES ('1269', '1', '11_002', 'c_是否按时上门', '0');
INSERT INTO `ontology_resource` VALUES ('1270', '1', '11_002', 'c_未清理', '0');
INSERT INTO `ontology_resource` VALUES ('1271', '1', '12_001', 'e_产品标识', '0');
INSERT INTO `ontology_resource` VALUES ('1272', '1', '12_001', 'e_说明书', '0');
INSERT INTO `ontology_resource` VALUES ('1273', '1', '12_001', 'e_产品', '0');
INSERT INTO `ontology_resource` VALUES ('1274', '1', '12_001', 'c_简单易懂', '0');
INSERT INTO `ontology_resource` VALUES ('1275', '1', '12_001', 'c_便捷性', '0');
INSERT INTO `ontology_resource` VALUES ('1276', '1', '12_001', 'e_设计', '0');
INSERT INTO `ontology_resource` VALUES ('1277', '1', '12_001', 'c_不清晰', '0');
INSERT INTO `ontology_resource` VALUES ('1278', '1', '12_001', '(e_日常使用+c_不方便)#', '0');
INSERT INTO `ontology_resource` VALUES ('1279', '1', '12_001', 'e_产品设计', '0');
INSERT INTO `ontology_resource` VALUES ('1280', '1', '12_001', '(e_说明书+c_复杂难懂)', '0');
INSERT INTO `ontology_resource` VALUES ('1281', '1', '12_001', 'c_不方便', '0');
INSERT INTO `ontology_resource` VALUES ('1282', '1', '12_001', 'c_复杂难懂', '0');
INSERT INTO `ontology_resource` VALUES ('1283', '1', '12_001', 'c_不合理', '0');
INSERT INTO `ontology_resource` VALUES ('1284', '1', '12_002', 'e_质量', '0');
INSERT INTO `ontology_resource` VALUES ('1285', '1', '12_002', 'c_稳定可靠', '0');
INSERT INTO `ontology_resource` VALUES ('1286', '1', '12_002', 'c_节能', '0');
INSERT INTO `ontology_resource` VALUES ('1287', '1', '12_002', 'e_使用效果', '0');
INSERT INTO `ontology_resource` VALUES ('1288', '1', '12_002', '#(e_质量+c_差)#', '0');
INSERT INTO `ontology_resource` VALUES ('1289', '1', '12_002', '#c_考虑+c_退换货 #', '0');
INSERT INTO `ontology_resource` VALUES ('1290', '1', '12_002', 'c_能耗高', '0');
INSERT INTO `ontology_resource` VALUES ('1291', '1', '12_002', 'c_短期+c_反复维修', '0');
INSERT INTO `ontology_resource` VALUES ('1292', '1', '13_001', 'c_冷淡', '0');
INSERT INTO `ontology_resource` VALUES ('1293', '1', '13_001', 'c_冲撞', '0');
INSERT INTO `ontology_resource` VALUES ('1294', '1', '13_001', 'c_热情', '0');
INSERT INTO `ontology_resource` VALUES ('1295', '1', '13_001', 'c_规范', '0');
INSERT INTO `ontology_resource` VALUES ('1296', '1', '13_002', 'e_上门', '0');
INSERT INTO `ontology_resource` VALUES ('1297', '1', '13_002', 'c_到位', '0');
INSERT INTO `ontology_resource` VALUES ('1298', '1', '13_002', 'c_修复', '0');
INSERT INTO `ontology_resource` VALUES ('1299', '1', '13_002', 'e_技术水平', '0');
INSERT INTO `ontology_resource` VALUES ('1300', '1', '13_002', 'c_熟练性', '0');
INSERT INTO `ontology_resource` VALUES ('1301', '1', '13_002', 'c_时间长', '0');
INSERT INTO `ontology_resource` VALUES ('1302', '1', '13_002', 'c_较低', '0');
INSERT INTO `ontology_resource` VALUES ('1303', '1', '13_004', 'c_响应速度', '0');
INSERT INTO `ontology_resource` VALUES ('1304', '1', '13_004', 'c_及时性', '0');
INSERT INTO `ontology_resource` VALUES ('1305', '1', '13_004', 'c_时间是否一致', '0');
INSERT INTO `ontology_resource` VALUES ('1306', '1', '13_004', 'c_未按要求', '0');
INSERT INTO `ontology_resource` VALUES ('1307', '1', '13_005', 'e_电话', '0');
INSERT INTO `ontology_resource` VALUES ('1308', '1', '13_005', 'c_一拨就通', '0');
INSERT INTO `ontology_resource` VALUES ('1309', '1', '13_005', 'e_接听人员', '0');
INSERT INTO `ontology_resource` VALUES ('1310', '1', '13_005', 'e_信息记录+c_准确', '0');
INSERT INTO `ontology_resource` VALUES ('1311', '1', '13_005', 'c_合理', '0');
INSERT INTO `ontology_resource` VALUES ('1312', '1', '13_005', 'c_不容易拨通', '0');
INSERT INTO `ontology_resource` VALUES ('1313', '1', '13_005', '#e_接听人员+c_不专业#', '0');
INSERT INTO `ontology_resource` VALUES ('1314', '1', '14_001', 'e_节假日', '0');
INSERT INTO `ontology_resource` VALUES ('1315', '1', '14_001', 'c_是否保养', '0');
INSERT INTO `ontology_resource` VALUES ('1316', '1', '14_001', 'e_会员活动', '0');
INSERT INTO `ontology_resource` VALUES ('1317', '1', '14_001', 'c_感受到+c_关心', '0');
INSERT INTO `ontology_resource` VALUES ('1318', '1', '14_001', 'c_优惠推介', '0');
INSERT INTO `ontology_resource` VALUES ('1319', '1', '14_001', 'c_关注', '0');
INSERT INTO `ontology_resource` VALUES ('1320', '1', '14_001', 'c_用不上 ', '0');
INSERT INTO `ontology_resource` VALUES ('1321', '1', '14_002', 'c_是否会选择', '0');
INSERT INTO `ontology_resource` VALUES ('1322', '1', '14_002', 'e_亲戚朋友', '0');
INSERT INTO `ontology_resource` VALUES ('1323', '1', '14_002', 'c_会推荐', '0');
INSERT INTO `ontology_resource` VALUES ('1324', '1', '14_002', 'e_选购家电', '0');
INSERT INTO `ontology_resource` VALUES ('1325', '1', '14_002', 'c_考虑', '0');
INSERT INTO `ontology_resource` VALUES ('1326', '1', '14_002', 'c_不一定', '0');
INSERT INTO `ontology_resource` VALUES ('1327', '1', '14_002', 'e_自己', '0');
INSERT INTO `ontology_resource` VALUES ('1328', '1', '14_002', 'c_都不', '0');
INSERT INTO `ontology_resource` VALUES ('1329', '1', '14_002', 'c_不满意', '0');
INSERT INTO `ontology_resource` VALUES ('1331', '3', '65_003', '#(e_服务+k_很不错)#', '0');
INSERT INTO `ontology_resource` VALUES ('1337', '3', '50', '#e_运送方式+c_响应速度#', '0');
INSERT INTO `ontology_resource` VALUES ('1339', '3', '65_003', '#e_安装人员+k_送#', '0');
INSERT INTO `ontology_resource` VALUES ('1341', '2', '36', '#k_实用#', '0');
INSERT INTO `ontology_resource` VALUES ('1342', '1', '11_002', 'k_安装', '0');
INSERT INTO `ontology_resource` VALUES ('1343', '1', '13_004', 'k_超快', '0');
INSERT INTO `ontology_resource` VALUES ('1344', '1', '13_004', 'k_神速', '0');
INSERT INTO `ontology_resource` VALUES ('1345', '1', '13_001', 'k_客服', '0');
INSERT INTO `ontology_resource` VALUES ('1346', '1', '12_001', 'k_实用', '0');
INSERT INTO `ontology_resource` VALUES ('1347', '1', '11_001', 'k_发货', '0');
INSERT INTO `ontology_resource` VALUES ('1348', '1', '12_002', 'c_无噪音', '0');
INSERT INTO `ontology_resource` VALUES ('1349', '1', '12_002', 'k_噪音', '0');
INSERT INTO `ontology_resource` VALUES ('1351', '3', '50', '#k_发货+c_响应速度#', '0');
INSERT INTO `ontology_resource` VALUES ('1352', '2', '37', '#e_商品+c_无噪音#', '0');
INSERT INTO `ontology_resource` VALUES ('1353', '3', '65_002', '#k_师傅+c_热情#', '0');
INSERT INTO `ontology_resource` VALUES ('1354', '2', '37', '#e_商品+k_安静#', '0');
INSERT INTO `ontology_resource` VALUES ('1360', '3', '65_003', '#e_安装人员+k_负责#', '0');
INSERT INTO `ontology_resource` VALUES ('1387', '3', '46', '#e_上门+k_安排+k_有待加强#', '0');
INSERT INTO `ontology_resource` VALUES ('1389', '2', '38_005', '#e_颜色+k_好看#', '0');
INSERT INTO `ontology_resource` VALUES ('1390', '2', '38_006', '#(e_设计+c_不合理)#', '0');
INSERT INTO `ontology_resource` VALUES ('1391', '2', '38_006', '#(e_设计+c_是否人性化)#', '0');
INSERT INTO `ontology_resource` VALUES ('1392', '2', '38_006', '#(e_商品+e_设计+k_不合理)#', '0');
INSERT INTO `ontology_resource` VALUES ('1393', '2', '38_008', '#(e_产品+c_不多)#', '0');
INSERT INTO `ontology_resource` VALUES ('1394', '2', '38_005', '#e_颜色+k_不好看#', '0');
INSERT INTO `ontology_resource` VALUES ('1396', '2', '38_008', '#k_外观+k_好看#', '0');
INSERT INTO `ontology_resource` VALUES ('1397', '2', '38_008', '#e_商品+k_更完美#', '0');
INSERT INTO `ontology_resource` VALUES ('1398', '2', '38_008', '#e_产品+k_好看+k_奢华#', '0');
INSERT INTO `ontology_resource` VALUES ('1399', '2', '38_008', '#e_款式+k_喜欢#', '0');
INSERT INTO `ontology_resource` VALUES ('1401', '2', '38_006', '#e_设计+c_合理#', '0');
INSERT INTO `ontology_resource` VALUES ('1402', '2', '38_005', '#e_颜色+k_不正#', '0');
INSERT INTO `ontology_resource` VALUES ('1403', '2', '59_005', '#(e_说明书+c_简单易懂)#', '0');
INSERT INTO `ontology_resource` VALUES ('1404', '2', '59_005', '#e_说明书+c_便捷性#', '0');
INSERT INTO `ontology_resource` VALUES ('1406', '2', '59_005', '#(e_说明书+c_不清晰)#', '0');
INSERT INTO `ontology_resource` VALUES ('1407', '2', '59_005', 'e_说明书+k_不能', '0');
INSERT INTO `ontology_resource` VALUES ('1408', '2', '59_005', '#(e_说明书+c_不方便)#', '0');
INSERT INTO `ontology_resource` VALUES ('1409', '2', '59_005', '#(e_说明书+c_复杂难懂)#', '0');
INSERT INTO `ontology_resource` VALUES ('1411', '2', '62_001', '#e_配件+c_稳定可靠#', '0');
INSERT INTO `ontology_resource` VALUES ('1412', '2', '59_001', 'c_好评', '0');
INSERT INTO `ontology_resource` VALUES ('1413', '2', '59_001', 'c_差评', '0');
INSERT INTO `ontology_resource` VALUES ('1414', '2', '59_001', 'c_中评', '0');
INSERT INTO `ontology_resource` VALUES ('1415', '2', '59_005', '#e_说明书+k_提醒用户#', '0');
INSERT INTO `ontology_resource` VALUES ('1416', '2', '59_003', '#(k_奢华+k_艺术品)#', '0');
INSERT INTO `ontology_resource` VALUES ('1417', '2', '62_005', '#(e_商品+c_性能低+e_性价比)#', '0');
INSERT INTO `ontology_resource` VALUES ('1418', '2', '62_005', '#(e_商品+c_性能高+e_性价比)#', '0');
INSERT INTO `ontology_resource` VALUES ('1419', '2', '60_006', '#(e_商品+k_大小+c_容积大)#', '0');
INSERT INTO `ontology_resource` VALUES ('1420', '2', '60_006', '#(e_商品+c_容积大)#', '0');
INSERT INTO `ontology_resource` VALUES ('1421', '2', '60_006', '#(e_商品+c_容积小)#', '0');
INSERT INTO `ontology_resource` VALUES ('1422', '2', '60_006', '#(e_商品+k_容积+c_一般)#', '0');
INSERT INTO `ontology_resource` VALUES ('1423', '2', '60_005', '#(e_商品+e_制冷+c_好)#', '0');
INSERT INTO `ontology_resource` VALUES ('1424', '2', '60_005', '#(e_商品+e_制冷+c_不好)#', '0');
INSERT INTO `ontology_resource` VALUES ('1425', '2', '60_005', '#(e_商品+e_制冷+c_一般)#', '0');
INSERT INTO `ontology_resource` VALUES ('1426', '2', '60_004', '#(e_商品+e_温控+c_好)#', '0');
INSERT INTO `ontology_resource` VALUES ('1427', '2', '60_004', '#(e_商品+e_温控+c_不好)#', '0');
INSERT INTO `ontology_resource` VALUES ('1428', '2', '60_004', '#(e_商品+e_温控+c_一般)#', '0');
INSERT INTO `ontology_resource` VALUES ('1429', '2', '60_002', '#(e_设计+k_人性化)#', '0');
INSERT INTO `ontology_resource` VALUES ('1430', '2', '60_002', '#(e_设计+c_便捷性)#', '0');
INSERT INTO `ontology_resource` VALUES ('1431', '2', '60_002', '#(e_设计+c_不方便)#', '0');
INSERT INTO `ontology_resource` VALUES ('1432', '2', '60_001', '#(e_商品+k_无氟)#', '0');
INSERT INTO `ontology_resource` VALUES ('1434', '2', '61_002', '#e_抽屉+c_不方便#', '0');
INSERT INTO `ontology_resource` VALUES ('1435', '2', '61_002', '#e_抽屉+c_多#', '0');
INSERT INTO `ontology_resource` VALUES ('1436', '2', '61_001', '#e_照明+k_不错#', '0');
INSERT INTO `ontology_resource` VALUES ('1437', '2', '61_001', '#e_照明+c_不好#', '0');
INSERT INTO `ontology_resource` VALUES ('1438', '2', '61_001', '#e_照明+c_一般#', '0');
INSERT INTO `ontology_resource` VALUES ('1439', '2', '61_003', '#e_搁物架+c_一般#', '0');
INSERT INTO `ontology_resource` VALUES ('1440', '2', '61_003', '#e_搁物架+c_合理#', '0');
INSERT INTO `ontology_resource` VALUES ('1441', '2', '61_003', '#e_搁物架+c_不合理#', '0');
INSERT INTO `ontology_resource` VALUES ('1442', '2', '61_004', '#k_空间+k_一般#', '0');
INSERT INTO `ontology_resource` VALUES ('1443', '2', '61_004', '#k_空间+k_小#', '0');
INSERT INTO `ontology_resource` VALUES ('1444', '2', '61_004', '#k_空间+k_大#', '0');
INSERT INTO `ontology_resource` VALUES ('1445', '1', '13_006', 'e_发票', '0');
INSERT INTO `ontology_resource` VALUES ('1446', '3', '63', '#e_发票+e_服务#', '0');
INSERT INTO `ontology_resource` VALUES ('1447', '3', '64_001', '#c_送货+c_及时性#', '0');
INSERT INTO `ontology_resource` VALUES ('1449', '3', '64_001', '#c_送货+c_一般+k_速度#', '0');
INSERT INTO `ontology_resource` VALUES ('1450', '3', '64_002', '#(e_商品+k_外观+k_不好看)#', '0');
INSERT INTO `ontology_resource` VALUES ('1451', '3', '64_002', '#(e_商品+k_外观+k_好看)#', '0');
INSERT INTO `ontology_resource` VALUES ('1452', '3', '64_002', '#(e_商品+k_外观+c_一般)#', '0');
INSERT INTO `ontology_resource` VALUES ('1453', '3', '64_003', '#(e_物流+c_响应速度)#', '0');
INSERT INTO `ontology_resource` VALUES ('1454', '3', '64_003', '#(e_物流+c_一般)#', '0');
INSERT INTO `ontology_resource` VALUES ('1455', '3', '64_002', '#(k_包装+k_完整)#', '0');
INSERT INTO `ontology_resource` VALUES ('1456', '3', '64_003', '#(e_物流+c_责任)#', '0');
INSERT INTO `ontology_resource` VALUES ('1457', '3', '64_003', '#(e_物流+k_走错路线)#', '0');
INSERT INTO `ontology_resource` VALUES ('1458', '2', '37', '#k_没有+k_嘈音#', '0');
INSERT INTO `ontology_resource` VALUES ('1459', '2', '38_008', '#k_外观+k_漂亮#', '0');
INSERT INTO `ontology_resource` VALUES ('1460', '2', '62_001', '#e_配件+c_好#', '0');
INSERT INTO `ontology_resource` VALUES ('1461', '2', '61_005', '#k_冷藏室+k_够大#', '0');
INSERT INTO `ontology_resource` VALUES ('1462', '2', '61_002', '#e_抽屉+c_不多#', '0');
INSERT INTO `ontology_resource` VALUES ('1463', '2', '60_005', '#(e_制冷+c_好)#', '0');
INSERT INTO `ontology_resource` VALUES ('1464', '2', '60_004', '#(e_温控+c_好)#', '0');
INSERT INTO `ontology_resource` VALUES ('1465', '2', '60_004', '#(e_温控+c_不好)#', '0');
INSERT INTO `ontology_resource` VALUES ('1466', '2', '60_004', '#(e_温控+c_一般)#', '0');
INSERT INTO `ontology_resource` VALUES ('1467', '2', '60_003', '#(k_零度+k_保鲜)#', '0');
INSERT INTO `ontology_resource` VALUES ('1469', '3', '67', '#(e_现场+c_布置合理)#', '0');
INSERT INTO `ontology_resource` VALUES ('1470', '3', '66_001', '#(e_送货人员|e_用户)+c_时间#', '0');
INSERT INTO `ontology_resource` VALUES ('1471', '3', '66_001', '#(e_送货人员|e_用户)+c_规范#', '0');
INSERT INTO `ontology_resource` VALUES ('1472', '3', '66_001', '#(e_送货人员|e_用户)+c_开箱验机#', '0');
INSERT INTO `ontology_resource` VALUES ('1473', '3', '66_001', '#(e_机器+c_开箱验机)#', '0');
INSERT INTO `ontology_resource` VALUES ('1474', '3', '66_001', '#(c_搬运+e_机器)#', '0');
INSERT INTO `ontology_resource` VALUES ('1475', '3', '66_001', 'k_送装同步', '0');
INSERT INTO `ontology_resource` VALUES ('1476', '3', '66_001', '#(c_开箱验机+c_不专业)#', '0');
INSERT INTO `ontology_resource` VALUES ('1477', '3', '66_001', '#(e_用户+c_搬运)#', '0');
INSERT INTO `ontology_resource` VALUES ('1478', '3', '66_001', '#(c_要求+c_开箱验机)#', '0');
INSERT INTO `ontology_resource` VALUES ('1479', '3', '66_001', '#(e_送货人员+c_送货)#', '0');
INSERT INTO `ontology_resource` VALUES ('1480', '3', '67', '#(e_现场+c_不好)#', '0');
INSERT INTO `ontology_resource` VALUES ('1481', '3', '67', '#(e_现场+c_好)#', '0');
INSERT INTO `ontology_resource` VALUES ('1483', '3', '66_002', '#(e_送货人员+c_热情)#', '0');
INSERT INTO `ontology_resource` VALUES ('1484', '3', '66_002', '#(e_送货人员+c_冷淡)#', '0');
INSERT INTO `ontology_resource` VALUES ('1485', '3', '66_002', '#(e_态度+c_一般)#', '0');
INSERT INTO `ontology_resource` VALUES ('1487', '3', '68_002', '#(e_日常使用|e_上门)+c_反复维修#', '0');
INSERT INTO `ontology_resource` VALUES ('1488', '3', '68_002', 'e_产品+c_退换货+c_不熟练', '0');
INSERT INTO `ontology_resource` VALUES ('1489', '3', '68_002', '#(k_售后人员+e_产品+k_不了解)#', '0');
INSERT INTO `ontology_resource` VALUES ('1490', '3', '68_002', '#e_服务+c_操作+c_熟练#', '0');
INSERT INTO `ontology_resource` VALUES ('1491', '3', '68_002', '#(e_服务|e_用户)+c_培训#', '0');
INSERT INTO `ontology_resource` VALUES ('1492', '3', '68_002', '#k_多次+e_服务人员+k_确认#', '0');
INSERT INTO `ontology_resource` VALUES ('1493', '3', '68_001', '#(c_热情+k_服务)#', '0');
INSERT INTO `ontology_resource` VALUES ('1494', '3', '68_001', '#e_服务+k_一如既往的好#', '0');
INSERT INTO `ontology_resource` VALUES ('1495', '3', '68_001', '#e_服务+c_热情+k_避免二次污染#', '0');
INSERT INTO `ontology_resource` VALUES ('1497', '3', '68_001', '#(e_导购人员+c_冷淡)#', '0');
INSERT INTO `ontology_resource` VALUES ('1498', '3', '68_001', '#(e_态度+k_太上火还惹不起)#', '0');
INSERT INTO `ontology_resource` VALUES ('1499', '3', '68_001', '#c_一般+e_服务人员#', '0');
INSERT INTO `ontology_resource` VALUES ('1500', '2', '59_004', '#k_底脚+k_调节#', '0');
INSERT INTO `ontology_resource` VALUES ('1501', '2', '59_004', '#k_底脚+k_调节+k_不可以#', '0');
INSERT INTO `ontology_resource` VALUES ('1502', '2', '59_003', '#k_粗糙+e_商品#', '0');
INSERT INTO `ontology_resource` VALUES ('1503', '2', '59_003', '#k_效果+c_一般+k_速冻#', '0');
INSERT INTO `ontology_resource` VALUES ('1504', '2', '60_003', '#k_温度+k_不均匀#', '0');
INSERT INTO `ontology_resource` VALUES ('1505', '2', '60_003', '#k_制冷+k_均匀+k_快#', '0');
INSERT INTO `ontology_resource` VALUES ('1506', '2', '60_003', '#k_制冷+k_均匀+k_一般#', '0');
INSERT INTO `ontology_resource` VALUES ('1507', '2', '60_005', '#k_制冷+k_快#', '0');
INSERT INTO `ontology_resource` VALUES ('1508', '2', '61_004', '#(k_内胆|k_蒸发器)+k_易结冰#', '0');
INSERT INTO `ontology_resource` VALUES ('1576', '5', '15_001_001', 'k_卡萨帝+e_冰箱', '0');
INSERT INTO `ontology_resource` VALUES ('1577', '5', '15_002_001', 'k_卡萨帝+e_洗衣机', '0');
INSERT INTO `ontology_resource` VALUES ('1581', '5', '35_001_002_001', 'e_洗衣机', '0');
INSERT INTO `ontology_resource` VALUES ('1582', '5', '35_002_003', 'k_对开门冰箱', '0');
INSERT INTO `ontology_resource` VALUES ('1583', '3', '68_001', '#k_还不错+k_最好#', '0');
INSERT INTO `ontology_resource` VALUES ('1584', '2', '58_001', '#(e_质ca+c_好)#', '0');
INSERT INTO `ontology_resource` VALUES ('1585', '1', '10_001', 'e_购买', '0');
INSERT INTO `ontology_resource` VALUES ('1586', '4', '55', 'k_超满意', '0');
INSERT INTO `ontology_resource` VALUES ('1587', '4', '55', 'k_很满意', '0');
INSERT INTO `ontology_resource` VALUES ('1588', '2', '58_001', '#k_还没打+c_好#', '0');
INSERT INTO `ontology_resource` VALUES ('1589', '5', '35_002_001', 'e_冰箱', '0');
INSERT INTO `ontology_resource` VALUES ('1590', '3', '53', '#(e_服务人员+k_失望)#', '0');
INSERT INTO `ontology_resource` VALUES ('1591', '3', '66_002', '#(e_送货人员+k_很失望)#', '0');
INSERT INTO `ontology_resource` VALUES ('1592', '3', '66', '#(e_送货人员+k_失望)#', '0');

-- ----------------------------
-- Table structure for project_function
-- ----------------------------
DROP TABLE IF EXISTS `project_function`;
CREATE TABLE `project_function` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `functionName` varchar(255) DEFAULT NULL COMMENT '功能名称',
  `createTime` varchar(255) DEFAULT NULL COMMENT '创建时间',
  `updateTime` varchar(255) DEFAULT NULL COMMENT '更新时间',
  `projectid` int(11) DEFAULT NULL COMMENT '所属项目id',
  `requireid` int(11) DEFAULT NULL COMMENT '所属需求id',
  `moduleid` int(11) DEFAULT NULL COMMENT '所属模块id',
  `priority` int(255) DEFAULT '0' COMMENT '任务优先级(0:暂不;1:一般;2:需要;3:急;4:很急;)',
  `functionDesc` mediumtext COMMENT '功能描述',
  PRIMARY KEY (`id`),
  KEY `projectid` (`projectid`) USING BTREE,
  KEY `requireid` (`requireid`) USING BTREE,
  KEY `moduleid` (`moduleid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of project_function
-- ----------------------------
INSERT INTO `project_function` VALUES ('1', '上传文件', '2014-05-16 21:41:04', '2014-05-16 21:41:04', '2', '4', '4', '4', '功能模块化是将程序划分成若干个功能模块，每个功能模块完成了一个子功能，再把这些功能模块总起来组成一个整体。以满足所要求的整个系统的功能。功能模块化的根据是，如果一个问题有多个问题组合而成，那么这个组合问题的复杂程度将大于分别考虑这个问题时的复杂程度之和。');

-- ----------------------------
-- Table structure for project_information
-- ----------------------------
DROP TABLE IF EXISTS `project_information`;
CREATE TABLE `project_information` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `projectName` varchar(255) DEFAULT NULL COMMENT '项目名称',
  `clientName` varchar(255) DEFAULT NULL COMMENT '客户名称',
  `projectLeader` varchar(255) DEFAULT NULL COMMENT '项目负责人',
  `developNumber` int(255) DEFAULT NULL COMMENT '开发人数',
  `projectTime` varchar(255) DEFAULT NULL COMMENT '立项时间',
  `updateTime` varchar(255) DEFAULT NULL COMMENT '更新时间',
  `taskPriority` int(11) NOT NULL DEFAULT '0' COMMENT '任务优先级(0:暂不;1:一般;2:需要;3:急;4:很急;)',
  `status` int(11) DEFAULT '0' COMMENT '状态(0:未开始；1：正在进行中；2：已完成)',
  `remark` mediumtext COMMENT '项目信息备注',
  PRIMARY KEY (`id`),
  KEY `projectName` (`projectName`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of project_information
-- ----------------------------
INSERT INTO `project_information` VALUES ('1', '管理平台', '北京威凯优势科技有限公司', '独泪了无痕', '1', '2013-04-01 09:00:32', '2013-04-31 16:43:32', '0', '2', null);
INSERT INTO `project_information` VALUES ('2', '基于角色的权限系统', '中科鼑富（北京）科技发展有限公司', '独泪了无痕', '1', '2014-02-10 09:00:32', '2014-06-24 12:38:37', '4', '1', '如果说，命运是这世界上最烂的编剧；那么，我就要争取做我人生最好的演员');

-- ----------------------------
-- Table structure for project_module
-- ----------------------------
DROP TABLE IF EXISTS `project_module`;
CREATE TABLE `project_module` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `moduleName` varchar(255) DEFAULT NULL COMMENT '模块名称',
  `mark` varchar(255) DEFAULT NULL COMMENT '标识',
  `createTime` varchar(255) DEFAULT NULL COMMENT '创建时间',
  `updateTime` varchar(255) DEFAULT NULL COMMENT '更新时间',
  `projectid` int(11) DEFAULT NULL,
  `requireid` int(11) DEFAULT NULL COMMENT '所属需求',
  `priority` int(255) DEFAULT NULL COMMENT '任务优先级(0:暂不;1:一般;2:需要;3:急;4:很急;)',
  `implementState` int(11) NOT NULL DEFAULT '0' COMMENT '实现状态(0:未实现;1：正在实现中;2:已实现)',
  `Moduledescribe` mediumtext COMMENT '需求描述',
  PRIMARY KEY (`id`),
  KEY `moduleName` (`moduleName`) USING BTREE,
  KEY `requireid` (`requireid`) USING BTREE,
  KEY `projectid` (`projectid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of project_module
-- ----------------------------
INSERT INTO `project_module` VALUES ('1', '登陆模块', '11', '2014-05-29 21:41:04', '2014-06-27 18:36:35', '2', '2', '0', '0', '注册成功后，用户即可使用注册的用户名和密码进行登录。输入正确的用户名和密码，当用户单击\"登录\"按钮后，将在数据信息处理页中对提交的数据进行验证，如果正确，则提示用户登录成功；否则，返回用户登录页面');
INSERT INTO `project_module` VALUES ('2', '用户管理模块', '11', '2014-05-29 21:41:04', '2014-06-28 09:04:09', '2', '3', '0', '0', '用户管理模块式每个系统不可缺少的模块，所有的系统都应该有用户的管理，针对每一个用户都需要有用户的查询、删除、修改以及添加功能、登陆系统的历史记录，这样能对系统用户进行管理.');
INSERT INTO `project_module` VALUES ('3', '角色菜单模块', '11', '2014-05-29 21:41:04', '2014-06-28 09:08:40', '2', '3', '0', '0', '一个用户可以在多个角色里，一个角色里可以有多个用户，是（多对多的关系）角色 - 操作权限之间的关系就是， 这个角色到底有那些操作权限？');
INSERT INTO `project_module` VALUES ('4', '文件模块上传、下载模块', '11', '2014-05-29 21:41:04', '2014-05-29 21:41:04', '2', '4', '0', '1', null);
INSERT INTO `project_module` VALUES ('5', '职员管理模块', '11', '2014-05-29 21:41:04', '2014-08-06 15:37:20', '2', '5', '0', '0', '');
INSERT INTO `project_module` VALUES ('6', '资源图片模块', '11', '2014-05-29 21:41:04', '2014-05-29 21:41:04', '2', '6', '0', '0', null);

-- ----------------------------
-- Table structure for project_program
-- ----------------------------
DROP TABLE IF EXISTS `project_program`;
CREATE TABLE `project_program` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `programTitle` varchar(255) DEFAULT NULL COMMENT '计划标题',
  `projectid` int(11) DEFAULT NULL COMMENT '所属项目id',
  `executor` varchar(255) DEFAULT NULL COMMENT '执行人',
  `leader` varchar(255) DEFAULT NULL COMMENT '负责人',
  `period` varchar(255) DEFAULT NULL COMMENT '工期',
  `priority` int(255) DEFAULT '0' COMMENT '任务优先级(0:暂不;1:一般;2:需要;3:急;4:很急;)',
  `status` int(11) DEFAULT '0' COMMENT '状态(0:未发布；1：已发布，正在执行中；2：已完成)',
  `extended` mediumtext COMMENT '是否超期',
  PRIMARY KEY (`id`),
  KEY `projectid` (`projectid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of project_program
-- ----------------------------
INSERT INTO `project_program` VALUES ('1', '软件管理', '2', '独泪了无痕', '独泪了无痕', null, '0', '0', null);
INSERT INTO `project_program` VALUES ('2', '项目系统', '2', '独泪了无痕', '独泪了无痕', null, '4', '1', '0');

-- ----------------------------
-- Table structure for project_require
-- ----------------------------
DROP TABLE IF EXISTS `project_require`;
CREATE TABLE `project_require` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `requireTheme` varchar(255) NOT NULL COMMENT '需求主题',
  `projectid` int(11) DEFAULT NULL COMMENT '所属项目id',
  `createTime` varchar(255) NOT NULL COMMENT '创建时间',
  `updateTime` varchar(255) DEFAULT NULL COMMENT '更新时间',
  `remark` mediumtext COMMENT '备注',
  `isCreate` int(11) NOT NULL DEFAULT '0' COMMENT '是否生成需求(0：未发布；1：已发布；2：以实现 )',
  PRIMARY KEY (`id`),
  KEY `demandTheme` (`requireTheme`) USING BTREE,
  KEY `projectid` (`projectid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of project_require
-- ----------------------------
INSERT INTO `project_require` VALUES ('1', '项目系统需求', '2', '2014-04-16 21:41:04', '2014-05-16 21:41:04', '爱！追爱？还是等爱？爱情与年华，谁成全了谁？谁又蹉跎了谁？“我知道那些不该说的话，让你负气流浪。想知道多年漂浮的时光，是否你也想家。如果当时吻你，当时抱你，也许结局难讲。我那么多遗憾，那么多期盼，你知道吗？我爱你，是多么清楚，多么坚固的信仰。”对，爱是一种信仰。', '1');
INSERT INTO `project_require` VALUES ('2', '登陆管理需求', '2', '2014-02-16 21:41:04', '2014-02-16 21:41:04', '有没有一个人，在你心中永远抹不去。有没有一些事，是你不愿提及却难以割舍。有没有一份回忆，只属于你自己.如果有那么一天，你不再记得从前，我也不再记得，时光一定会代替我们记得，后来的我总想起从前的你.你,是我生命里不得不寂寞的哀伤,很久以后我会告诉我自己:你来过一阵子，而我，想念一辈子', '2');
INSERT INTO `project_require` VALUES ('3', '权限管理需求', '2', '2014-03-01 21:41:04', '2014-03-01 21:41:04', '操作权限就不只是添加、删除、修改权限，那其实是一个技术思维上的权限而已，真正的业务权限就是：“谁有人事管理权限，谁有项目管理权限，谁有管理客户的权限”，是一个很笼统的业务上的权限，而不是所谓的 谁有添加、删除、修改的权限这么细腻', '1');
INSERT INTO `project_require` VALUES ('4', '文件上传、下载需求', '2', '2014-03-16 21:41:04', '2014-03-16 21:41:04', '有人曾问过我，是不是后悔说过的话。我不后悔我做过的事，我后悔的只是有些事我有机会做的时候，我却没有做。默默疼着你，是我最后的温柔。真正的爱情不是一时好感，而是明明知道没结果，还想要坚持下去的冲动。无论吵闹多少次，说多少伤人的话，最后还是会因为舍不得你而和好如初，这种感觉真好。', '0');
INSERT INTO `project_require` VALUES ('5', '职员管理需求', '2', '2014-03-16 21:41:04', '2014-03-16 21:41:04', '山有木兮木有枝，心悦君兮君不知', '0');
INSERT INTO `project_require` VALUES ('6', '资源需求', '2', '2014-04-16 21:41:04', '2014-04-16 21:41:04', '有人，骗我、欺我、笑我、辱我、害我，何以处置？唯有，敬他、容他、让他、忍他、随他，看准时机弄死他', '0');

-- ----------------------------
-- Table structure for rbps_permit
-- ----------------------------
DROP TABLE IF EXISTS `rbps_permit`;
CREATE TABLE `rbps_permit` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id ',
  `permitName` varchar(255) NOT NULL,
  `flag` varchar(255) NOT NULL COMMENT '权限标志',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rbps_permit
-- ----------------------------
INSERT INTO `rbps_permit` VALUES ('1', '新增', 'add');
INSERT INTO `rbps_permit` VALUES ('2', '编辑', 'edit');
INSERT INTO `rbps_permit` VALUES ('3', '移除', 'remove');
INSERT INTO `rbps_permit` VALUES ('4', '刷新', '');
INSERT INTO `rbps_permit` VALUES ('5', '发布', '');
INSERT INTO `rbps_permit` VALUES ('6', '变更', '');
INSERT INTO `rbps_permit` VALUES ('7', '下载(导出)', '');
INSERT INTO `rbps_permit` VALUES ('8', '导入', '');
INSERT INTO `rbps_permit` VALUES ('9', '角色授权', '');
INSERT INTO `rbps_permit` VALUES ('10', '用户授权', '');
INSERT INTO `rbps_permit` VALUES ('11', '发放', '');
INSERT INTO `rbps_permit` VALUES ('12', '查看', '');

-- ----------------------------
-- Table structure for record_land
-- ----------------------------
DROP TABLE IF EXISTS `record_land`;
CREATE TABLE `record_land` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `loginName` varchar(255) NOT NULL COMMENT '登陆名',
  `ipAddress` varchar(255) NOT NULL COMMENT 'ip地址',
  `city` varchar(255) NOT NULL COMMENT '所在城市',
  `createTime` varchar(255) NOT NULL COMMENT '创建时间',
  `type` int(11) NOT NULL DEFAULT '-1' COMMENT '类别(-1：登陆系统；1：退出系统)',
  `browser` varchar(255) DEFAULT NULL COMMENT '浏览器(Chrome 30.0、Firefox 30.0、QQ浏览器 7.7 、UC浏览器、IE 9.0 )',
  `loginStyle` varchar(255) DEFAULT NULL COMMENT '登录方式(用户名登录、WAP登录、暂无数据)',
  `os` varchar(255) DEFAULT NULL COMMENT '登陆系统时使用的操作系统(移动设备:Android等；电脑：WinXP、Win7)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=891 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of record_land
-- ----------------------------
INSERT INTO `record_land` VALUES ('601', 'admin', '192.168.2.116', '局域网', '2014-09-02 10:01:00', '-1', 'Firefox 31.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('602', 'admin', '192.168.2.116', '局域网', '2014-09-02 10:30:36', '-1', 'Firefox 31.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('603', 'admin', '192.168.2.120', '局域网', '2014-09-02 11:04:10', '-1', 'Google Chrome 31.0.1650.63', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('604', 'admin', '192.168.2.116', '局域网', '2014-09-02 11:24:47', '-1', 'Firefox 31.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('605', 'admin', '192.168.2.116', '局域网', '2014-09-02 12:26:01', '-1', 'Firefox 31.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('606', 'admin', '192.168.2.116', '局域网', '2014-09-02 12:42:47', '-1', 'Firefox 31.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('607', 'admin', '192.168.2.116', '局域网', '2014-09-02 12:43:54', '-1', 'Firefox 31.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('608', 'admin', '192.168.2.116', '局域网', '2014-09-02 13:02:28', '-1', 'Firefox 31.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('609', 'admin', '192.168.2.116', '局域网', '2014-09-02 13:04:04', '-1', 'Firefox 31.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('610', 'admin', '192.168.2.116', '局域网', '2014-09-02 13:06:05', '-1', 'Firefox 31.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('611', 'admin', '192.168.2.116', '局域网', '2014-09-02 13:08:27', '-1', 'Firefox 31.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('612', 'admin', '192.168.2.116', '局域网', '2014-09-02 13:36:37', '-1', 'Firefox 31.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('613', 'admin', '192.168.2.116', '局域网', '2014-09-02 13:38:46', '-1', 'Firefox 31.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('614', 'admin', '192.168.2.116', '局域网', '2014-09-02 13:40:02', '-1', 'Firefox 31.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('615', 'admin', '192.168.2.116', '局域网', '2014-09-02 13:44:06', '-1', 'Firefox 31.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('616', 'admin', '192.168.2.116', '局域网', '2014-09-02 13:47:45', '-1', 'Firefox 31.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('617', 'admin', '192.168.2.116', '局域网', '2014-09-02 14:32:06', '-1', 'Firefox 31.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('618', 'admin', '192.168.2.116', '局域网', '2014-09-02 14:38:01', '-1', 'Firefox 31.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('619', 'admin', '192.168.2.116', '局域网', '2014-09-02 15:14:58', '-1', 'Firefox 31.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('620', 'admin', '192.168.2.116', '局域网', '2014-09-02 15:37:07', '-1', 'Firefox 31.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('621', 'admin', '192.168.2.116', '局域网', '2014-09-02 15:51:58', '-1', 'Firefox 31.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('622', 'admin', '192.168.2.116', '局域网', '2014-09-02 16:25:16', '-1', 'Firefox 31.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('623', 'admin', '192.168.2.116', '局域网', '2014-09-02 16:52:46', '-1', 'Firefox 31.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('624', 'admin', '192.168.2.116', '局域网', '2014-09-02 17:15:05', '-1', 'Firefox 31.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('625', 'admin', '192.168.2.116', '局域网', '2014-09-02 17:22:29', '-1', 'Firefox 31.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('626', 'admin', '192.168.2.116', '局域网', '2014-09-02 17:39:13', '-1', 'Firefox 31.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('627', 'admin', '192.168.2.116', '局域网', '2014-09-02 17:56:11', '-1', 'Firefox 31.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('628', 'admin', '192.168.2.116', '局域网', '2014-09-02 17:57:35', '-1', 'Firefox 31.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('629', 'admin', '192.168.2.116', '局域网', '2014-09-03 08:08:51', '-1', 'Firefox 31.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('630', 'admin', '192.168.2.116', '局域网', '2014-09-03 10:29:47', '-1', 'Firefox 31.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('631', 'admin', '192.168.2.116', '局域网', '2014-09-03 12:33:40', '-1', 'Firefox 31.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('632', 'admin', '192.168.2.116', '局域网', '2014-09-03 15:01:31', '-1', 'Firefox 31.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('633', 'admin', '192.168.2.116', '局域网', '2014-09-03 15:28:39', '-1', 'Firefox 31.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('634', 'admin', '192.168.2.116', '局域网', '2014-09-03 15:51:43', '-1', 'Firefox 31.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('635', 'admin', '192.168.2.116', '局域网', '2014-09-04 08:08:56', '-1', 'Firefox 31.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('636', 'admin', '192.168.2.116', '局域网', '2014-09-04 10:55:13', '-1', 'Firefox 31.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('637', 'admin', '192.168.2.120', '局域网', '2014-09-04 11:21:27', '-1', 'Google Chrome 31.0.1650.63', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('638', 'admin', '192.168.2.116', '局域网', '2014-09-04 13:00:52', '-1', 'Firefox 31.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('639', 'admin', '192.168.2.116', '局域网', '2014-09-04 13:32:39', '-1', 'Firefox 31.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('640', 'admin', '192.168.2.116', '局域网', '2014-09-04 13:36:01', '-1', 'Firefox 31.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('641', 'admin', '192.168.2.116', '局域网', '2014-09-04 13:43:52', '-1', 'Firefox 31.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('642', 'admin', '192.168.2.116', '局域网', '2014-09-04 13:45:57', '-1', 'Firefox 31.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('643', 'admin', '192.168.2.120', '局域网', '2014-09-04 14:41:38', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('644', 'admin', '192.168.2.116', '局域网', '2014-09-04 15:51:37', '-1', 'Firefox 31.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('645', 'admin', '192.168.2.116', '局域网', '2014-09-04 18:33:37', '-1', 'Firefox 31.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('646', 'admin', '192.168.2.116', '局域网', '2014-09-05 08:38:04', '-1', 'Firefox 31.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('647', 'admin', '192.168.2.116', '局域网', '2014-09-05 08:56:28', '-1', 'Google Chrome 31.0.1650.63', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('648', 'admin', '192.168.2.116', '局域网', '2014-09-05 09:01:25', '-1', 'IE 9.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('649', 'admin', '192.168.2.156', '局域网', '2014-09-05 09:40:26', '-1', 'IE 7.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('650', 'admin', '192.168.2.116', '局域网', '2014-09-05 10:21:08', '-1', 'Firefox 31.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('651', 'admin', '192.168.2.116', '局域网', '2014-09-05 11:01:57', '-1', 'IE 9.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('652', 'admin', '192.168.2.116', '局域网', '2014-09-05 13:00:28', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('653', 'admin', '192.168.2.116', '局域网', '2014-09-05 13:11:09', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('654', 'admin', '192.168.2.116', '局域网', '2014-09-05 14:04:00', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('655', 'admin', '192.168.2.116', '局域网', '2014-09-05 14:25:31', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('656', 'admin', '192.168.2.116', '局域网', '2014-09-05 15:02:45', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('657', 'admin', '192.168.2.116', '局域网', '2014-09-05 15:14:24', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('658', 'admin', '192.168.2.116', '局域网', '2014-09-05 15:32:00', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('659', 'admin', '192.168.2.116', '局域网', '2014-09-05 16:00:10', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('660', 'admin', '192.168.2.116', '局域网', '2014-09-05 18:09:05', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('661', 'admin', '192.168.2.116', '局域网', '2014-09-05 18:49:23', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('662', 'admin', '192.168.13.104', '局域网', '2014-09-05 23:05:28', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('663', 'admin', '192.168.13.104', '局域网', '2014-09-05 23:11:45', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('664', 'admin', '192.168.13.104', '局域网', '2014-09-05 23:23:24', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('665', 'admin', '192.168.13.104', '局域网', '2014-09-05 23:40:42', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('666', 'admin', '192.168.13.104', '局域网', '2014-09-06 07:58:21', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('667', 'admin', '192.168.13.104', '局域网', '2014-09-06 15:30:11', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('668', 'admin', '192.168.13.104', '局域网', '2014-09-06 15:39:58', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('669', 'admin', '192.168.13.104', '局域网', '2014-09-06 16:35:05', '1', null, null, null);
INSERT INTO `record_land` VALUES ('670', 'admin', '192.168.13.104', '局域网', '2014-09-06 16:46:58', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('671', 'admin', '192.168.13.104', '局域网', '2014-09-06 16:47:41', '1', null, null, null);
INSERT INTO `record_land` VALUES ('672', 'admin', '192.168.13.104', '局域网', '2014-09-06 16:48:21', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('673', 'admin', '192.168.13.104', '局域网', '2014-09-06 17:34:10', '1', null, null, null);
INSERT INTO `record_land` VALUES ('674', 'admin', '192.168.2.116', '局域网', '2014-09-07 11:00:45', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('675', 'admin', '192.168.2.116', '局域网', '2014-09-07 14:37:16', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('676', 'admin', '192.168.2.116', '局域网', '2014-09-07 14:38:24', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('677', 'admin', '192.168.2.116', '局域网', '2014-09-07 14:49:57', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('678', 'admin', '192.168.2.116', '局域网', '2014-09-07 14:52:14', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('679', 'admin', '192.168.2.116', '局域网', '2014-09-07 14:56:01', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('680', 'admin', '192.168.2.116', '局域网', '2014-09-07 14:58:03', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('681', 'admin', '192.168.2.116', '局域网', '2014-09-07 15:00:15', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('682', 'admin', '192.168.2.116', '局域网', '2014-09-07 15:07:25', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('683', 'admin', '192.168.2.116', '局域网', '2014-09-07 17:35:09', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('684', 'admin', '192.168.2.116', '局域网', '2014-09-07 17:38:38', '1', null, null, null);
INSERT INTO `record_land` VALUES ('685', 'guest', '192.168.2.116', '局域网', '2014-09-07 17:38:46', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('686', 'admin', '192.168.2.116', '局域网', '2014-09-08 10:11:17', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('687', 'admin', '192.168.2.116', '局域网', '2014-09-08 10:11:42', '1', null, null, null);
INSERT INTO `record_land` VALUES ('688', 'admin', '192.168.2.116', '局域网', '2014-09-08 10:11:49', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('689', 'admin', '192.168.2.116', '局域网', '2014-09-08 12:01:09', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('690', 'admin', '192.168.2.116', '局域网', '2014-09-08 12:17:18', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('691', 'admin', '192.168.2.116', '局域网', '2014-09-08 12:27:07', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('692', 'admin', '192.168.2.116', '局域网', '2014-09-08 12:29:08', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('693', 'admin', '192.168.2.116', '局域网', '2014-09-08 12:38:35', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('694', 'admin', '192.168.2.116', '局域网', '2014-09-08 12:43:18', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('695', 'admin', '192.168.2.116', '局域网', '2014-09-08 12:47:57', '1', null, null, null);
INSERT INTO `record_land` VALUES ('696', 'admin', '192.168.2.116', '局域网', '2014-09-08 12:48:04', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('697', 'admin', '192.168.2.116', '局域网', '2014-09-08 13:21:40', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('698', 'admin', '192.168.2.116', '局域网', '2014-09-08 15:03:19', '-1', 'Google Chrome 31.0.1650.63', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('699', 'admin', '192.168.2.116', '局域网', '2014-09-08 15:07:26', '-1', 'IE 9.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('700', 'admin', '192.168.2.116', '局域网', '2014-09-08 16:05:22', '1', null, null, null);
INSERT INTO `record_land` VALUES ('701', 'admin', '192.168.2.116', '局域网', '2014-09-09 08:49:35', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('702', 'admin', '192.168.2.116', '局域网', '2014-09-09 08:52:08', '-1', 'Google Chrome 31.0.1650.63', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('703', 'admin', '192.168.2.116', '局域网', '2014-09-09 11:12:20', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('704', 'admin', '192.168.2.116', '局域网', '2014-09-09 14:08:25', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('705', 'admin', '192.168.2.116', '局域网', '2014-09-10 08:05:36', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('706', 'admin', '192.168.2.116', '局域网', '2014-09-10 08:27:35', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('707', 'admin', '192.168.2.116', '局域网', '2014-09-10 11:00:06', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('708', 'admin', '192.168.2.116', '局域网', '2014-09-10 11:11:49', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('709', 'admin', '192.168.2.116', '局域网', '2014-09-10 11:12:56', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('710', 'admin', '192.168.2.116', '局域网', '2014-09-10 11:19:15', '1', null, null, null);
INSERT INTO `record_land` VALUES ('711', 'admin', '192.168.2.116', '局域网', '2014-09-10 11:19:30', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('712', 'admin', '192.168.2.116', '局域网', '2014-09-10 11:21:06', '1', null, null, null);
INSERT INTO `record_land` VALUES ('713', 'admin', '192.168.2.116', '局域网', '2014-09-10 11:21:15', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('714', 'admin', '192.168.2.116', '局域网', '2014-09-10 11:31:56', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('715', 'admin', '192.168.2.116', '局域网', '2014-09-10 13:22:27', '-1', 'Google Chrome 31.0.1650.63', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('716', 'admin', '192.168.2.116', '局域网', '2014-09-10 13:31:42', '-1', 'IE 9.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('717', 'admin', '192.168.2.116', '局域网', '2014-09-10 14:38:00', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('718', 'admin', '192.168.2.116', '局域网', '2014-09-10 14:40:19', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('719', 'admin', '192.168.2.116', '局域网', '2014-09-10 14:41:53', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('720', 'admin', '192.168.2.116', '局域网', '2014-09-10 14:44:03', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('721', 'admin', '192.168.2.116', '局域网', '2014-09-10 14:59:06', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('722', 'admin', '192.168.2.116', '局域网', '2014-09-10 15:00:14', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('723', 'admin', '192.168.2.116', '局域网', '2014-09-10 15:04:36', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('724', 'admin', '192.168.2.116', '局域网', '2014-09-10 15:28:43', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('725', 'admin', '192.168.2.116', '局域网', '2014-09-10 15:31:11', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('726', 'admin', '192.168.2.116', '局域网', '2014-09-10 15:33:07', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('727', 'admin', '192.168.2.116', '局域网', '2014-09-10 15:37:50', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('728', 'admin', '192.168.2.116', '局域网', '2014-09-10 15:44:18', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('729', 'admin', '192.168.2.116', '局域网', '2014-09-10 15:47:56', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('730', 'admin', '192.168.2.116', '局域网', '2014-09-10 15:51:34', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('731', 'admin', '192.168.2.116', '局域网', '2014-09-10 15:58:21', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('732', 'admin', '192.168.2.116', '局域网', '2014-09-10 16:02:16', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('733', 'admin', '192.168.2.116', '局域网', '2014-09-10 16:04:45', '1', null, null, null);
INSERT INTO `record_land` VALUES ('734', 'admin', '192.168.2.116', '局域网', '2014-09-10 16:05:20', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('735', 'admin', '192.168.2.116', '局域网', '2014-09-10 16:16:26', '1', null, null, null);
INSERT INTO `record_land` VALUES ('736', 'admin', '192.168.2.116', '局域网', '2014-09-10 16:17:38', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('737', 'admin', '192.168.2.116', '局域网', '2014-09-10 16:18:42', '-1', 'IE 9.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('738', 'admin', '192.168.2.116', '局域网', '2014-09-10 16:40:54', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('739', 'admin', '192.168.2.116', '局域网', '2014-09-11 08:48:40', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('740', 'admin', '192.168.2.116', '局域网', '2014-09-11 09:12:18', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('741', 'admin', '192.168.2.116', '局域网', '2014-09-11 09:16:42', '1', null, null, null);
INSERT INTO `record_land` VALUES ('742', 'admin', '192.168.2.116', '局域网', '2014-09-11 13:15:55', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('743', 'admin', '192.168.2.116', '局域网', '2014-09-12 13:59:05', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('744', 'guest', '192.168.2.116', '局域网', '2014-09-12 15:46:01', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('745', 'guest', '192.168.2.116', '局域网', '2014-09-12 16:07:34', '1', null, null, null);
INSERT INTO `record_land` VALUES ('746', 'admin', '192.168.2.116', '局域网', '2014-09-12 16:07:41', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('747', 'admin', '192.168.13.105', '局域网', '2014-09-13 19:39:36', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('748', 'admin', '192.168.13.105', '局域网', '2014-09-13 19:39:48', '1', null, null, null);
INSERT INTO `record_land` VALUES ('749', 'admin', '192.168.13.105', '局域网', '2014-09-13 19:39:55', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('750', 'admin', '192.168.13.105', '局域网', '2014-09-13 19:40:50', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('751', 'admin', '192.168.13.105', '局域网', '2014-09-14 15:16:44', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('752', 'admin', '192.168.13.105', '局域网', '2014-09-14 17:55:00', '1', null, null, null);
INSERT INTO `record_land` VALUES ('753', 'admin', '192.168.2.116', '局域网', '2014-09-15 08:27:04', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('754', 'admin', '192.168.2.116', '局域网', '2014-09-15 08:29:45', '-1', 'IE 9.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('755', 'admin', '192.168.2.116', '局域网', '2014-09-15 11:01:21', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('756', 'admin', '192.168.2.116', '局域网', '2014-09-15 14:26:30', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('757', 'admin', '192.168.2.116', '局域网', '2014-09-16 10:20:18', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('758', 'admin', '192.168.2.116', '局域网', '2014-09-16 13:36:12', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('759', 'admin', '192.168.2.119', '局域网', '2014-09-16 14:32:11', '-1', 'Safari 4.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('760', 'admin', '192.168.2.119', '局域网', '2014-09-19 15:20:32', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('761', 'admin', '192.168.2.119', '局域网', '2014-09-19 16:05:54', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('762', 'admin', '192.168.13.102', '局域网', '2014-09-19 21:29:43', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('763', 'admin', '192.168.13.102', '局域网', '2014-09-19 22:42:03', '1', null, null, null);
INSERT INTO `record_land` VALUES ('764', 'admin', '192.168.1.101', '局域网', '2014-09-20 23:26:01', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('765', 'admin', '192.168.2.118', '局域网', '2014-09-21 08:20:13', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('766', 'admin', '192.168.2.118', '局域网', '2014-09-23 09:12:09', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('767', 'admin', '192.168.2.118', '局域网', '2014-09-23 10:20:10', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('768', 'admin', '192.168.2.118', '局域网', '2014-09-23 10:36:32', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('769', 'admin', '192.168.2.118', '局域网', '2014-09-23 10:55:15', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('770', 'admin', '192.168.2.118', '局域网', '2014-09-23 10:58:21', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('771', 'admin', '192.168.2.118', '局域网', '2014-09-23 10:59:36', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('772', 'admin', '192.168.2.118', '局域网', '2014-09-23 11:02:55', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('773', 'admin', '192.168.2.118', '局域网', '2014-09-23 11:18:15', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('774', 'admin', '192.168.2.118', '局域网', '2014-09-23 11:22:07', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('775', 'admin', '192.168.2.118', '局域网', '2014-09-23 11:30:47', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('776', 'admin', '192.168.2.118', '局域网', '2014-09-23 11:38:25', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('777', 'admin', '192.168.2.118', '局域网', '2014-09-23 11:43:59', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('778', 'admin', '192.168.2.118', '局域网', '2014-09-23 11:45:38', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('779', 'admin', '192.168.2.118', '局域网', '2014-10-03 15:56:36', '1', null, null, null);
INSERT INTO `record_land` VALUES ('780', 'admin', '192.168.2.118', '局域网', '2014-10-03 15:56:40', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('781', 'admin', '192.168.2.118', '局域网', '2014-10-03 16:08:05', '1', null, null, null);
INSERT INTO `record_land` VALUES ('782', 'admin', '192.168.2.118', '局域网', '2014-10-03 16:08:14', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('783', 'admin', '192.168.2.116', '局域网', '2014-10-06 09:38:02', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('784', 'admin', '192.168.2.116', '局域网', '2014-10-06 14:38:46', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('785', 'admin', '192.168.2.116', '局域网', '2014-10-06 17:41:32', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('786', 'admin', '192.168.2.116', '局域网', '2014-10-07 08:57:46', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('787', 'admin', '192.168.2.116', '局域网', '2014-10-07 09:00:06', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('788', 'admin', '192.168.2.116', '局域网', '2014-10-07 11:49:30', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('789', 'admin', '192.168.2.116', '局域网', '2014-10-07 11:55:16', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('790', 'admin', '192.168.2.116', '局域网', '2014-10-07 13:20:52', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('791', 'admin', '192.168.2.116', '局域网', '2014-10-07 13:24:02', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('792', 'admin', '192.168.2.116', '局域网', '2014-10-07 14:03:56', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('793', 'admin', '192.168.2.116', '局域网', '2014-10-07 14:06:19', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('794', 'admin', '192.168.2.116', '局域网', '2014-10-08 08:24:44', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('795', 'admin', '192.168.2.116', '局域网', '2014-10-08 10:38:37', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('796', 'admin', '192.168.2.116', '局域网', '2014-10-08 10:44:27', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('797', 'admin', '192.168.2.138', '局域网', '2014-10-08 13:01:19', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('798', 'admin', '192.168.2.138', '局域网', '2014-10-08 13:05:36', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('799', 'admin', '192.168.2.138', '局域网', '2014-10-08 13:08:47', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('800', 'admin', '192.168.2.138', '局域网', '2014-10-08 14:14:42', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('801', 'admin', '192.168.2.138', '局域网', '2014-10-08 14:36:10', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('802', 'admin', '192.168.2.138', '局域网', '2014-10-08 14:50:14', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('803', 'admin', '192.168.2.138', '局域网', '2014-10-08 14:58:08', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('804', 'admin', '192.168.2.138', '局域网', '2014-10-08 15:04:13', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('805', 'admin', '192.168.2.138', '局域网', '2014-10-08 15:09:37', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('806', 'admin', '192.168.2.138', '局域网', '2014-10-08 15:31:16', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('807', 'admin', '192.168.2.138', '局域网', '2014-10-08 15:33:03', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('808', 'admin', '192.168.2.138', '局域网', '2014-10-08 15:55:11', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('809', 'admin', '192.168.2.138', '局域网', '2014-10-08 15:56:32', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('810', 'admin', '192.168.2.138', '局域网', '2014-10-08 17:41:13', '-1', 'IE 9.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('811', 'admin', '192.168.2.138', '局域网', '2014-10-09 08:25:08', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('812', 'admin', '192.168.2.132', '局域网', '2014-10-09 08:49:10', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('813', 'admin', '192.168.2.132', '局域网', '2014-10-09 08:50:15', '-1', 'Google Chrome 31.0.1650.63', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('814', 'admin', '192.168.2.138', '局域网', '2014-10-09 09:47:51', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('815', 'admin', '192.168.2.138', '局域网', '2014-10-09 09:50:55', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('816', 'admin', '192.168.2.138', '局域网', '2014-10-09 09:52:33', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('817', 'admin', '192.168.2.139', '局域网', '2014-10-11 09:24:06', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('818', 'admin', '192.168.2.139', '局域网', '2014-10-11 10:07:04', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('819', 'admin', '192.168.2.139', '局域网', '2014-10-11 10:35:37', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('820', 'admin', '192.168.2.139', '局域网', '2014-10-11 10:37:40', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('821', 'admin', '192.168.2.139', '局域网', '2014-10-11 10:48:41', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('822', 'admin', '192.168.2.139', '局域网', '2014-10-11 14:28:11', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('823', 'admin', '192.168.2.139', '局域网', '2014-10-11 15:23:58', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('824', 'admin', '192.168.2.139', '局域网', '2014-10-11 15:28:01', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('825', 'admin', '192.168.2.139', '局域网', '2014-10-13 08:45:33', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('826', 'admin', '192.168.2.139', '局域网', '2014-10-14 14:15:14', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('827', 'admin', '192.168.2.139', '局域网', '2014-10-14 14:27:48', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('828', 'admin', '192.168.2.139', '局域网', '2014-10-14 14:28:35', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('829', 'admin', '192.168.2.139', '局域网', '2014-10-14 15:02:45', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('830', 'admin', '192.168.2.139', '局域网', '2014-10-14 15:55:02', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('831', 'admin', '192.168.2.139', '局域网', '2014-10-14 15:57:25', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('832', 'admin', '192.168.2.139', '局域网', '2014-10-14 15:58:28', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('833', 'admin', '192.168.2.139', '局域网', '2014-10-14 16:01:55', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('834', 'admin', '192.168.2.139', '局域网', '2014-10-14 16:20:57', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('835', 'admin', '192.168.2.139', '局域网', '2014-10-14 16:31:48', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('836', 'admin', '192.168.2.139', '局域网', '2014-10-14 16:32:28', '-1', 'IE 9.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('837', 'admin', '192.168.2.139', '局域网', '2014-10-14 16:32:59', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('838', 'admin', '192.168.2.139', '局域网', '2014-10-14 17:16:07', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('839', 'admin', '192.168.2.139', '局域网', '2014-10-14 17:37:45', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('840', 'admin', '192.168.2.139', '局域网', '2014-10-14 18:10:01', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('841', 'admin', '192.168.2.139', '局域网', '2014-10-14 18:14:16', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('842', 'admin', '192.168.2.139', '局域网', '2014-10-14 18:17:25', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('843', 'admin', '192.168.2.139', '局域网', '2014-10-14 18:44:26', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('844', 'admin', '192.168.2.139', '局域网', '2014-10-14 22:58:11', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('845', 'admin', '192.168.2.139', '局域网', '2014-10-15 07:31:09', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('846', 'admin', '192.168.2.139', '局域网', '2014-10-15 08:30:06', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('847', 'admin', '192.168.2.139', '局域网', '2014-10-15 08:44:54', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('848', 'admin', '192.168.2.139', '局域网', '2014-10-15 09:10:44', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('849', 'admin', '192.168.2.139', '局域网', '2014-10-15 09:18:23', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('850', 'admin', '192.168.2.139', '局域网', '2014-10-15 09:27:39', '-1', 'Firefox 32.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('851', 'admin', '192.168.2.139', '局域网', '2014-10-15 10:02:01', '-1', 'Firefox 33.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('852', 'admin', '192.168.2.139', '局域网', '2014-10-15 10:51:24', '1', null, null, null);
INSERT INTO `record_land` VALUES ('853', 'admin', '192.168.2.139', '局域网', '2014-10-15 14:17:03', '-1', 'Firefox 33.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('854', 'admin', '192.168.2.139', '局域网', '2014-10-15 14:43:45', '-1', 'Firefox 33.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('855', 'admin', '192.168.2.139', '局域网', '2014-10-15 15:08:09', '-1', 'Firefox 33.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('856', 'admin', '192.168.2.139', '局域网', '2014-10-15 15:28:59', '-1', 'Firefox 33.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('857', 'admin', '192.168.2.139', '局域网', '2014-10-15 15:42:15', '-1', 'Firefox 33.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('858', 'admin', '192.168.2.139', '局域网', '2014-10-15 15:48:02', '-1', 'Firefox 33.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('859', 'admin', '192.168.2.139', '局域网', '2014-10-15 16:09:11', '-1', 'Firefox 33.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('860', 'admin', '192.168.2.139', '局域网', '2014-10-15 16:16:10', '-1', 'Firefox 33.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('861', 'admin', '192.168.2.139', '局域网', '2014-10-15 16:18:08', '-1', 'Firefox 33.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('862', 'admin', '192.168.2.139', '局域网', '2014-10-15 16:40:40', '-1', 'IE 9.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('863', 'admin', '192.168.2.139', '局域网', '2014-10-15 16:46:02', '-1', 'Firefox 33.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('864', 'admin', '192.168.2.139', '局域网', '2014-10-15 16:49:18', '-1', 'Firefox 33.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('865', 'admin', '192.168.2.139', '局域网', '2014-10-15 16:49:42', '-1', 'IE 9.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('866', 'admin', '192.168.2.139', '局域网', '2014-10-15 16:51:36', '-1', 'Firefox 33.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('867', 'admin', '192.168.2.139', '局域网', '2014-10-15 16:55:14', '-1', 'Firefox 33.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('868', 'admin', '192.168.2.139', '局域网', '2014-10-15 16:58:55', '-1', 'IE 9.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('869', 'admin', '192.168.2.139', '局域网', '2014-10-15 16:59:09', '-1', 'Firefox 33.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('870', 'admin', '192.168.2.139', '局域网', '2014-10-15 17:50:41', '-1', 'Firefox 33.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('871', 'admin', '192.168.2.139', '局域网', '2014-10-15 18:04:46', '-1', 'Firefox 33.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('872', 'admin', '192.168.2.139', '局域网', '2014-10-15 18:08:32', '-1', 'IE 9.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('873', 'admin', '192.168.2.139', '局域网', '2014-10-15 18:11:11', '-1', 'IE 9.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('874', 'admin', '192.168.2.139', '局域网', '2014-10-15 18:13:26', '1', null, null, null);
INSERT INTO `record_land` VALUES ('875', 'admin', '192.168.2.139', '局域网', '2014-10-15 18:13:36', '-1', 'IE 9.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('876', 'admin', '192.168.2.139', '局域网', '2014-10-15 18:13:49', '1', null, null, null);
INSERT INTO `record_land` VALUES ('877', 'admin', '192.168.2.139', '局域网', '2014-10-15 18:14:07', '-1', 'IE 9.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('878', 'admin', '192.168.2.139', '局域网', '2014-10-15 18:15:04', '1', null, null, null);
INSERT INTO `record_land` VALUES ('879', 'admin', '192.168.2.139', '局域网', '2014-10-16 09:18:07', '-1', 'Firefox 33.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('880', 'admin', '192.168.2.139', '局域网', '2014-10-16 14:29:49', '-1', 'Firefox 33.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('881', 'admin', '192.168.2.139', '局域网', '2014-10-16 14:49:39', '-1', 'Firefox 33.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('882', 'admin', '192.168.2.139', '局域网', '2014-10-16 14:59:27', '-1', 'IE 9.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('883', 'admin', '192.168.2.139', '局域网', '2014-10-16 16:13:24', '-1', 'Firefox 33.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('884', 'admin', '192.168.2.139', '局域网', '2014-10-17 10:17:22', '-1', 'Firefox 33.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('885', 'admin', '192.168.2.139', '局域网', '2014-10-27 14:40:27', '-1', 'Firefox 33.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('886', 'admin', '192.168.2.139', '局域网', '2014-10-27 14:43:42', '-1', 'Firefox 33.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('887', 'admin', '192.168.2.164', '局域网', '2014-11-10 11:07:59', '-1', 'Firefox 33.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('888', 'admin', '192.168.2.164', '局域网', '2014-11-10 11:09:23', '1', null, null, null);
INSERT INTO `record_land` VALUES ('889', 'admin', '192.168.2.164', '局域网', '2014-11-18 16:48:11', '-1', 'Firefox 33.0', '用户名登录', 'Windows Vista');
INSERT INTO `record_land` VALUES ('890', 'admin', '192.168.2.164', '局域网', '2014-11-18 16:56:11', '1', null, null, null);

-- ----------------------------
-- Table structure for record_type
-- ----------------------------
DROP TABLE IF EXISTS `record_type`;
CREATE TABLE `record_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` int(11) NOT NULL DEFAULT '0',
  `value` varchar(255) NOT NULL COMMENT '登陆类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of record_type
-- ----------------------------
INSERT INTO `record_type` VALUES ('1', '-1', '登陆系统');
INSERT INTO `record_type` VALUES ('2', '1', '退出系统');

-- ----------------------------
-- Table structure for resumeinfo
-- ----------------------------
DROP TABLE IF EXISTS `resumeinfo`;
CREATE TABLE `resumeinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  `age` varchar(255) DEFAULT NULL,
  `education` varchar(255) DEFAULT NULL,
  `experience` varchar(255) DEFAULT NULL,
  `phone` double(200,0) DEFAULT NULL,
  `job` varchar(255) DEFAULT NULL,
  `entertime` varchar(255) DEFAULT NULL,
  `interviewTime` varchar(255) DEFAULT NULL COMMENT '面试时间',
  `source` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=134 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of resumeinfo
-- ----------------------------
INSERT INTO `resumeinfo` VALUES ('1', '唐娟', '女', '24', '本科', '二年以上', '18201140910', 'java高级软件工程师', '2013-10-21 10:10:00', '2014-03-26 14:56:44', '51job');
INSERT INTO `resumeinfo` VALUES ('2', '崔星范', '男', '27', '本科', '二年以上', '18600557703', '系统架构师/项目经理', '2013-10-21 00:00:00', '2014-03-26 14:56:45', '51job');
INSERT INTO `resumeinfo` VALUES ('3', '雷立春', '男', '25', '本科', '三年以上', '18702238091', '系统架构师/项目经理', '2013-10-21 00:00:00', '2014-03-26 14:56:45', '51job');
INSERT INTO `resumeinfo` VALUES ('4', '孟飞', '男', '23', '本科', '三年以上', '13969931327', '搜索引擎研发工程师', '2013-10-21 00:00:00', '2014-03-26 14:56:45', '51job');
INSERT INTO `resumeinfo` VALUES ('5', '彭龙', '男', '24', '本科', '一年以上', '13910429964', '搜索引擎研发工程师', '2013-10-21 00:00:00', '2014-03-26 14:56:45', '51job');
INSERT INTO `resumeinfo` VALUES ('6', '董振兴', '男', '23', '本科', '一年以上', '15239986830', '搜索引擎研发工程师', '2013-10-21 00:00:00', '2014-03-26 14:56:45', '51job');
INSERT INTO `resumeinfo` VALUES ('7', '贾宝玉', '男', '25', '本科', '一年以上', '18311060816', '搜索引擎研发工程师', '2013-10-21 00:00:00', '2014-03-26 14:56:45', '51job');
INSERT INTO `resumeinfo` VALUES ('8', '甘家利', '男', '22', '本科', '二年以上', '13141198426', 'java高级软件工程师', '2013-10-21 00:00:00', '2014-03-26 14:56:45', '51job');
INSERT INTO `resumeinfo` VALUES ('9', '杨江红', '男', '22', '本科', '一年以上', '13167395376', 'java高级软件工程师', '2013-10-21 00:00:00', '2014-03-26 14:56:45', '51job');
INSERT INTO `resumeinfo` VALUES ('10', '张帆', '男', '25', '本科', '二年以上', '13521905245', '系统架构师/项目经理', '2013-10-21 00:00:00', '2014-03-26 14:56:45', '51job');
INSERT INTO `resumeinfo` VALUES ('11', '安坚兵', '男', '26', '本科', '三年以上', '15801152142', '系统架构师/项目经理', '2013-10-21 00:00:00', '2014-03-26 14:56:45', '51job');
INSERT INTO `resumeinfo` VALUES ('12', '路德棋', '男', '26', '本科', '三年以上', '18600498838', '系统架构师/项目经理', '2013-10-21 00:00:00', '2014-03-26 14:56:45', '51job');
INSERT INTO `resumeinfo` VALUES ('13', '张舒', '男', '25', '本科', '三年以上', '15810670356', '搜索引擎研发工程师', '2013-10-21 00:00:00', '2014-03-26 14:56:45', '51job');
INSERT INTO `resumeinfo` VALUES ('14', '郑豪杰', '男', '24', '本科', '三年以上', '18311065026', '搜索引擎研发工程师', '2013-10-21 00:00:00', '2014-03-26 14:56:45', '51job');
INSERT INTO `resumeinfo` VALUES ('15', '戴景祥', '男', '23', '本科', '一年以上', '15510765362', '搜索引擎研发工程师', '2013-10-21 00:00:00', '2014-03-26 14:56:45', '51job');
INSERT INTO `resumeinfo` VALUES ('16', '王义', '男', '26', '本科', '应届毕业生', '15600049446', '搜索引擎研发工程师', '2013-10-21 00:00:00', '2014-03-26 14:56:45', '51job');
INSERT INTO `resumeinfo` VALUES ('17', '郑健', '男', '27', '本科', '一年以上', '18645600090', 'java软件工程师', '2013-10-21 00:00:00', '2014-03-26 14:56:45', '51job');
INSERT INTO `resumeinfo` VALUES ('18', '蔡野', '男', '25', '本科', '三年以上', '13311349525', 'JAVA开发工程师', '2013-10-21 00:00:00', '2014-03-26 14:56:45', '智联招聘');
INSERT INTO `resumeinfo` VALUES ('19', '陈冠军', '男', '23', '本科', '三年以上', '18600439394', 'JAVA开发工程师', '2013-11-10 00:00:00', '2014-03-26 14:56:45', '智联招聘');
INSERT INTO `resumeinfo` VALUES ('20', '王雪刚', '男', '24', '本科', '二年以上', '13520267820', 'JAVA高级软件工程师', '2013-11-10 00:00:00', '2014-03-26 14:56:45', '智联招聘');
INSERT INTO `resumeinfo` VALUES ('21', '徐记涛', '男', '23', '本科', '三年以上', '13716758966', '搜索引擎研发工程师', '2013-11-10 00:00:00', '2014-03-26 14:56:45', '智联招聘');
INSERT INTO `resumeinfo` VALUES ('22', '刘洋', '男', '23', '本科', '二年以上', '18810437927', 'JAVA开发工程师', '2013-11-10 00:00:00', '2014-03-26 14:56:45', '智联招聘');
INSERT INTO `resumeinfo` VALUES ('23', '臧永亮', '男', '26', '本科', '二年以上', '18911151570', 'JAVA开发工程师', '2013-11-10 00:00:00', '2014-03-26 14:56:45', '智联招聘');
INSERT INTO `resumeinfo` VALUES ('24', '方传奇', '男', '29', '本科', '三年以上', '18800107340', '搜索引擎研发工程师', '2013-11-10 00:00:00', '2014-03-26 14:56:45', '智联招聘');
INSERT INTO `resumeinfo` VALUES ('25', 'ChuanQi Fang', 'Male', '29', 'Bachelor', '三年以上', '18800107340', '搜索引擎研发工程师', '2013-11-10 00:00:00', '2014-03-26 14:56:45', '智联招聘');
INSERT INTO `resumeinfo` VALUES ('26', '吕国华', '男', '23', '本科', '三年以上', '18650590162', '搜索引擎研发工程师', '2013-11-10 00:00:00', '2014-03-26 14:56:45', '智联招聘');
INSERT INTO `resumeinfo` VALUES ('27', '纪晓良', '男', '23', '本科', '一年以上', '18511616529', '搜索引擎研发工程师', '2013-11-09 00:00:00', '2014-03-26 14:56:45', '智联招聘');
INSERT INTO `resumeinfo` VALUES ('28', '刘杨', '男', '24', '本科', '一年以上', '15040382454', '搜索引擎研发工程师', '2013-11-10 00:00:00', '2014-03-26 14:56:45', '智联招聘');
INSERT INTO `resumeinfo` VALUES ('29', '周伟', '男', '25', '本科', '应届毕业生', '13641052476', 'JAVA高级软件工程师', '2013-11-10 00:00:00', '2014-03-26 14:56:45', '智联招聘');
INSERT INTO `resumeinfo` VALUES ('30', '李钊', '男', '26', '本科', '二年以上', '15811073766', 'JAVA高级软件工程师', '2013-11-11 00:00:00', '2014-03-26 14:56:45', '智联招聘');
INSERT INTO `resumeinfo` VALUES ('31', '宋银宇', '男', '25', '本科', '三年以上', '13264407421', '搜索引擎研发工程师', '2013-11-11 00:00:00', '2014-03-26 14:56:45', '智联招聘');
INSERT INTO `resumeinfo` VALUES ('32', '王丽莉', '女', '38', '本科', '三年以上', '18701197693', '会计经理/主管', '2013-11-11 00:00:00', '2014-03-26 14:56:45', '智联招聘');
INSERT INTO `resumeinfo` VALUES ('33', '王冰', '男', '31', '本科', '16', '13810128665', '会计经理/主管', '2013-11-08 00:00:00', '2014-03-26 14:56:45', '智联招聘');
INSERT INTO `resumeinfo` VALUES ('34', '崔女士', '女', '31', '', '9', '18810465826', '会计经理/主管', '2013-11-07 00:00:00', '2014-03-26 14:56:45', '智联招聘');
INSERT INTO `resumeinfo` VALUES ('35', '丁鑫', '女', '29', '本科', '5', '18810994959', '会计经理/主管', '2013-11-06 00:00:00', '2014-03-26 14:56:45', '智联招聘');
INSERT INTO `resumeinfo` VALUES ('36', 'Ding Xin', 'Female', '29', 'Bachelor', '6', '18810994959', '会计经理/主管', '2013-11-06 00:00:00', '2014-03-26 14:56:45', '智联招聘');
INSERT INTO `resumeinfo` VALUES ('37', '丁磊', '女', '29', '本科', '6', '13624308920', '会计经理/主管', '2013-11-06 00:00:00', '2014-03-26 14:56:45', '智联招聘');
INSERT INTO `resumeinfo` VALUES ('38', '刘女士', '女', '33', '大专', '6', '13520188265', '会计经理/主管', '2013-11-06 00:00:00', '2014-03-26 14:56:45', '智联招聘');
INSERT INTO `resumeinfo` VALUES ('39', '杨丽娜', '女', '28', '大专', '10', '13693648693', '会计经理/主管', '2013-11-06 00:00:00', '2014-03-26 14:56:45', '智联招聘');
INSERT INTO `resumeinfo` VALUES ('40', '郑小贤', '女', '30', '本科', '9', '13301190225', '会计经理/主管', '2013-11-06 00:00:00', '2014-03-26 14:56:45', '智联招聘');
INSERT INTO `resumeinfo` VALUES ('41', '魏艳娟', '女', '30', '本科', '5', '13466678069', '会计经理/主管', '2013-11-06 00:00:00', '2014-03-26 14:56:45', '智联招聘');
INSERT INTO `resumeinfo` VALUES ('42', 'weiyanjuan', 'Female', '30', 'Bachelor', '6', '13466678069', '会计经理/主管', '2013-11-06 00:00:00', '2014-03-26 14:56:45', '智联招聘');
INSERT INTO `resumeinfo` VALUES ('43', '李高斌', '男', '31', '大专', '6', '18611041528', '会计经理/主管', '2013-11-06 00:00:00', '2014-03-26 14:56:45', '智联招聘');
INSERT INTO `resumeinfo` VALUES ('44', '赵二静', '女', '28', '大专', '9', '13811903346', '会计经理/主管', '2013-11-06 00:00:00', '2014-03-26 14:56:45', '智联招聘');
INSERT INTO `resumeinfo` VALUES ('45', '沈秋菊', '女', '33', '本科', '9', '18701321540', '会计经理/主管', '2013-11-06 00:00:00', '2014-03-26 14:56:45', '智联招聘');
INSERT INTO `resumeinfo` VALUES ('46', '郝海江', '男', '21', '中专', '12', '18010033614', '会计经理/主管', '2013-11-06 00:00:00', '2014-03-26 14:56:45', '智联招聘');
INSERT INTO `resumeinfo` VALUES ('47', '张双双', '女', '28', '', '一年以上', '18201048040', '会计经理/主管', '2013-11-05 00:00:00', '2014-03-26 14:56:45', '智联招聘');
INSERT INTO `resumeinfo` VALUES ('48', '冯付娟', '女', '36', '大专', '4', '13521966210', '会计经理/主管', '2013-11-06 00:00:00', '2014-03-26 14:56:45', '智联招聘');
INSERT INTO `resumeinfo` VALUES ('49', '贾瑞平', '女', '41', '大专', '14', '13683311389', '会计经理/主管', '2013-11-05 00:00:00', '2014-03-26 14:56:45', '智联招聘');
INSERT INTO `resumeinfo` VALUES ('50', '韩颖', '女', '31', '本科', '14', '13699299215', '会计经理/主管', '2013-11-05 00:00:00', '2014-03-26 14:56:45', '智联招聘');
INSERT INTO `resumeinfo` VALUES ('51', '朱小娇', '女', '32', '本科', '10', '13261067172', '会计经理/主管', '2013-11-05 00:00:00', '2014-03-26 14:56:45', '智联招聘');
INSERT INTO `resumeinfo` VALUES ('52', '秦海洋', '男', '26', '大专', '11', '18601931112', '会计经理/主管', '2013-11-05 00:00:00', '2014-03-26 14:56:45', '智联招聘');
INSERT INTO `resumeinfo` VALUES ('53', '李世平', '女', '52', '大专', '三年以上', '13311510541', '会计经理/主管', '2013-11-05 00:00:00', '2014-03-26 14:56:45', '智联招聘');
INSERT INTO `resumeinfo` VALUES ('54', '邱锦霞', '女', '30', '大专', '15', '15901265776', '会计经理/主管', '2013-11-05 00:00:00', '2014-03-26 14:56:45', '智联招聘');
INSERT INTO `resumeinfo` VALUES ('55', '钟瑞', '女', '26', '硕士', '12', '15010596116', '会计经理/主管', '2013-11-05 00:00:00', '2014-03-26 14:56:45', '智联招聘');
INSERT INTO `resumeinfo` VALUES ('56', '陈利芬', '女', '27', '本科', '三年以上', '18600755027', '会计经理/主管', '2013-11-05 00:00:00', '2014-03-26 14:56:45', '智联招聘');
INSERT INTO `resumeinfo` VALUES ('57', '宋桂清', '女', '41', '本科', '7', '18210588639', '会计经理/主管', '2013-11-05 00:00:00', '2014-03-26 14:56:45', '智联招聘');
INSERT INTO `resumeinfo` VALUES ('58', '郭登雪', '女', '39', '本科', '12', '13522577356', '会计经理/主管', '2013-11-05 00:00:00', '2014-03-26 14:56:45', '智联招聘');
INSERT INTO `resumeinfo` VALUES ('59', '张智瑕', '女', '23', '大专', '17', '13261445472', '会计经理/主管', '2013-11-05 00:00:00', '2014-03-26 14:56:45', '智联招聘');
INSERT INTO `resumeinfo` VALUES ('60', '康雨墨', '女', '20', '本科', '一年以上', '15133425355', '会计经理/主管', '2013-11-05 00:00:00', '2014-03-26 14:56:45', '智联招聘');
INSERT INTO `resumeinfo` VALUES ('61', '杨东军', '男', '30', '大专', '一年以上', '13815661444', '会计经理/主管', '2013-11-05 00:00:00', '2014-03-26 14:56:45', '智联招聘');
INSERT INTO `resumeinfo` VALUES ('62', '王春华', '女', '43', '大专', '7', '13683529094', '会计经理/主管', '2013-11-05 00:00:00', '2014-03-26 14:56:45', '智联招聘');
INSERT INTO `resumeinfo` VALUES ('63', '刘东超', '男', '30', '', '15', '13121822415', '会计经理/主管', '2013-11-05 00:00:00', '2014-03-26 14:56:45', '智联招聘');
INSERT INTO `resumeinfo` VALUES ('64', '芦志雄', '男', '39', '本科', '5', '13718475035', '会计经理/主管', '2013-11-05 00:00:00', '2014-03-26 14:56:46', '智联招聘');
INSERT INTO `resumeinfo` VALUES ('65', '彭涛', '男', '31', '大专', '16', '13811719834', '会计经理/主管', '2013-11-09 00:00:00', '2014-03-26 14:56:46', '智联招聘');
INSERT INTO `resumeinfo` VALUES ('66', '胡丽娜', '女', '31', '本科', '12', '13910286101', '会计经理/主管', '2013-11-09 00:00:00', '2014-03-26 14:56:46', '智联招聘');
INSERT INTO `resumeinfo` VALUES ('67', '张艳伦', '男', '26', '大专', '12', '18210782292', '会计经理/主管', '2013-11-09 00:00:00', '2014-03-26 14:56:46', '智联招聘');
INSERT INTO `resumeinfo` VALUES ('68', '赫艳', '女', '35', '大专', '5', '15101083907', '会计经理/主管', '2013-11-08 00:00:00', '2014-03-26 14:56:46', '智联招聘');
INSERT INTO `resumeinfo` VALUES ('69', '李玲芳', '女', '29', '本科', '14', '13811976052', '会计经理/主管', '2013-11-08 00:00:00', '2014-03-26 14:56:46', '智联招聘');
INSERT INTO `resumeinfo` VALUES ('70', 'lingfang li', 'Female', '29', 'Bachelor', '6', '13811976052', '会计经理/主管', '2013-11-08 00:00:00', '2014-03-26 14:56:46', '智联招聘');
INSERT INTO `resumeinfo` VALUES ('71', '戈涵', '女', '29', '本科', '6', '15810239089', '会计经理/主管', '2013-11-08 00:00:00', '2014-03-26 14:56:46', '智联招聘');
INSERT INTO `resumeinfo` VALUES ('72', '帅长红', '男', '34', '大专', '6', '13366437806', '会计经理/主管', '2013-11-08 00:00:00', '2014-03-26 14:56:46', '智联招聘');
INSERT INTO `resumeinfo` VALUES ('73', '梁晓波', '女', '32', '本科', '13', '15011597886', '会计经理/主管', '2013-11-07 00:00:00', '2014-03-26 14:56:46', '智联招聘');
INSERT INTO `resumeinfo` VALUES ('74', '满秀杰', '女', '34', '大专', '10', '13691115120', '会计经理/主管', '2013-11-07 00:00:00', '2014-03-26 14:56:46', '智联招聘');
INSERT INTO `resumeinfo` VALUES ('75', 'man xiu jie', 'Female', '34', 'Associate', '13', '13691115120', '会计经理/主管', '2013-11-08 00:00:00', '2014-03-26 14:56:46', '智联招聘');
INSERT INTO `resumeinfo` VALUES ('76', '杨洋', '女', '23', '大专', '13', '18810530921', '会计经理/主管', '2013-11-08 00:00:00', '2014-03-26 14:56:46', '智联招聘');
INSERT INTO `resumeinfo` VALUES ('77', '展钰棹', '女', '34', '本科', '应届毕业生', '13146054909', '会计经理/主管', '2013-11-08 00:00:00', '2014-03-26 14:56:46', '智联招聘');
INSERT INTO `resumeinfo` VALUES ('78', '孙振富', '男', '28', '', '9', '13895747448', '会计经理/主管', '2013-11-07 00:00:00', '2014-03-26 14:56:46', '智联招聘');
INSERT INTO `resumeinfo` VALUES ('79', '姚冬云', '女', '27', '大专', '6', '18810719606', '会计经理/主管', '2013-11-08 00:00:00', '2014-03-26 14:56:46', '智联招聘');
INSERT INTO `resumeinfo` VALUES ('80', '刘寒松', '男', '33', '本科', '4', '13621081226', '会计经理/主管', '2013-11-08 00:00:00', '2014-03-26 14:56:46', '智联招聘');
INSERT INTO `resumeinfo` VALUES ('81', 'Hansong Liu', 'Male', '33', 'Bachelor', '10', '13621081226', '会计经理/主管', '2013-11-06 00:00:00', '2014-03-26 14:56:46', '智联招聘');
INSERT INTO `resumeinfo` VALUES ('82', '张国民', '男', '30', '大专', '10', '15910372824', '会计经理/主管', '2013-11-06 00:00:00', '2014-03-26 14:56:46', '智联招聘');
INSERT INTO `resumeinfo` VALUES ('83', '鲁红', '女', '30', '大专', '5', '13466689913', '会计经理/主管', '2013-11-07 00:00:00', '2014-03-26 14:56:46', '智联招聘');
INSERT INTO `resumeinfo` VALUES ('84', 'lu hong', 'Female', '30', 'Associate', '6', '13466689913', '会计经理/主管', '2013-11-07 00:00:00', '2014-03-26 14:56:46', '智联招聘');
INSERT INTO `resumeinfo` VALUES ('85', 'Li Hong Xia', '女', '39', '本科', '6', '18613880685', '会计经理/主管', '2013-11-07 00:00:00', '2014-03-26 14:56:46', '智联招聘');
INSERT INTO `resumeinfo` VALUES ('86', '张丽', '男', '35', '大专', '9', '13683672664', '会计经理/主管', '2013-11-07 00:00:00', '2014-03-26 14:56:46', '智联招聘');
INSERT INTO `resumeinfo` VALUES ('87', '张燕京', '女', '27', '本科', '12', '13426159736', '会计经理/主管', '2013-11-07 00:00:00', '2014-03-26 14:56:46', '智联招聘');
INSERT INTO `resumeinfo` VALUES ('88', '徐卿珊', '女', '34', '本科', '5', '13671076116', '会计经理/主管', '2013-11-06 00:00:00', '2014-03-26 14:56:46', '智联招聘');
INSERT INTO `resumeinfo` VALUES ('89', 'Qingshan Xu', 'Female', '34', 'Bachelor', '10', '13671076116', '会计经理/主管', '2013-11-06 00:00:00', '2014-03-26 14:56:46', '智联招聘');
INSERT INTO `resumeinfo` VALUES ('90', '韩丹丹', '女', '25', '本科', '10', '13718049347', '会计经理/主管', '2013-11-06 00:00:00', '2014-03-26 14:56:46', '智联招聘');
INSERT INTO `resumeinfo` VALUES ('91', '李慧敏', '女', '33', '本科', '三年以上', '13714631579', '会计经理/主管', '2013-11-07 00:00:00', '2014-03-26 14:56:46', '智联招聘');
INSERT INTO `resumeinfo` VALUES ('92', '张丰', '男', '32', '本科', '10', '15901430143', '会计经理/主管', '2013-11-07 00:00:00', '2014-03-26 14:56:46', '智联招聘');
INSERT INTO `resumeinfo` VALUES ('93', '马文华', '女', '33', '本科', '8', '13161248890', '会计经理/主管', '2013-11-07 00:00:00', '2014-03-26 14:56:46', '智联招聘');
INSERT INTO `resumeinfo` VALUES ('94', '王艳', '女', '43', '其他', '8', '18600183975', '会计经理/主管', '2013-11-07 00:00:00', '2014-03-26 14:56:46', '智联招聘');
INSERT INTO `resumeinfo` VALUES ('95', '', 'Female', '43', 'Other', '13', '18600183975', '会计经理/主管', '2013-11-06 00:00:00', '2014-03-26 14:56:46', '智联招聘');
INSERT INTO `resumeinfo` VALUES ('96', '胡中超', '男', '26', '大专', '13', '13683069761', '会计经理/主管', '2013-11-06 00:00:00', '2014-03-26 14:56:46', '智联招聘');
INSERT INTO `resumeinfo` VALUES ('97', '赵森利', '男', '34', '大专', '5', '13810999728', '会计经理/主管', '2013-11-07 00:00:00', '2014-03-26 14:56:46', '智联招聘');
INSERT INTO `resumeinfo` VALUES ('98', '袁瑞瑞', '女', '24', '硕士', '12', '15010916125', '会计经理/主管', '2013-11-07 00:00:00', '2014-03-26 14:56:46', '智联招聘');
INSERT INTO `resumeinfo` VALUES ('99', '张文', '女', '30', '', '二年以上', '13810476208', '会计经理/主管', '2013-11-07 00:00:00', '2014-03-26 14:56:46', '智联招聘');
INSERT INTO `resumeinfo` VALUES ('100', '冯媛媛', '女', '24', '本科', '4', '15801179220', '总经理助理', '2013-11-06 00:00:00', '2014-03-26 14:56:46', '智联招聘');
INSERT INTO `resumeinfo` VALUES ('101', '杨翠云', '女', '28', '本科', '二年以上', '15011569633', '总经理助理', '2013-11-09 00:00:00', '2014-03-26 14:56:46', '智联招聘');
INSERT INTO `resumeinfo` VALUES ('102', '苗谨渱', '女', '33', '本科', '5', '18611835806', '总经理助理', '2013-11-08 00:00:00', '2014-03-26 14:56:46', '智联招聘');
INSERT INTO `resumeinfo` VALUES ('103', '李萍', '女', '26', '大专', '12', '18801383888', '总经理助理', '2013-11-08 00:00:00', '2014-03-26 14:56:46', '智联招聘');
INSERT INTO `resumeinfo` VALUES ('104', '王紫轩', '女', '26', '本科', '三年以上', '15010452630', '总经理助理', '2013-11-07 00:00:00', '2014-03-26 14:56:46', '智联招聘');
INSERT INTO `resumeinfo` VALUES ('105', '马麒麟', '女', '28', '本科', '应届毕业生', '13521950145', '总经理助理', '2013-11-07 00:00:00', '2014-03-26 14:56:46', '智联招聘');
INSERT INTO `resumeinfo` VALUES ('106', 'Lynn Ma', 'Female', '28', 'Bachelor', '5', '13521950145', '总经理助理', '2013-11-08 00:00:00', '2014-03-26 14:56:46', '智联招聘');
INSERT INTO `resumeinfo` VALUES ('107', '白燕', '女', '41', '硕士', '5', '13691475729', '总经理助理', '2013-11-08 00:00:00', '2014-03-26 14:56:46', '智联招聘');
INSERT INTO `resumeinfo` VALUES ('108', '白燕', 'Female', '41', 'Master', '14', '13691475729', '总经理助理', '2013-11-07 00:00:00', '2014-03-26 14:56:46', '智联招聘');
INSERT INTO `resumeinfo` VALUES ('109', '王冰', '女', '27', '本科', '14', '13240450011', '总经理助理', '2013-11-07 00:00:00', '2014-03-26 14:56:46', '智联招聘');
INSERT INTO `resumeinfo` VALUES ('110', 'Tammy', 'Female', '27', 'Bachelor', '7', '13240450011', '总经理助理', '2013-11-06 00:00:00', '2014-03-26 14:56:46', '智联招聘');
INSERT INTO `resumeinfo` VALUES ('111', '孙婷', '女', '26', '本科', '7', '13488847691', '总经理助理', '2013-11-06 00:00:00', '2014-03-26 14:56:46', '智联招聘');
INSERT INTO `resumeinfo` VALUES ('112', '陈瑞娟', '女', '23', '大专', '4', '18910592718', '总经理助理', '2013-11-07 00:00:00', '2014-03-26 14:56:46', '智联招聘');
INSERT INTO `resumeinfo` VALUES ('113', '卢爽', '女', '22', '本科', '二年以上', '15010131305', '总经理助理', '2013-11-07 00:00:00', '2014-03-26 14:56:46', '智联招聘');
INSERT INTO `resumeinfo` VALUES ('114', '李聪聪', '女', '24', '硕士', '二年以上', '15011361205', '总经理助理', '2013-11-07 00:00:00', '2014-03-26 14:56:46', '智联招聘');
INSERT INTO `resumeinfo` VALUES ('115', '杨小桐', '女', '24', '本科', '应届毕业生', '13811444868', '总经理助理', '2013-11-06 00:00:00', '2014-03-26 14:56:46', '智联招聘');
INSERT INTO `resumeinfo` VALUES ('116', '郭洁', '女', '28', '本科', '4', '13811409008', '总经理助理', '2013-11-07 00:00:00', '2014-03-26 14:56:46', '智联招聘');
INSERT INTO `resumeinfo` VALUES ('117', '张秋荣', '女', '26', '本科', '7', '18701675090', '总经理助理', '2013-11-07 00:00:00', '2014-03-26 14:56:46', '智联招聘');
INSERT INTO `resumeinfo` VALUES ('118', 'Zhang Qiurong', 'Female', '26', 'Bachelor', '4', '18701675090', '总经理助理', '2013-11-06 00:00:00', '2014-03-26 14:56:46', '智联招聘');
INSERT INTO `resumeinfo` VALUES ('119', '韩小野', '女', '30', '大专', '4', '18910731216', '总经理助理', '2013-11-06 00:00:00', '2014-03-26 14:56:46', '智联招聘');
INSERT INTO `resumeinfo` VALUES ('120', '潘荣梅', '女', '25', '本科', '5', '18210135093', '总经理助理', '2013-11-06 00:00:00', '2014-03-26 14:56:46', '智联招聘');
INSERT INTO `resumeinfo` VALUES ('121', 'Rihanna Liu', '女', '32', '本科', '三年以上', '18600507835', '总经理助理', '2013-11-05 00:00:00', '2014-03-26 14:56:46', '智联招聘');
INSERT INTO `resumeinfo` VALUES ('122', '张静', '女', '26', '大专', '10', '13141375902', '总经理助理', '2013-11-05 00:00:00', '2014-03-26 14:56:46', '智联招聘');
INSERT INTO `resumeinfo` VALUES ('123', '孙婷婷', '女', '26', '大专', '三年以上', '18800108394', '总经理助理', '2013-11-05 00:00:00', '2014-03-26 14:56:46', '智联招聘');
INSERT INTO `resumeinfo` VALUES ('124', '首汉莹', '女', '24', '本科', '三年以上', '18310052040', '总经理助理', '2013-11-05 00:00:00', '2014-03-26 14:56:46', '智联招聘');
INSERT INTO `resumeinfo` VALUES ('125', '赵敬丽', '女', '29', '本科', '二年以上', '15117925631', '总经理助理', '2013-11-05 00:00:00', '2014-03-26 14:56:46', '智联招聘');
INSERT INTO `resumeinfo` VALUES ('126', '齐欢', '女', '22', '中专', '6', '15810010134', '总经理助理', '2013-11-05 00:00:00', '2014-03-26 14:56:46', '智联招聘');
INSERT INTO `resumeinfo` VALUES ('127', '段玉', '女', '26', '本科', '三年以上', '18910480470', '总经理助理', '2013-11-06 00:00:00', '2014-03-26 14:56:46', '智联招聘');
INSERT INTO `resumeinfo` VALUES ('128', '王余', '女', '26', '本科', '4', '18810935024', '总经理助理', '2013-11-05 00:00:00', '2014-03-26 14:56:46', '智联招聘');
INSERT INTO `resumeinfo` VALUES ('129', '张校娜', '女', '25', '本科', '三年以上', '18511076394', '总经理助理', '2013-11-06 00:00:00', '2014-03-26 14:56:46', '智联招聘');
INSERT INTO `resumeinfo` VALUES ('130', '刘佳', '女', '26', '本科', '一年以上', '18210669635', '总经理助理', '2013-11-05 00:00:00', '2014-03-26 14:56:46', '智联招聘');
INSERT INTO `resumeinfo` VALUES ('131', 'eva liu', '女', '45', 'MBA', '4', '18210976201', '总经理助理', '2013-11-05 00:00:00', '2014-03-26 14:56:46', '智联招聘');
INSERT INTO `resumeinfo` VALUES ('132', '李琳', '女', '21', '大专', '15', '15801471901', '总经理助理', '2013-11-06 00:00:00', '2014-03-26 14:56:46', '智联招聘');
INSERT INTO `resumeinfo` VALUES ('133', '袁小晓', '女', '25', '大专', '应届毕业生', '13716966031', '总经理助理', '2013-11-06 00:00:00', '2014-03-26 14:56:46', '智联招聘');

-- ----------------------------
-- Table structure for seed_word
-- ----------------------------
DROP TABLE IF EXISTS `seed_word`;
CREATE TABLE `seed_word` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `word` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of seed_word
-- ----------------------------
INSERT INTO `seed_word` VALUES ('3', '安全');
INSERT INTO `seed_word` VALUES ('4', '启动');
INSERT INTO `seed_word` VALUES ('21', 'SDFSD');
INSERT INTO `seed_word` VALUES ('22', 'SDFSD');
INSERT INTO `seed_word` VALUES ('23', 'SDFSD');
INSERT INTO `seed_word` VALUES ('24', 'WERWER');
INSERT INTO `seed_word` VALUES ('32', '测试');

-- ----------------------------
-- Table structure for t_auth
-- ----------------------------
DROP TABLE IF EXISTS `t_auth`;
CREATE TABLE `t_auth` (
  `authId` int(11) NOT NULL AUTO_INCREMENT COMMENT '权限菜单节点id(编号(主键))',
  `authName` varchar(255) DEFAULT NULL COMMENT '权限菜单名称',
  `authPath` varchar(255) DEFAULT NULL COMMENT '权限菜单路径',
  `parentId` int(11) DEFAULT NULL COMMENT '权限菜单节点父id',
  `authDescription` mediumtext COMMENT '权限菜单节点描述',
  `state` varchar(255) DEFAULT 'open' COMMENT '状态',
  `iconCls` varchar(255) DEFAULT NULL COMMENT '菜单图标样式',
  `resourceType` int(11) DEFAULT '0' COMMENT '0：菜单；1：功能',
  `number` int(11) DEFAULT NULL COMMENT '序号',
  PRIMARY KEY (`authId`),
  UNIQUE KEY `authPath` (`authPath`,`parentId`,`authName`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_auth
-- ----------------------------
INSERT INTO `t_auth` VALUES ('1', '系统管理', '', '-1', '管理系统的资源、角色、机构、用户等信息', 'closed', 'icon-application_view_tile', '0', '1');
INSERT INTO `t_auth` VALUES ('2', '系统监控', '', '-1', '监控系统运行情况等信息', 'closed', 'icon-monitor', '0', '2');
INSERT INTO `t_auth` VALUES ('3', '系统报表', '', '-1', '查看系统相关报表图标', 'closed', 'icon-chart_curve', '0', '3');
INSERT INTO `t_auth` VALUES ('4', '系统日志', '', '-1', '', 'closed', 'icon-permission', '0', '4');
INSERT INTO `t_auth` VALUES ('5', '用户管理', 'web/Permission/userManage.jsp', '1', '', 'open', 'icon-userManage', '0', '1');
INSERT INTO `t_auth` VALUES ('6', '角色管理', 'web/Permission/roleManage.jsp', '1', '', 'open', 'icon-roleManage', '0', '2');
INSERT INTO `t_auth` VALUES ('7', '菜单管理', 'web/Permission/menuManage.jsp', '1', '', 'open', 'icon-standard-book-open', '0', '3');
INSERT INTO `t_auth` VALUES ('8', '权限(功能)管理', 'web/Resources/Resources.jsp', '1', '', 'open', 'icon-hamburg-special\r\nicon-hamburg-special\r\n', '0', '4');
INSERT INTO `t_auth` VALUES ('9', '上传文件监控', 'web/Monitor/Upload.jsp', '2', '', 'open', 'icon-monitor_link', '0', '2');
INSERT INTO `t_auth` VALUES ('10', '登陆历史监控', 'web/Monitor/UserEvent.jsp', '2', '', 'open', 'icon-hamburg-customers', '0', '1');
INSERT INTO `t_auth` VALUES ('11', '用户注册报表', 'web/MapCharts/CreateDatetimeChart.jsp', '3', '', 'open', 'icon-chart_bar', '0', '1');
INSERT INTO `t_auth` VALUES ('12', '用户登陆报表', 'web/MapCharts/LogReport.jsp', '3', '', 'open', 'ext-icon-land-chart', '0', '3');
INSERT INTO `t_auth` VALUES ('13', '用户角色分布', 'web/MapCharts/userRoleChart.jsp', '3', '', 'open', 'icon-Pie_Chart', '0', '2');
INSERT INTO `t_auth` VALUES ('14', '操作日志管理', '', '4', '', 'closed', 'icon-hamburg-address', '0', '0');
INSERT INTO `t_auth` VALUES ('15', '异常日志管理', '', '4', '', 'closed', 'icon-hamburg-address', '0', '0');
INSERT INTO `t_auth` VALUES ('16', '用户管理日志', 'web/Log/user.jsp', '14', '', 'open', 'icon-hamburg-config', '0', '0');
INSERT INTO `t_auth` VALUES ('17', '用户异常日志', '', '15', '', 'open', 'icon-hamburg-config', '0', '0');
INSERT INTO `t_auth` VALUES ('18', '角色异常日志', '', '15', '', 'open', 'icon-hamburg-config', '0', '0');
INSERT INTO `t_auth` VALUES ('19', '权限异常日志', '', '15', '', 'open', 'icon-hamburg-config', '0', '0');
INSERT INTO `t_auth` VALUES ('20', '菜单异常日志', '', '15', '', 'open', 'icon-hamburg-config', '0', '0');

-- ----------------------------
-- Table structure for t_auth_resource
-- ----------------------------
DROP TABLE IF EXISTS `t_auth_resource`;
CREATE TABLE `t_auth_resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '权限菜单节点id(编号(主键))',
  `type` int(11) DEFAULT '0' COMMENT '0：菜单；1：功能',
  `Name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_auth_resource
-- ----------------------------
INSERT INTO `t_auth_resource` VALUES ('1', '0', '菜单');
INSERT INTO `t_auth_resource` VALUES ('2', '1', '功能');

-- ----------------------------
-- Table structure for t_post
-- ----------------------------
DROP TABLE IF EXISTS `t_post`;
CREATE TABLE `t_post` (
  `postId` int(11) NOT NULL AUTO_INCREMENT,
  `postName` varchar(255) DEFAULT NULL COMMENT '职务名称',
  `postRemark` text COMMENT '备注',
  `deptNo` int(11) NOT NULL COMMENT '部门编号',
  PRIMARY KEY (`postId`),
  UNIQUE KEY `postName` (`postName`,`deptNo`) USING BTREE,
  KEY `t_post_ibfk_1` (`deptNo`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_post
-- ----------------------------
INSERT INTO `t_post` VALUES ('1', '技术研发部经理', '1. 全面负责软件研发部日常管理工作; 2. 规范软件体系设计，监督相应的设计开发过程;3. 负责建立软件系统资源库，实现资源重用；4. 负责软件研发团队建设和技术人员的招聘、培养与考评;5. 制定和落实部门项目研发开发计划，总体掌握研发进度。6. 确定软件部技术研究方向，组织人员对关键技术进行攻关和积累;7. 指导/评审/公司项目软件部分的开发活动;8. 解决公司产品线中相关的技术难题，提供技术支持;9. 统筹协调软件研发部与其它部门的关系;10. 负责相关技术资料的整理;11. 负责相关知识产权等技术文档编制;12. 完成公司交办的其它工作。', '1');
INSERT INTO `t_post` VALUES ('2', '技术研发部副经理', '1.协助经理完成日常管理工作;2.完成分管的方面技术工作；3.经理不在时，代经理处理部门事务；4.按计划推进自己负责项目的实施；5.参与指导/评审/公司项目应用软件部分的开发活动；6.协助经理进行团队建设、人员培养和考评；7.负责相关技术领域的技术积累和整理；8.解决公司产品线中相关的技术难题；9.负责相关技术资料的整理；10.负责相关知识产权等技术文档编制；11.融会贯通上下级之间的协作、交流和沟通；12.负责公司或部门交办的其它工作。', '1');
INSERT INTO `t_post` VALUES ('3', '网络系统软件工程师    ', '1.基本的计算机应用能力、英文阅读能力和知识产权能力；2.常用的计算机操作系统、通用编程语言和数据库基础；3.常用数据库设计、维护、管理基础；4.简单前端界面设计基础：JS/AS/CSS+DIV；5.常见网络系统软件体系架构：C/S、B/S；6.网络系统应用软件的方案与实施规划；7.C/C++网络系统软件编制设计/测试/调试/发布；8.Java-J2EE-SSH/SSI网络系统软件编制设计/测试/调试/发布；9.C#/F#-ASP.NET-VS网络系统软件编制设计/测试/调试/发布；10.常见车-地有线/无线网络传输协议及其软件实现；11.相关技术资料的整理与技术文档写作能力；12. 完成临时交办的其它技术任务', '1');
INSERT INTO `t_post` VALUES ('4', '前端界面设计工程师', '1.基本的计算机应用能力、英文阅读能力和知识产权能力；2.常用的计算机操作系统、通用编程语言和数据库基础；3.车-地信号设备、运行图与信息管理知识基础；4.脚本语言基础：JS、AS、CSS+DIV、Ajax、Json/XML等；5.美工工具基础：Photoshop、Flash、3D-Max等；6.图形图像处理基础：过滤、增强、渲染、突出、钝化等；7.网页动态用户界面的方案和实施规划；8.网页动态用户界面的开发、编程、测试；9.网页动态用户界面的分析、提取与转变能力；10.网络流媒体音视频播放及其软件开发；11.相关技术资料的整理与技术文档写作能力；12.完成临时交办的其它技术任务', '1');
INSERT INTO `t_post` VALUES ('5', '可视化软件工程师', '1.基本的计算机应用能力、英文阅读能力和知识产权能力；2.常用的计算机操作系统、通用编程语言和数据库基础；3.车-地信号设备、运行图与信息管理知识基础；4.熟悉Windows下开发环境：VS(VC++/VC#)、RS(BCB)；5.熟悉Linux下开发环境：KDevelop + QT或QDevelop；6.Windows或Linux下可视化应用/测试软件方案与实施规划；7.熟练使用C/C++进行所需的可视化应用或测试软件开发调试；8.常见桌上数据库的设计、维护、管理；9.可视化流媒体音视频播放及其软件开发；10.常见车-地有线/无线网络通信传输协议及其软件实现；11.相关技术资料的整理与技术文档写作能力；12.完成临时交办的其它技术任务。', '1');
INSERT INTO `t_post` VALUES ('6', '嵌入式应用软件工程师', '1.基本的计算机应用能力、英文阅读能力和知识产权能力；2.常用的计算机操作系统、通用编程语言和数据库基础；3.车-地信号设备、运行图与信息管理知识基础；4.熟悉Windows Phone开发环境：PB、VS(VC#)；5.熟悉ARM-Linux/Android开发环境：JDK+Eclips+ADT/CDT；6.Windows或Linux下可视化应用/测试软件方案与实施规划；7.熟练使用C/C++/Java进行嵌入式应用软件开发调试；8.常见嵌入式数据库的设计、维护、管理；9.嵌入式流媒体音视频播放及其软件开发；10.常见车-地有线/无线网络通信传输协议及其软件实现；11.相关技术资料的整理与技术文档写作能力；12.完成临时交办的其它技术任务。', '1');
INSERT INTO `t_post` VALUES ('7', '系统软件测试工程师', '1.基本的计算机应用能力、英文阅读能力和知识产权能力；2.常用的计算机操作系统、通用编程语言和数据库基础；3.车-地信号设备、运行图与信息管理知识基础；4.熟练掌握软件测试的基本知识和技能；5.熟悉QC或TD等常用软件测试工具与流程；6.熟练使用LoaderRunner、Bugzilla等工具；7.Windows或Linux软件测试方案与实施规划；8.Windows或Linux软件测试与软件改进建议；9.熟悉常见车-地有线/无线网络通信传输协议；10.项目中与各软件设计人员的技术沟通交流；11.相关技术资料的整理与技术文档写作能力；12.完成临时交办的其它技术任务。', '1');
INSERT INTO `t_post` VALUES ('8', '应用软件工程师    ', '网络系统软件工程师+前端界面设计工程师+可视化软件工程师+嵌入式应用软件工程师+系统软件测试工程师', '1');
INSERT INTO `t_post` VALUES ('9', '财务部总监', '', '2');
INSERT INTO `t_post` VALUES ('10', '财务部经理', '', '2');
INSERT INTO `t_post` VALUES ('11', '财务部副经理', '', '2');
INSERT INTO `t_post` VALUES ('12', '税务会计', '', '2');
INSERT INTO `t_post` VALUES ('13', '成本会计', '', '2');
INSERT INTO `t_post` VALUES ('14', '出纳', '', '2');
INSERT INTO `t_post` VALUES ('15', '人力资源部经理', '', '3');
INSERT INTO `t_post` VALUES ('16', '人事专员', '', '3');
INSERT INTO `t_post` VALUES ('17', '培训专员', '', '3');
INSERT INTO `t_post` VALUES ('18', '绩效考核专员', '', '3');
INSERT INTO `t_post` VALUES ('19', '绩效薪酬专员', '', '3');
INSERT INTO `t_post` VALUES ('20', '', '', '14');

-- ----------------------------
-- Table structure for t_resource
-- ----------------------------
DROP TABLE IF EXISTS `t_resource`;
CREATE TABLE `t_resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '名称',
  `resources` mediumtext CHARACTER SET utf8 COMMENT '资源',
  `menu_id` int(11) DEFAULT NULL COMMENT '归属',
  `resourceType` int(11) DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`,`menu_id`)
) ENGINE=MyISAM AUTO_INCREMENT=54 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of t_resource
-- ----------------------------
INSERT INTO `t_resource` VALUES ('1', '用户列表', '/base/UserController/doNotNeedSessionAndSecurity/list.do', '5', '1');
INSERT INTO `t_resource` VALUES ('2', '添加用户', '/base/UserController/saveOrUpdata.do', '5', '1');
INSERT INTO `t_resource` VALUES ('3', '编辑用户', '/base/UserController/saveOrUpdata.do?userId=', '5', '1');
INSERT INTO `t_resource` VALUES ('4', '删除用户', '/base/UserController/delete.do', '5', '1');
INSERT INTO `t_resource` VALUES ('5', '查看用户', '', '5', '1');
INSERT INTO `t_resource` VALUES ('6', '用户授权', '', '5', '1');
INSERT INTO `t_resource` VALUES ('7', '角色列表', '/base/RoleController/doNotNeedSessionAndSecurity/roleListInfo.do', '6', '1');
INSERT INTO `t_resource` VALUES ('8', '添加角色', '/base/RoleController/doNotNeedSessionAndSecurity/saveOrUpdata.do', '6', '1');
INSERT INTO `t_resource` VALUES ('9', '用户角色', 'base/RoleController/doNotNeedSessionAndSecurity/roleListInfo.do', '5', '1');
INSERT INTO `t_resource` VALUES ('10', '编辑角色', '/base/RoleController/doNotNeedSessionAndSecurity/saveOrUpdata.do?roleId=', '6', '1');
INSERT INTO `t_resource` VALUES ('11', '删除角色', '/base/RoleController/doNotNeedSessionAndSecurity/delete.do', '6', '1');
INSERT INTO `t_resource` VALUES ('12', '查看角色', '', '6', '1');
INSERT INTO `t_resource` VALUES ('13', '角色授权', '/base/AuthController/doNotNeedSessionAndSecurity/authMenu.do?parentId=-1&roleId=', '6', '1');
INSERT INTO `t_resource` VALUES ('14', '菜单列表', '/base/AuthController/doNotNeedSessionAndSecurity/authTreeGridMenu.do?parentId=-1', '7', '1');
INSERT INTO `t_resource` VALUES ('15', '添加菜单', '/base/AuthController/doNotNeedSessionAndSecurity/saveOrUpdata.do?parentId=', '7', '1');
INSERT INTO `t_resource` VALUES ('16', '编辑菜单', '/base/AuthController/doNotNeedSessionAndSecurity/saveOrUpdata.do?authId=', '7', '1');
INSERT INTO `t_resource` VALUES ('17', '删除菜单', '/base/AuthController/doNotNeedSessionAndSecurity/delete.do', '7', '1');
INSERT INTO `t_resource` VALUES ('18', '查看菜单', '', '7', '1');
INSERT INTO `t_resource` VALUES ('19', '资源列表', '/base/AuthController/doNotNeedSessionAndSecurity/menuFunction.do', '8', '1');
INSERT INTO `t_resource` VALUES ('20', '添加资源', '/base/ResourceController/doNotNeedSessionAndSecurity/addOrUpdate.do', '8', '1');
INSERT INTO `t_resource` VALUES ('21', '编辑资源', '', '8', '1');
INSERT INTO `t_resource` VALUES ('22', '删除资源', '', '8', '1');
INSERT INTO `t_resource` VALUES ('23', '查看资源', '', '8', '1');
INSERT INTO `t_resource` VALUES ('24', '文件列表', '/UploadFile/List.do', '9', '1');
INSERT INTO `t_resource` VALUES ('25', '上传文件', '/UploadFile/save.do', '9', '1');
INSERT INTO `t_resource` VALUES ('26', '下载 \"上传文件\"', 'UploadFile/download.do?uploadFileid=', '9', '1');
INSERT INTO `t_resource` VALUES ('27', '查看 \"上传文件\"', '/UploadFile/showInfo.do', '9', '1');
INSERT INTO `t_resource` VALUES ('28', '删除  \"上传文件\"', '/UploadFile/deleteLoadFile.do', '9', '1');
INSERT INTO `t_resource` VALUES ('29', '导入数据', '', '9', '1');
INSERT INTO `t_resource` VALUES ('30', '导出数据', '', '9', '1');
INSERT INTO `t_resource` VALUES ('31', '信息列表', '/base/UserController/doNotNeedSessionAndSecurity/history.do', '10', '1');
INSERT INTO `t_resource` VALUES ('32', '用户注册时间分布图', 'base/FusionCharts/doNotNeedSessionAndSecurity/userCreateDatetimeChart.do', '11', '1');
INSERT INTO `t_resource` VALUES ('33', '用户注册时间数据', '/base/FusionCharts/doNotNeedSecurity/userCreateDatetimeChart.do', '11', '1');
INSERT INTO `t_resource` VALUES ('34', '登陆时间分布图', 'base/FusionCharts/doNotNeedSecurity/user/userLogChart.do', '12', '1');
INSERT INTO `t_resource` VALUES ('35', '登陆时间数据', '/base/FusionCharts/doNotNeedSecurity/userLogintimeChart.do', '12', '1');
INSERT INTO `t_resource` VALUES ('36', '登陆次数分布图', '', '12', '1');
INSERT INTO `t_resource` VALUES ('37', '角色 - 用户分布图', 'base/FusionCharts/doNotNeedSessionAndSecurity/userRoleChart.do', '13', '1');
INSERT INTO `t_resource` VALUES ('38', '角色 - 用户分布数据', 'user/getRoleList.do', '13', '1');
INSERT INTO `t_resource` VALUES ('39', '日志信息列表', '', '16', '1');
INSERT INTO `t_resource` VALUES ('40', '导入用户日志', '', '16', '1');
INSERT INTO `t_resource` VALUES ('41', '导出用户日志', '', '16', '1');
INSERT INTO `t_resource` VALUES ('42', '日志信息列表', '', '17', '1');
INSERT INTO `t_resource` VALUES ('43', '导入用户日志', '', '17', '1');
INSERT INTO `t_resource` VALUES ('44', '导出用户日志', '', '17', '1');
INSERT INTO `t_resource` VALUES ('45', '日志信息列表', '', '18', '1');
INSERT INTO `t_resource` VALUES ('46', '导入用户日志', '', '18', '1');
INSERT INTO `t_resource` VALUES ('47', '导出用户日志', '', '18', '1');
INSERT INTO `t_resource` VALUES ('48', '日志信息列表', '', '19', '1');
INSERT INTO `t_resource` VALUES ('49', '导入用户日志', '', '19', '1');
INSERT INTO `t_resource` VALUES ('50', '导出用户日志', '', '19', '1');
INSERT INTO `t_resource` VALUES ('51', '日志信息列表', '', '20', '1');
INSERT INTO `t_resource` VALUES ('52', '导入用户日志', '', '20', '1');
INSERT INTO `t_resource` VALUES ('53', '导出用户日志', '', '20', '1');

-- ----------------------------
-- Table structure for t_resource_beta
-- ----------------------------
DROP TABLE IF EXISTS `t_resource_beta`;
CREATE TABLE `t_resource_beta` (
  `resourceId` int(11) NOT NULL AUTO_INCREMENT,
  `resourceName` varchar(255) DEFAULT NULL COMMENT '资源名称',
  `iconClsName` varchar(255) DEFAULT NULL COMMENT '图标名称',
  `resourceUrl` varchar(255) DEFAULT NULL COMMENT '资源地址',
  `resourceType` int(11) NOT NULL DEFAULT '0' COMMENT '资源类型',
  `resourceDescription` mediumtext COMMENT '资源描述',
  `parentId` int(11) NOT NULL COMMENT '节点父id',
  `state` varchar(255) NOT NULL DEFAULT 'open' COMMENT '状态',
  PRIMARY KEY (`resourceId`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_resource_beta
-- ----------------------------
INSERT INTO `t_resource_beta` VALUES ('1', '系统管理', 'icon-application_view_tile', '', '0', '管理系统的资源、角色、机构、用户等信息', '-1', 'closed');
INSERT INTO `t_resource_beta` VALUES ('2', '系统监控', 'icon-monitor', '', '0', '监控系统运行情况等信息', '-1', 'closed');
INSERT INTO `t_resource_beta` VALUES ('3', '系统报表', 'icon-chart_curve', '', '0', '查看系统相关报表图标', '-1', 'closed');
INSERT INTO `t_resource_beta` VALUES ('4', '权限管理', 'icon-permission', '', '0', '', '1', 'closed');
INSERT INTO `t_resource_beta` VALUES ('5', '机构管理', 'icon-department', '', '0', '', '1', 'closed');
INSERT INTO `t_resource_beta` VALUES ('6', '资源管理', 'icon-newspaper_link', 'web/Resources/Resources.jsp', '0', '', '1', 'open');
INSERT INTO `t_resource_beta` VALUES ('7', '数据源(上传文件)监控', 'icon-monitor_link', 'web/Monitor/Upload.jsp', '0', '', '2', 'open');
INSERT INTO `t_resource_beta` VALUES ('8', '用户登陆历史监控', 'icon-chart_line', 'web/Monitor/UserEvent.jsp', '0', '', '2', 'open');
INSERT INTO `t_resource_beta` VALUES ('9', '用户注册报表', 'icon-chart_bar', 'web/FusionCharts/CreateDatetimeChart.jsp', '0', '', '3', 'open');
INSERT INTO `t_resource_beta` VALUES ('10', '用户登陆报表', 'ext-icon-land-chart', 'web/FusionCharts/LogReport.jsp', '0', '', '3', 'open');
INSERT INTO `t_resource_beta` VALUES ('11', '用户角色分布', 'icon-Pie_Chart', 'web/FusionCharts/userRoleChart.jsp', '0', '', '3', 'open');
INSERT INTO `t_resource_beta` VALUES ('12', '用户管理', 'icon-userManage', 'web/Permission/userManage.jsp', '0', '', '4', 'open');
INSERT INTO `t_resource_beta` VALUES ('13', '角色管理', 'icon-roleManage', 'web/Permission/roleManage.jsp', '0', '', '4', 'open');
INSERT INTO `t_resource_beta` VALUES ('14', '菜单管理', 'icon-menuManage', 'web/Permission/menuManage.jsp', '0', '', '4', 'open');
INSERT INTO `t_resource_beta` VALUES ('15', '职员系统', 'icon-staff_system', '', '0', '', '5', 'closed');
INSERT INTO `t_resource_beta` VALUES ('16', '考勤系统', 'icon-attendance', '', '0', '', '5', 'closed');
INSERT INTO `t_resource_beta` VALUES ('17', '部门系统', 'icon-department', '', '0', '', '5', 'closed');
INSERT INTO `t_resource_beta` VALUES ('18', '职员信息', 'icon-employee', 'web/Employer/EmployerInfo.jsp', '0', '', '15', 'open');
INSERT INTO `t_resource_beta` VALUES ('19', '工资信息', 'icon-item', 'web/Employer/Wage_list.jsp', '0', '', '15', 'open');
INSERT INTO `t_resource_beta` VALUES ('20', '简历管理', 'icon-job', 'web/Employer/ResumeInfo.jsp', '0', '', '15', 'open');
INSERT INTO `t_resource_beta` VALUES ('21', '考勤类型', '', '', '0', '', '16', 'open');
INSERT INTO `t_resource_beta` VALUES ('22', '员工考勤信息', '', '', '0', '', '16', 'open');
INSERT INTO `t_resource_beta` VALUES ('23', '职位信息', 'icon-job_information', 'web/Employer/Post.jsp', '0', '', '17', 'open');
INSERT INTO `t_resource_beta` VALUES ('24', '部门信息', 'icon-department', 'web/Employer/Department.jsp', '0', '', '17', 'open');

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `roleId` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号（主键）',
  `roleName` varchar(255) DEFAULT NULL COMMENT '角色名称',
  `authIds` varchar(255) DEFAULT NULL COMMENT '菜单权限 ID 集合',
  `roleDescription` mediumtext COMMENT '备注',
  PRIMARY KEY (`roleId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('1', '超级管理员', '1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20', '超级管理员，是管理员组的成员，拥有系统所有权限.管理员帐户就是允许您进行将影响其他用户的更改的用户帐户。');
INSERT INTO `t_role` VALUES ('2', '来宾用户', '1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,18,19', '来宾用户，来宾组允许偶尔或临时用户登录计算机的内置来宾帐户，并授予有限的能力。来宾帐户是供在计算机或域中没有永久帐户的用户使用的帐户。它允许人们使用计算机，但没有访问个人文件的权限。使用来宾帐户的人无法安装软件或硬件，但可以访问已经安装在计算机上的程序；无法更改来宾帐户类型；可以更改来宾帐户图片。');
INSERT INTO `t_role` VALUES ('3', '系统监控管理员', '2,9,10', '系统监控管理员,');
INSERT INTO `t_role` VALUES ('4', '系统维护人员', '4,14,15,16,17,18,19', '');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `userId` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号（主键）',
  `userName` varchar(255) COLLATE utf8_bin NOT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '密码',
  `createdatetime` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '创建时间',
  `updatedatetime` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '修改时间',
  `userType` tinyint(4) NOT NULL COMMENT '用户类型',
  `roleId` int(11) DEFAULT NULL COMMENT '角色 ID（外键）',
  `userDescription` mediumtext CHARACTER SET utf8 COMMENT '备注',
  PRIMARY KEY (`userId`),
  KEY `roleId` (`roleId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'admin', 'admin', '2014-03-13 19:41:04', '2014-05-13 20:41:04', '1', '1', '是管理员组的成员，拥有系统所有权限.');
INSERT INTO `t_user` VALUES ('2', 'guest', 'admin', '2014-07-14 13:10:30', '2014-07-14 13:10:30', '2', '2', '是来宾组成员，是允许偶尔或临时用户登录所设置的账户，只有有限的权利');

-- ----------------------------
-- Table structure for t_wage
-- ----------------------------
DROP TABLE IF EXISTS `t_wage`;
CREATE TABLE `t_wage` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `empName` varchar(255) NOT NULL COMMENT '职员姓名',
  `years` varchar(255) DEFAULT NULL COMMENT '年份',
  `month` varchar(255) DEFAULT NULL COMMENT '月份',
  `wage_total` varchar(255) DEFAULT NULL COMMENT '总工资',
  `wage_deduct` varchar(255) DEFAULT NULL COMMENT '扣除工资',
  `wage_real` varchar(255) DEFAULT NULL COMMENT '实发工资',
  `paymasterPeople` varchar(255) DEFAULT NULL COMMENT '发款人',
  `wage_status` int(11) DEFAULT '0' COMMENT '状态(1:已发放；0：未发放)',
  PRIMARY KEY (`id`),
  KEY `empName` (`empName`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_wage
-- ----------------------------

-- ----------------------------
-- Table structure for uploadfile
-- ----------------------------
DROP TABLE IF EXISTS `uploadfile`;
CREATE TABLE `uploadfile` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uploadFileName` varchar(255) DEFAULT NULL COMMENT '上传文件名',
  `uploadFilePerson` varchar(255) DEFAULT NULL COMMENT '上传者',
  `uploadTime` varchar(255) DEFAULT NULL COMMENT '上传文件时间',
  `uploadFileSize` int(11) DEFAULT NULL COMMENT '文件长度',
  `uploadFileType` varchar(255) DEFAULT NULL COMMENT '文件类型',
  `belong` varchar(255) DEFAULT NULL COMMENT '所属模块',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用来检测上传文件';

-- ----------------------------
-- Records of uploadfile
-- ----------------------------
