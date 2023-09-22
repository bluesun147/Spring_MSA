package com.example.msa;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/review")
public class ReviewController {

    @RequestMapping("/")
    public String getReviews() {

        return "review Information";
    }

    @RequestMapping("/2")
    public String getReviews2() {

        return "review Information 22";
    }
}