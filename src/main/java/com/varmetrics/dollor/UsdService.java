package com.varmetrics.dollor;

import com.varmetrics.model.Usd;
import com.varmetrics.repository.UsdRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsdService {

    private static final Logger logger = LogManager.getLogger(UsdService.class);

    private final UsdRepository usdRepository;

    @Autowired
    public UsdService(UsdRepository usdRepository) {
        this.usdRepository = usdRepository;
    }

    public List<Usd> getAllUsd() {
        return usdRepository.findAll();
    }
}
