package cn.cerish.service;

import cn.cerish.entity.College;
import cn.cerish.mapper.CollegeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollegeService {
    @Autowired
    private CollegeMapper collegeMapper;

    public List<College> getAllCollege(String keywords) {
        return collegeMapper.getAllCollege(keywords);
    }


}
