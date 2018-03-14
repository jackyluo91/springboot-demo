package com.example.springboot.bean;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.Arrays;
import java.util.Map;

@Configuration
public class CacheManagerConfiguration {

    private static Logger log = LoggerFactory.getLogger(CacheManagerConfiguration.class);

    @Autowired
    private CacheProperties cacheProperties;

    @Bean
    public CacheManager cacheManager(RedisTemplate redisTemplate) {
        log.info("初始化CacheManager...");
        Map<String, Long> expireMap = cacheProperties.getExpireMap();
        Long defaultExpire = cacheProperties.getDefaultExpire();
        expireMap.put(CacheProperties.DEFAULT_CACHE_NAME, defaultExpire);
        log.info("添加Cache：" + StringUtils.join(expireMap.keySet(), ", "));
        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
        cacheManager.setExpires(expireMap);
        //设置默认缓存过期时间
        cacheManager.setDefaultExpiration(defaultExpire);
        return cacheManager;
    }

    //缓存管理器
    @Bean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        log.info("初始化RedisTemplate...");
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        //设置序列化工具
        setSerializer(template);
        template.afterPropertiesSet();
        return template;
    }

    @Bean
    public CacheResolver cacheResolver(CacheManager cacheManager) {
        log.info("初始化CacheResolver...");
        return new AutoAdpaterCacheResolver(cacheManager);
    }

    /*@Bean
    public Cache cache(CacheManager cacheManager) {
        return cacheManager.getCache(CacheProperties.DEFAULT_CACHE_NAME);
    }*/

    private void setSerializer(RedisTemplate template) {
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        //使用StringRedisSerializer来序列化和反序列化redis的key值
        template.setKeySerializer(new StringRedisSerializer());
        //使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值
        template.setValueSerializer(jackson2JsonRedisSerializer);
    }
}
