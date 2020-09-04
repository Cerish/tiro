package cn.cerish.controller;

import cn.cerish.annotation.SerializedField;
import cn.cerish.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/detail")
public class UserDetailController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private VisitorService visitorService;

    @GetMapping("/")
    @SerializedField(excludes = {"password"})
    public UserDetails getUserDetail(Integer userId, Integer roleId) {
        if(roleId == 1) {
            return adminService.getAdminById(userId);
        }else if(roleId == 2) {
            return teacherService.getTeacherById(userId);
        }else if(roleId == 4) {
            return studentService.getStudentById(userId);
        }else if(roleId == 5) {
            return visitorService.getVisitorById(userId);
        }
        return null;
    }
}
