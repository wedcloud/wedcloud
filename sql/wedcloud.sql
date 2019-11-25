/*
Navicat MySQL Data Transfer

Source Server         : 148.70.116.45
Source Server Version : 80017
Source Host           : 148.70.116.45:3306
Source Database       : wedcloud

Target Server Type    : MYSQL
Target Server Version : 80017
File Encoding         : 65001

Date: 2019-11-25 09:56:28
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `album`
-- ----------------------------
DROP TABLE IF EXISTS `album`;
CREATE TABLE `album` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `album_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '相簿名称',
  `imgurl` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '相册封面路径',
  `status` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '0 启动 1停用',
  `cancel` int(1) DEFAULT NULL COMMENT '0 未删除 1已删除',
  `created_at` datetime DEFAULT NULL COMMENT '创建时间',
  `modified_at` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin ROW_FORMAT=COMPACT COMMENT='相册';

-- ----------------------------
-- Records of album
-- ----------------------------
INSERT INTO `album` VALUES ('1', '旅行习惯', 'url', '0', '0', '2019-10-15 21:07:51', null);
INSERT INTO `album` VALUES ('2', '我的视频', 'http', '0', '0', '2019-10-15 21:07:53', '2019-11-14 06:42:04');
INSERT INTO `album` VALUES ('3', '建筑风格', 'http', '0', '0', '2019-10-15 21:07:54', null);
INSERT INTO `album` VALUES ('10', '秋天', 'url', '0', '1', '2019-11-07 03:14:56', '2019-11-14 06:42:32');
INSERT INTO `album` VALUES ('11', '家庭照', 'images_url', '0', '0', '2019-11-13 14:24:23', null);

-- ----------------------------
-- Table structure for `menu`
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `menu_name` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '菜单名称',
  `menu_url` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '菜单url',
  `menu_icon` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '菜单图标',
  `menu_level` tinyint(4) DEFAULT '0' COMMENT '菜单级别',
  `menu_parent` tinyint(4) DEFAULT NULL COMMENT '父级菜单',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态',
  `cancel` int(1) DEFAULT NULL COMMENT '0未删除 1已删除',
  `created_at` datetime DEFAULT NULL COMMENT '创建时间',
  `modified_at` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin ROW_FORMAT=COMPACT COMMENT='相册';

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('1', '我的设置', null, null, '0', null, null, null, null, null);
INSERT INTO `menu` VALUES ('2', '天气', null, null, '0', null, null, null, null, null);
INSERT INTO `menu` VALUES ('3', '相册', null, null, '0', null, null, null, null, null);
