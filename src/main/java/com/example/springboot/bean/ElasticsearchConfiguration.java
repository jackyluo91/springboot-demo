package com.example.springboot.bean;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Configuration
public class ElasticsearchConfiguration {

    private static Logger log = LoggerFactory.getLogger(ElasticsearchConfiguration.class);

    @Value("${elasticsearch.cluster.name}")
    private String clusterName;
    @Value("${elasticsearch.host}")
    private String host;
    @Value("${elasticsearch.port}")
    private Integer port;
    @Value("${elasticsearch.pool}")
    private Integer pool;
    @Value("${elasticsearch.sniff}")
    private Boolean sniff;

    @Bean
    public TransportClient transportClient() {
        log.info("初始化TransportClient...");
        TransportClient transportClient = null;
        try {
            // 配置信息
            Settings esSetting = Settings.builder()
                    .put("cluster.name", clusterName)
                    .put("client.transport.sniff", sniff)//增加嗅探机制，找到ES集群
                    .put("thread_pool.search.size", pool)//增加线程池个数
                    .build();
            transportClient = new PreBuiltTransportClient(esSetting);
            InetSocketTransportAddress inetSocketTransportAddress = new InetSocketTransportAddress(InetAddress.getByName(host), port);
            transportClient.addTransportAddresses(inetSocketTransportAddress);
        } catch (UnknownHostException e) {
            log.error("Elasticsearch TransportClient Created Error!", e);
        }
        return transportClient;
    }
}
