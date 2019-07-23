package com.rht;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 启动器
 */
@EnableAdminServer
@EnableDiscoveryClient
@SpringBootApplication
public class RhtMonitorApplication {

    public static void main(String[] args) {
        SpringApplication.run(RhtMonitorApplication.class, args);
    }

}
