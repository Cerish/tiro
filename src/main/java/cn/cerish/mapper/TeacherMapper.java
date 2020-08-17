package cn.cerish.mapper;


import cn.cerish.entity.Teacher;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TeacherMapper {
    // 根据名字查找教师
    public Teacher loadUserByUsername(String username);
    // 根据 Id 查找老师
    public Teacher getTeacherById(Integer id);

    // 根据 条件获取 total
    public List<Teacher> getTeacherByPage(@Param("page") Integer page, @Param("size") Integer size,
                                          @Param("teacher") Teacher teacher);
    // 根据 条件获取 total
    public long getTotal(@Param("teacher") Teacher teacher);

    // 根据id 删除一个老师
    public int deleteTeacherById(Integer id);

    // 根据id 更新一个老师信息
    public int updateTeacherById(Teacher teacher);

    // 添加一个老师
    public int addTeacher(Teacher teacher);

    public List<Teacher> getTeacherByCollegeId(Integer id);
}
