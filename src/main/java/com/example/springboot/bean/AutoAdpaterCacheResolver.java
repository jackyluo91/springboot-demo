package com.example.springboot.bean;

import com.google.common.collect.Collections2;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.interceptor.CacheOperationInvocationContext;
import org.springframework.cache.interceptor.SimpleCacheResolver;
import org.springframework.data.redis.cache.RedisCacheManager;

import java.util.Collection;
import java.util.Set;

public class AutoAdpaterCacheResolver extends SimpleCacheResolver {

    private static Logger log = LoggerFactory.getLogger(RedisConfiguration.class);

    public AutoAdpaterCacheResolver(CacheManager cacheManager) {
        super(cacheManager);
    }

    @Override
    public Collection<? extends Cache> resolveCaches(CacheOperationInvocationContext<?> context) {
        log.info("查询缓存：" + StringUtils.join(context.getOperation().getCacheNames(), ", "));
        RedisCacheManager cacheManager = (RedisCacheManager) super.getCacheManager();
        Set<String> cacheNames = context.getOperation().getCacheNames();
        Collection<Cache> caches = (Collection<Cache>) super.resolveCaches(context);
        if (caches.isEmpty()) {
            Cache cache = cacheManager.getCache(CacheProperties.DEFAULT_CACHE_NAME);
            caches.add(cache);
        }
        log.info("缓存结果：" + StringUtils.join(Collections2.transform(caches, (c) -> {
            return c.getName();
        }), ", "));
        return caches;
    }
}
