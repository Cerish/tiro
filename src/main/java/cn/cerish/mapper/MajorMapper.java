package cn.cerish.mapper;

import cn.cerish.entity.Major;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MajorMapper {

    // 根据 Id 查找专业
    public Major getMajorById(Integer id);
    // 根据学院查找id
    public List<Major> getMajorByCollegeId(Integer id);
    // 根据 条件获取 专业(分页)
    public List<Major> getMajorByPage(@Param("page") Integer page,
                                      @Param("size") Integer size,
                                      @Param("major") Major major);
    // 根据 条件获取 total
    public long getTotal(@Param("major") Major major);

    // 根据id 删除一个专业
    public int deleteMajorById(Integer id);

    // 根据id 更新一个专业信息
    public int updateMajorById(Major major);

    // 添加一个专业
    public int addMajor(Major major);
}
