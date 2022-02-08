package com.mvp.world.mybatisplusdynamicsharding.model.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@TableName("`t_order_item`")
public class OrderItemPo {
    @TableId
    Long orderItemId;
    Long userId;
    Long orderId;
    Integer statusNo;
    BigDecimal price;
    Date createdTime;
    Date updatedTime;
}
