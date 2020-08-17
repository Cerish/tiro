package cn.cerish.controller;

import cn.cerish.entity.ResPageBean;
import cn.cerish.entity.RespBean;
import cn.cerish.entity.Student;
import cn.cerish.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;


    @GetMapping("/")
    public ResPageBean getStudentByPage(@RequestParam Integer page,
                                      @RequestParam Integer size,
                                        Student student) {
        return studentService.getStudentByPage(page, size, student);
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Integer id) {
        return studentService.getStudentById(id);
    }

    @GetMapping("/class/{classid}")
    public List<Student> getStudentByClassId(@PathVariable Integer classid) {
        return studentService.getStudentByClassId(classid);
    }


    @PostMapping("/")
    public RespBean addStudent(@RequestBody Student student) {
        if (studentService.addStudent(student) == 1) {
            return RespBean.success("学生添加成功!");
        }
        return RespBean.error("学生添加失败!");
    }

    @DeleteMapping("/{id}")
    public RespBean deleteStudentById(@PathVariable Integer id) {
        if (studentService.deleteStudentById(id) == 1) {
            return RespBean.success("学生删除成功!");
        }
        return RespBean.error("学生删除失败!");
    }
    @PutMapping("/")
    public RespBean updateStudentById(@RequestBody Student student) {
        if (studentService.updateStudentById(student) == 1) {
            return RespBean.success("学生更新成功!");
        }
        return RespBean.error("学生更新失败!");
    }

}
