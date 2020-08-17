package cn.cerish.controller;

import cn.cerish.entity.Class;
import cn.cerish.entity.Course;
import cn.cerish.entity.ResPageBean;
import cn.cerish.entity.RespBean;
import cn.cerish.mapper.CourseMapper;
import cn.cerish.service.ClassService;
import cn.cerish.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseService courseService;


    @GetMapping("/")
    public ResPageBean getCourseByPage(Integer page,
                                       Integer size,
                                       Course course) {
        return courseService.getCourseByPage(page, size, course);
    }

    @GetMapping("/{id}")
    public Course getCourseById(@PathVariable Integer id) {
        return courseService.getCourseById(id);
    }

    @PostMapping("/")
    public RespBean addCourse(@RequestBody Course course) {
        if (courseService.addCourse(course) == 1) {
            return RespBean.success("课程添加成功!");
        }
        return RespBean.error("课程添加失败!");
    }

    @DeleteMapping("/{id}")
    public RespBean deleteCourseById(@PathVariable Integer id) {
        if (courseService.deleteCourseById(id) == 1) {
            return RespBean.success("课程删除成功!");
        }
        return RespBean.error("课程删除失败!");
    }
    @PutMapping("/")
    public RespBean updateCourseById(@RequestBody Course course) {
        if (courseService.updateCourseById(course) == 1) {
            return RespBean.success("课程更新成功!");
        }
        return RespBean.error("课程更新失败!");
    }

}
