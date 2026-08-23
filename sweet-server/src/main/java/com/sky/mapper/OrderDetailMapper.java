package com.sky.mapper;

import com.sky.entity.OrderDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderDetailMapper {
    //批量插入订单明细数据
    void insertBatch(List<OrderDetail> orderDetailList);

    //查询订单明细
    List<OrderDetail> getByOrderId(Long orderId);
}
