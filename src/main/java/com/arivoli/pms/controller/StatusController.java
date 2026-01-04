package com.arivoli.pms.controller;

import com.arivoli.pms.model.StatusResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/status")
public class StatusController {

    @Value("${spring.application.name}")
    private String applicationName;

    @GetMapping()
    public StatusResponse checkStatus() {
        return new StatusResponse(
                applicationName,
                "UP",
                LocalDateTime.now()
        );
    }
}
