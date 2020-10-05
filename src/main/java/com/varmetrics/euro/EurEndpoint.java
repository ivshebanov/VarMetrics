package com.varmetrics.euro;

import com.varmetrics.model.Eur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/eur")
public class EurEndpoint {

    private final EurService eurService;

    @Autowired
    public EurEndpoint(EurService eurService) {
        this.eurService = eurService;
    }

    @GetMapping("/all")
    public List<Eur> getAllEur() {
        return eurService.getAllEur();
    }
}
