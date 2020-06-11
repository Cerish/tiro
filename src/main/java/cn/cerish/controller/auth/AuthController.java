package cn.cerish.controller.auth;

import cn.cerish.entity.User;
import cn.cerish.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class AuthController {
    @Autowired
    private UserService userService;

    @GetMapping("/findAll")
    public List<User> findAll(){
        return userService.findALl();
    }

    @GetMapping("/{id}")
    public User findUserById(@PathVariable("id") int id) {
        return userService.findUserById(id);
    }
}
