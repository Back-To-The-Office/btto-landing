package com.btto.landing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LandingController {
    @SuppressWarnings("SpringMVCViewInspection")
    @GetMapping("/")
    public String main() {
        return "index.html";
    }
}
