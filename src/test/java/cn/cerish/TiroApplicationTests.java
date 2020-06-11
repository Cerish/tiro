package cn.cerish;

import cn.cerish.util.JwtUtil;
import cn.cerish.util.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootTest
class TiroApplicationTests {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private JwtUtil jwtUtil;

    @Test
    void contextLoads() {
        redisUtil.set("user", "jack");
        redisUtil.set("name", "李华");

        System.out.println(redisUtil.get("user"));
        System.out.println(redisUtil.get("name"));
    }

    @Test
    void generateToken() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "jack");
        String s = jwtUtil.generateToken(map);
        System.out.println(s);

        System.out.println("获取数据" + jwtUtil.getClaimsFromToken(s));
        System.out.println("过期时间" + jwtUtil.getExpirationDateFromToken(s));
        System.out.println("是否过期" + jwtUtil.isTokenExpired(s));
        System.out.println("是否有效" + jwtUtil.validateToken(s));
        System.out.println("是否有效" + jwtUtil.validateToken(
                "eyJhbGciOiJIUzI1NiJ9.eyJuYmYiOjE1OTE2OTUxOTMsIm5hbWUiOiJqYWNrIiwiZXhwIjoxNTkxNzAyMzkzLCJpYXQiOjE1OTE2OTUxdjijdoOTN9.ghtmfe6-mEwlskdpspz04lCD1oRIL03CUZB99U4MuFyMjvrp90\n"
        ));
    }

    @Test
    void testThread() {
        ExecutorService service = Executors.newCachedThreadPool(); //创建一个线程池
        final CountDownLatch cdOrder = new CountDownLatch(1);//指挥官的命令，设置为1，指挥官一下达命令，则cutDown,变为0，战士们执行任务

        final CountDownLatch cdAnswer = new CountDownLatch(3);//因为有三个战士，所以初始值为3，每一个战士执行任务完毕则cutDown一次，当三个都执行完毕，变为0，则指挥官停止等待。
        for (int i = 0; i < 3; i++) {
            Runnable runnable = new Runnable() {

                public void run() {
                    try {
                        System.out.println("线程" + Thread.currentThread().getName() + "正准备接受命令");
                        cdOrder.await(); //战士们都处于等待命令状态
                        System.out.println("线程" + Thread.currentThread().getName() + "已接受命令");
                        Thread.sleep((long) (Math.random() * 10000));
                        System.out.println("线程" + Thread.currentThread().getName() + "回应命令处理结果");

                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        cdAnswer.countDown(); //任务执行完毕，返回给指挥官，cdAnswer减1。
                    }
                }

            };
            service.execute(runnable);//为线程池添加任务
        }
        try {
            Thread.sleep((long) (Math.random() * 10000));
            System.out.println("线程" + Thread.currentThread().getName() + "即将发布命令");
            cdOrder.countDown(); //发送命令，cdOrder减1，处于等待的战士们停止等待转去执行任务。
            System.out.println("线程" + Thread.currentThread().getName() + "已发送命令，正在等待结果");
            cdAnswer.await(); //命令发送后指挥官处于等待状态，一旦cdAnswer为0时停止等待继续往下执行
            System.out.println("线程" + Thread.currentThread().getName() +
                    "已收到所有响应结果");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
        service.shutdown(); //任务结束，停止线程池的所有线程
    }


}
