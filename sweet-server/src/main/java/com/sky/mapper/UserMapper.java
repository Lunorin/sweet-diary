package com.sky.mapper;

import com.sky.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

@Mapper
public interface UserMapper {

    //根据openid查询用户
    @Select("select * from user where openid=#{openid}")
    User getByOpenid(String openid);

    //自动注册
    void insert(User user);

    //根据id查询
    @Select("select * from user where id=#{id}")
    User getById(Long id);

    //统计用户数量
    Integer countByMap(Map map);
}
