package com.ray.nacos.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ny
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ConfigApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConfigApplication.class, args);
    }

    @RestController
    @RefreshScope
    public class ConfigClass {
        @Value("${user.name}")
        private String userName;

        @GetMapping("/hi")
        public String hi() {
            return "hi " + userName;
        }
    }

    @RestController
    @RefreshScope
    public class ExtConfigClass {
        @Value("${db.pool}")
        private int poolSize;

        @Value("${http.encoding}")
        private String encoding;

        @Value("${log.file}")
        private String logFile;

        @GetMapping("/ext/config")
        public String config() {
            return "poolSize: " + poolSize + ", encoding: " + encoding + ", logFile: " + logFile;
        }
    }
}
