package com.ssm.tmall.mapper;

import com.ssm.tmall.pojo.Order;
import com.ssm.tmall.pojo.OrderItem;
import com.ssm.tmall.pojo.User;

import java.util.List;

public interface OrderItemMapper {
    int add(OrderItem orderItem);

    void delete(int id);

    void update(OrderItem orderItem);

    List<OrderItem> listByUserId(User user);

    OrderItem get(int id);

    List<OrderItem> listByOrderId(Order order);

    List<OrderItem> listByProductId(int pid);
}
