package com.ssm.tmall.service;

import com.ssm.tmall.pojo.User;
import com.ssm.tmall.util.Page;

import java.util.List;

public interface UserService {
    List<User> list(Page page);

    int total();

    User get(int id);

    void add(User user);

    void delete(int id);

    void update(User user);

    boolean isExist(String name);

    User loginVeri(String name, String password);
}
