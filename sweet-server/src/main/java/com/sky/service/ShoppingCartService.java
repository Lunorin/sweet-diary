package com.sky.service;

import com.sky.dto.ShoppingCartDTO;
import com.sky.entity.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {
    //添加购物车
    void add(ShoppingCartDTO shoppingCartDTO);

    //减少购物车商品数量
    void sub(ShoppingCartDTO shoppingCartDTO);

    //查看购物车
    List<ShoppingCart> showShoppingCart();

    //清空购物车
    void cleanShoppingCart();
}
