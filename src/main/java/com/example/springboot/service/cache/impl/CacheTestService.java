package com.example.springboot.service.cache.impl;

import com.example.springboot.entity.sell.Good;
import com.example.springboot.service.cache.ICacheTestService;
import com.example.springboot.service.sell.IGoodService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CacheTestService implements ICacheTestService {

    private static Logger log = LoggerFactory.getLogger(CacheTestService.class);

    @Resource
    private IGoodService goodService;

    @Cacheable("test")
    public List<Good> get() {
        log.info("未使用缓存...");
        return goodService.findGoodsByPage(1, 5);
    }

    @Override
    @CacheEvict(value = "test", allEntries = true)
    public void clear() {
        log.info("清空缓存：test");
    }
}
