package com.maoshulin.redis.test;


import com.maoshulin.redis.RedisApplication;
import com.maoshulin.redis.utils.RedisUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 描述:测试类
 *
 * @author maoshulin
 * @create 2017-10-16 13:18
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RedisApplication.class)
public class BaseTest {

    @Test
    public void test() throws Exception {


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


        RedisUtils.hashSet("testHashMap", "code", "testHSetValue"+ sdf.format(new Date()));
        System.out.println("测试HashMap:"+RedisUtils.hashGet("testHashMap", "code"));

        RedisUtils.saveString("testString", "testStringValue"+ sdf.format(new Date()));
        System.out.println("测试String:"+RedisUtils.getString("testString"));

        RedisUtils.saveToSet("testHashSet", "testStringValue"+ sdf.format(new Date()));
        System.out.println("测试testHashSet:"+RedisUtils.getFromSet("testHashSet"));
    }

}
