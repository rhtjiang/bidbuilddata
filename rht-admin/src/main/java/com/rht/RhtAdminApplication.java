package com.rht;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 启动器
 */
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages={"com.rht"})
public class RhtAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(RhtAdminApplication.class, args);
    }

}
