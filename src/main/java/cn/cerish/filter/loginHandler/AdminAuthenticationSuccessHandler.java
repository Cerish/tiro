package cn.cerish.filter.loginHandler;

import cn.cerish.entity.Response;
import cn.cerish.entity.User;
import cn.cerish.service.UserService;
import cn.cerish.util.JwtUtil;
import cn.cerish.util.RedisUtil;
import cn.cerish.util.ResponseUtils;
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
import java.util.HashMap;
import java.util.Map;

@Component
public class AdminAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication auth) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        response.setStatus(200);

        User user = (User)auth.getPrincipal();

        String token = jwtUtil.generateToken(user);
//        response.setHeader("X-Authorzation", token);


        // obj 写入对象
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("token", token);
        map.put("userInfo", user);
        Response result = ResponseUtils.success(map);
        ResponseUtils.writeJson(response, result);
    }
}
