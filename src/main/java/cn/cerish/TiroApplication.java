package cn.cerish;

import cn.cerish.entity.FileProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@MapperScan("cn.cerish.mapper")
@EnableConfigurationProperties(FileProperties.class)
public class TiroApplication {

    public static void main(String[] args) {
        SpringApplication.run(TiroApplication.class, args);
    }

}
