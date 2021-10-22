package com.athome.redis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author Zengfc
 * @Date 2021/10/12 15:45
 * @Version 1.0
 */
@RestController
public class TestController {

    @Autowired
    RedisTemplate<String,Object> redisTemplate;

    @RequestMapping("/setRedis")
    public Object setGetRedis(){

        redisTemplate.opsForValue().set("name","lucy");

      return   redisTemplate.opsForValue().get("name");
    }
}
