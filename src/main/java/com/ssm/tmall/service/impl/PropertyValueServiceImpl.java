package com.ssm.tmall.service.impl;

import com.ssm.tmall.mapper.PropertyValueMapper;
import com.ssm.tmall.pojo.Product;
import com.ssm.tmall.pojo.PropertyValue;
import com.ssm.tmall.service.PropertyService;
import com.ssm.tmall.service.PropertyValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyValueServiceImpl implements PropertyValueService {
    @Autowired
    PropertyValueMapper propertyValueMapper;
    @Autowired
    PropertyService propertyService;

    @Override
    public PropertyValue get(int pid, int ptid) {
        PropertyValue propertyValue= propertyValueMapper.get(pid, ptid);
        setProperty(propertyValue);
        return propertyValue;
    }

    @Override
    public void update(PropertyValue propertyValue) {
        propertyValueMapper.update(propertyValue);
    }

    @Override
    public List<PropertyValue> list(int pid) {
        List<PropertyValue>pvs= propertyValueMapper.list(pid);
        setProperty(pvs);
        return pvs;
    }

    @Override
    public void init(Product product) {
        int pid = product.getId();
        int cid=product.getCid();
        List<Integer> ptids = propertyService.listNotPage(cid);
        List<Integer> ptids_exist = propertyValueMapper.listwithint(pid);
        for (Integer ptid_exist : ptids_exist)
            if (!ptids.contains(ptid_exist))
                propertyValueMapper.delete(pid, ptid_exist);

        for (Integer ptid : ptids) {
            PropertyValue propertyValue = propertyValueMapper.get(pid, ptid);
            if (propertyValue == null)
                propertyValueMapper.add(pid, ptid);
        }
    }

    private void setProperty(PropertyValue propertyValue){
        propertyValue.setProperty(propertyService.get(propertyValue.getPtid()));
    }

    private void setProperty(List<PropertyValue> propertyValues){
        for(PropertyValue propertyValue:propertyValues)
            setProperty(propertyValue);
    }
}
