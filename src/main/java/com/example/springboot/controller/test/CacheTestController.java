package com.example.springboot.controller.test;

import com.example.springboot.service.cache.ICacheTestService;
import com.example.springboot.service.sell.IGoodService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/cache")
public class CacheTestController {

    private static Logger log = LoggerFactory.getLogger(CacheTestController.class);

    @Resource
    private ICacheTestService cacheTestService;
    @Autowired
    private CacheManager cacheManager;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public Object get() {
        return cacheTestService.get();
    }

    @RequestMapping(value = "/clear", method = RequestMethod.GET)
    public Object clear() {
        cacheTestService.clear();
        return "Success!";
    }

    @RequestMapping(value = "/status", method = RequestMethod.GET)
    public Object status() {
        log.info("查询到Cache：" + cacheManager.getCacheNames().size());
        log.info("查询到Cache：" + StringUtils.join(cacheManager.getCacheNames(), ", "));
        return "Success!";
    }
}
