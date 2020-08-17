package cn.cerish.service;

import cn.cerish.entity.Role;
import cn.cerish.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    private RoleMapper roleMapper;

    public List<Role> getAllRole() {
        return roleMapper.getAllRole();
    }

    public Role getRoleById(Integer id) {
        return roleMapper.getRoleById(id);
    }
}
