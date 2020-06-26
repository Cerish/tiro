package cn.cerish.controller.auth;

import cn.cerish.entity.User;
import cn.cerish.service.AuthServiceImpl;
import cn.cerish.service.UserService;
import cn.cerish.util.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/auth")
@RestController
@Api(description = "登录模块 API")
@Component
public class AuthController {
    // @Autowired
    // private HpUser hpUser;
    // @Autowired
    // private HpUserMapper hpUserMapper;

    @Autowired
    private AuthServiceImpl authService;


    @ApiOperation(value = "登录")
    @PostMapping("/signin")
    public void signin(
            @RequestParam String password,
            @RequestParam String username,
            HttpServletResponse resp) {

    }
    @GetMapping("/hello")
    public String hello() {

        return "hello auth";
    }

}
