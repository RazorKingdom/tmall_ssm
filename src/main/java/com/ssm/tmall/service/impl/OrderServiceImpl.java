package com.ssm.tmall.service.impl;

import com.ssm.tmall.mapper.OrderMapper;
import com.ssm.tmall.pojo.Order;
import com.ssm.tmall.pojo.User;
import com.ssm.tmall.service.OrderItemService;
import com.ssm.tmall.service.OrderService;
import com.ssm.tmall.service.UserService;
import com.ssm.tmall.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    OrderItemService orderItemService;
    @Autowired
    UserService userService;

    @Override
    public void add(Order order) {
        orderMapper.add(order);
    }

    @Override
    public void delete(int id) {
        orderMapper.delete(id);
    }

    @Override
    public void update(Order order) {
        orderMapper.update(order);
    }

    @Override
    public Order get(int id) {
        Order order = orderMapper.get(id);
        orderItemService.fill(order);
        setUser(order);
        return order;
    }


    @Override
    public List<Order> list(User user) {
        List<Order> orders = orderMapper.listByUserId(user);
        orderItemService.fill(orders);
        setUser(orders);
        return orders;
    }


    @Override
    public int total() {
        return orderMapper.total();
    }

    @Override
    public List<Order> list(Page page) {
        List<Order> orders=orderMapper.listAll(page);
        orderItemService.fill(orders);
        setUser(orders);
        return orders;
    }

    private void setUser(List<Order> orders) {
        for(Order order:orders)
            setUser(order);
    }

    private void setUser(Order order) {
        User user=userService.get(order.getUid());
        order.setUser(user);
    }
}
