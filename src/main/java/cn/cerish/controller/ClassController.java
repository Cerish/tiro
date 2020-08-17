package cn.cerish.controller;

import cn.cerish.annotation.SerializedField;
import cn.cerish.entity.Class;
import cn.cerish.entity.Major;
import cn.cerish.entity.ResPageBean;
import cn.cerish.entity.RespBean;
import cn.cerish.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/class")
public class ClassController {
    @Autowired
    private ClassService classService;


    @GetMapping("/")
    public ResPageBean getClassByPage( Integer page,
                                       Integer size,
                                      Class aclass) {
        return classService.getClassByPage(page, size, aclass);
    }

    @GetMapping("/{id}")
    public Class getAllClass(@PathVariable Integer id) {
        return classService.getClassById(id);
    }

    @GetMapping("/major/{id}")
    public List<Class> getClassByMajorId(@PathVariable Integer id) {
        return classService.getClassByMajorId(id);
    }

    @GetMapping("/college/{id}")
    public List<Class> getClassByCollegeId(@PathVariable Integer id) {
        return classService.getClassByCollegeId(id);
    }


    @PostMapping("/")
    public RespBean addClass(@RequestBody Class aclass) {
        if (classService.addClass(aclass) == 1) {
            return RespBean.success("班级添加成功!");
        }
        return RespBean.error("班级添加失败!");
    }

    @DeleteMapping("/{id}")
    public RespBean deleteClassById(@PathVariable Integer id) {
        if (classService.deleteClassById(id) == 1) {
            return RespBean.success("班级删除成功!");
        }
        return RespBean.error("班级删除失败!");
    }
    @PutMapping("/")
    public RespBean updateClassById(@RequestBody Class aclass) {
        if (classService.updateClassById(aclass) == 1) {
            return RespBean.success("班级更新成功!");
        }
        return RespBean.error("班级更新失败!");
    }

}
