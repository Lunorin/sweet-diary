package com.sky.mapper;

import com.sky.entity.DishFlavor;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DishFlavorMapper {
    //新增口味
    void insert(List<DishFlavor> flavors);

    //删除口味
    void deleteByDishId(Long id);

    //根据菜品id查询口味
    List<DishFlavor> getByDishId(Long id);

}
