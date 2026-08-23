package com.sky.controller.admin;

import com.sky.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

@RestController("adminShopController")
@RequestMapping("/admin/shop")
@Slf4j
@Api("商家营业状态")

public class ShopController {
    public static final String KEY="SHOP_STATUS";

    @Autowired
    private RedisTemplate redisTemplate;

    //设置营业状态
    @ApiOperation("设置营业状态")
    @PutMapping("/{status}")
    public Result setStatus(@PathVariable Integer status){
        log.info("当前营业状态为：{}",status==1?"营业中":"打烊中");
       redisTemplate.opsForValue().set(KEY,status);
        return Result.success();
    }

    //查询营业状态
    @ApiOperation("查询营业状态")
    @GetMapping("/status")
    public Result<Integer> getStatus(){
       Integer  shopStatus = (Integer) redisTemplate.opsForValue().get(KEY);
       log.info("当前营业状态为：{}",shopStatus==1?"营业中":"打烊中");
       return Result.success(shopStatus);
    }
}
