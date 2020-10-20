import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.SQLException;

public class s {
    @Autowired
    private DataSource dataSource;
    public static void main(String[] args) throws SQLException {
        System.out.println(new s().dataSource.getConnection());
    }
}
