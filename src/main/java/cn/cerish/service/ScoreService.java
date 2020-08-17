package cn.cerish.service;

import cn.cerish.entity.Score;
import cn.cerish.entity.ResPageBean;
import cn.cerish.mapper.ScoreMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScoreService {
    @Autowired
    private ScoreMapper scoreMapper;

    // 根据条件 获取 score 列表
    public ResPageBean getScoreByPage(Integer page, Integer size, Score score) {
        if (page != null && size != null) {
            page = (page - 1) * size;
        }
        List<Score> data = scoreMapper.getScoreByPage(page, size, score);;
        Long total = scoreMapper.getTotal(score);
        ResPageBean bean = new ResPageBean();
        bean.setData(data);
        bean.setTotal(total);
        return bean;
    }

    public List<Score> getScoreByCollegeId(Integer id) {
        return scoreMapper.getScoreByCollegeId(id);
    }
    public Score getScoreById(Integer id) {
        return scoreMapper.getScoreById(id);
    }
}
