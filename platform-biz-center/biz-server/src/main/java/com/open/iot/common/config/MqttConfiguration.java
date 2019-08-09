package com.open.iot.common.config;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;

import com.open.iot.common.mqtt.enums.TopicName;
import com.open.iot.common.mqtt.event.MqttEvent;

import java.util.Arrays;

/**
 * 
* @ClassName: MqttConfiguration 
* @Description: 配置文件 
* @author huy
* @date 2019年8月6日 下午9:01:45 
*
 */
@Configuration
@Slf4j
public class MqttConfiguration {
    @Autowired
    private MqttProperties mqttProperties;

    @Bean
    public MqttConnectOptions getMqttConnectOptions(){
        MqttConnectOptions mqttConnectOptions=new MqttConnectOptions();
        mqttConnectOptions.setUserName(mqttProperties.getUsername());
        mqttConnectOptions.setPassword(mqttProperties.getMqpassword().toCharArray());
        mqttConnectOptions.setServerURIs(new String[]{mqttProperties.getHostUrl()});
        mqttConnectOptions.setKeepAliveInterval(2);
        mqttConnectOptions.setKeepAliveInterval(mqttProperties.getKeepAlive());
        return mqttConnectOptions;
    }
    @Bean
    public MqttPahoClientFactory mqttClientFactory() {
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        factory.setConnectionOptions(getMqttConnectOptions());
        return factory;
    }

    @Bean
    public MessageChannel mqttInputChannel() {
        return new DirectChannel();
    }

    /**
     * 配置client,监听的topic
     */
    @Bean
    public MessageProducer inbound() {
        MqttPahoMessageDrivenChannelAdapter adapter =
                new MqttPahoMessageDrivenChannelAdapter(mqttProperties.getClientId()+"_inbound", mqttClientFactory(),
                        mqttProperties.getDefaultTopic().split(","));
        adapter.setCompletionTimeout(Long.valueOf(mqttProperties.getCompletionTimeout()));
        adapter.setConverter(new DefaultPahoMessageConverter());
        //默认添加TopicName中所有tipic
        Arrays.stream(TopicName.values()).forEach(topicName -> adapter.addTopic(topicName.getValue(),2));
        adapter.addTopic();
        adapter.setQos(2);
        adapter.setOutputChannel(mqttInputChannel());
        return adapter;
    }

    /**
     * 事件触发
     */
    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Bean
    @ServiceActivator(inputChannel = "mqttInputChannel")
    public MessageHandler handler() {
        return new MessageHandler() {
            @Override
            public void handleMessage(Message<?> message) throws MessagingException {
                String topic = message.getHeaders().get("mqtt_receivedTopic").toString();
                String qos = message.getHeaders().get("mqtt_receivedQos").toString();
                //如果要对topic做通配符的匹配规则，在此处可以做，然后转到相应的主题中去
                //触发事件 这里不再做业务处理，包 listener中做处理
                eventPublisher.publishEvent(new MqttEvent(this,topic,message.getPayload().toString()));
                log.info("topic:"+topic+" Qos:"+qos+" message:"+message.getPayload());
            }
        };
    }

    @Bean
    @ServiceActivator(inputChannel = "mqttOutboundChannel")
    public MessageHandler mqttOutbound() {
        MqttPahoMessageHandler messageHandler =  new MqttPahoMessageHandler(mqttProperties.getClientId(), mqttClientFactory());
        messageHandler.setAsync(true);
        messageHandler.setDefaultTopic(mqttProperties.getDefaultTopic());
        return messageHandler;
    }

    @Bean
    public MessageChannel mqttOutboundChannel() {
        return new DirectChannel();
    }
}

