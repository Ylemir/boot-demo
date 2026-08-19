package me.boot.config.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * 配置服务端
 *
 * @since 2023/12/03
 **/
@SpringBootApplication
@EnableConfigServer
public class ConfigServer {

    public static void main(String[] arguments) {
        SpringApplication.run(ConfigServer.class, arguments);
    }
}

