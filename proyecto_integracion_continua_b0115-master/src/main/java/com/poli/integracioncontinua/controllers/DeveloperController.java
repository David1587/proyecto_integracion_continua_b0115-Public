package com.poli.integracioncontinua.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.models.DeveloperInfo;
import com.poli.integracioncontinua.services.RawgService;
import com.poli.integracioncontinua.services.RedisService;

import reactor.core.publisher.Mono;

@RestController
public class DeveloperController {

    @Autowired
    private RawgService rawgService;

    @Autowired
    private RedisService redisService;

    @GetMapping("/developer/{name}")
    public Mono<DeveloperInfo> getDeveloperInfo(@PathVariable final String name) {
        final String cachedData = redisService.get(name);
        if (cachedData != null) {
            return Mono.just(new Gson().fromJson(cachedData, DeveloperInfo.class));
        } else {
            return rawgService.getDeveloperInfo(name)
                    .doOnSuccess(data -> redisService.set(name, new Gson().toJson(data)));
        }
    }
}
