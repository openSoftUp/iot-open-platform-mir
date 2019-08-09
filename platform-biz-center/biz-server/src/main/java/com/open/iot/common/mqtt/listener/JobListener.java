package com.open.iot.common.mqtt.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import com.open.iot.common.mqtt.event.MqttEvent;


/**
 * 
* @ClassName: JobListener 
* @Description: 触发event topic 事件;异步
* @author huy
* @date 2019年8月6日 下午9:02:40 
*
 */
@Slf4j
@Component
@EnableAsync
public class JobListener {


    /**
     * 监听topic
     * @param mqttEvent
     */
	@Async
    @EventListener(condition = "#mqttEvent.topic.equals(T(com.open.iot.common.mqtt.enums.TopicName).ROLL_CALL_DEFAULT.getValue())")
    public void onEmqttCall(MqttEvent mqttEvent){

        log.info("1-------------接收到消息{}",mqttEvent.getMessage());

    }
    @EventListener(condition ="@emqttPredicate.check(#mqttEvent)")
    public void onEmqttCallTest(MqttEvent mqttEvent){
        log.info("1-------------测试通过{}",mqttEvent.getMessage());
    }
    
    @EventListener(condition = "#mqttEvent.topic.equals(T(com.open.iot.common.mqtt.enums.TopicName).ROLL_CALL_SLEEP.getValue())")
    public void onEmqttCallForMy(MqttEvent mqttEvent){

        log.info("1-------------自定义主题接收到消息{}",mqttEvent.getMessage());

    }
    
    @EventListener(condition = "#mqttEvent.topic.equals('/json/wristband/sport/info')")
    public void onEmqttCallForMyA(MqttEvent mqttEvent){

        log.info("1-------------A自定义主题接收到消息{}",mqttEvent.getMessage());

    }
}

