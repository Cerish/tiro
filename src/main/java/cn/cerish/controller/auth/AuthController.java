package cn.cerish.controller.auth;

import cn.cerish.entity.RespBean;
import cn.cerish.entity.User;
import cn.cerish.service.AuthServiceImpl;
import cn.cerish.util.KaptchaUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    @PostMapping("/login")
    public void signin(
            @RequestParam String password,
            @RequestParam String username,
            HttpServletResponse response) {

    }

    @PostMapping("/signup")
    public RespBean signup(@RequestBody Map<String, Object> map,
                           HttpServletRequest request,
                           HttpServletResponse response,
                           Authentication authentication) {

        Boolean IsCode = kaptchaUtil.checkVerificationCode(map.get("code").toString(), request);

        ObjectMapper mapper = new ObjectMapper();
        User user = mapper.convertValue(map.get("user"), new TypeReference<User>() { });
        Object role = map.get("role");
        if(!IsCode) {
            response.setStatus(404);
            return RespBean.error(404, "验证码错误！");
        }

//        Response signup = authService.signup(user);

        return null;
    }

}
