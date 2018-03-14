package com.example.springboot.queue.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class TestQueueListener {

    private static Logger log = LoggerFactory.getLogger(TestQueueListener.class);

    @JmsListener(destination = "test.queue")
    public void receiveMsg(String msg) {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("处理队列 =========> " + msg);
    }
}
