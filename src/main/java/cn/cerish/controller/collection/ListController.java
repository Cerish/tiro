package cn.cerish.controller.collection;

import cn.cerish.annotation.SerializedField;
import cn.cerish.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/collection")
public class ListController {
    @GetMapping("/list")
    @SerializedField(includes = {"username", "id"}, excludes = {"password"})
    public User getList() {
        User user = new User();
        user.setPassword("123");
        user.setUsername("jack");
        user.setId(10);
        System.out.println(user);
        return user;
    }
}
