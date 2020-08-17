package cn.cerish.service;

import cn.cerish.entity.Class;
import cn.cerish.entity.Major;
import cn.cerish.entity.ResPageBean;
import cn.cerish.mapper.MajorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MajorService {
    @Autowired
    private MajorMapper majorMapper;

    // 根据条件 获取 major 列表
    public ResPageBean getMajorByPage(Integer page, Integer size, Major major) {
        if (page != null && size != null) {
            page = (page - 1) * size;
        }
        List<Major> data = majorMapper.getMajorByPage(page, size, major);;
        Long total = majorMapper.getTotal(major);
        ResPageBean bean = new ResPageBean();
        bean.setData(data);
        bean.setTotal(total);
        return bean;
    }

    public List<Major> getMajorByCollegeId(Integer id) {
        return majorMapper.getMajorByCollegeId(id);
    }
    public Major getMajorById(Integer id) {
        return majorMapper.getMajorById(id);
    }
}
