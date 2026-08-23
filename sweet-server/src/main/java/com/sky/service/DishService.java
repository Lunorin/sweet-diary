package com.sky.service;

import com.sky.annotation.AutoFill;
import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.enumeration.OperationType;
import com.sky.result.PageResult;
import com.sky.vo.DishVO;

import java.util.List;

public interface DishService {
    //新增菜品
    void saveWithFlavor(DishDTO dishDTO);

    //分页查询
    PageResult pageQuery(DishPageQueryDTO dishPageQueryDTO);

    //批量删除菜品
    void delteByIds(List<Long> ids);

    //根据ID查询回显菜品
    DishVO getById(Long id);

    //修改菜品
    void update(DishDTO dishDTO);

    //根据分类id查询菜品
    List<Dish> list(Long categoryId);

    //起售和停售
    @AutoFill(value = OperationType.UPDATE)
    void startOrStop(Integer status, Long id);
    /**
     * 条件查询菜品和口味
     * @param dish
     * @return
     */
    List<DishVO> listWithFlavor(Dish dish);
}
