package cn.cerish.controller.system;

import cn.cerish.entity.MyUserDetail;
import cn.cerish.service.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/system/basic")
public class MyUserDetailController {
    @Autowired
    private MyUserDetailService myUserDetailService;

    @GetMapping("/")
    public MyUserDetail getUserDetail(Integer userId,Integer roleId) {
        return myUserDetailService.getUserDetail(userId, roleId);
    }
}
