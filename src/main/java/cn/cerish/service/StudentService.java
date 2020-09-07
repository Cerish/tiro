package cn.cerish.service;

import cn.cerish.entity.ResPageBean;
import cn.cerish.entity.Student;
import cn.cerish.entity.Teacher;
import cn.cerish.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class StudentService implements UserDetailsService {
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Student student = studentMapper.loadUserByUsername(username);
        if(student == null) {
            throw new UsernameNotFoundException("没有找到该学生");
        }
        return student;
    }
    public ResPageBean getStudentByPage(Integer page, Integer size, Student student) {
        if (page != null && size != null) {
            page = (page - 1) * size;
        }
        List<Student> data = studentMapper.getStudentByPage(page, size, student);
        Long total = studentMapper.getTotal(student);
        ResPageBean bean = new ResPageBean();
        bean.setData(data);
        bean.setTotal(total);
        return bean;
    }
    public Student getStudentById(Integer id) {
        return studentMapper.getStudentById(id);
    }

    public Integer deleteStudentById(Integer id) {
        return studentMapper.deleteStudentById(id);
    }

    public Integer updateStudentById(Student student) {
        return studentMapper.updateStudentById(student);
    }
    public Integer addStudent(Student student) {
        return studentMapper.addStudent(student);
    }

    public List<Student> getStudentByClassId(Integer classid) {
        return studentMapper.getStudentByClassId(classid);
    }
}
