package com.hkjava.demo.demofinnhub.infra;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

public class RedisObjectMapper {

  public static ObjectMapper of() {
    ObjectMapper redisObjectMapper = new ObjectMapper() //
        .registerModule(new ParameterNamesModule())
        .registerModule(new Jdk8Module()) //
        .registerModule(new JavaTimeModule());
    return redisObjectMapper;
  }
}
