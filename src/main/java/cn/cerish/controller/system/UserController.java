package cn.cerish.controller.system;

import cn.cerish.entity.User;
import cn.cerish.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/system/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/")
    public List<User> getAllUsers(String keywords, Authentication authentication) {
        return userService.getAllHrs(keywords);
    }
}
