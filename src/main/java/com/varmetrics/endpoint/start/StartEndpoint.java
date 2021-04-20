package com.varmetrics.endpoint.start;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class StartEndpoint {

    @Value("${spring.profiles.active:PROD}")
    private String profile;

    @GetMapping
    public String main(Model model) {

        model.addAttribute("isDevMode", "DEV".equals(profile));

        return "index";
    }
}
