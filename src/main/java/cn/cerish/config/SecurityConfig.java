package cn.cerish.config;

import cn.cerish.filter.AdminAuthenticationProcessingFilter;
import cn.cerish.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    // 加密方式
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private UserService userService;


    /**
     * 用户密码校验过滤器
     */
    private final AdminAuthenticationProcessingFilter adminAuthenticationProcessingFilter;

    public SecurityConfig(AdminAuthenticationProcessingFilter adminAuthenticationProcessingFilter) {
        this.adminAuthenticationProcessingFilter = adminAuthenticationProcessingFilter;
    }

    // 自定义验证规则
    // @Override
    // protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    //     auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    // }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/**").permitAll()  // 放开登录模块的验证
                // .anyRequest().authenticated()
                .and().csrf().disable();  //所以请求都要进行验证


        // 自定义过滤器认证用户名密码
        http.addFilterAt(adminAuthenticationProcessingFilter, UsernamePasswordAuthenticationFilter.class);
    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        // web
        //     .ignoring()
        //     .antMatchers(
        //             "/csrf",
        //             "swagger-ui.html",
        //             "**/swagger-ui.html",
        //             "/favicon.ico",
        //             "/**/*.css",
        //             "/**/*.js",
        //             "/**/*.png",
        //             "/**/*.gif",
        //             "/v2/**",
        //             "/**/*.ttf"
        //     );
        //放行swagger
        web.ignoring().antMatchers(
                "/v2/api-docs",
                "/swagger-resources",
                "/swagger-resources/**",
                "/configuration/ui",
                "/configuration/security",
                "/swagger-ui.html/**",
                "/webjars/**");
    }
}
