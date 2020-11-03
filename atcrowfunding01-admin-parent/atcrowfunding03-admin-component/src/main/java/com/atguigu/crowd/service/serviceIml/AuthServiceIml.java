package com.atguigu.crowd.service.serviceIml;

import com.atguigu.crowd.entity.Auth;
import com.atguigu.crowd.entity.AuthExample;
import com.atguigu.crowd.mapper.AuthMapper;
import com.atguigu.crowd.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class AuthServiceIml implements AuthService {

    @Autowired
    private AuthMapper authMapper;




    @Override
    public List<Auth> getAll() {
       return authMapper.selectByExample(new AuthExample());
    }

    @Override
    public List<Integer> getAssignedAuthIdByRoleId(Integer roleId) {
        return authMapper.selectAssignedAuthIdByRoleId(roleId);
    }

    @Override
    public void saveRoleAuthRelationship(Map<String, List<Integer>> map) {
        List<Integer> roleIdList = map.get("roleId");
        Integer roleId = roleIdList.get(0);
        // 删除 旧的
        authMapper.deleteOldRelationship(roleId);

        List<Integer> authIdList = map.get("authIdArray");
        if(authIdList !=null && authIdList.size()>0){
            authMapper.insertNewRelationship(roleId,authIdList);

        }
    }

    @Override
    public List<String> getAssignedAuthNameByadminId(Integer admiId) {
        return authMapper.selectAssignedAuthNameByadminId(admiId);
    }
}

