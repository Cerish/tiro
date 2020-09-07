package cn.cerish.controller;

import cn.cerish.entity.*;
import cn.cerish.service.CourseService;
import cn.cerish.service.FileService;
import cn.cerish.service.ScoreService;
import cn.cerish.service.StudentService;
import cn.cerish.util.UserServiceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private ScoreService scoreService;
    @Autowired
    private FileService fileService;
    @Autowired
    private UserServiceUtils userServiceUtils;

    // 加密方式
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @GetMapping("/course")
    public ResPageBean getCourseByPage(@RequestParam Integer page,
                                       @RequestParam Integer size,
                                       Course course,
                                       Authentication authentication) {
        Student student = (Student) authentication.getPrincipal();
        course.setClassId(student.getClassId());
        return courseService.getCourseByPage(page, size, course);
    }

    @GetMapping("/score")
    public ResPageBean getScoreByPage(Integer page,
                                      Integer size,
                                      Score score,
                                      Authentication authentication) {
        Student student = (Student) authentication.getPrincipal();
        score.setStudentNo(student.getStudentNo());
        return scoreService.getScoreByPage(page, size, score);
    }
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
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
    @PutMapping("/")
    public RespBean updateStudentById(Student student,
                                      MultipartFile file,
                                      Authentication authentication) {
        Student principal = (Student) authentication.getPrincipal();

        if(file != null) {
            String path = fileService.storeAvatarFile(file);
            String uri = userServiceUtils.generateUri(path);
            // 先删除原来的头像
            String userface = principal.getUserface();
            if(userface != null) {
                userServiceUtils.delAvatar(userface);
            }
            student.setUserface(uri);
        }
        student.setEnabled(true);
        if (studentService.updateStudentById(student) == 1) {
            return RespBean.success("学生更新成功!");
        }
        return RespBean.error("学生更新失败!");
    }
    @PutMapping("/changePwd")
    public RespBean updateVisitorPassword(@RequestBody Map<String, String> map) {

        Student student= studentService.getStudentById(Integer.parseInt(map.get("userId")));
        boolean isValid = passwordEncoder().matches(map.get("oldPassword"), student.getPassword());
        // 验证密码
        if (!isValid) {
            return RespBean.error("原密码错误!");
        }
        student.setPassword(passwordEncoder().encode(map.get("newPassword")));
        if (studentService.updateStudentById(student) == 1) {
            return RespBean.success("密码更新成功!");
        }
        return RespBean.error("密码更新失败!");
    }

}
