package cn.cerish.mapper;

import cn.cerish.entity.Course;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CourseMapper {
    // 查找所有的课程
    public List<Course> getAllCourse(String keywords);
    // 根据 Id 查找课程
    public Course getCourseById(Integer id);

    // 根据 条件获取 课程
    public List<Course> getCourseByPage(@Param("page") Integer page,@Param("size") Integer size,
                                        @Param("course") Course course);

    // 根据 条件获取 total
    public long getTotal(@Param("course") Course course);

    // 根据id 删除一个课程
    public int deleteCourseById(Integer id);

    // 根据id 更新一个课程信息
    public int updateCourseById(Course course);

    // 添加一个课程
    public int addCourse(Course course);
}
