package com.mvp.world.mybatisplusdynamicsharding.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDaoTest {

    @Resource
    OrderDao orderDao;

    @Test
    public void getUserOrderTest() {
        Long userId = 1L;
        Long orderId = 2L;
        List<Map<String, String>> userOrder = orderDao.getUserOrder(userId, orderId);
        System.out.println("userOrder=" + userOrder);
    }
}
