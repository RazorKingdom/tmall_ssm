package com.ssm.tmall.service;

import com.ssm.tmall.pojo.Order;
import com.ssm.tmall.pojo.User;
import com.ssm.tmall.util.Page;

import java.util.List;

public interface OrderService {
    String waitPay = "waitPay";

    String waitDelivery = "waitDelivery";


    String waitConfirm = "waitConfirm";


    String waitReview = "waitReview";


    String finish = "finsish";


    String delete = "delete";

    void add(Order order);

    void delete(int id);

    void update(Order order);

    Order get(int id);

    List<Order> list(User user);

    int total();

    List<Order> list(Page page);
}
