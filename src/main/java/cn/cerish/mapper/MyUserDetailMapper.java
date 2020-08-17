package cn.cerish.mapper;

import cn.cerish.entity.MyUserDetail;

public interface MyUserDetailMapper {
    public MyUserDetail getUserDetail(Integer userId,Integer roleId);
}
