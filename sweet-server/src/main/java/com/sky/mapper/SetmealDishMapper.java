package com.sky.mapper;

import com.sky.entity.SetmealDish;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SetmealDishMapper {

    //根据菜品ID查询菜单ID
    List<Long> getSetmealIdByDishId(@Param("ids") List<Long> ids);

    //根据菜品ID查询菜单ID
    List<SetmealDish> getDishIdBySetmealId(Long id);
}
