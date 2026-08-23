package com.sky.controller.admin;

import com.sky.dto.DishPageQueryDTO;
import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.mapper.SetmealMapper;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.SetmealService;
import com.sky.vo.SetmealVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/admin/setmeal")
@Api("套餐相关")
public class SetmealController {

    @Autowired
    private SetmealService setmealService;

    //新增套餐
    @ApiOperation("新增套餐")
    @CacheEvict(cacheNames="setmealCache",key="#SetmealDTO.categoryId")
    @PostMapping
    public Result save(@RequestBody SetmealDTO setmealDTO){
        log.info("新增菜品");
        setmealService.save(setmealDTO);
        return Result.success();
    }

    //分页查询
    @ApiOperation("分页查询")
    @GetMapping("/page")
    public Result<PageResult> pageQuery(SetmealPageQueryDTO setmealPageQueryDTO){
        log.info("分页查询:{}",setmealPageQueryDTO);
        PageResult pageResult=setmealService.pageQuery(setmealPageQueryDTO);
        return Result.success(pageResult);
    }

    //批量删除
    @ApiOperation("批量删除")
    @CacheEvict(cacheNames="setmealCache",allEntries = true)
    @DeleteMapping
    public Result deleteByIds(@RequestParam List<Long> ids){
        log.info("批量删除:{}",ids);
        setmealService.deleteByIds(ids);
        return Result.success();
    }

    //根据id查询回显套餐
    @ApiOperation("根据id查询回显套餐")
    @GetMapping("/{id}")
    public Result<SetmealVO> getById(@PathVariable Long id){
        log.info("根据id查询回显套餐:{}",id);
        SetmealVO setmealVO=setmealService.getById(id);
        return Result.success(setmealVO);
    }

    //修改套餐
    @ApiOperation("修改套餐")
    @CacheEvict(cacheNames="setmealCache",allEntries = true)
    @PutMapping
    public Result update(@RequestBody SetmealDTO setmealDTO){
        log.info("修改套餐:{}",setmealDTO);
        setmealService.update(setmealDTO);
        return Result.success();
    }

    //套餐停售和起售
    @ApiOperation("套餐停售和起售")
    @CacheEvict(cacheNames="setmealCache",allEntries = true)
    @PostMapping("/status/{status}")
    public Result<String> startOrStop(@PathVariable("status") Integer status,Long id){
        log.info("套餐停售和起售");
        setmealService.startOrStop(status,id);
        return Result.success();
    }

}
