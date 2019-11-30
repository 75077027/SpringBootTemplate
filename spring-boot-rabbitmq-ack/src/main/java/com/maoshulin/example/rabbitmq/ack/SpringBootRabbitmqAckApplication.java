package com.maoshulin.example.rabbitmq.ack;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 描述: Rabbitmq启动类；
 * @author  毛树林 maoshulin
 * @create 2017-10-16
 *
 *配置步骤：
 * 1、MVN引入对应的JAR包，版本自行查询Mvn仓库
 *           <!--!!!需要加入 amqp 依赖包 开始-->
 *         <dependency>
 *             <groupId>org.springframework.boot</groupId>
 *             <artifactId>spring-boot-starter-amqp</artifactId>
 *         </dependency>
 *         <!--!!!需要加入 amqp 依赖包 结束-->
 * 2、增加配置文件 application.ymal
 * 3、在RabbitMQ的管理页面，依次配置 Exchange="order-exchange"; Queue="order-queue" 建立绑定关系
 * 4、发布者参考：com.maoshulin.example.rabbitmq.ack.producer.TestSender  订阅者参考：com.maoshulin.example.rabbitmq.ack.receiver.TestReceiver
 *
 *
 * 说明：
 * 如果方式简单，直接监听Queue即可，为了保留扩展性，可以使用交换机方式，比如多系统分不同的路由
 * 务必对发送端和接收端ACK以及异常处理
  **/


@SpringBootApplication
@ComponentScan(value = {"com.maoshulin.example.rabbitmq.ack"})
public class SpringBootRabbitmqAckApplication {

    public static void main(String[] args) {

        SpringApplication.run(SpringBootRabbitmqAckApplication.class, args);

    }
}
