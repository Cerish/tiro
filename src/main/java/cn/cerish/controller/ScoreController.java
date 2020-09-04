package cn.cerish.controller;

import cn.cerish.entity.Course;
import cn.cerish.entity.RespBean;
import cn.cerish.entity.Score;
import cn.cerish.entity.ResPageBean;
import cn.cerish.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping("/{id}")
    public RespBean deleteScoreById(@PathVariable Integer id) {
        if (scoreService.deleteScoreById(id) == 1) {
            return RespBean.success("分数删除成功!");
        }
        return RespBean.error("分数删除失败!");
    }
    @PutMapping("/")
    public RespBean updateCourseById(@RequestBody Score score) {
        if (scoreService.updateScoreById(score) == 1) {
            return RespBean.success("分数更新成功!");
        }
        return RespBean.error("分数更新失败!");
    }
}
