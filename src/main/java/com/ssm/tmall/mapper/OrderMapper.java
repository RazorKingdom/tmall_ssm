package com.ssm.tmall.mapper;

import com.ssm.tmall.pojo.Order;
import com.ssm.tmall.pojo.User;
import com.ssm.tmall.util.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderMapper {

    void add(Order order);

    void delete(@Param("id") int id);

    void update(Order order);

    Order get(@Param("id") int id);

    List<Order> listByUserId(@Param("user") User user);

    int total();

    List<Order> listAll(@Param("page") Page page);
}
