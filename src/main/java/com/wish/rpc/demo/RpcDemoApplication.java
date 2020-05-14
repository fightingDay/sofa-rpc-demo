package com.wish.rpc.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@ImportResource({"classpath*:rpc-start-plat-flow-engine-client.xml"})
@SpringBootApplication
public class RpcDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(RpcDemoApplication.class, args);
    }

}
