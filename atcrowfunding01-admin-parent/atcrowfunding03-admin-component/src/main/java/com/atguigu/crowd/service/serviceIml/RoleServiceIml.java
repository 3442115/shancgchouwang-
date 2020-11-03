package com.atguigu.crowd.service.serviceIml;

import com.atguigu.crowd.entity.Role;
import com.atguigu.crowd.entity.RoleExample;
import com.atguigu.crowd.mapper.RoleMapper;
import com.atguigu.crowd.service.RoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceIml implements RoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public PageInfo<Role> getPageInfo(Integer pageNum, Integer pageSize, String keyword) {
        PageHelper.startPage(pageNum,pageSize);

        List<Role> roles = roleMapper.selectByKeyword(keyword);

        return new PageInfo<>(roles);
    }

    @Override
    public void saveRole(Role role) {
        roleMapper.insert(role);
    }

    @Override
    public void updateInfo(Role role) {
        roleMapper.updateByPrimaryKey(role);
    }

    @Override
    public void deleteInfo(List<Integer> list) {
        RoleExample roleExample = new RoleExample();

        RoleExample.Criteria criteria = roleExample.createCriteria();

        criteria.andIdIn(list);
        roleMapper.deleteByExample(roleExample);
    }

    @Override
    public List<Role> getAssignRole(Integer id) {

        return  roleMapper.selectAssignRole(id);
    }

    @Override
    public List<Role> getUnAssignRole(Integer id) {
        return  roleMapper.selectUnAssignRole(id);
    }
}
