package com.example.msa;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/news")
public class NewsController {

    @RequestMapping("/")
    public String getNews() {

        return "news Information";
    }

    @RequestMapping("/2")
    public String getNews2() {

        return "news Information 22";
    }
}