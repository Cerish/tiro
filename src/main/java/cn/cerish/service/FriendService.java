package cn.cerish.service;

import cn.cerish.entity.Friend;
import cn.cerish.entity.ResPageBean;
import cn.cerish.mapper.FriendMapper;
import com.google.common.util.concurrent.ListenableFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;


@Service
public class FriendService {
    @Autowired
    private FriendMapper friendMapper;
    @Autowired
    private AdminService adminService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private VisitorService visitorService;

//    @Async
//    public Future<UserDetails> getUserDetailsById(Friend afriend) {
//        UserDetails userDetails = null;
//        int friendRoleId = afriend.getFriendRoleId();
//        int friendId = afriend.getFriendId();
//        if(friendRoleId == 1) {
//            userDetails = adminService.getAdminById(friendId);
//        }else if(friendRoleId == 2) {
//            userDetails = teacherService.getTeacherById(friendId);
//        }else if(friendRoleId == 4) {
//            userDetails = studentService.getStudentById(friendId);
//        }else if(friendRoleId == 5) {
//            userDetails = visitorService.getVisitorById(friendId);
//        }
//        return new AsyncResult<>(userDetails);
//    }

    public ResPageBean getFriends(Friend friend) throws ExecutionException, InterruptedException {
        List<Friend> friends = friendMapper.getFriends(friend);

        Long total = friendMapper.getTotal(friend);
        ResPageBean result = new ResPageBean<>();
        result.setData(friends);
        result.setTotal(total);
        return result;
    }
}
