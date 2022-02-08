package com.mvp.world.mybatisplusdynamicsharding.service;

import com.mvp.world.mybatisplusdynamicsharding.model.po.OrderPo;
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
public class OrderServiceImplTest {

    @Resource
    OrderService orderService;

    @Test
    public void getOrderPoByUserIdTest() {
        Long userId = 4L;
        Long orderId = 575814544442523649L;
        OrderPo orderPo = orderService.getOrderPoByUserId(userId, orderId);
        System.out.println("orderPo=" + orderPo);
    }

    @Test
    public void getOrderPoByIdTest() {
        Long id = 1L;
        OrderPo orderPo = orderService.getOrderPoById(id);
        System.out.println("orderPo=" + orderPo);
    }

    @Test
    public void saveOneTest() {
        BigDecimal price = new BigDecimal("6.5");
        Long userId = 2L;
        Long orderId = 1L;
        String remark = "备注测试";
        Long resultOrderId = orderService.saveOne(userId, orderId, price, remark);
        System.out.println("orderId=" + resultOrderId);
    }

    @Test
    public void insertBatchOrdersTest() {
        List<OrderPo> orderList = Lists.newArrayList();

        Long userId = 4L;
        OrderPo orderPo = new OrderPo();
        orderPo.setOrderId(575788488717238273L);
        orderPo.setUserId(userId);
        BigDecimal price = new BigDecimal("6.5");
        orderPo.setPrice(price);
        orderPo.setStatusNo(1);
        String remark = "test";
        orderPo.setRemark(remark);

        Long userId1 = 6L;
        OrderPo orderPo1 = new OrderPo();
        orderPo1.setOrderId(575788488713043968L);
        orderPo1.setUserId(userId1);
        BigDecimal price1 = new BigDecimal("6.5");
        orderPo1.setPrice(price1);
        orderPo1.setStatusNo(1);
        String remark1 = "test";
        orderPo1.setRemark(remark1);

        orderList.add(orderPo);
        orderList.add(orderPo1);

        int resultAdd = orderService.insertBatchOrders(orderList);
        System.out.println("resultAdd=" + resultAdd);
    }

}
