package com.example.msa.bucket;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;

// https://khj93.tistory.com/entry/Bucket4j를-사용한-Spring-API-요청-한도-트래픽-제한하기

@Slf4j
@RestController
//@RequiredArgsConstructor
@RequestMapping("/bucket")
public class BucketController {

    private final Bucket bucket;
    private final BucketService bucketService;

    public BucketController(BucketService bucketService) {
        this.bucketService = bucketService;
        // 1분에 10개의 요청 처리할 수 있는 버킷 생성
        Bandwidth limit = Bandwidth.classic(10, Refill.intervally(10, Duration.ofMinutes(1)));
        this.bucket = Bucket.builder()
                .addLimit(limit)
                .build();
    }

    // 연속으로 10개까지 가능, 다음부턴 1분간 x
    @PostMapping("/bucketTest")
    public ResponseEntity<Object> bucketTest(@RequestBody String request) {
        if(bucket.tryConsume(1)) {
            log.info("!!!!!!!!! success");
            return ResponseEntity.status(HttpStatus.OK).build();
        }

        log.info("TOO MANY REQUEST");
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
    }

    @GetMapping("/refillToken")
    public void refillToken() throws InterruptedException {
        bucketService.refillToken();
    }

    @GetMapping("/limitTokenRequest")
    public void limitTokenRequest() throws InterruptedException {
        bucketService.limitTokenRequest();
    }
}
