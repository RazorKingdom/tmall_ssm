package com.ssm.tmall.mapper;

import com.ssm.tmall.pojo.Review;

import java.util.List;

public interface ReviewMapper {

    List<Review> listByProduct(int pid);

    void add(Review review);

    void delete(int id);

    void update(Review review);

    Review get(int id);

    int getCount(int pid);
}
