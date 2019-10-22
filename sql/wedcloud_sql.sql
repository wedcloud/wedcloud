/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 8.0.17 : Database - wedcloud
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`wedcloud` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `wedcloud`;

/*Table structure for table `album` */

DROP TABLE IF EXISTS `album`;

CREATE TABLE `album` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `album_name` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '相簿名称',
  `imgurl` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '相册封面路径',
  `status` varchar(10) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '状态',
  `created_at` datetime DEFAULT NULL COMMENT '创建时间',
  `modified_at` datetime DEFAULT NULL COMMENT '更新时间',
  `deleted_at` datetime DEFAULT NULL COMMENT '作废时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin ROW_FORMAT=COMPACT COMMENT='相册';

/*Data for the table `album` */

insert  into `album`(`id`,`album_name`,`imgurl`,`status`,`created_at`,`modified_at`,`deleted_at`) values (1,'旅行习惯',NULL,NULL,'2019-10-15 21:07:51',NULL,NULL),(2,'自然美景',NULL,NULL,'2019-10-15 21:07:53',NULL,NULL),(3,'建筑风格',NULL,NULL,'2019-10-15 21:07:54',NULL,NULL);

/*Table structure for table `menu` */

DROP TABLE IF EXISTS `menu`;

CREATE TABLE `menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `menu_name` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '菜单名称',
  `menu_url` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '菜单url',
  `menu_icon` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '菜单图标',
  `menu_level` tinyint(4) DEFAULT '0' COMMENT '菜单级别',
  `menu_parent` tinyint(4) DEFAULT NULL COMMENT '父级菜单',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态',
  `created_at` datetime DEFAULT NULL COMMENT '创建时间',
  `modified_at` datetime DEFAULT NULL COMMENT '更新时间',
  `deleted_at` datetime DEFAULT NULL COMMENT '作废时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin ROW_FORMAT=COMPACT COMMENT='相册';

/*Data for the table `menu` */

insert  into `menu`(`id`,`menu_name`,`menu_url`,`menu_icon`,`menu_level`,`menu_parent`,`status`,`created_at`,`modified_at`,`deleted_at`) values (1,'我的设置',NULL,NULL,0,NULL,NULL,NULL,NULL,NULL),(2,'天气',NULL,NULL,0,NULL,NULL,NULL,NULL,NULL),(3,'相册',NULL,NULL,0,NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `user_info` */

DROP TABLE IF EXISTS `user_info`;

CREATE TABLE `user_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户名称',
  `password_hash` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '密码',
  `sex` char(1) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '性别（0女 1男）',
  `age` int(13) DEFAULT NULL COMMENT '年龄',
  `open_id` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '微信用户ID',
  `locked` tinyint(4) DEFAULT NULL COMMENT '是否冻结',
  `email` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '手机号',
  `wechat` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '微信号',
  `headimgurl` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户头像路径',
  `status` varchar(10) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '状态',
  `created_at` datetime DEFAULT NULL COMMENT '创建时间',
  `modified_at` datetime DEFAULT NULL COMMENT '更新时间',
  `deleted_at` datetime DEFAULT NULL COMMENT '作废时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin ROW_FORMAT=COMPACT COMMENT='用户管理表';

/*Data for the table `user_info` */

insert  into `user_info`(`id`,`username`,`password_hash`,`sex`,`age`,`open_id`,`locked`,`email`,`mobile`,`wechat`,`headimgurl`,`status`,`created_at`,`modified_at`,`deleted_at`) values (1,'许先生','123','1',26,NULL,NULL,NULL,'18100792153',NULL,NULL,NULL,'2019-10-15 21:05:59',NULL,NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
