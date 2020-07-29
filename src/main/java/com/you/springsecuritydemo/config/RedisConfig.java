package com.you.springsecuritydemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.GenericToStringSerializer;

/**
 * @ClassName: RedisConfig
 * @Description: redis 的 配置类
 * @author: D
 * @create: 2020-07-21 17:10
 **/

@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate redisTemplate(@Lazy RedisConnectionFactory redisConnectionFactory){
        RedisTemplate redis = new RedisTemplate();
        GenericToStringSerializer<String> genericToStringSerializer = new GenericToStringSerializer<>(String.class);
        GenericJackson2JsonRedisSerializer jackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer();
        redis.setKeySerializer(genericToStringSerializer);
        redis.setHashKeySerializer(genericToStringSerializer);
        redis.setValueSerializer(jackson2JsonRedisSerializer);
        redis.setHashValueSerializer(jackson2JsonRedisSerializer);
        redis.setConnectionFactory(redisConnectionFactory);
        return redis;

    }
}
