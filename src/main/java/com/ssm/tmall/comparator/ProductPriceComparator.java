package com.ssm.tmall.comparator;

import com.ssm.tmall.pojo.Product;

import java.util.Comparator;

public class ProductPriceComparator implements Comparator<Product> {
    @Override
    public int compare(Product product, Product product2) {
        return (int)(product.getPromotePrice()-product2.getPromotePrice());
    }
}
