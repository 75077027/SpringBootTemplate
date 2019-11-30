package com.maoshulin.swagger.run;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 描述:启动类
 *
 * 配置步骤：
 * 1、增加SwaggerConfig，开启@EnableSwagger2注解
 *          <!--!!!需要加入 swagger2 依赖包 开始-->
 *         <dependency>
 *             <groupId>io.springfox</groupId>
 *             <artifactId>springfox-swagger2</artifactId>
 *             <version>2.7.0</version>
 *         </dependency>
 *         <dependency>
 *             <groupId>io.springfox</groupId>
 *             <artifactId>springfox-swagger-ui</artifactId>
 *             <version>2.7.0</version>
 *         </dependency>
 *         <!--!!!需要加入 swagger2 依赖包  结束-->
 * 2、在对应的接口上使用 @Api  @ApiOperation 暴漏服务.
 * 3、使用 注解 @ComponentScan(value = {"com.maoshulin.swagger"}) 添加到容器中
 * 3、在对应的实体上使用@ApiModelProperty(value = "id") 更容易读懂。【Should，建议】
 *
 *访问端口：http://127.0.0.1:8080/swagger-ui.html
 *
 * @author maoshulin 毛树林
 * @create 2017-10-26
 **/
@SpringBootApplication
@ComponentScan(value = {"com.maoshulin.swagger"})
public class SwaggerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SwaggerApplication.class, args);


    }
}
