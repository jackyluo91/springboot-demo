package com.example.springboot.controller.test;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.Queue;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
public class MQTestController {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;
    @Autowired
    private Queue queue;

    @RequestMapping(value = "/queue/sendMsg", method = RequestMethod.POST)
    public void send(HttpServletRequest request) {
        try {
            String msg = IOUtils.toString(request.getInputStream(), "UTF-8");
            jmsMessagingTemplate.convertAndSend(queue, msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/queue/autoSendMsg", method = RequestMethod.POST)
    public void autoSend() {
        for (int i = 0; i < 100; i++) {
            jmsMessagingTemplate.convertAndSend(queue, i);
        }
    }
}
