package cn.cerish.mapper;

import cn.cerish.entity.Class;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ClassMapper {
    // 查找所有的班级
//    public List<Class> getAllClass(String keywords);

    // 根据 Id 查找班级
    public Class getClassById(Integer id);
    // 根据 majorId 查班级
    public List<Class> getClassByMajorId(Integer id);
    // 根据 collegeId 查班级
    public List<Class> getClassByCollegeId(Integer id);

    // 根据 条件获取 total
    public List<Class> getClassByPage(@Param("page") Integer page,
                                      @Param("size") Integer size,
                                      @Param("aclass") Class aclass);
    // 根据 条件获取 total
    public long getTotal(@Param("aclass") Class aclass);

    // 根据id 删除一个班级
    public int deleteClassById(Integer id);

    // 根据id 更新一个班级信息
    public int updateClassById(Class aclass);

    // 添加一个班级
    public int addClass(Class record);

}
