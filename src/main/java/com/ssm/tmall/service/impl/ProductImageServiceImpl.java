package com.ssm.tmall.service.impl;

import com.ssm.tmall.mapper.ProductImageMapper;
import com.ssm.tmall.pojo.ProductImage;
import com.ssm.tmall.service.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductImageServiceImpl implements ProductImageService {
    @Autowired
    ProductImageMapper productImageMapper;

    @Override
    public List<ProductImage> list(int pid, String type) {
        return productImageMapper.list(pid,type);
    }

    @Override
    public void add(ProductImage productImage) {
        productImageMapper.add(productImage);
    }

    @Override
    public ProductImage get(int id) {
        return productImageMapper.get(id);
    }

    @Override
    public void delete(int id) {
        productImageMapper.delete(id);
    }

}
