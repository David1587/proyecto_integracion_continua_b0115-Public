package com.poli.integracioncontinua.services;

import com.models.DeveloperInfo;

import reactor.core.publisher.Mono;

public interface RawgService {

    Mono<DeveloperInfo> getDeveloperInfo(String developerName);

}
