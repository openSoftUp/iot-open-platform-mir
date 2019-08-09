package com.open.iot.common.mqtt.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * 
* @ClassName: MqttEvent 
* @Description: topic事件
* @author huy
* @date 2019年8月6日 下午9:02:29 
*
 */
@Getter
public class MqttEvent extends ApplicationEvent {

    /**
     *
     */
    private String topic;
    /**
     * 发送的消息
     */
    private String message;

    public MqttEvent(Object source,String topic,String message) {
        super(source);
        this.topic = topic;
        this.message = message;
    }

}
