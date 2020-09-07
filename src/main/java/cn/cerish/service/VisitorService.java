package cn.cerish.service;

import cn.cerish.entity.ResPageBean;
import cn.cerish.entity.Visitor;
import cn.cerish.entity.Visitor;
import cn.cerish.mapper.VisitorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class VisitorService implements UserDetailsService {
    @Autowired
    private VisitorMapper visitorMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Visitor visitor = visitorMapper.loadUserByUsername(username);
        if(visitor == null) {
            throw new UsernameNotFoundException("没有找到该游客");
        }
        return visitor;
    }
    public ResPageBean getVisitorByPage(Integer page, Integer size, Visitor visitor) {
        if (page != null && size != null) {
            page = (page - 1) * size;
        }
        List<Visitor> data = visitorMapper.getVisitorByPage(page, size, visitor);
        Long total = visitorMapper.getTotal(visitor);
        ResPageBean bean = new ResPageBean();
        bean.setData(data);
        bean.setTotal(total);
        return bean;
    }
    public Visitor getVisitorById(Integer id) {
        return visitorMapper.getVisitorById(id);
    }

    public Integer deleteVisitorById(Integer id) {
        return visitorMapper.deleteVisitorById(id);
    }

    public Integer updateVisitorById(Visitor visitor) {
        return visitorMapper.updateVisitorById(visitor);
    }
    public Integer addVisitor(Visitor visitor) {
        return visitorMapper.addVisitor(visitor);
    }

}
