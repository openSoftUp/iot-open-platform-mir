package com.open.iot.common.mqtt.enums;

/**
 * 
* @ClassName: TopicName 
* @Description: 主题名
* @author huy
* @date 2019年8月6日 下午9:02:14 
*
 */
public enum TopicName {
    ROLL_CALL_DEFAULT(1,"listenDefault"),
	ROLL_CALL_HELLO(2,"hello"),
	ROLL_CALL_SLEEP(3,"/json/wristband/sleep/info");

    private final Integer key;
    private final String value;

    private TopicName(Integer key,String value){
        this.key = key;
        this.value = value;
    }
    public Integer getKey() {
        return key;
    }
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
