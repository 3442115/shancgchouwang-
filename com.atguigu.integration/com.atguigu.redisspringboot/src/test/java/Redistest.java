import com.atguigu.rsb.MainRedis;
import com.atguigu.rsb.RedisCautil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;


@SpringBootTest(classes = MainRedis.class)
public class Redistest {
    @Resource
    private RedisTemplate redisCacheUtil;

    @Test
    public void  get(){
new RedisCautil().get().afterPropertiesSet();
    }
}
