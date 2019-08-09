package com.open.iot.common.mqtt.server;

import org.springframework.integration.annotation.GatewayHeader;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Component
@MessagingGateway(defaultRequestChannel = "mqttOutboundChannel"
        , defaultHeaders = {@GatewayHeader(name = "tic", value = "PushException")}
        )
public interface MqttSendServer {
    void sendToMqtt(@Header(MqttHeaders.TOPIC)String topic,String data);
    void sendToMqtt(String data);
    void sendToMqtt(@Header(MqttHeaders.TOPIC) String topic, @Header(MqttHeaders.QOS) int qos, String payload);
}
