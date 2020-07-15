package cn.cerish.controller.auth;

import cn.cerish.entity.Response;
import cn.cerish.entity.User;
import cn.cerish.service.AuthServiceImpl;
import cn.cerish.service.UserService;
import cn.cerish.util.JwtUtil;
import cn.cerish.util.KaptchaUtil;
import cn.cerish.util.ResponseUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/auth")
@RestController
@Api(value = "登录模块 API")
@Component
public class AuthController {

    @Autowired
    private AuthServiceImpl authService;
    @Autowired
    private KaptchaUtil kaptchaUtil;

    @ApiOperation(value = "登录")
    @PostMapping("/signin")
    public void signin(
            @RequestParam String password,
            @RequestParam String username,
            HttpServletResponse resp) {

    }

    @PostMapping("/signup")
    public Response signup(@RequestParam("code") String code,
                            @RequestBody User user,
                           HttpServletRequest req,
                           HttpServletResponse res) {

        Boolean IsCode = kaptchaUtil.checkVerificationCode(code, req);
        if(!IsCode) {
            res.setStatus(404);
            return ResponseUtils.error(404, "验证码错误！");
        }

        Response signup = authService.signup(user);
        return signup;
    }

    @GetMapping("/test")
    public boolean signup(String code,
                           HttpServletRequest req) {
        return kaptchaUtil.checkVerificationCode(code, req);
    }
}
