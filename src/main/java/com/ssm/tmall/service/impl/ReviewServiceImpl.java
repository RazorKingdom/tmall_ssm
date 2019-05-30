package com.ssm.tmall.service.impl;

import com.ssm.tmall.mapper.ReviewMapper;
import com.ssm.tmall.pojo.Product;
import com.ssm.tmall.pojo.Review;
import com.ssm.tmall.pojo.User;
import com.ssm.tmall.service.ReviewService;
import com.ssm.tmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    ReviewMapper reviewMapper;
    @Autowired
    UserService userService;

    @Override
    public List<Review> list(Product product) {
        List<Review> reviews = reviewMapper.listByProduct(product.getId());
        setUser(reviews);
        return reviews;
    }

    private void setUser(List<Review> reviews) {
        for (Review review : reviews)
            setUser(review);
    }

    private void setUser(Review review) {
        User user = userService.get(review.getUid());
        review.setUser(user);
    }

    @Override
    public void add(Review review) {
        reviewMapper.add(review);
    }

    @Override
    public void delete(int id) {
        reviewMapper.delete(id);
    }

    @Override
    public void update(Review review) {
        reviewMapper.update(review);
    }

    @Override
    public Review get(int id) {
        Review review = reviewMapper.get(id);
        setUser(review);
        return review;
    }

    @Override
    public int getCount(int pid) {
        return reviewMapper.getCount(pid);
    }

}
