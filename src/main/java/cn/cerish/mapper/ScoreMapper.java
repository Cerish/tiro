package cn.cerish.mapper;

import cn.cerish.entity.Score;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ScoreMapper {
    // 根据 Id 查找分数
    public Score getScoreById(Integer id);
    // 根据学院查找id
    public List<Score> getScoreByCollegeId(Integer id);
    // 根据 条件获取 分数(分页)
    public List<Score> getScoreByPage(@Param("page") Integer page,
                                      @Param("size") Integer size,
                                      @Param("score") Score score);
    // 根据 条件获取 total
    public long getTotal(@Param("score") Score score);

    // 根据id 删除一个分数
    public int deleteScoreById(Integer id);

    // 根据id 更新一个分数信息
    public int updateScoreById(Score score);

    // 添加一个分数
    public int addScore(Score score);
}
