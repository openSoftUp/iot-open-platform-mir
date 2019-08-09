package com.open.iot.common.mqtt.listener;

import org.springframework.stereotype.Component;

import com.open.iot.common.mqtt.event.MqttEvent;


@Component
public class EmqttPredicate {

	//校验
    public Boolean check(MqttEvent event){
        return Boolean.FALSE;
    }
}
