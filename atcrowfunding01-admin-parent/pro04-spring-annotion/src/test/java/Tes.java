import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;
import java.sql.SQLException;

public class Tes {
@Autowired
private DataSource dataSource;
@Autowired
private JdbcTemplate jdbcTemplate;

 @Test
    public void csa() throws SQLException {
     System.out.println(dataSource.getConnection());
 }

 @Test
    public void asd(){
     // 创建 BCryptPasswordEncoder对象
     BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
     // 准备明文字符串
     String str="123456";

     System.out.println(bCryptPasswordEncoder.encode(str));
 }

}
