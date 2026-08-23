package com.sky.service;

import com.sky.annotation.AutoFill;
import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.enumeration.OperationType;
import com.sky.result.PageResult;
import com.sky.vo.DishItemVO;
import com.sky.vo.SetmealVO;

import java.util.List;

public interface SetmealService {
    //新增套餐
    void save(SetmealDTO setmealDTO);

    //分页查询
    PageResult pageQuery(SetmealPageQueryDTO setmealPageQueryDTO);

    //批量删除
    void deleteByIds(List<Long> ids);

    //根据id查询回显套餐
    SetmealVO getById(Long id);

    //修改套餐
    @AutoFill(value = OperationType.UPDATE)
    void update(SetmealDTO setmealDTO);

    //套餐停售和起售
    @AutoFill(value = OperationType.UPDATE)
    void startOrStop(Integer status, Long id);


    /**
     * 条件查询
     * @param setmeal
     * @return
     */
    List<Setmeal> list(Setmeal setmeal);

    /**
     * 根据id查询菜品选项
     * @param id
     * @return
     */
    List<DishItemVO> getDishItemById(Long id);
}
