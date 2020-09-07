package cn.cerish.controller;

import cn.cerish.entity.*;
import cn.cerish.service.FileService;
import cn.cerish.service.TeacherService;
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
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private FileService fileService;
    @Autowired
    private UserServiceUtils userServiceUtils;
    // 加密方式
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }



    @GetMapping("/")
    public ResPageBean getClassByPage(Integer page,
                                      Integer size,
                                      Teacher teacher) {
        return teacherService.getTeacherByPage(page, size, teacher);
    }

    @GetMapping("/{id}")
    public Teacher getTeacherById(@PathVariable Integer id) {
        return teacherService.getTeacherById(id);
    }

    @GetMapping("/college/{collegeId}")
    public List<Teacher> getTeacherByCollegeId(@PathVariable Integer collegeId) {
        return teacherService.getTeacherByCollegeId(collegeId);
    }
    @PostMapping("/")
    public RespBean addTeacherdClass(@RequestBody Teacher teacher) {
        if (teacherService.addTeacher(teacher) == 1) {
            return RespBean.success("教师添加成功!");
        }
        return RespBean.error("教师添加失败!");
    }

    @DeleteMapping("/{id}")
    public RespBean deleteTeacherById(@PathVariable Integer id) {
        if (teacherService.deleteTeacherById(id) == 1) {
            return RespBean.success("教师删除成功!");
        }
        return RespBean.error("教师删除失败!");
    }
    @PutMapping("/")
    public RespBean updateTeacherById(Teacher teacher,
                                      MultipartFile file,
                                      Authentication authentication) {
        Teacher principal = (Teacher) authentication.getPrincipal();

        if(file != null) {
            String path = fileService.storeAvatarFile(file);
            String uri = userServiceUtils.generateUri(path);
            // 先删除原来的头像
            String userface = principal.getUserface();
            if(userface != null) {
                userServiceUtils.delAvatar(userface);
            }
            teacher.setUserface(uri);
        }
        teacher.setEnabled(true);
        if (teacherService.updateTeacherById(teacher) == 1) {
            return RespBean.success("教师更新成功!");
        }
        return RespBean.error("教师更新失败!");
    }

    @PutMapping("/changePwd")
    public RespBean updateVisitorPassword(@RequestBody Map<String, String> map) {

        Teacher teacher= teacherService.getTeacherById(Integer.parseInt(map.get("userId")));
        boolean isValid = passwordEncoder().matches(map.get("oldPassword"), teacher.getPassword());
        // 验证密码
        if (!isValid) {
            return RespBean.error("原密码错误!");
        }
        teacher.setPassword(passwordEncoder().encode(map.get("newPassword")));
        if (teacherService.updateTeacherById(teacher) == 1) {
            return RespBean.success("密码更新成功!");
        }
        return RespBean.error("密码更新失败!");
    }
}
