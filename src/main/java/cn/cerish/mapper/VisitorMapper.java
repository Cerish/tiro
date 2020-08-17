package cn.cerish.mapper;

import cn.cerish.entity.Visitor;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VisitorMapper {
    // 根据 username 查找游客
    public Visitor loadUserByUsername(String username);
    // 根据 Id 查找游客
    public Visitor getVisitorById(Integer id);

    // 根据 条件获取 游客
    public List<Visitor> getVisitorByPage(@Param("page") Integer page,
                                          @Param("size") Integer size,
                                          @Param("visitor") Visitor visitor);
    // 根据 条件获取 total
    public long getTotal(@Param("visitor") Visitor visitor);

    // 根据id 删除一个游客
    public int deleteVisitorById(Integer id);

    // 根据id 更新一个游客信息
    public int updateVisitorById(Visitor visitor);

    // 添加一个游客
    public int addVisitor(Visitor visitor);
}
