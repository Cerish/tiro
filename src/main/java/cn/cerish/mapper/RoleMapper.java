package cn.cerish.mapper;

import cn.cerish.entity.Role;

import java.util.List;

public interface RoleMapper {
    public List<Role> getAllRole();

    public Role getRoleById(Integer id);
}
