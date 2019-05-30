package com.ssm.tmall.comparator;

import com.ssm.tmall.pojo.Product;

import java.util.Comparator;

public class ProductDateComparator implements Comparator<Product> {
    @Override
    public int compare(Product product, Product t1) {
        return product.getCreateDate().compareTo(t1.getCreateDate());
    }
}
