package com.example.springboot.bean;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedisConfiguration extends CachingConfigurerSupport {

    private static Logger log = LoggerFactory.getLogger(RedisConfiguration.class);

    @Autowired
    private CacheResolver cacheResolver;

    @Bean
    @Override
    public KeyGenerator keyGenerator() {
        log.info("初始化KeyGenerator...");
        return (target, method, objects) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(target.getClass().getName());
            sb.append("::");
            sb.append(method.getName());
            sb.append(":");
            sb.append(StringUtils.joinWith("|", objects));
            return sb.toString();
        };
    }

    @Override
    public CacheResolver cacheResolver() {
        return cacheResolver;
    }
}