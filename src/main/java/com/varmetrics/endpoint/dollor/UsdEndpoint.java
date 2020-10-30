package com.varmetrics.endpoint.dollor;

import com.varmetrics.dao.model.Usd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/usd")
public class UsdEndpoint {

    private final UsdService usdService;

    @Autowired
    public UsdEndpoint(UsdService usdService) {
        this.usdService = usdService;
    }

    @GetMapping
    public List<Usd> getAllUsd() {
        return usdService.getAllUsd();
    }
}
