package cn.cerish.mapper;

import cn.cerish.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

public interface UserMapper {
    public List<User> findAll();

    public User findUserById(int id);

    public User loadUserByUsername(String username);
}
