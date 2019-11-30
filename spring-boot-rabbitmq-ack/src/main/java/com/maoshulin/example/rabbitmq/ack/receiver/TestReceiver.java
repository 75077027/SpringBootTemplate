package com.maoshulin.example.rabbitmq.ack.receiver;

import com.maoshulin.example.rabbitmq.ack.controller.IndexController;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;
@Component
public class TestReceiver {

    private Logger logger = LoggerFactory.getLogger(TestReceiver.class);

    @RabbitListener(
            bindings = @QueueBinding(                    //数据是否持久化
                    value = @Queue(value = "order-queue",durable = "true"),
                    exchange = @Exchange(name = "order-exchange",
                            durable = "true",type = "topic"),
                    key="order.*"
            )
    )
    @RabbitHandler
    public void onOrderMessage(@Payload IndexController.Order order, @Headers Map<String,Object> headers, Channel channel) throws Exception {
        logger.info("----收到消息，开始消费-----");
        logger.info("d订单id："+order.getId());
        Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);

        try {
            //******重点重点来了***********
            //@毛树林   先处理业务瑞吉，后ACK,注意业务逻辑处理需要有 【幂等性】
            //告诉服务器收到这条消息 已经被我消费了 可以在队列删掉 这样以后就不会再发了 否则消息服务器以为这条消息没处理掉 后续还会在发
            //消息确认  因为我在属性配置文件里面开启了ACK确认 所以如果代码没有执行ACK确认 你在RabbitMQ的后台会看到消息会一直留在队列里面未消费掉 只要程序一启动开始接受该队列消息的时候 又会收到
            //******重点重点来了***********
//            int a=100/0;

            channel.basicAck(deliveryTag,false);
            logger.info("--------消费完成--------");
        } catch (IOException e) {
            logger.info("HelloReceiver 消息接收失败");
            // ack返回false，并重新放回队列
            try {
                //******重点重点来了***********
                //@毛树林   对于异常的数据如果反复重新放回队列，可能会造成效率低下或者消息阻塞；建议记录下来再处理，保证消息通道的通常
                //******重点重点来了***********

                //方案一：重新放入队列
                logger.info("HelloReceiver ack返回false，并重新放回队列");
                channel.basicNack(deliveryTag, false, true);

                //方案二：抛弃此条消息并记录日志，程序上线每天排查，后期可以监控【建议】
                //channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);

                //方案三：丢入死信队列，监听死信队列消费(还需要监听额外的对立)

            } catch (IOException e1) {

            }
        }


        logger.info("--------消费完成--------");
    }


}
