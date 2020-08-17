package cn.cerish.service;

import cn.cerish.entity.Class;
import cn.cerish.entity.ResPageBean;
import cn.cerish.mapper.ClassMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassService {
    @Autowired
    private ClassMapper classMapper;


    public ResPageBean getClassByPage(Integer page, Integer size, Class aclass) {
        if (page != null && size != null) {
            page = (page - 1) * size;
        }
        List<Class> data = classMapper.getClassByPage(page, size, aclass);
        Long total = classMapper.getTotal(aclass);
        ResPageBean bean = new ResPageBean();
        bean.setData(data);
        bean.setTotal(total);
        return bean;
    }
    public Class getClassById(Integer id) {
         return classMapper.getClassById(id);
    }

    public List<Class> getClassByMajorId(Integer id) {
         return classMapper.getClassByMajorId(id);
    }
    public List<Class> getClassByCollegeId(Integer id) {
         return classMapper.getClassByCollegeId(id);
    }

    public Integer deleteClassById(Integer id) {
        return classMapper.deleteClassById(id);
    }

    public Integer updateClassById(Class aclass) {
        return classMapper.updateClassById(aclass);
    }
    public Integer addClass(Class record) {
        return classMapper.addClass(record);
    }
}
