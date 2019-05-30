package com.ssm.tmall.comparator;

import com.ssm.tmall.pojo.Product;

import java.util.Comparator;

public class ProductReviewComparator implements Comparator<Product> {
    @Override
    public int compare(Product product, Product product2) {
        return product.getReviewCount()-product2.getReviewCount();
    }
}
