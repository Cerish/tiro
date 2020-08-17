package cn.cerish.service;

import cn.cerish.entity.MyUserDetail;
import cn.cerish.mapper.MyUserDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailService {
    @Autowired
    private MyUserDetailMapper myUserDetailMapper;

    public MyUserDetail getUserDetail(Integer userId,Integer roleId) {
        return myUserDetailMapper.getUserDetail(userId, roleId);
    }
}
