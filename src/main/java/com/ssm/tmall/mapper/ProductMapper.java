package com.ssm.tmall.mapper;

import com.ssm.tmall.pojo.Product;
import com.ssm.tmall.util.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductMapper {
    List<Product> list(@Param("cid") int cid, @Param("page") Page page);
    Product get(int id);
    void add(Product product);
    void delete(int id);
    int total(int cid);
    void update(Product product);

    List<Product> listByCategoryId(Integer cid);

    List<Product> search(String keyword);
}
