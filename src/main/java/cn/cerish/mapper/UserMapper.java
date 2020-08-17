package cn.cerish.mapper;

import cn.cerish.entity.User;

import java.util.List;

public interface UserMapper {
    // 查找所有的用户
    public List<User> getAllUsers(String keywords);
    // 根据 Id 查找用户
    public User findUserById(int id);
    // 根据 Username 查找用户
    public User loadUserByUsername(String username);
    // 注册用户
    public int signup(User user);
}
