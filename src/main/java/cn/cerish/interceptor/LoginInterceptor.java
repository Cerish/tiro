package cn.cerish.interceptor;

import cn.cerish.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request,
                      HttpServletResponse response, Object handler) throws Exception {

         //   被@RequestMapping注解修饰的controller方法就是HandlerMethod
        System.out.println(handler instanceof HandlerMethod);
         if(!(handler instanceof HandlerMethod)) return true;
         // 验证 token
         String token = request.getHeader("x-authorization");
         // 取不到 token 则不能继续访问
         if(token == null) return false;
         Boolean tokenExpired = jwtUtil.isTokenExpired(token.substring(5));
         if(!tokenExpired) return false;
         return true;
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
