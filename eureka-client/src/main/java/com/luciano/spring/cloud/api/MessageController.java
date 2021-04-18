package com.luciano.spring.cloud.api;

import com.netflix.discovery.EurekaClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageController.class);

    private final EurekaClient eurekaClient;

    public MessageController(@Qualifier("eurekaClient") EurekaClient eurekaClient) {
        this.eurekaClient = eurekaClient;
    }

    @Value("${spring.application.name}")
    private String appName;

    @Value("${server.port}")
    private String portNumber;

    @GetMapping
    public String message() {
        LOGGER.info("Request received on port number {} ", portNumber);
        return String.format("Hello from '%s with Port Number %s'!", eurekaClient.getApplication(appName)
                .getName(), portNumber);
    }

}
