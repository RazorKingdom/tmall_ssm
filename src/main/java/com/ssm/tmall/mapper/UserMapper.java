package com.ssm.tmall.mapper;

import com.ssm.tmall.pojo.User;
import com.ssm.tmall.util.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    List<User> list(@Param("page") Page page);

    int total();

    User get(int id);

    void add(User user);

    void delete(int id);

    void update(User user);

    List<User> listByName(@Param("name") String name);

    List<User> loginVeri(@Param("name") String name, @Param("password") String password);
}
