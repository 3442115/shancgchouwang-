package com.atguigu.sbm.service.serviceIml;

import com.atguigu.sbm.entity.Emple;
import com.atguigu.sbm.entity.EmpleExample;
import com.atguigu.sbm.mapper.EmpleMapper;
import com.atguigu.sbm.service.EmpleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpleServiceIml implements EmpleService {
   @Autowired
   private EmpleMapper empleMapper;

    public long countByExample(EmpleExample example) {
        return 0;
    }

    public int deleteByExample(EmpleExample example) {
        return 0;
    }

    public int deleteByPrimaryKey(Integer empId) {
        return 0;
    }

    public int insert(Emple record) {
        return 0;
    }

    public int insertSelective(Emple record) {
        return 0;
    }

    public List<Emple> selectByExample(EmpleExample example) {
        return null;
    }

    public Emple selectByPrimaryKey(Integer empId) {
        return empleMapper.selectByPrimaryKey(empId);
    }

    public int updateByExampleSelective(Emple record, EmpleExample example) {
        return 0;
    }

    public int updateByExample(Emple record, EmpleExample example) {
        return 0;
    }

    public int updateByPrimaryKeySelective(Emple record) {
        return 0;
    }

    public int updateByPrimaryKey(Emple record) {
        return 0;
    }
}
