package cn.cerish.controller;

import cn.cerish.entity.ResPageBean;
import cn.cerish.entity.RespBean;
import cn.cerish.entity.Visitor;
import cn.cerish.service.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/visitor")
public class VisitorController {
    @Autowired
    private VisitorService visitorService;
    // 加密方式
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @GetMapping("/")
    public ResPageBean getVisitorByPage(@RequestParam Integer page,
                                        @RequestParam Integer size,
                                        Visitor visitor) {
        return visitorService.getVisitorByPage(page, size, visitor);
    }

    @GetMapping("/{id}")
    public Visitor getVisitorById(@PathVariable Integer id) {
        return visitorService.getVisitorById(id);
    }

    @PostMapping("/")
    public RespBean addVisitor(@RequestBody Visitor visitor) {
        if (visitorService.addVisitor(visitor) == 1) {
            return RespBean.success("游客添加成功!");
        }
        return RespBean.error("游客添加失败!");
    }

    @DeleteMapping("/{id}")
    public RespBean deleteVisitorById(@PathVariable Integer id) {
        if (visitorService.deleteVisitorById(id) == 1) {
            return RespBean.success("游客删除成功!");
        }
        return RespBean.error("游客删除失败!");
    }
    @PutMapping("/")
    public RespBean updateVisitorById(@RequestBody Visitor visitor) {
        if (visitorService.updateVisitorById(visitor) == 1) {
            return RespBean.success("游客更新成功!");
        }
        return RespBean.error("游客更新失败!");
    }
    @PutMapping("/changePwd")
    public RespBean updateVisitorPassword(@RequestBody Map<String, String> map) {

        Visitor visitor= visitorService.getVisitorById(Integer.parseInt(map.get("userId")));
        boolean isValid = passwordEncoder().matches(map.get("oldPassword"), visitor.getPassword());
        // 验证密码
        if (!isValid) {
            return RespBean.error("原密码错误!");
        }
        visitor.setPassword(passwordEncoder().encode(map.get("newPassword")));
        if (visitorService.updateVisitorById(visitor) == 1) {
            return RespBean.success("密码更新成功!");
        }
        return RespBean.error("密码更新失败!");
    }
}