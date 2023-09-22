package com.example.msa;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/advertising")
public class AdvertisingController {

    @RequestMapping("/")
    public String getAdvertisings() {

        return "advertising Information";
    }

    @RequestMapping("/2")
    public String getAdvertisings2() {

        return "advertising Information 22";
    }
}