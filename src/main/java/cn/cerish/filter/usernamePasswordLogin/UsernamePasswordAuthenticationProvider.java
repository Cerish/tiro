package cn.cerish.filter.usernamePasswordLogin;

import cn.cerish.entity.*;
import cn.cerish.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.Class;
import java.util.HashMap;

@Component
public class UsernamePasswordAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private AdminService adminService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private VisitorService visitorService;

    @Autowired
    private HttpServletRequest request;


    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();
//        authentication.getDetails().;

//        String roleType = "1";
        String roleType = ((HashMap) authentication.getDetails()).get("roletype").toString();

        // 用来存储用户数据
        HashMap<String, Object> hashMap = new HashMap();
        hashMap.put("roletype", roleType);
        // 不同角色的不同处理
        boolean isValid = false;
        if(roleType.equals("1")) {
            Visitor userInfo = (Visitor) visitorService.loadUserByUsername(username);
            isValid = passwordEncoder().matches(password, userInfo.getPassword());
            hashMap.put("username", userInfo.getUsername());
            hashMap.put("userId", userInfo.getId());
            hashMap.put("roleId", userInfo.getRoleId());
        } else if(roleType.equals("2")) {
            Student userInfo = (Student) studentService.loadUserByUsername(username);
            isValid = passwordEncoder().matches(password, userInfo.getPassword());
            hashMap.put("username", userInfo.getUsername());
            hashMap.put("userId", userInfo.getId());
            hashMap.put("roleId", userInfo.getRoleId());
        } else if(roleType.equals("3")) {
            Teacher userInfo = (Teacher) teacherService.loadUserByUsername(username);
            isValid = passwordEncoder().matches(password, userInfo.getPassword());
            hashMap.put("username", userInfo.getUsername());
            hashMap.put("userId", userInfo.getId());
            hashMap.put("roleId", userInfo.getRoleId());
        } else if(roleType.equals("4")) {
            Admin userInfo = (Admin) adminService.loadUserByUsername(username);
            isValid = passwordEncoder().matches(password, userInfo.getPassword());
            hashMap.put("username", userInfo.getUsername());
            hashMap.put("userId", userInfo.getId());
            hashMap.put("roleId", userInfo.getRoleId());
        }

        // 验证密码
        if (!isValid) {
            throw new BadCredentialsException("密码错误！");
        }


        UserPwdAuthenticationToken result = new UserPwdAuthenticationToken(username,
                password);
//        Object details = authentication.getDetails();
        result.setDetails(hashMap);
        return result;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UserPwdAuthenticationToken.class.isAssignableFrom(authentication));
    }

//    private Set<GrantedAuthority> listUserGrantedAuthorities(String username) {
//        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
//        if (CheckUtils.isEmpty(username)) {
//            return authorities;
//        }
//        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
//        if ("admin".equals(username)) {
//            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
//        }
//        return authorities;
//    }
}