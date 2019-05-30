package com.ssm.tmall.service;

import com.ssm.tmall.pojo.Product;
import com.ssm.tmall.pojo.PropertyValue;

import java.util.List;

public interface PropertyValueService {
    PropertyValue get(int pid, int ptid);

    void update(PropertyValue propertyValue);

    List<PropertyValue> list(int pid);

    void init(Product product);

}
