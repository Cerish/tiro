package cn.cerish;

import cn.cerish.entity.User;
import cn.cerish.util.JwtUtil;
import cn.cerish.util.RedisUtil;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.lang.reflect.Field;
import java.util.HashMap;


@SpringBootTest
class TiroApplicationTests {
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void testToken() throws NoSuchFieldException, IllegalAccessException {
        User user = new User();
        user.setId(10);
        user.setUsername("jack");
        user.setPassword("123");

        Field username = user.getClass().getDeclaredField("username");
        username.setAccessible(true);
        Object o = username.get(user);
        String name = username.getName();
        System.out.println(o.toString());
        System.out.println(name);

//        String token = jwtUtil.generateAccessToken(user.getUsername(), new HashMap<>());
//        System.out.println(token);
//
//        System.out.println("================= token 数据 =================");
//        System.out.println(jwtUtil.getClaimsFromToken(token));
//        System.out.println("================= token 是否过期 =================");
//        System.out.println(jwtUtil.isTokenExpired(token));
    }

    @Test
    public void testRedis() {
        System.out.println("testRedis");
    }
    @Test
    public void testEqual() {
        String a = new String("4");
        String b = a;
        System.out.println(b.equals("4"));
        System.out.println(b == "4");
    }

    @Test
    public void testLog() {
        Logger logger = Logger.getLogger(TiroApplicationTests.class);
        logger.trace("trace");
        logger.info("info");
        logger.error("error");

    }

    @Test
    public void generatePwd() {
        String encode = new BCryptPasswordEncoder().encode("123456Aa");
        boolean isValid = new BCryptPasswordEncoder().matches("88362216Hi", "$10$sZ6Q0l6Z74fi/vOCzGE.hu3d8kSxx6WHMDykWGMp6rYoUPdaUfm6m");
        System.out.println(isValid);
        System.out.println(encode);
    }
}
