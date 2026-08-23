package com.sky.mapper;

import com.sky.entity.ShoppingCart;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

@Mapper
public interface ShoppingCartMapper {
    //添加购物车
    List<ShoppingCart> list(ShoppingCart shoppingCart);

    //已有商品，数量加1
    @Update("update shopping_cart set number=#{number} where id=#{id}")
    void updateNumberById(ShoppingCart cart);

    //未有商品，插入购物车
    @Insert("insert into shopping_cart(name, image, user_id, dish_id, setmeal_id, dish_flavor, amount, create_time)" +
            " values (#{name}, #{image}, #{userId}, #{dishId}, #{setmealId}, #{dishFlavor}, #{amount}, #{createTime})")
    void insert(ShoppingCart shoppingCart);

    //清空购物车
    @Delete("delete from shopping_cart where user_id=#{userId}")
    void delete(Long userId);

    //根据id删除购物车商品
    @Delete("delete from shopping_cart where id=#{id}")
    void deleteById(Long id);

    //将购物车对象批量插入
    void insertBatch(List<ShoppingCart> shoppingCartList);
}
