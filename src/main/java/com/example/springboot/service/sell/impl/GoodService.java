package com.example.springboot.service.sell.impl;

import com.example.springboot.entity.sell.Good;
import com.example.springboot.exception.ParamsInvalidException;
import com.example.springboot.mapper.sell.GoodMapper;
import com.example.springboot.service.sell.IGoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodService implements IGoodService {

    @Autowired
    private GoodMapper mapper;

    @Override
    @Cacheable("good")
    public List<Good> findGoodsByPage(int pageNum, int pageSize) {
        if (pageNum <= 0 || pageSize <= 0) {
            throw new ParamsInvalidException("参数不能为负数");
        }
        int startNum = (pageNum - 1) * pageSize;
        return mapper.selectGoodByPage(startNum, pageSize);
    }
}
