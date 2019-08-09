package com.open.iot.common.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties("spring.mqtt")
@Component
@Getter
@Setter
public class MqttProperties {

    private String username;

    private String mqpassword;

    private String hostUrl;

    private String clientId;

    private String defaultTopic;

    private String completionTimeout;

    private Integer keepAlive;
}
