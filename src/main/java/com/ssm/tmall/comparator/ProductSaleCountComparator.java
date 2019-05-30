package com.ssm.tmall.comparator;

import com.ssm.tmall.pojo.Product;

import java.util.Comparator;

public class ProductSaleCountComparator implements Comparator<Product> {
    @Override
    public int compare(Product product, Product product2) {
        return product.getSaleCount()-product2.getSaleCount();
    }
}
