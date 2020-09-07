package cn.cerish.util;

import cn.cerish.entity.FileProperties;
import cn.cerish.filter.loginHandler.MyAuthenticationProvider;
import cn.cerish.service.AdminService;
import cn.cerish.service.StudentService;
import cn.cerish.service.TeacherService;
import cn.cerish.service.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Component
public final class UserServiceUtils {
    @Autowired
    private AdminService adminService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private VisitorService visitorService;

    @Autowired
    private FileProperties fileProperties;


    // 不同角色获取 UserDetails
    public UserDetails getUserDetails(String username, int roleId) {
        if(roleId == 1) {
            return adminService.loadUserByUsername(username);
        }else if(roleId == 2){
            return teacherService.loadUserByUsername(username);
        }else if(roleId == 4){
            return studentService.loadUserByUsername(username);
        }else if(roleId == 5){
            return visitorService.loadUserByUsername(username);
        }
        return null;
    }
    // 获取 ProviderManager List
    public List<AuthenticationProvider> getProviderList() {
        ArrayList<AuthenticationProvider> myAuthenticationProviders = new ArrayList<AuthenticationProvider>();
        PasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        MyAuthenticationProvider myAuthenticationProvider1 = new MyAuthenticationProvider();
        myAuthenticationProvider1.setPasswordEncoder(bCryptPasswordEncoder);
        myAuthenticationProvider1.setUserDetailsService(adminService);

        MyAuthenticationProvider myAuthenticationProvider2 = new MyAuthenticationProvider();
        myAuthenticationProvider2.setPasswordEncoder(bCryptPasswordEncoder);
        myAuthenticationProvider2.setUserDetailsService(teacherService);

        MyAuthenticationProvider myAuthenticationProvider3 = new MyAuthenticationProvider();
        myAuthenticationProvider3.setPasswordEncoder(bCryptPasswordEncoder);
        myAuthenticationProvider3.setUserDetailsService(studentService);

        MyAuthenticationProvider myAuthenticationProvider4 = new MyAuthenticationProvider();
        myAuthenticationProvider4.setPasswordEncoder(bCryptPasswordEncoder);
        myAuthenticationProvider4.setUserDetailsService(visitorService);

        myAuthenticationProviders.add(myAuthenticationProvider1);
        myAuthenticationProviders.add(myAuthenticationProvider2);
        myAuthenticationProviders.add(myAuthenticationProvider3);
        myAuthenticationProviders.add(myAuthenticationProvider4);
        return myAuthenticationProviders;
    }
    // 生成 头像url
    public String generateUri(String filePath) {

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(StringUtils.cleanPath(fileProperties.getUploadDir()))
                .path("/")
                .path(filePath)
                .toUriString();
        return fileDownloadUri;
    }
    // 删除原有的头像
    public void delAvatar(String path) {
        path = path.substring(path.indexOf("img"));
        boolean delete = new File(path).delete();
    }
}
