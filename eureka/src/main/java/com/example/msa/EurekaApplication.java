package com.example.msa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/*
https://velog.io/@jkijki12/Eureka란

LB(Load Balancer)는 MSA 각 모듈에 대한 혀연결 정보 알고 있음
우리는 각 모듈의 연결 정보를 lb에 등록해야 함.
but cicd 수행하면서 각 모듈은 계속 업그레이드 되면서 연결 정보도 바뀜.
그때마다 lb에 새로 등록해야 함.
-> 이 문제를 eureka가 해결

유레카는 eureka server 와 eureka client로 구성

registry : 서비스의 연결 정보를 등록하는 것

<흐름>
1. eureka client가 시작될 때 eureka server에 자신의 정보 등록
2. 클라는 서버로부터 다른 클라 연결 정보가 등록되어있는 registry 받고 자신의 local에 저장
3. 30초마다 서버로부터 변경 사항 갱신 받기
4. 30초마다 ping으로 자신이 동작하고 있다는 신호 보냄. 서버는 신호 안 온 클라 registry에서 제외시킴.

위 흐름과 같이 모든 서비스 모듈이 스스로 유기적으로 상호작용 하는 기술이 eureka.
 */

// 유레카 서버
@EnableEurekaServer
@SpringBootApplication
public class EurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaApplication.class, args);
	}
}