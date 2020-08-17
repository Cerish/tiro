package cn.cerish.controller;

import cn.cerish.annotation.SerializedField;
import cn.cerish.entity.Major;
import cn.cerish.entity.ResPageBean;
import cn.cerish.service.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/major")
public class MajorController {
    @Autowired
    private MajorService majorService;

    @GetMapping("/")
    public ResPageBean getMajorByPage(Integer page,
                                    Integer size,
                                   Major major) {
        return majorService.getMajorByPage(page, size, major);
    }
    @GetMapping("/college/{id}")
    public List<Major>  getMajorByCollegeId(@PathVariable Integer id) {
        return majorService.getMajorByCollegeId(id);
    }
    @GetMapping("/{id}")
    public Major getMajorById(@PathVariable Integer id) {
        return majorService.getMajorById(id);
    }
}
