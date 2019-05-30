package com.ssm.tmall.service;

import com.ssm.tmall.pojo.Category;
import com.ssm.tmall.util.Page;

import java.util.List;

public interface CategoryService {
    List<Category> list(Page page);
    int total();
    void add(Category category);
    void delete(Category category);
    Category get(int id);
    void update(Category category);

    List<Category> listAll();
}
