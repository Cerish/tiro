package cn.cerish.controller;


import cn.cerish.entity.Admin;
import cn.cerish.entity.ResPageBean;
import cn.cerish.entity.RespBean;
import cn.cerish.entity.Admin;
import cn.cerish.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

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
}
