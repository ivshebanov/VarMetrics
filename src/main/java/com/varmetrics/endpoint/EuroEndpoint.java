package com.varmetrics.endpoint;

import com.varmetrics.model.Euro;
import com.varmetrics.repository.EuroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/euro")
public class EuroEndpoint {

    private final EuroRepository euroRepository;

    @Autowired
    public EuroEndpoint(EuroRepository euroRepository) {
        this.euroRepository = euroRepository;
    }

    @GetMapping("/all")
    public List<Euro> getEuro() {
        return euroRepository.findAll();
    }
}
