package com.ssm.tmall.service.impl;

import com.ssm.tmall.mapper.PropertyMapper;
import com.ssm.tmall.pojo.Property;
import com.ssm.tmall.service.PropertyService;
import com.ssm.tmall.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyServiceImpl implements PropertyService {
    @Autowired
    PropertyMapper propertyMapper;

    @Override
    public List<Property> list(int cid, Page page) {
        return propertyMapper.list(cid,page);
    }

    @Override
    public Property get(int id) {
        return propertyMapper.get(id);
    }

    @Override
    public void add(Property property) {
        propertyMapper.add(property);
    }

    @Override
    public void update(Property property) {
        propertyMapper.update(property);
    }

    @Override
    public void delete(int id) {
        propertyMapper.delete(id);
    }

    @Override
    public int total(int cid) {
        return propertyMapper.total(cid);
    }

    @Override
    public List<Integer> listNotPage(int cid) {
        return propertyMapper.listNotPage(cid);
    }
}
