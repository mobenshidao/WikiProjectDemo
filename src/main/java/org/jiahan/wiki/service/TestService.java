package org.jiahan.wiki.service;

import jakarta.annotation.Resource;
import org.jiahan.wiki.domain.Test;
import org.jiahan.wiki.mapper.TestMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService {

    @Resource
    private TestMapper testMapper;

    public List<Test> list(){

        return testMapper.list();

    }

}
