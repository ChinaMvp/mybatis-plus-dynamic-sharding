package com.mvp.world.mybatisplusdynamicsharding.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mvp.world.mybatisplusdynamicsharding.dao.OrderDao;
import com.mvp.world.mybatisplusdynamicsharding.model.po.OrderPo;
import com.mvp.world.mybatisplusdynamicsharding.service.OrderService;
import lombok.Data;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderDao, OrderPo> implements OrderService {

    @Resource
    OrderDao orderDao;

    @Override
    @DS("sharding")
    public OrderPo getOrderPoByUserId(Long userId, Long id) {
        QueryWrapper<OrderPo> wrapper = Wrappers.query();
        wrapper.eq("order_id", id);
        wrapper.eq("user_id", userId);
        return this.getOne(wrapper);
    }

    @Override
    @DS("sharding")
    public OrderPo getOrderPoById(Long id) {
        QueryWrapper<OrderPo> wrapper = Wrappers.query();
        wrapper.eq("order_id", id);
        return this.getOne(wrapper);
    }

    @Override
    @DS("sharding")
    public Long saveOne(Long userId, Long orderId, BigDecimal price, String remark) {
        OrderPo orderPo = new OrderPo();
        orderPo.setUserId(userId);
        if (Objects.nonNull(orderId)) {
            orderPo.setOrderId(orderId);
        }
        orderPo.setRemark(remark);
        orderPo.setPrice(price);
        orderDao.insert(orderPo);
        return orderPo.getOrderId();
    }

    @Override
    public int insertBatchOrders(List<OrderPo> orderList) {
        return orderDao.insertOrders(orderList);
    }

}
