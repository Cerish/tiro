package cn.cerish.filter.loginHandler;

import cn.cerish.entity.RespBean;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class AdminAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest,
                                        HttpServletResponse response,
                                        AuthenticationException e) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        response.setStatus(403);
        String msg = "";
        if (e instanceof UsernameNotFoundException || e instanceof BadCredentialsException) {
            msg = "用户名或密码错误！";
        } else if (e instanceof LockedException) {
            msg = "账户被锁定，请联系管理员!";
        } else if (e instanceof CredentialsExpiredException) {
            msg = "证书过期，请联系管理员!";
        } else if (e instanceof AccountExpiredException) {
            msg = "账户过期，请联系管理员!";
        } else if (e instanceof DisabledException) {
            msg ="账户被禁用，请联系管理员!";
        } else {
            msg = "登录失败!";
        }
        PrintWriter writer = response.getWriter();
        RespBean res = RespBean.error(403, msg);
        writer.write(new ObjectMapper().writeValueAsString(res));
        writer.flush();
        writer.close();
    }
}