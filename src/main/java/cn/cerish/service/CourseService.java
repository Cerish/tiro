package cn.cerish.service;

import cn.cerish.entity.Course;
import cn.cerish.entity.ResPageBean;
import cn.cerish.mapper.CourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    @Autowired
    private CourseMapper courseMapper;

    public ResPageBean getCourseByPage(Integer page, Integer size, Course course) {
        if (page != null && size != null) {
            page = (page - 1) * size;
        }
        List<Course> data = courseMapper.getCourseByPage(page, size,course);
        Long total = courseMapper.getTotal(course);
        ResPageBean bean = new ResPageBean();
        bean.setData(data);
        bean.setTotal(total);
        return bean;
    }
    public Course getCourseById(Integer id) {
        return courseMapper.getCourseById(id);
    }

    public Integer deleteCourseById(Integer id) {
        return courseMapper.deleteCourseById(id);
    }

    public Integer updateCourseById(Course course) {
        return courseMapper.updateCourseById(course);
    }
    public Integer addCourse(Course course) {
        return courseMapper.addCourse(course);
    }
}
