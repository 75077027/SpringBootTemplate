package com.maoshulin.redis.run;


import com.maoshulin.redis.utils.RedisUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.io.IOException;
import java.net.ServerSocket;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 描述: Redis启动类；
 * @author  毛树林 maoshulin
 * @create 2017-10-16
 *
 *配置步骤：
 * 1、MVN引入对应的JAR包，版本自行查询Mvn仓库
 *         <!--!!!需要加入 Redis 依赖包，需要序列化反序列化，间接依赖 com.alibaba.fastjson 包 开始-->
 *         <dependency>
 *             <groupId>org.springframework.boot</groupId>
 *             <artifactId>spring-boot-starter-data-redis</artifactId>
 *         </dependency>
 *         <dependency>
 *             <groupId>com.alibaba</groupId>
 *             <artifactId>fastjson</artifactId>
 *             <version>1.2.38</version>
 *         </dependency>
 *         <!--Redis 依赖包 结束-->
 * 2、增加配置文件 application.properties 或者 application.ymal
 * 3、在Config文件夹下增加 Configuration、RedisConfig 类
 * 4、在工具文件夹utils下 增加 RedisUtils
 *
 *
 * 调用示例代码：
 *
 *         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 *
 *         RedisUtils.hashSet("testHashMap", "code", "testHSetValue"+ sdf.format(new Date()));
 *         System.out.println("测试HashMap:"+RedisUtils.hashGet("testHashMap", "code"));
 *
 *         RedisUtils.saveString("testString", "testStringValue"+ sdf.format(new Date()));
 *         System.out.println("测试String:"+RedisUtils.getString("testString"));
 *
 *         RedisUtils.saveToSet("testHashSet", "testStringValue"+ sdf.format(new Date()));
 *         System.out.println("测试testHashSet:"+RedisUtils.getFromSet("testHashSet"));
 **/
@SpringBootApplication
@ComponentScan(value = {"com.maoshulin.redis"})
public class RedisApplication {

   /* public static void main(String[] args) {
        SpringApplication.run(RedisApplication.class,args);

    }*/


    public static void main(String[] args) throws IOException {
        SpringApplication.run(RedisApplication.class, args);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        RedisUtils.hashSet("testHashMap", "code", "testHSetValue"+ sdf.format(new Date()));
        System.out.println("测试HashMap:"+RedisUtils.hashGet("testHashMap", "code"));

        RedisUtils.saveString("testString", "testStringValue"+ sdf.format(new Date()));
        System.out.println("测试String:"+RedisUtils.getString("testString"));

        RedisUtils.saveToSet("testHashSet", "testStringValue"+ sdf.format(new Date()));
        System.out.println("测试testHashSet:"+RedisUtils.getFromSet("testHashSet"));



    }
}
