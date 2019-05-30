package com.ssm.tmall.mapper;

import com.ssm.tmall.pojo.Property;
import com.ssm.tmall.util.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PropertyMapper {
    List<Property> list(@Param("cid") int cid, @Param("page") Page page);

    List<Integer> listNotPage(@Param("cid") int cid);

    int total(@Param("cid") int cid);

    void add(Property property);

    void update(Property property);

    void delete(@Param("id") int id);

    Property get(@Param("id") int id);
}
