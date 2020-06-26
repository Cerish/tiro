package cn.cerish.service;

import cn.cerish.entity.User;
import cn.cerish.filter.CusAuthenticationManager;
import cn.cerish.mapper.UserMapper;
import cn.cerish.util.JwtUtil;
import cn.cerish.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthServiceImpl {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    CusAuthenticationManager authenticationManager;

    public List<User> findALl() {
        return userMapper.findAll();
    }

    public String login(String username, String password) {
        final Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));


        //存储认证信息
        SecurityContextHolder.getContext().setAuthentication(authentication);

        User userDetails = userMapper.loadUserByUsername( username );
        String token = jwtUtil.generateToken(userDetails);
        return token;

    }
}