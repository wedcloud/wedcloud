SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;
-- ----------------------------
-- Table structure for user_info
-- ----------------------------
drop table `user_info`
CREATE TABLE `user_info`
(
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `username` VARCHAR(32) NULL DEFAULT NULL COMMENT '用户名称',
    `password_hash` VARCHAR(100) NULL DEFAULT NULL COMMENT '密码',
    `sex` CHAR(1) DEFAULT NULL COMMENT '性别（0女 1男）',
    `age` INT(13) DEFAULT NULL COMMENT '年龄',
    `open_id` VARCHAR(50) DEFAULT NULL COMMENT '微信用户ID',
    `locked` TINYINT NULL DEFAULT NULL COMMENT '是否冻结',
    `email` VARCHAR(100) NULL DEFAULT NULL COMMENT '邮箱',
    `mobile` VARCHAR(32) NULL DEFAULT NULL COMMENT '手机号',
    `wechat` VARCHAR(100) NULL DEFAULT NULL COMMENT '微信号',
    `headimgurl` VARCHAR(255) NULL DEFAULT NULL COMMENT '用户头像路径',
    `status` VARCHAR(10) NULL DEFAULT NULL COMMENT '状态',
    `created_at` DATETIME DEFAULT NULL COMMENT '创建时间',
    `modified_at` DATETIME DEFAULT NULL COMMENT '更新时间',
    `deleted_at` DATETIME DEFAULT NULL COMMENT '作废时间',
    PRIMARY KEY (id),
    UNIQUE KEY `username` (`username`)
)COMMENT='用户管理表' ENGINE=INNODB AUTO_INCREMENT=1 CHARACTER SET=utf8mb4 COLLATE=utf8mb4_bin ROW_FORMAT = COMPACT;

-- ----------------------------
-- Table structure for album
-- ----------------------------
drop table `album`
CREATE TABLE `album`
(
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `album_name` VARCHAR(32) NULL DEFAULT NULL COMMENT '相簿名称',
    `imgurl` VARCHAR(255) NULL DEFAULT NULL COMMENT '相册封面路径',
    `status` VARCHAR(10) NULL DEFAULT NULL COMMENT '状态',
    `created_at` DATETIME DEFAULT NULL COMMENT '创建时间',
    `modified_at` DATETIME DEFAULT NULL COMMENT '更新时间',
    `deleted_at` DATETIME DEFAULT NULL COMMENT '作废时间',
    PRIMARY KEY (id)
)COMMENT='相册' ENGINE=INNODB AUTO_INCREMENT=1 CHARACTER SET=utf8mb4 COLLATE=utf8mb4_bin ROW_FORMAT = COMPACT;

-- ----------------------------
-- Table structure for menu
-- ----------------------------
drop table `menu`
CREATE TABLE `menu`
(
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `menu_name` VARCHAR(32) NULL DEFAULT NULL COMMENT '菜单名称',
    `menu_url` VARCHAR(255) NULL DEFAULT NULL COMMENT '菜单url',
    `menu_icon` VARCHAR(32) NULL DEFAULT NULL COMMENT '菜单图标',
    `menu_level` TINYINT(4) DEFAULT '0' COMMENT '菜单级别',
    `menu_parent` TINYINT(4) DEFAULT NULL COMMENT '父级菜单',
    `status` TINYINT(4) DEFAULT '0' DEFAULT NULL COMMENT '状态',
    `created_at` DATETIME NULL DEFAULT NULL COMMENT '创建时间',
    `modified_at` DATETIME NULL DEFAULT NULL COMMENT '更新时间',
    `deleted_at` DATETIME NULL DEFAULT NULL COMMENT '作废时间',
    PRIMARY KEY (id)
)COMMENT='相册' ENGINE=INNODB AUTO_INCREMENT=1 CHARACTER SET=utf8mb4 COLLATE=utf8mb4_bin ROW_FORMAT = COMPACT;