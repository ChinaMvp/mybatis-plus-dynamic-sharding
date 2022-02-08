package com.mvp.world.mybatisplusdynamicsharding.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mvp.world.mybatisplusdynamicsharding.dao.OrderItemDao;
import com.mvp.world.mybatisplusdynamicsharding.model.po.OrderItemPo;
import com.mvp.world.mybatisplusdynamicsharding.service.OrderItemService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OrderItemServiceImpl extends ServiceImpl<OrderItemDao, OrderItemPo> implements OrderItemService {
    @Resource
    OrderItemDao orderItemDao;

    @Override
    public List<OrderItemPo> getOrderPoByUserId(Long userId, Long id) {
        QueryWrapper<OrderItemPo> wrapper = Wrappers.query();
        wrapper.eq("order_item_id", id);
        wrapper.eq("user_id", userId);
        return this.list(wrapper);
    }

    @Override
    public List<OrderItemPo> getOrderPoById(Long id) {
        QueryWrapper<OrderItemPo> wrapper = Wrappers.query();
        wrapper.eq("order_id", id);
        return this.list(wrapper);
    }

    @Override
    public boolean saveBatchItems(List<OrderItemPo> orderItemPos) {
        return this.saveBatch(orderItemPos);
    }


    @Override
    public Long saveOne(OrderItemPo orderItemPo) {
        this.orderItemDao.insert(orderItemPo);
        return orderItemPo.getOrderItemId();
    }
}
