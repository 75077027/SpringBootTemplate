package com.maoshulin.logback.run;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 描述: 启动类
 *配置步骤：
 * 1、新增一个com.maoshulin.logback.config.annotation.Log 接口，使用注解
 * @Target({ElementType.TYPE, ElementType.METHOD})
 * @Retention(RetentionPolicy.RUNTIME)
 * 2、实现一个类 ControllerInterceptor extends HandlerInterceptorAdapter 重写 preHandle，实现链路打通
 * 3、编写一个切面 LogAspect，注释 @Aspect  @Component； 对com.alibaba.fastjson 和Log4j 有依赖
 * 4、引入LogBack
 *   1)引入JAR包
 *    <!--!!!需要加入 aop 依赖包，需要序列化反序列化，间接依赖 com.alibaba.fastjson 包；Log4j因为springboot自带，不用额外引入开始-->
 *         <dependency>
 *             <groupId>org.springframework.boot</groupId>
 *             <artifactId>spring-boot-starter-aop</artifactId>
 *         </dependency>
 *         <dependency>
 *             <groupId>com.alibaba</groupId>
 *             <artifactId>fastjson</artifactId>
 *             <version>1.2.31</version>
 *         </dependency>
 *         <!--!!!需要加入 aop 依赖包 结束-->
 *     2) 增加 logback-spring.xml  ，可以复制粘贴
 *     3）application配置文件中增加 Log4j 个性化配置，也可以使用profile 或者配置中心灵活配置
 *          logging.level.root=WARN
 *          logging.path=./data/logs/spring-boot-logback
 * 5、启动类LogbackApplication中将组件扫面进容器  @ComponentScan(value = {"com.maoshulin.logback"})
 *
 * @author 毛树林  maoshulin
 * @create 2017-10-16
 **/
@SpringBootApplication

public class LogbackApplication {

    public static void main(String[] args) {
        SpringApplication.run(LogbackApplication.class, args);
    }
}
