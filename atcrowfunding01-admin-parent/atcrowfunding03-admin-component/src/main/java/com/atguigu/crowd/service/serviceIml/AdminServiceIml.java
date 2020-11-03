package com.atguigu.crowd.service.serviceIml;

import com.atguigu.crowd.constant.CrowdConstant;
import com.atguigu.crowd.entity.Admin;
import com.atguigu.crowd.entity.AdminExample;
import com.atguigu.crowd.exception.LoginFailException;
import com.atguigu.crowd.mapper.AdminMapper;
import com.atguigu.crowd.service.AdminService;
import com.atguigu.crowd.util.CrowdUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.Objects;

@Service
public class AdminServiceIml implements AdminService {

    @Autowired
    private AdminMapper adminMapper;
    @Override
    public void saveInfo(Admin admin) {
        adminMapper.insert(admin);
    }

    @Override
    public List<Admin> getAll() {
        return adminMapper.selectByExample(new AdminExample());
    }

    // 登陆验证
    @Override
    public Admin getAdminByAcctPswd(String loginAcct, String userPswd) {
        // 1.根据登录账号查询Admin对象
           //创建AdminExample对象

        AdminExample adminExample = new AdminExample();
        //创建Criteria 对象 封装查询条件
        AdminExample.Criteria criteria= adminExample.createCriteria();
        //在criteria封装查询条件
        criteria.andLoginAcctEqualTo(loginAcct);
        List<Admin> list = adminMapper.selectByExample(adminExample);

        if (list == null || list.size() == 0){
            throw new LoginFailException(CrowdConstant.MESSAGE_LOGIN_FAILED);
        }
        if (list.size()>1){
            throw new RuntimeException(CrowdConstant.MESSAGE_SYSTEM_ERROR_LOGIN_NOT_UNIQUE);
        }

        // 2.判断 Admin 对象是否为空
        // 3.如果为空Admin则抛出异常
        Admin admin = list.get(0);
        if (admin == null){
            throw new LoginFailException(CrowdConstant.MESSAGE_LOGIN_FAILED);
        }
        // 4.如果Admin对象不为空 则将数据库密码从admin对象中取出来
        String userPswdDB = admin.getUserPswd();
        // 5.将提交的密码 进行md5 加密
        String encoding = CrowdUtil.md5(userPswd);
        // 6.对密码进行比较
        if(!Objects.equals(encoding,userPswdDB)){
            // 7.如果密码不一致 抛出异常
            throw new LoginFailException(CrowdConstant.MESSAGE_LOGIN_FAILED);
        }
            // 8.相同返回Admin
            return admin;

    }

    // 分页查询
    @Override
    public PageInfo<Admin> getPageInfo(String keyword, Integer pageNum, Integer pageSize) {
        // 调用PageHelper的静态方法开启分页功能
        PageHelper.startPage(pageNum,pageSize);

        // 2执行查询
        List<Admin> list = adminMapper.selectAdminKeyword(keyword);

        return new PageInfo<>(list);
    }

    @Override
    public void remove(Integer adminId) {
        adminMapper.deleteByPrimaryKey(adminId);
    }

    @Override
    public Admin selectById(Integer integer) {
        return adminMapper.selectByPrimaryKey(integer);
    }

    @Override
    public void update(Admin admin) {
        adminMapper.updateByPrimaryKeySelective(admin);
    }

    @Override
    public void saveAssignAdmin(Integer adminId, List<Integer> roleIdList) {
      // 先删除所有的 然后重新保存
        adminMapper.deleteOldRelationShip(adminId);
        // 保存新的对应关系
        if(roleIdList != null&&roleIdList.size()>=0){

        adminMapper.saveNewRelationShip(adminId,roleIdList);

        }

    }

    @Override
    public Admin getAdminByLoginacct(String username) {
        AdminExample adminExample = new AdminExample();
        AdminExample.Criteria criteri = adminExample.createCriteria();
        criteri.andLoginAcctEqualTo(username);
        List<Admin> list = adminMapper.selectByExample(adminExample);
        Admin admin=list.get(0);
        System.out.println(admin.toString());
        return admin;
    }


}
