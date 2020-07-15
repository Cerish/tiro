package cn.cerish;

import cn.cerish.annotation.SerializedField;
import cn.cerish.entity.User;
import cn.cerish.util.JwtUtil;
import cn.cerish.util.RedisUtil;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileNotFoundException;


@SpringBootTest
class TiroApplicationTests {
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void testToken() {
        User user = new User();
        user.setId(10);
        user.setUsername("jack");
        user.setPassword("123");

        String token = jwtUtil.generateToken(user);
        System.out.println(token);

        System.out.println("================= token 数据 =================");
        System.out.println(jwtUtil.getClaimsFromToken(token));
        System.out.println("================= token 是否过期 =================");
        System.out.println(jwtUtil.isTokenExpired(token));
    }

    @Test
    public void testRedis() {
        System.out.println("testRedis");
    }

    @Test
    public void testLog() {
        Logger logger = Logger.getLogger(TiroApplicationTests.class);
        logger.trace("trace");
        logger.info("info");
        logger.error("error");

    }



}
