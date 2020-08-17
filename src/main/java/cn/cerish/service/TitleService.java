package cn.cerish.service;

import cn.cerish.entity.Title;
import cn.cerish.mapper.TitleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TitleService {
    @Autowired
    private TitleMapper titleMapper;

    public List<Title> getTitle() {
        return titleMapper.getTitle();
    }
}
