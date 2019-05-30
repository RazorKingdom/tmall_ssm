package com.ssm.tmall.mapper;

import com.ssm.tmall.pojo.PropertyValue;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PropertyValueMapper {
    PropertyValue get(@Param("pid") int pid, @Param("ptid") int ptid);

    void update(PropertyValue propertyValue);

    List<PropertyValue> list(@Param("pid") int pid);

    void add(@Param("pid") Integer pid, @Param("ptid") Integer ptid);

    List<Integer> listwithint(@Param("pid") int pid);

    void delete(@Param("pid") int pid, @Param("ptid") int ptid);
}
