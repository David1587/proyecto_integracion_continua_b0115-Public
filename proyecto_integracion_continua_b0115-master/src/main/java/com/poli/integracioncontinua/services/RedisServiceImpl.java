package com.poli.integracioncontinua.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public void set(final String key, final String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    public String get(final String key) {
        return redisTemplate.opsForValue().get(key);
    }
}
