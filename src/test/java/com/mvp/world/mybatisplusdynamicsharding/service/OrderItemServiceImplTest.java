package com.mvp.world.mybatisplusdynamicsharding.service;

import com.mvp.world.mybatisplusdynamicsharding.dao.OrderItemDao;
import com.mvp.world.mybatisplusdynamicsharding.model.po.OrderItemPo;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderItemServiceImplTest {

    @Resource
    OrderItemDao orderItemDao;

    /**
     * 经验证，直接使用mybatis结合sharding进行批量插入，是好使的
     */
    @Test
    public void insertBatchItems() {
        // orderId 1357610682201989121L
        Long userId = 1L;
        Long orderId = 1357610682201989121L;
        OrderItemPo orderItemPo = new OrderItemPo();
        orderItemPo.setOrderItemId(1L);
        orderItemPo.setOrderId(orderId);
        orderItemPo.setUserId(userId);
        BigDecimal price = new BigDecimal("6.5");
        orderItemPo.setPrice(price);
        orderItemPo.setStatusNo(0);

        OrderItemPo orderItemPo1 = new OrderItemPo();
        orderItemPo1.setOrderItemId(2L);
        orderItemPo1.setOrderId(orderId);
        orderItemPo1.setUserId(userId);
        BigDecimal price1 = new BigDecimal("7.5");
        orderItemPo1.setPrice(price1);
        orderItemPo1.setStatusNo(0);

        // 订单ID 1357610682201989121L
        Long userId1 = 2L;
        Long orderId1 = 1357610682201989122L;
        OrderItemPo orderItemPo2 = new OrderItemPo();
        orderItemPo2.setOrderItemId(3L);
        orderItemPo2.setOrderId(orderId1);
        orderItemPo2.setUserId(userId1);
        BigDecimal price2 = new BigDecimal("8.5");
        orderItemPo2.setPrice(price2);
        orderItemPo2.setStatusNo(0);

        OrderItemPo orderItemPo3 = new OrderItemPo();
        orderItemPo3.setOrderItemId(4L);
        orderItemPo3.setOrderId(orderId1);
        orderItemPo3.setUserId(userId1);
        BigDecimal price3 = new BigDecimal("9.5");
        orderItemPo3.setPrice(price3);
        orderItemPo3.setStatusNo(0);

        List<OrderItemPo> orderItemPos = Lists.newArrayList();
        orderItemPos.add(orderItemPo);
        orderItemPos.add(orderItemPo1);
        orderItemPos.add(orderItemPo2);
        orderItemPos.add(orderItemPo3);

        int result = orderItemDao.insertOrderItems(orderItemPos);
        System.out.println("result=" + result);
    }
}
