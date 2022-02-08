
#### 存储于自定义数据库
## 用户表
DROP TABLE IF EXISTS`user`;
CREATE TABLE `user` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT "自增id",
  `user_name` varchar(255) DEFAULT '' COMMENT "用户名称",
  `mobile` varchar(32) DEFAULT '' COMMENT "手机号",
  `age` INTEGER(10) DEFAULT 0 COMMENT "用户年龄",
  `sex` INTEGER(10) DEFAULT 0 COMMENT "性别 0女 1男",
  `school` varchar(255) DEFAULT '' COMMENT "用户学校",
  `type` INTEGER(10) DEFAULT 0 COMMENT "类型 0正常 1马甲",
  `remark` varchar(255) DEFAULT '' COMMENT "备注",
  `created_time` DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT "创建时间",
  `updated_time` DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT "更新时间",
   PRIMARY KEY (`id`),
   KEY `school` (`school`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT="用户记录表";