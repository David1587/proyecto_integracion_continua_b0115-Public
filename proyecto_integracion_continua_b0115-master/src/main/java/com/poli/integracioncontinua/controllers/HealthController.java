package com.poli.integracioncontinua.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.poli.integracioncontinua.IntegracioncontinuaApplication;

@RestController
public class HealthController {

    @Autowired
    private IntegracioncontinuaApplication app;

    @GetMapping("/health")
    public String getHealth() {
        return "Probando cambios desde trigger de jenkins. Valor obtenido desde la base de datos de redis: " + app.getRedisValue();
    }
}
