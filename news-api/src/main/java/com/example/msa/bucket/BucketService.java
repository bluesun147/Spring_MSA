package com.example.msa.bucket;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import org.springframework.stereotype.Service;
import java.time.Duration;
import static java.lang.Thread.sleep;

@Service
public class BucketService {
    public void refillToken() throws InterruptedException {
        // Refill.intervally token = 2 : 1회 충전 시 2개의 토큰 충전
        // Duration.ofSeconds = 1 : 1초마다 토큰 충전
        Refill refill = Refill.intervally(2, Duration.ofSeconds(1));

        // Bandwith capacity : Bucket의 총 크기는 3
        Bandwidth limit = Bandwidth.classic(3, refill);

        // 총 크기가 3이며 1초마다 2개의 token을 충전하는 bucket 생성
        Bucket bucket = Bucket.builder()
                .addLimit(limit)
                .build();

        String str = "";

        for (int i=0; i<10; i++) {
             System.out.println("\nAvailable Token : " + bucket.getAvailableTokens());

            // try Consume = 1, 한번에 소모할 token 지정
            if (bucket.tryConsume(1)) {
                 System.out.println("index : " + i);
            } else {
                System.out.println("index : " + i + ", token is empty!");
                // token 다 떨어지면 1초동안 2개 충전
                sleep(1000);
            }
        }
    }

    // 1분당 5개의 트래픽 제한 존재
    // + 10초안에 모든 토큰 소진시키는 트래픽 피하기
    // bucket4j 사용하면 동일한 버킷에 여러 limit 설정 가능
    public void limitTokenRequest() throws InterruptedException {
        // 1분에 5개 요청 가능한 분당 limit 설정
        Bandwidth minLimit = Bandwidth.classic(5, Refill.intervally(5, Duration.ofMinutes(1)));

        // 10초에 2개 요청 가능한 초당 limit 설정
        Bandwidth secLimit = Bandwidth.classic(2, Refill.intervally(2, Duration.ofSeconds(10)));

        // 2개의 limit으로 bucket 생성
        Bucket bucket = Bucket.builder()
                .addLimit(minLimit)
                .addLimit(secLimit)
                .build();

        for (int i=1; i<13; i++) {
            System.out.println("\nAvailable Token : " + bucket.getAvailableTokens());

            // try Consume = 1, 한번에 소모할 token 지정
            if (bucket.tryConsume(1)) {
                 System.out.println("index : " + i);
            } else {
                 System.out.println("index : " + i + ", token is empty!");
                // token 다 떨어지면 1초동안 2개 충전
                sleep(10000);
            }
        }
    }
}