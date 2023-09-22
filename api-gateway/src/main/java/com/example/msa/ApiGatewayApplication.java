package com.example.msa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
//import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/*
zuul : netflix에서 제공하는 API Gateaway 또는 API 서비스 기술
JVM-based router 및 Server-side load Balancer
내부적으로 Eureka 서버를 사용하고, 부하 분산을 위해 Ribbon을 사용
zuul 또한 eureka client.
 */
//@EnableZuulProxy
@EnableDiscoveryClient
@SpringBootApplication
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

}
