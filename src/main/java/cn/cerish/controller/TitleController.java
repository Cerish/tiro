package cn.cerish.controller;

import cn.cerish.entity.Title;
import cn.cerish.service.TitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/title")
public class TitleController {
    @Autowired
    private TitleService titleService;

    @GetMapping("/")
    public List<Title> getTitle() {
        return titleService.getTitle();
    }
}
