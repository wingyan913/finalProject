package com.hkjava.demo.demofinnhub.config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hkjava.demo.demofinnhub.infra.AppleRestClient;
import com.hkjava.demo.demofinnhub.infra.RedisHelper;
import com.hkjava.demo.demofinnhub.infra.RedisObjectMapper;

@Configuration
@EnableCaching
public class AppConfig {

  @Value(value = "${api.finnhub.token}")
  private String token;

  @Bean
  ModelMapper modelMapper() {
    return new ModelMapper();
  }

  @Bean
  String finnhubToken() {
    return token;
  }

  @Bean
  AppleRestClient stockService(RestTemplate restTemplate) {
    return new AppleRestClient(restTemplate);
  }

  @Bean
  RestTemplate restTemplate() {
    return new RestTemplate();
  }

  @Bean
  ObjectMapper redisObjectMapper() {
    return RedisObjectMapper.of();
  }

  @Bean
  RedisHelper redisProfileHelper(RedisConnectionFactory factory,
      ObjectMapper redisObjectMapper) {
    return new RedisHelper(factory, redisObjectMapper);
  }


  // @Bean
  // RedisTemplate<String, Object> redisProfileTemplate(
  // RedisConnectionFactory factory, ObjectMapper redisObjectMapper) { // method name -> bean name
  // RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
  // redisTemplate.setConnectionFactory(factory);
  // redisTemplate.setKeySerializer(RedisSerializer.string());
  // redisTemplate.setValueSerializer(RedisSerializer.json());
  // // redisTemplate.setHashKeySerializer(RedisSerializer.string());
  // // redisTemplate.setHashValueSerializer(RedisSerializer.json());
  // redisTemplate.afterPropertiesSet();
  // Jackson2JsonRedisSerializer<Object> serializer =
  // new Jackson2JsonRedisSerializer<>(Object.class);
  // serializer.setObjectMapper(redisObjectMapper);
  // redisTemplate.setValueSerializer(serializer);
  // return redisTemplate;
  // }

}
