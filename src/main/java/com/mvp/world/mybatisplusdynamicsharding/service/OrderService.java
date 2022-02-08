package com.mvp.world.mybatisplusdynamicsharding.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mvp.world.mybatisplusdynamicsharding.model.po.OrderPo;

import java.math.BigDecimal;
import java.util.List;

public interface OrderService extends IService<OrderPo> {

    OrderPo getOrderPoByUserId(Long userId, Long id);

    OrderPo getOrderPoById(Long id);

    Long saveOne(Long userId, Long orderId, BigDecimal price, String remark);

    int insertBatchOrders(List<OrderPo> orderList);
}
