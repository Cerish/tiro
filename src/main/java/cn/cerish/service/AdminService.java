package cn.cerish.service;

import cn.cerish.entity.Admin;
import cn.cerish.entity.ResPageBean;
import cn.cerish.entity.Admin;
import cn.cerish.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class AdminService implements UserDetailsService {
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private HttpServletRequest request;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Admin admin = adminMapper.loadUserByUsername(username);
        if(admin == null) {
            throw new UsernameNotFoundException("没有找到该管理员");
        }
        String roleType = request.getParameter("roleType");
        if(!roleType.equals(admin.getRoleId() + "")) {
            throw new UsernameNotFoundException("你走错片场啦，不是你这个角色该来的地方");
        }


        return admin;
    }

    public ResPageBean getAdminByPage(Integer page, Integer size, Admin admin) {
        if (page != null && size != null) {
            page = (page - 1) * size;
        }
        List<Admin> data = adminMapper.getAdminByPage(page, size, admin);
        Long total = adminMapper.getTotal(admin);
        ResPageBean bean = new ResPageBean();
        bean.setData(data);
        bean.setTotal(total);
        return bean;
    }
    public Admin getAdminById(Integer id) {
        return adminMapper.getAdminById(id);
    }

    public Integer deleteAdminById(Integer id) {
        return adminMapper.deleteAdminById(id);
    }

    public Integer updateAdminById(Admin admin) {
        return adminMapper.updateAdminById(admin);
    }
    public Integer addAdmin(Admin admin) {
        return adminMapper.addAdmin(admin);
    }

}
