package cn.cerish.controller;

import cn.cerish.annotation.SerializedField;
import cn.cerish.entity.College;
import cn.cerish.service.CollegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/college")
public class CollegeController {
    @Autowired
    private CollegeService collegeService;

    @GetMapping("/all")
    @SerializedField(includes = {"id", "nameZh"})
    public List<College> getAllCollege(String keywords) {
        return collegeService.getAllCollege(keywords);
    }
}
