
#### 分库分表
## 订单明细表
DROP TABLE IF EXISTS`t_order_item_0`;
CREATE TABLE `t_order_item_0`(
	`order_item_id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT "自增id",
	`order_id` bigint(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT "订单ID",
	`user_id` bigint(20) NOT NULL DEFAULT 0 COMMENT "用户ID",
	`price` decimal(10, 2) NOT NULL DEFAULT '0.0' COMMENT "价格",
-- 	`goods_id` int(10) NOT NULL DEFAULT 0 COMMENT "商品ID",
-- 	`goods_number` int(10) NOT NULL DEFAULT 0 COMMENT "商品个数",
	`status_no` int(10) NOT NULL DEFAULT 0 COMMENT "状态 0有效 -1删除",
	`created_time` DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT "创建时间",
	`updated_time` DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT "更新时间",
     PRIMARY KEY(`order_item_id`),
	 KEY idx_order(`order_id`)
) ENGINE= InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT="订单明细表";

