package cn.cerish.controller;


import cn.cerish.entity.*;
import cn.cerish.entity.Admin;
import cn.cerish.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    // 加密方式
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @GetMapping("/")
    public ResPageBean getAdminByPage(Integer page, Integer size,
                                      Admin admin) {
        return adminService.getAdminByPage(page, size, admin);
    }
    
    @GetMapping("/{id}")
    public Admin getAdminById(@PathVariable Integer id) {
        return adminService.getAdminById(id);
    }


    @PostMapping("/")
    public RespBean addAdmin(@RequestBody Admin admin) {
        if (adminService.addAdmin(admin) == 1) {
            return RespBean.success("学生添加成功!");
        }
        return RespBean.error("学生添加失败!");
    }

    @DeleteMapping("/{id}")
    public RespBean deleteAdminById(@PathVariable Integer id) {
        if (adminService.deleteAdminById(id) == 1) {
            return RespBean.success("学生删除成功!");
        }
        return RespBean.error("学生删除失败!");
    }
    @PutMapping("/")
    public RespBean updateAdminById(@RequestBody Admin admin) {
        if (adminService.updateAdminById(admin) == 1) {
            return RespBean.success("学生更新成功!");
        }
        return RespBean.error("学生更新失败!");
    }
    @PutMapping("/changePwd")
    public RespBean updateVisitorPassword(@RequestBody Map<String, String> map) {

        Admin admin= adminService.getAdminById(Integer.parseInt(map.get("userId")));
        boolean isValid = passwordEncoder().matches(map.get("oldPassword"), admin.getPassword());
        // 验证密码
        if (!isValid) {
            return RespBean.error("原密码错误!");
        }
        admin.setPassword(passwordEncoder().encode(map.get("newPassword")));
        if (adminService.updateAdminById(admin) == 1) {
            return RespBean.success("密码更新成功!");
        }
        return RespBean.error("密码更新失败!");
    }
}
