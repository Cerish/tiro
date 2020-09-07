package cn.cerish.controller;


import cn.cerish.entity.*;
import cn.cerish.entity.Admin;
import cn.cerish.service.AdminService;
import cn.cerish.service.FileService;
import cn.cerish.util.UserServiceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private FileService fileService;
    @Autowired
    private UserServiceUtils userServiceUtils;
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
    public RespBean updateAdminById(Admin admin,
                                    MultipartFile file,
                                    Authentication authentication) {
        Admin principal = (Admin) authentication.getPrincipal();

        if(file != null) {
            String path = fileService.storeAvatarFile(file);
            String uri = userServiceUtils.generateUri(path);
            // 先删除原来的头像
            String userface = principal.getUserface();
            if(userface != null) {
                userServiceUtils.delAvatar(userface);
            }
            admin.setUserface(uri);
        }
        admin.setEnabled(true);
        if (adminService.updateAdminById(admin) == 1) {
            return RespBean.success("管理员更新成功!");
        }
        return RespBean.error("管理员更新失败!");
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
