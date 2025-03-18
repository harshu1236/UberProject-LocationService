package org.example.uberprojectlocationservice.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {
    @Bean
    public RedisConnectionFactory redisConnectionFactory(){
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();   // Creates a connection to Redis
        jedisConnectionFactory.setHostName("localhost");
        jedisConnectionFactory.setPort(6379);
        return jedisConnectionFactory; 
    }

    @Bean
    public RedisTemplate<String, String> redisTemplate(){
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();  // Creates RedisTemplate for key-value operations
        redisTemplate.setConnectionFactory(redisConnectionFactory());    // Connects template to Redis
        redisTemplate.setStringSerializer(new StringRedisSerializer());  // Serializes keys as strings
        redisTemplate.setValueSerializer(new StringRedisSerializer());   // Serializes values as strings
        return redisTemplate;
    }
}
