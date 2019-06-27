package com.ssm.tmall.service;

import com.ssm.tmall.pojo.Order;
import com.ssm.tmall.pojo.OrderItem;
import com.ssm.tmall.pojo.Product;
import com.ssm.tmall.pojo.User;

import java.util.List;

public interface OrderItemService {
    Integer add(OrderItem orderItem);
    void delete(int id);
    void update(OrderItem orderItem);
    List<OrderItem> list(User user);
    List<OrderItem> list(Order order);
    List<OrderItem> list(Product product);
    OrderItem get(int id);

    void fill(List<Order> orders);
    void fill(Order order);
}
