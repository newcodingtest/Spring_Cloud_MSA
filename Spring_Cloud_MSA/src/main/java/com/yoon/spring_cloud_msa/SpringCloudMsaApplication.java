package com.yoon.spring_cloud_msa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer //유레카 서버 역할로 등록
public class SpringCloudMsaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudMsaApplication.class, args);
    }

}
