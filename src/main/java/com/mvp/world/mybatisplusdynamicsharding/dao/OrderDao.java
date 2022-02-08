package com.mvp.world.mybatisplusdynamicsharding.dao;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mvp.world.mybatisplusdynamicsharding.model.po.OrderPo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface OrderDao extends BaseMapper<OrderPo> {

    @DS("sharding")
    List<Map<String, String>> getUserOrder(@Param("user_id") Long userId, @Param("order_id") Long orderId);

    @DS("sharding")
    int insertOrders(@Param("orderInfos") List<OrderPo> orderInfos);

}
