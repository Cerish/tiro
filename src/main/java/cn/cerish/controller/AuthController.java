package cn.cerish.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @PostMapping(value = "/login", headers = "content-type=multipart/form-data")
    public void signin( String username,
                        String password,
                        Integer roleType) {

    }
}
