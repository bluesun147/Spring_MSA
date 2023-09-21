package com.example.msa;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

// Spring MSA
// https://taes-k.github.io/trick&basic.html

@Controller
public class VIewController {
    @RequestMapping("/")
    public String index(Model model) {
        return "index";
    }
}