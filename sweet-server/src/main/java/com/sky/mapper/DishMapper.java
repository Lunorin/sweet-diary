package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.enumeration.OperationType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface DishMapper {

    /**
     * 根据分类id查询菜品数量
     * @param categoryId
     * @return
     */
    @Select("select count(id) from dish where category_id = #{categoryId}")
    Integer countByCategoryId(Long categoryId);

    //新增菜品
    @AutoFill(value = OperationType.INSERT)
    void insert(Dish dish);

    //分页查询
    Page<DishDTO> pageQuery(DishPageQueryDTO dishPageQueryDTO);

    //根据菜品id获得dish数据
    Dish getById(Long id);

    //根据菜品id删除菜品
    void deleteById(Long id);

    //修改菜品
    @AutoFill(value = OperationType.UPDATE)
    void update(Dish dish);

    //根据分类id查询菜品
    List<Dish> select(Dish dish);

    //起售和停售
    void startOrStop(Dish dish);

    //条件查询菜品和口味
    List<Dish> list(Dish dish);

        /**
         * 根据条件统计菜品数量
         * @param map
         * @return
         */

    Integer countByMap(Map map);


}
