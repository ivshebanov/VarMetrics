package com.varmetrics.endpoint;

import com.varmetrics.model.Dollar;
import com.varmetrics.repository.DollarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dollar")
public class DollarEndpoint {

    private final DollarRepository dollarRepository;

    @Autowired
    public DollarEndpoint(DollarRepository dollarRepository) {
        this.dollarRepository = dollarRepository;
    }

    @GetMapping("/all")
    public List<Dollar> getDollars() {
        return dollarRepository.findAll();
    }
}
