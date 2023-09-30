package com.hkjava.demo.demofinnhub.infra;

import java.util.concurrent.TimeUnit;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.web.client.ResourceAccessException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RedisHelper {

  // key value pair, key must be unqiue, most likely String
  private RedisTemplate<String, Object> redisTemplate;

  public RedisHelper(RedisTemplate<String, Object> redisTemplate) {
    this.redisTemplate = redisTemplate;
  }

  public RedisHelper(RedisConnectionFactory factory, ObjectMapper redisObjectMapper) {
    this.redisTemplate = template(factory, redisObjectMapper);
  }

  public static RedisTemplate<String, Object> template(
      RedisConnectionFactory factory, ObjectMapper redisObjectMapper) { // method name -> bean name
    RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
    redisTemplate.setConnectionFactory(factory);
    redisTemplate.setKeySerializer(RedisSerializer.string());
    redisTemplate.setValueSerializer(RedisSerializer.json());
    // redisTemplate.setHashKeySerializer(RedisSerializer.string());
    // redisTemplate.setHashValueSerializer(RedisSerializer.json());
    redisTemplate.afterPropertiesSet();
    Jackson2JsonRedisSerializer<Object> serializer =
        new Jackson2JsonRedisSerializer<>(Object.class);
    serializer.setObjectMapper(redisObjectMapper);
    redisTemplate.setValueSerializer(serializer);
    return redisTemplate;
  }

  public boolean set(String key, Object value) {
    try {
      redisTemplate.opsForValue().set(key, value);
      return true;
    } catch (Exception e) {
      throw new ResourceAccessException("Redis unavailable.");
    }
  }

  public boolean set(String key, Object value, long time) {
    try {
      System.out.println("set before=" + key + " " + value + " " + time);
      redisTemplate.opsForValue().set(key, value, time, TimeUnit.MILLISECONDS);
      System.out.println("set after");
      return true;
    } catch (Exception e) {
      System.out.println("redis.set=" + e.getMessage());
      throw new ResourceAccessException(
          "Redis unavailable (set method) msg=" + e.getMessage());
    }
  }

  public Object get(String key) {
    try {
      if (key != null) {
        System.out.println("!!!!!!!!!!!!!!!!");
        Object object = redisTemplate.opsForValue().get(key);
        System.out.println("!!!!!!!!!!!!!!!!= " + object);
        return object;
      }
      return null;
    } catch (Exception e) {
      throw new ResourceAccessException(
          "Redis unavailable (get method) msg=" + e.getMessage());
    }
  }

  public boolean expire(String key, long time) {
    try {
      if (time > 0) {
        redisTemplate.expire(key, time, TimeUnit.MILLISECONDS);
        return true;
      }
      return false;
    } catch (Exception e) {
      throw new ResourceAccessException("Redis unavailable.");
    }
  }

  public static String key(String head, String source) {
    return head.concat(":").concat(source);
  }
}
