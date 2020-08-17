package cn.cerish.mapper;

import cn.cerish.entity.Admin;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminMapper {
    // 根据 username 查找管理员
    public Admin loadUserByUsername(String username);
    // 根据 Id 查找管理员
    public Admin getAdminById(Integer id);

    // 根据 条件获取 管理员
    public List<Admin> getAdminByPage(@Param("page") Integer page,
                                          @Param("size") Integer size,
                                          @Param("admin") Admin admin);
    // 根据 条件获取 total
    public long getTotal(@Param("admin") Admin admin);

    // 根据id 删除一个管理员
    public int deleteAdminById(Integer id);

    // 根据id 更新一个管理员信息
    public int updateAdminById(Admin admin);

    // 添加一个管理员
    public int addAdmin(Admin admin);
}
