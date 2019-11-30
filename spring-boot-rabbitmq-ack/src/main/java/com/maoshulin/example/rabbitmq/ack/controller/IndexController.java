package com.maoshulin.example.rabbitmq.ack.controller;



//import com.hks.example.springboot.rabbitmq.ack.producer.HelloSender;
import com.maoshulin.example.rabbitmq.ack.producer.TestSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 描述:测试用 http://127.0.0.1:9091/index/?content=HelloWorld
 *
 * @author 毛树林  maoshulin
 * @create 2017-10-16
 *
 **/
@RestController
@RequestMapping(value = "/index")
public class IndexController {

    @Autowired
    private TestSender testSender;

    @RequestMapping(value="", method= RequestMethod.GET)
    public String index(@RequestParam String content) {
        LocalDateTime localDateTime = LocalDateTime.now();

        for (int i = 0; i < 100; i++) {
            testSender.send();
        }


        return localDateTime + ",content:" + content;
    }

    public static class Order implements Serializable {

        private Integer id;

        private String name;

        private String message_id;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name == null ? null : name.trim();
        }

        public String getMessage_id() {
            return message_id;
        }

        public void setMessage_id(String message_id) {
            this.message_id = message_id == null ? null : message_id.trim();
        }
    }
}
