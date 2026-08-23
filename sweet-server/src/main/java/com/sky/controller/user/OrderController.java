package com.sky.controller.user;

import com.sky.dto.DishPageQueryDTO;
import com.sky.dto.OrdersPaymentDTO;
import com.sky.dto.OrdersSubmitDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.OrderService;
import com.sky.vo.OrderPaymentVO;
import com.sky.vo.OrderSubmitVO;
import com.sky.vo.OrderVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@RestController("userOrderController")
@RequestMapping("/user/order")
@Slf4j
@Api("用户端订单相关")
public class OrderController {
    @Autowired
    private OrderService orderService;

    //用户下单
    @ApiOperation("用户下单")
    @PostMapping("/submit")
    public Result<OrderSubmitVO> submit(@RequestBody OrdersSubmitDTO ordersSubmitDTO){
        log.info("用户下单:{}",ordersSubmitDTO);
        OrderSubmitVO orderSubmitVO=orderService.submitOrder(ordersSubmitDTO);
        return Result.success(orderSubmitVO);
    }



    /**
     * 订单支付
     *
     * @param ordersPaymentDTO
     * @return
     */
//    @PutMapping("/payment")
//    @ApiOperation("订单支付")
//    public Result<OrderPaymentVO> payment(@RequestBody OrdersPaymentDTO ordersPaymentDTO) throws Exception {
//        log.info("订单支付：{}", ordersPaymentDTO);
//        OrderPaymentVO orderPaymentVO = orderService.payment(ordersPaymentDTO);
//        log.info("生成预支付交易单：{}", orderPaymentVO);
//        return Result.success(orderPaymentVO);
//    }

    //订单支付（开发环境模拟支付，直接标记支付成功；自提/配送通用）
    @PutMapping("/payment")
    @ApiOperation("订单支付")
    public Result<String> payment(@RequestBody OrdersPaymentDTO ordersPaymentDTO) {
        log.info("订单支付：{}", ordersPaymentDTO);
        if (ordersPaymentDTO == null || ordersPaymentDTO.getOrderNumber() == null
                || ordersPaymentDTO.getOrderNumber().trim().isEmpty()) {
            return Result.error("订单号不能为空");
        }
        orderService.paySuccess(ordersPaymentDTO.getOrderNumber());
        return Result.success();
    }

    //历史订单查询(分页查询+关联查询)
    @GetMapping("/historyOrders")
    @ApiOperation("历史订单查询")
    public Result<PageResult> page(int page,int pageSize,Integer status){
        log.info("历史订单查询");
        PageResult pageResult=orderService.pageQuery(page,pageSize,status);
        return Result.success(pageResult);
    }

    //查看订单详情
    @GetMapping("/orderDetail/{id}")
    @ApiOperation("查看订单详情")
    public Result<OrderVO> details(@PathVariable("id") Long id){
        log.info("查看订单详情");
        OrderVO orderVO=orderService.details(id);
        return Result.success(orderVO);
    }

    //取消订单
    @PutMapping("/cancel/{id}")
    @ApiOperation("取消订单")
    public Result cancel(@PathVariable("id") Long id) throws Exception {
        log.info("取消订单");
        orderService.cancel(id);
        return Result.success();
    }

    //再来一单
    @PostMapping("/repetition/{id}")
    @ApiOperation("再来一单")
    public Result repetition(@PathVariable("id") Long id){
        log.info("再来一单");
        orderService.repetition(id);
        return Result.success();
    }

    //客户催单
    @GetMapping("/reminder/{id}")
    @ApiOperation("客户催单")
    public Result reminder(@PathVariable("id") Long id){
        log.info("客户催单:{}",id);
        orderService.reminder(id);
        return Result.success();
    }

}
