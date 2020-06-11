package cn.cerish.service;

import cn.cerish.entity.User;
import cn.cerish.mapper.UserMapper;
import cn.cerish.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisUtil redisUtil;

    public List<User> findALl() {
        return userMapper.findAll();
    }

    public User findUserById(int id) {
        String key = "user_" + id;

        boolean hasKey = redisUtil.hasKey(key);

        if (hasKey) {
            User user = (User) redisUtil.get(key);

            return user;
        } else {
            User user = userMapper.findUserById(id);
            // 写入缓存
            redisUtil.set(key, user);
            redisUtil.set("user11", "我是中文");
            return user;
        }
    }
}
