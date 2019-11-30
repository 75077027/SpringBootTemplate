package com.maoshulin.example.rabbitmq.ack.producer;

import com.maoshulin.example.rabbitmq.ack.controller.IndexController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.UUID;

@Component
public class TestSender {

    private Logger logger = LoggerFactory.getLogger(TestSender.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send() {
        //@毛树林 根据实际场景更换实体
        IndexController.Order order = new IndexController.Order();
        order.setId(new Random().nextInt());
        order.setName(UUID.randomUUID().toString());
        order.setMessage_id(UUID.randomUUID().toString()+System.currentTimeMillis());

        //设置投递回调
        rabbitTemplate.setConfirmCallback(confirmCallback);
        //失败后返回消息回调  当消息发送，将会把消息退回 如果有任何一个路由队列接收投递消息成功，则不会退回消息
        rabbitTemplate.setReturnCallback(returnCallback);

        CorrelationData correlationData = new CorrelationData();
        correlationData.setId(UUID.randomUUID().toString());

        rabbitTemplate.convertAndSend("order-exchange",
                "order.abcd",
                order,
                correlationData);
    }

    //失败后返回消息回调  当消息发送，将会把消息退回 如果有任何一个路由队列接收投递消息成功，则不会退回消息
    final RabbitTemplate.ReturnCallback returnCallback = new RabbitTemplate.ReturnCallback() {


        @Override
        public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
             logger.info("失败返回消息回调--message:" + new String(message.getBody()) + ",replyCode:" + replyCode + ",replyText:" + replyText + ",exchange:" + exchange + ",routingKey:" + routingKey);
            //@毛树林  强烈建议：自定义逻辑，最好去数据库记录一条日志
            //1）可以后续定时任务继续推送
            //2）可以后续监控使用
        }
    };

//   实现ConfirmCallback ACK=true仅仅标示消息已被Broker接收到，并不表示已成功投放至消息队列中 ACK=false标示消息由于Broker处理错误，消息并未处理成功
    final RabbitTemplate.ConfirmCallback confirmCallback = new RabbitTemplate.ConfirmCallback() {

        /**
         *
         * @param correlationData 唯一标识，有了这个唯一标识，我们就知道可以确认（失败）哪一条消息了
         * @param ack  是否投递成功
         * @param cause 失败原因
         */
        @Override
        public void confirm(CorrelationData correlationData, boolean ack, String cause) {
            String messageId = correlationData.getId();



            //返回成功，表示消息被正常投递
            //@Maoshulin 强烈建议：自定义逻辑，最好去更新一下数据库的状态
            if (ack) {
                logger.info("投递成功："+messageId);
            } else {
                logger.info("投递失败："+messageId+"失败："+cause);
            }
        }
    };

}
