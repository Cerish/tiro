package cn.cerish.mapper;

import cn.cerish.entity.College;

import java.util.List;

public interface CollegeMapper {
    public List<College> getAllCollege(String keyword);
}
