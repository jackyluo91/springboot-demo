package com.example.springboot.bean;

import org.apache.activemq.command.ActiveMQQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jms.Queue;

@Configuration
public class QueueConfiguration {

    private static Logger log = LoggerFactory.getLogger(QueueConfiguration.class);

    @Bean
    public Queue queue() {
        log.info("初始化Queue...");
        return new ActiveMQQueue("test.queue");
    }
}
