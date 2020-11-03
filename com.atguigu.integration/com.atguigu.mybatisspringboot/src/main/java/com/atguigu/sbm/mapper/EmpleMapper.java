package com.atguigu.sbm.mapper;


import java.util.List;

import com.atguigu.sbm.entity.Emple;
import com.atguigu.sbm.entity.EmpleExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


public interface EmpleMapper {
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