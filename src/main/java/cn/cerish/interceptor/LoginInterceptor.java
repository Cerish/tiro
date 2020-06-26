package cn.cerish.interceptor;

import cn.cerish.common.exception.JwtException;
import cn.cerish.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request,
                      HttpServletResponse response, Object handler) throws Exception {
        return true;

        // // 被@RequestMapping注解修饰的controller方法就是HandlerMethod
        // if(!(handler instanceof HandlerMethod)) return true;
        //
        // String token = request.getHeader("Authorzation");
        //
        // // 验证 token
        // Boolean tokenExpired = jwtUtil.isTokenExpired(token);
        //
        // return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // System.out.println("postHandle 执行");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // System.out.println("afterCompletion 执行");
    }
}
