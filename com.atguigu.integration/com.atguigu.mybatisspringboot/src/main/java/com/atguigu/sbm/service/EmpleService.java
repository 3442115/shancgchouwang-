package com.atguigu.sbm.service;

import com.atguigu.sbm.entity.Emple;
import com.atguigu.sbm.entity.EmpleExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmpleService {
    long countByExample(EmpleExample example);

    int deleteByExample(EmpleExample example);

    int deleteByPrimaryKey(Integer empId);

    int insert(Emple record);

    int insertSelective(Emple record);

    List<Emple> selectByExample(EmpleExample example);

    Emple selectByPrimaryKey(Integer empId);

    int updateByExampleSelective(@Param("record") Emple record, @Param("example") EmpleExample example);

    int updateByExample(@Param("record") Emple record, @Param("example") EmpleExample example);

    int updateByPrimaryKeySelective(Emple record);

    int updateByPrimaryKey(Emple record);
}
