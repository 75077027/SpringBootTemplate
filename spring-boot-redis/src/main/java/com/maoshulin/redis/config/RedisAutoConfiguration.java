package com.maoshulin.redis.config;

import com.maoshulin.redis.utils.RedisUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 描述: 注册配置类到容器
 *
 * @author maoshulin
 * @create 2017-10-16 14:50
 **/

@Configuration
@Import({RedisConfig.class, RedisUtils.class})
public class RedisAutoConfiguration {

}