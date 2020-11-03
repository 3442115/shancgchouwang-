import com.atguigu.crowd.util.CrowdUtil;
import org.junit.Test;

public class StringTest {

    @Test
    public void getMd5(){
        String str="123456";
        System.out.println(CrowdUtil.md5(str));

    }
}
