package cn.cerish.filter.loginHandler;

import cn.cerish.entity.*;
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
import java.lang.Class;
import java.lang.reflect.Field;
import java.util.HashMap;

@Component
public class AdminAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private RedisUtil redisUtil;

    private Long redis_expire = 60*60*24*15L;

    public Object getField(String fieldName, Object object){
        Class<?> clazz = object.getClass();

        Field declaredField = null;
        try {
            declaredField = clazz.getDeclaredField(fieldName);
            declaredField.setAccessible(true);
            return declaredField.get(object);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication auth) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        response.setStatus(200);

        Object principal = auth.getPrincipal();
        Object username = getField("username", principal);
        Object userId = getField("id", principal);
        Object roleId = getField("roleId", principal);
        Object roleName =  getField("roleNameZh", principal);



        HashMap hashMap = new HashMap();
        hashMap.put("username", username);
        hashMap.put("userId", userId);
        hashMap.put("roleId", roleId);
        hashMap.put("roleName", roleName);

        HashMap jwtMap = new HashMap();
        jwtMap.putAll(hashMap);

        // 生成token 并将其存储在 redis 中
        String token = jwtUtil.generateAccessToken(username.toString(), jwtMap);
        response.setHeader("x-authorization", token);

        redisUtil.set(username.toString(), token, redis_expire);

        PrintWriter writer = response.getWriter();

        RespBean res = RespBean.success("登录成功", hashMap);
        writer.write(new ObjectMapper().writeValueAsString(res));
        writer.flush();
        writer.close();
    }
}
