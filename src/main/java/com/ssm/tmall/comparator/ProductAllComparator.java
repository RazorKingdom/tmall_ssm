package com.ssm.tmall.comparator;

import com.ssm.tmall.pojo.Product;

import java.util.Comparator;

public class ProductAllComparator implements Comparator<Product> {
    @Override
    public int compare(Product product1,Product product2){
        return product1.getSaleCount()*product1.getReviewCount()-product2.getSaleCount()*product2.getReviewCount();
    }
}
