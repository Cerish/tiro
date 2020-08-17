package cn.cerish.controller;

import cn.cerish.entity.Role;
import cn.cerish.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping("/")
    public List<Role> getAllRole() {
        return roleService.getAllRole();
    }

    @GetMapping("/{id}")
    public Role getRoleById(Integer id) {
        return roleService.getRoleById(id);
    }
}
