package cn.cerish.config;

import cn.cerish.filter.JwtAuthorizationTokenFilter;
import cn.cerish.filter.loginHandler.AdminAuthenticationFailureHandler;
import cn.cerish.filter.loginHandler.AdminAuthenticationSuccessHandler;
import cn.cerish.filter.loginHandler.MyAuthenticationProvider;
import cn.cerish.service.*;
import cn.cerish.util.RedisUtil;
import cn.cerish.util.UserServiceUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private RedisUtil redisUtil;
    // 由于分表 产生的多个 Service
    @Autowired
    private AdminService adminService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private VisitorService visitorService;

    @Autowired
    private UserServiceUtils userServiceUtils;

    // 加入 token 验证
    @Autowired
    private JwtAuthorizationTokenFilter jwtAuthorizationTokenFilter;


    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        List<AuthenticationProvider> providerList = userServiceUtils.getProviderList();
        ProviderManager manager = new ProviderManager(providerList);
        return manager;
    }

    // 加密方式
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    // 成功失败的处理函数
    @Autowired
    private AdminAuthenticationSuccessHandler adminAuthenticationSuccessHandler;
    @Autowired
    private AdminAuthenticationFailureHandler adminAuthenticationFailureHandler;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/auth/**")
                .permitAll()
                .anyRequest().authenticated() //所以请求都要进行验证
                .and().formLogin().loginProcessingUrl("/auth/login")
                .successHandler(adminAuthenticationSuccessHandler)
                .failureHandler(adminAuthenticationFailureHandler)
                .and().logout().logoutUrl("/auth/logout")
                // 配置一个 LogoutHandler，开发者可以在这里完成一些数据清除工做
                .addLogoutHandler(new LogoutHandler() {
                    @Override
                    public void logout(HttpServletRequest request,
                                       HttpServletResponse response,
                                       Authentication auth) {
                        // 设置返回的 response 为空
                        response.setHeader("X-Authorzation", "");
                        // 清空 redis 和 auth
                        if(auth != null) {
                            String username = auth.getPrincipal().toString();
                            SecurityContext context = SecurityContextHolder.getContext();
                            context.setAuthentication(null);
                            redisUtil.del(username);
                        }
                    }
                })
                // 配置一个 LogoutSuccessHandler，开发者可以在这里处理注销成功后的业务逻辑
                .logoutSuccessHandler(new LogoutSuccessHandler() {
                    @Override
                    public void onLogoutSuccess(HttpServletRequest request,
                                                HttpServletResponse response,
                                                Authentication auth)
                            throws IOException, ServletException {
                        response.setContentType("application/json;charset=utf-8");
                        PrintWriter writer = response.getWriter();
                        response.setStatus(200);
                        Map<String, Object> map = new HashMap<>();
                        map.put("status", 200);
                        map.put("msg", "注销成功！");
                        writer.write(new ObjectMapper().writeValueAsString(map));
                        writer.flush();
                        writer.close();

                    }
                })
                .and().csrf().disable()
                // 禁用 session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);


        // 自定义过滤器认证用户名密码 每次请求都经过该过滤链
       http.addFilterAfter(jwtAuthorizationTokenFilter,  UsernamePasswordAuthenticationFilter.class);
    }

    // 放行web 静态资源 swagger
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
                "/img/avatar/**",
                "/v2/api-docs",
                "/swagger-resources",
                "/swagger-resources/**",
                "/configuration/ui",
                "/configuration/security",
                "/swagger-ui.html/**",
                "/webjars/**");
    }
}
