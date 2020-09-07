package cn.cerish;

import cn.cerish.entity.Friend;
import cn.cerish.mapper.FriendMapper;
import cn.cerish.service.FriendService;
import cn.cerish.util.JwtTokenUtils;
import cn.cerish.util.RedisUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.ExecutionException;


@SpringBootTest
class TiroApplicationTests {
    @Autowired
    private JwtTokenUtils jwtTokenUtils;

    @Autowired
    private RedisUtil redisUtil;


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
    
    @Autowired
    private FriendService friendService;
    @Autowired
    private FriendMapper friendMapper;
    
    @Test
    public void threadTest() throws ExecutionException, InterruptedException {
        Friend friend = new Friend();
        friend.setUserId(1);
        friend.setUserRoleId(1);

        List<Friend> friends = friendMapper.getFriends(friend);

    }


    @Test
    public void testMybatis() throws IOException {
        String resource = "classpath:/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Test
    public void testDel() {
        String s = "/img/avatar/17316649.jpg";
        File file = new File(s);
        file.delete();
    }

}
