package com.ssm.tmall.service;

import com.ssm.tmall.pojo.ProductImage;

import java.util.List;

public interface ProductImageService {
    String type_single = "type_single";
    String type_detail = "type_detail";

    ProductImage get(int id);

    List<ProductImage> list(int pid, String type);

    void delete(int id);

    void add(ProductImage productImage);

}
