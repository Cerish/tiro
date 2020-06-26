package cn.cerish.service;

import cn.cerish.entity.User;
import cn.cerish.filter.CusAuthenticationManager;
import cn.cerish.mapper.UserMapper;
import cn.cerish.util.JwtUtil;
import cn.cerish.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;


    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userMapper.loadUserByUsername(username);
        if(user == null) {
             throw new UsernameNotFoundException("没有找到该用户");
        }
        return user;
    }
}
