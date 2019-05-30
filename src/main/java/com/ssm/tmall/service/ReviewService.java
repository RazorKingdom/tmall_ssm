package com.ssm.tmall.service;

import com.ssm.tmall.pojo.Product;
import com.ssm.tmall.pojo.Review;

import java.util.List;

public interface ReviewService {

    void add(Review review);

    void delete(int id);

    void update(Review review);

    Review get(int id);

    int getCount(int pid);

    List<Review> list(Product product);
}
