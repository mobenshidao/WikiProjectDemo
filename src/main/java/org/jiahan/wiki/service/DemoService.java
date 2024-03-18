package org.jiahan.wiki.service;

import jakarta.annotation.Resource;
import org.jiahan.wiki.domain.Demo;
import org.jiahan.wiki.mapper.DemoMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DemoService {

    @Resource
    private DemoMapper demoMapper;

    public List<Demo> list(){

        return demoMapper.selectByExample(null);

    }

}
