package cn.cerish.service;

import cn.cerish.entity.ResPageBean;
import cn.cerish.entity.Teacher;
import cn.cerish.mapper.TeacherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class TeacherService implements UserDetailsService {
    @Autowired
    private TeacherMapper teacherMapper;
    @Autowired
    private HttpServletRequest request;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Teacher teacher = teacherMapper.loadUserByUsername(username);
        if(teacher == null) {
            throw new UsernameNotFoundException("没有找到该教师");
        }
        String roleType = request.getParameter("roleType");
        if(!roleType.equals(teacher.getRoleId() + "")) {
            throw new UsernameNotFoundException("你走错片场啦，不是你这个角色该来的地方");
        }
        return teacher;
    }

    public ResPageBean getTeacherByPage(Integer page, Integer size, Teacher teacher) {
        if (page != null && size != null) {
            page = (page - 1) * size;
        }
        List<Teacher> data = teacherMapper.getTeacherByPage(page, size, teacher);
        Long total = teacherMapper.getTotal(teacher);
        ResPageBean bean = new ResPageBean();
        bean.setData(data);
        bean.setTotal(total);
        return bean;
    }
    public Teacher getTeacherById(Integer id) {
        return teacherMapper.getTeacherById(id);
}

    public List<Teacher> getTeacherByCollegeId(Integer id) {
        return teacherMapper.getTeacherByCollegeId(id);
    }

    public Integer deleteTeacherById(Integer id) {
        return teacherMapper.deleteTeacherById(id);
    }

    public Integer updateTeacherById(Teacher teacher) {
        return teacherMapper.updateTeacherById(teacher);
    }
    public Integer addTeacher(Teacher teacher) {
        return teacherMapper.addTeacher(teacher);
    }
}
