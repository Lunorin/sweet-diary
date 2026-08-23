package com.sky.service;

import com.sky.dto.*;
import com.sky.result.PageResult;
import com.sky.vo.OrderPaymentVO;
import com.sky.vo.OrderStatisticsVO;
import com.sky.vo.OrderSubmitVO;
import com.sky.vo.OrderVO;

public interface OrderService {

    //用户下单
    OrderSubmitVO submitOrder(OrdersSubmitDTO ordersSubmitDTO);

    /**
     * 订单支付
     * @param ordersPaymentDTO
     * @return
     */
    OrderPaymentVO payment(OrdersPaymentDTO ordersPaymentDTO) throws Exception;

    /**
     * 支付成功，修改订单状态
     * @param outTradeNo
     */
    void paySuccess(String outTradeNo);

    //历史订单查询
    PageResult pageQuery(int page, int pageSize, Integer status);

    //查看订单详情
    OrderVO details(Long id);

    //取消订单
    void cancel(Long id) throws Exception;

    //再来一单
    void repetition(Long id);

    //订单搜索
    PageResult conditionSearch(OrdersPageQueryDTO ordersPageQueryDTO);

    //各个状态的订单数量统计
    OrderStatisticsVO statistics();

    //商家接单
    void confirm(OrdersConfirmDTO ordersConfirmDTO);

    //制作完成（配送→待派送，自提→已完成）
    void completePreparation(Long id);

    //商家拒单
    void rejection(OrdersCancelDTO ordersCancelDTO) throws Exception;

    //派送订单
    void delivery(Long id);

    //完成订单
    void complete(Long id);

    //客户催单
    void reminder(Long id);
}
