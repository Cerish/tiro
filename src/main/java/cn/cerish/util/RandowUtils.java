package cn.cerish.util;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class RandowUtils {

    // 默认生成8位
    public String generate() {
        return generate(8);
    }

    // 生成指定位数的数字字符串
    public String generate(int len) {
        String result = "";
        for(int i = 0; i < len; i++) {
            result += new Random().nextInt(10);
        }
        return result;
    }
}
