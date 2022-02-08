package com.mvp.world.mybatisplusdynamicsharding.dao;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mvp.world.mybatisplusdynamicsharding.model.po.OrderItemPo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OrderItemDao extends BaseMapper<OrderItemPo> {

    @DS("sharding")
    int insertOrderItems(@Param("orderItemInfos") List<OrderItemPo> orderItemInfos);

}
