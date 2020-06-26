package cn.cerish;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@MapperScan("cn.cerish.mapper")
@EnableCaching
@EnableConfigurationProperties
public class TiroApplication {

    public static void main(String[] args) {
        SpringApplication.run(TiroApplication.class, args);
    }

}
