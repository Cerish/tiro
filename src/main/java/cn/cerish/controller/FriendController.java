package cn.cerish.controller;

import cn.cerish.entity.Course;
import cn.cerish.entity.Friend;
import cn.cerish.entity.ResPageBean;
import cn.cerish.service.FriendService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;


@RestController
@RequestMapping("/user/friend")
public class FriendController {
    @Autowired
    private FriendService friendService;

    @GetMapping("/")
    public ResPageBean getFriends(Friend friend) throws ExecutionException, InterruptedException {
        return friendService.getFriends(friend);
    }
}
