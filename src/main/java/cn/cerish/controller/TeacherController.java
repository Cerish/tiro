package cn.cerish.controller;

import cn.cerish.entity.*;
import cn.cerish.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;


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
    public RespBean updateTeacherById(@RequestBody Teacher teacher) {
        if (teacherService.updateTeacherById(teacher) == 1) {
            return RespBean.success("教师更新成功!");
        }
        return RespBean.error("教师更新失败!");
    }

}
