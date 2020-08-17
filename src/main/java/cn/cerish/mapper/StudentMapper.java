package cn.cerish.mapper;

import cn.cerish.entity.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentMapper {
    // 根据 username 查找学生
    public Student loadUserByUsername(String username);
    // 根据 Id 查找学生
    public Student getStudentById(Integer id);

    // 根据 条件获取 total
    public List<Student> getStudentByPage(@Param("page") Integer page,
                                          @Param("size") Integer size,
                                          @Param("student") Student student);
    // 根据 条件获取 total
    public long getTotal(@Param("student") Student student);

    // 根据id 删除一个学生
    public int deleteStudentById(Integer id);

    // 根据id 更新一个学生信息
    public int updateStudentById(Student student);

    // 添加一个学生
    public int addStudent(Student student);
    // 根据 classId 查询学生
    public List<Student> getStudentByClassId(Integer classId);
}
