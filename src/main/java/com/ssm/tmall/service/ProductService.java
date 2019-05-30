package com.ssm.tmall.service;

import com.ssm.tmall.pojo.Category;
import com.ssm.tmall.pojo.Product;
import com.ssm.tmall.util.Page;

import java.util.List;

public interface ProductService {
    void setSaleAndReviewNumber(Product p);

    void setSaleAndReviewNumber(List<Product> ps);

    List<Product> list(int cid, Page page);

    Product get(int id);

    void add(Product product);

    void delete(int id);

    int total(int cid);

    void update(Product product);

    void setFirstProductImage(Product product);

    void setFirstProductImage(List<Product> ps);

    void fill(List<Category> categorys);

    void fill(Category category);

    void fillByRow(List<Category> categorys);

    void setSingleAndDetailPic(Product product);
}
