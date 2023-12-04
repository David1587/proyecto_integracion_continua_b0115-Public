package com.poli.integracioncontinua.services;

public interface RedisService {

    void set(String key, String value);

    String get(String key);

}
