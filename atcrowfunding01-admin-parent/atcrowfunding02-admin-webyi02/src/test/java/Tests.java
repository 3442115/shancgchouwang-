

import com.atguigu.crowd.entity.Admin;
import com.atguigu.crowd.mapper.AdminMapper;
import com.atguigu.crowd.service.AdminService;
import com.atguigu.crowd.service.serviceIml.AdminServiceIml;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author zzk
 * @create 2020-03-23 22:22
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-persist-mybatis.xml","classpath:spring-persist-tx.xml"})
public class Tests {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private AdminMapper adminMapper;

    @Resource
    private AdminServiceIml adminService;
    @Test
    public void insertInfoTransaction(){
        Admin admin = new Admin(null, "jerry", "123", "杰瑞", "tom@qq.com", "2020/10/18");
        adminService.saveInfo(admin);
    }
    //测试数据库连通性
    @Test
    public void testConnection() throws SQLException {
        System.out.println(dataSource.getConnection());
    }
    @Test
    public void insertInfo(){
        Admin admin = new Admin(5, "tom", "123", "汤姆", "tom@qq.com", "2020/10/18");
        System.out.println(adminMapper.insert(admin));
    }
    @Test
    public void getLog(){
        //获得日志对象
        Logger logger = LoggerFactory.getLogger(Tests.class);
       //根据不同的级别 来打印日志
        logger.info("Hello A");
        logger.info("Hello B");

        logger.debug("Hello C");
        logger.debug("Hello D");
    }
}
