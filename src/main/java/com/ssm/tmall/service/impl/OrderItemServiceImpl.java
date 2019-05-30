package com.ssm.tmall.service.impl;

import com.ssm.tmall.mapper.OrderItemMapper;
import com.ssm.tmall.pojo.Order;
import com.ssm.tmall.pojo.OrderItem;
import com.ssm.tmall.pojo.Product;
import com.ssm.tmall.pojo.User;
import com.ssm.tmall.service.OrderItemService;
import com.ssm.tmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemServiceImpl implements OrderItemService {
    @Autowired
    OrderItemMapper orderItemMapper;
    @Autowired
    ProductService productService;

    @Override
    public void add(OrderItem orderItem) {
        orderItemMapper.add(orderItem);
    }

    @Override
    public void delete(int id) {
        orderItemMapper.delete(id);
    }

    @Override
    public void update(OrderItem orderItem) {
        orderItemMapper.update(orderItem);
    }

    @Override
    public List<OrderItem> list(User user) {
        return orderItemMapper.listByUserId(user);
    }

    @Override
    public List<OrderItem> list(Product product){
        return orderItemMapper.listByProductId(product.getId());
    }


    @Override
    public List<OrderItem> list(Order order) {
        return orderItemMapper.listByOrderId(order);
    }

    @Override
    public OrderItem get(int id) {
        return orderItemMapper.get(id);
    }

    @Override
    public void fill(List<Order> orders) {
        for (Order order : orders)
            fill(order);
    }

    @Override
    public void fill(Order order) {
        List<OrderItem> orderItems = orderItemMapper.listByOrderId(order);
        float total = 0;
        int totalNumber=0;
        for (OrderItem orderItem : orderItems) {
            Product product=productService.get(orderItem.getPid());
            orderItem.setProduct(product);
            total+=orderItem.getNumber()*product.getPromotePrice();
            totalNumber+=orderItem.getNumber();
        }
        order.setTotal(total);
        order.setTotalNumber(totalNumber);
        order.setOrderItems(orderItems);
    }
}
