package cn.cerish.filter;

import cn.cerish.entity.User;
import cn.cerish.service.UserService;
import cn.cerish.util.JwtUtil;
import cn.cerish.util.RedisUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class AdminAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserService userService;
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication auth) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        response.setStatus(201);
        User userDetails = (User)auth.getPrincipal();
        String token = jwtUtil.generateToken(userDetails);
        response.setHeader("Authorzation", token);

        // 登录成功后， 把token设置在 redis缓存中 (默认 15 天)
        redisUtil.set(userDetails.getUsername(), userDetails, 60 * 60 * 24 * 15);

    }
}
