package com.ssm.tmall.service.impl;

import com.ssm.tmall.mapper.ProductMapper;
import com.ssm.tmall.pojo.Category;
import com.ssm.tmall.pojo.OrderItem;
import com.ssm.tmall.pojo.Product;
import com.ssm.tmall.pojo.ProductImage;
import com.ssm.tmall.service.*;
import com.ssm.tmall.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductMapper productMapper;
    @Autowired
    ProductImageService productImageService;
    @Autowired
    OrderItemService orderItemService;
    @Autowired
    ReviewService reviewService;
    @Autowired
    CategoryService categoryService;

    @Override
    public void setSaleAndReviewNumber(Product p) {
        p.setReviewCount(reviewService.getCount(p.getId()));
        p.setSaleCount(getSaleCount(p));
    }

    @Override
    public void setSaleAndReviewNumber(List<Product> ps) {
        for (Product product : ps)
            setSaleAndReviewNumber(product);
    }

    @Override
    public List<Product> list(int cid, Page page) {
        List<Product> products = productMapper.list(cid, page);
        setFirstProductImage(products);
        return products;
    }

    @Override
    public int total(int cid) {
        return productMapper.total(cid);
    }

    @Override
    public void add(Product product) {
        productMapper.add(product);
    }

    @Override
    public void delete(int id) {
        productMapper.delete(id);
    }

    @Override
    public void update(Product product) {
        productMapper.update(product);
    }

    @Override
    public Product get(int id) {
        Product product = productMapper.get(id);
        setFirstProductImage(product);
        setCategory(product);
        return product;
    }

    @Override
    public void setFirstProductImage(Product product) {
        List<ProductImage> pis = productImageService.list(product.getId(), ProductImageService.type_single);
        if (!pis.isEmpty())
            product.setFirstProductImage(pis.get(0));
    }

    @Override
    public void setFirstProductImage(List<Product> ps) {
        for (Product p : ps) {
            setFirstProductImage(p);
        }
    }

    @Override
    public void fill(List<Category> categorys) {
        for (Category category : categorys)
            fill(category);
    }

    @Override
    public void fill(Category category) {
        List<Product> products = productMapper.listByCategoryId(category.getId());
        setFirstProductImage(products);
        category.setProducts(products);
    }

    @Override
    public void fillByRow(List<Category> categorys) {
        int productNumberPerRow = 8;
        for (Category category : categorys) {
            List<Product> products = category.getProducts();
            List<List<Product>> lists = new ArrayList<>();
            for (int i = 0; i < products.size(); i += productNumberPerRow) {
                int bound = (i + productNumberPerRow) < products.size() ? (i + productNumberPerRow) : products.size();
                List<Product> subProducts = products.subList(i, bound);
                lists.add(subProducts);
            }
            category.setProductsByRow(lists);
        }
    }

    @Override
    public void setSingleAndDetailPic(Product product) {
        List<ProductImage> productSingleImages = productImageService.list(product.getId(),
                ProductImageService.type_single);
        List<ProductImage> productDetailImages = productImageService.list(product.getId(),
                ProductImageService.type_detail);
        product.setProductSingleImages(productSingleImages);
        product.setProductDetailImages(productDetailImages);
    }

    private int getSaleCount(Product product) {
        List<OrderItem> orderItemList = orderItemService.list(product);
        int total = 0;
        for (OrderItem orderItem : orderItemList)
            total += orderItem.getNumber();
        return total;
    }

    private void setCategory(Product product){
        Category category=categoryService.get(product.getCid());
        product.setCategory(category);
    }

    private void setCategory(List<Product> products){
        for(Product product:products)
            setCategory(product);
    }

}
