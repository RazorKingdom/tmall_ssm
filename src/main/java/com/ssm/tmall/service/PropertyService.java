package com.ssm.tmall.service;

import com.ssm.tmall.pojo.Property;
import com.ssm.tmall.util.Page;

import java.util.List;

public interface PropertyService {
    List<Property> list(int cid, Page page);
    void add(Property property);
    void delete(int id);
    Property get(int id);
    void update(Property property);
    int total(int cid);

    List<Integer> listNotPage(int cid);
}
