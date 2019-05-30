package com.ssm.tmall.mapper;

import com.ssm.tmall.pojo.ProductImage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductImageMapper {
    ProductImage get(int id);

    List<ProductImage> list(@Param("pid") int pid, @Param("type") String type);

    void delete(int id);

    void add(ProductImage productImage);

}
