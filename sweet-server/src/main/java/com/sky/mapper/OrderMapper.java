package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.dto.GoodsSalesDTO;
import com.sky.dto.OrdersPageQueryDTO;
import com.sky.entity.Orders;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Mapper
public interface OrderMapper {
    //插入订单数据
    void insert(Orders orders);


    /**
     * 根据订单号查询订单
     * @param orderNumber
     */
    @Select("select * from orders where number = #{orderNumber}")
    Orders getByNumber(String orderNumber);

    /**
     * 修改订单信息
     * @param orders
     */
    void update(Orders orders);

    //历史订单查询
    Page<Orders> pageQuery(OrdersPageQueryDTO ordersPageQueryDTO);

    //查看订单详情
    Orders getById(Long id);

    //根据状态，分别统计不同状态的数量数量
    @Select("select count(*) from orders where status=#{status};")
    Integer countStatus(Integer status);

    //根据订单状态和下单时间查询订单
    @Select("select * from orders where status=#{status} and order_time<#{orderTime}")
    List<Orders> getByStatusAndOrderTimeLT(Integer status, LocalDateTime orderTime);

    //统计每天营业额
    Double sumByMap(Map map);

    //统计订单数
    Integer countByMap(Map map);

    List<GoodsSalesDTO> getSalesTop(LocalDateTime begin,LocalDateTime end,Integer status);
}
