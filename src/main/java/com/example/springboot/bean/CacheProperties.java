package com.example.springboot.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 加载yaml配置文件的方法
 * spring-boot更新到1.5.2版本后locations属性无法使用
 * @PropertySource注解只可以加载proprties文件,无法加载yaml文件
 * 故现在把数据放到application.yml文件中, spring-boot启动时会加载
 */
@Component
@ConfigurationProperties(prefix = "com.example.cache")
public class CacheProperties {

    public static final String DEFAULT_CACHE_NAME = "default";
    public static final long DEFAULT_EXPIRE = 86400;

    private Long defaultExpire;
    private Map<String, Long> expireMap = new HashMap<>();

    public Long getDefaultExpire() {
        if (defaultExpire == null) {
            defaultExpire = DEFAULT_EXPIRE;
        }
        return defaultExpire;
    }

    public void setDefaultExpire(Long defaultExpire) {
        this.defaultExpire = defaultExpire;
    }

    public Map<String, Long> getExpireMap() {
        if (expireMap == null) {
            expireMap = new HashMap<>();
        }
        return expireMap;
    }

    public void setExpireMap(Map<String, Long> expireMap) {
        this.expireMap = expireMap;
    }
}
