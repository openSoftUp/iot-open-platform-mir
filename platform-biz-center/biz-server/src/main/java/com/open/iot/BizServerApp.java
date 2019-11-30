package com.open.iot;

import com.open.iot.annotation.EnableLogging;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author huyong
 */
@EnableLogging
@SpringBootApplication
public class BizServerApp {

    public static void main(String[] args) {
        SpringApplication.run(BizServerApp.class, args);
    }

}
