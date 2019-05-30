package com.ssm.tmall.service.impl;

import com.ssm.tmall.mapper.UserMapper;
import com.ssm.tmall.pojo.User;
import com.ssm.tmall.service.UserService;
import com.ssm.tmall.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public List<User> list(Page page) {
        return userMapper.list(page);
    }

    @Override
    public int total() {
        return userMapper.total();
    }

    @Override
    public User get(int id) {
        return userMapper.get(id);
    }

    @Override
    public void add(User user) {
        userMapper.add(user);
    }

    @Override
    public void delete(int id) {
        userMapper.delete(id);
    }

    @Override
    public void update(User user) {
        userMapper.update(user);
    }

    @Override
    public boolean isExist(String name) {
        return userMapper.listByName(name).isEmpty()?false:true;
    }

    @Override
    public User loginVeri(String name, String password) {
        List<User> user=userMapper.loginVeri(name,password);
        if(user.isEmpty())
            return null;
        return user.get(0);
    }
}
