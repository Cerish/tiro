package cn.cerish.controller;

import cn.cerish.entity.Score;
import cn.cerish.entity.ResPageBean;
import cn.cerish.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/score")
public class ScoreController {
    @Autowired
    private ScoreService scoreService;

    @GetMapping("/")
    public ResPageBean getScoreByPage(Integer page,
                                      Integer size,
                                      Score score) {
        return scoreService.getScoreByPage(page, size, score);
    }
    @GetMapping("/college/{id}")
    public List<Score> getScoreByCollegeId(@PathVariable Integer id) {
        return scoreService.getScoreByCollegeId(id);
    }
    @GetMapping("/{id}")
    public Score getScoreById(@PathVariable Integer id) {
        return scoreService.getScoreById(id);
    }

}
