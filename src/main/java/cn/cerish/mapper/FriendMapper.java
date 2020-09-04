package cn.cerish.mapper;

import cn.cerish.entity.Friend;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FriendMapper {
    // 获取好友列表
    public List<Friend> getFriends(@Param("friend") Friend friend);
    // 获取好友列表总数
    public Long getTotal(@Param("friend") Friend friend);
}
