package com.sky.controller.admin;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Insert;
//import org.apache.ibatis.annotations.Result;
import com.sky.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@Slf4j
@RestController
@RequestMapping("/admin/dish")
@Api(tags = "菜谱相关")
public class DishController {
    @Autowired
    private DishService dishService;

    @Autowired
    private RedisTemplate redisTemplate;

    //新增菜品
    @PostMapping
    @ApiOperation("新增菜品")
    public Result save(@RequestBody DishDTO dishDTO){
        log.info("新增菜品:{}",dishDTO);
        dishService.saveWithFlavor(dishDTO);

        //清理缓存数据
        String key="dish_"+dishDTO.getCategoryId();
        redisTemplate.delete(key);


        return Result.success();
    }

    //分页查询
    @GetMapping("/page")
    @ApiOperation("分页查询")
    public Result<PageResult> pageQuery(DishPageQueryDTO dishPageQueryDTO){
        log.info("分页查询:{}",dishPageQueryDTO);
        PageResult pageResult=dishService.pageQuery(dishPageQueryDTO);
        return Result.success(pageResult);
    }

    //删除菜品
    @DeleteMapping
    @ApiOperation("删除菜品")
    public Result deleteByIds(@RequestParam List<Long> ids){
        log.info("删除菜品:{}",ids);
        dishService.delteByIds(ids);

        //将所有的菜品缓存数据清理掉，所以删除所有dish_开头的key
        Set keys=redisTemplate.keys("dish_*");
        redisTemplate.delete(keys);


        return Result.success();
    }


    //根据ID查询回显菜品
    @GetMapping("/{id}")
    @ApiOperation("根据ID查询回显菜品")
    public Result<DishVO> getById(@PathVariable Long id){
        log.info("根据ID查询回显菜品:{}",id);
        DishVO dishVO =dishService.getById(id);
        return Result.success(dishVO);
    }

    //根据ID修改菜品
    @PutMapping
    @ApiOperation("修改菜品")
    public Result update(@RequestBody DishDTO dishDTO){
        log.info("修改菜品：{}",dishDTO);
        dishService.update(dishDTO);

        //将所有的菜品缓存数据清理掉，所以删除所有dish_开头的key
        Set keys=redisTemplate.keys("dish_*");
        redisTemplate.delete(keys);

        return Result.success();
    }

    //根据分类id查询菜品
    @GetMapping("/list")
    @ApiOperation("根据分类id查询菜品")
    public Result<List<Dish>> list(Long categoryId){
        log.info("根据分类id查询菜品:{}",categoryId);
        List<Dish> list=dishService.list(categoryId);
        return Result.success(list);
    }

    //套餐停售和起售
    @ApiOperation("套餐停售和起售")
    @PostMapping("/status/{status}")
    public Result<String> startOrStop(@PathVariable("status") Integer status,Long id){
        log.info("套餐停售和起售:{},{}",status,id);
        dishService.startOrStop(status,id);

        //将所有的菜品缓存数据清理掉，所以删除所有dish_开头的key
        Set keys=redisTemplate.keys("dish_*");
        redisTemplate.delete(keys);

        return Result.success();
    }


}
