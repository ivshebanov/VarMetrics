package com.varmetrics.endpoint.dollor;

import com.varmetrics.dao.model.Usd;
import com.varmetrics.dao.repository.UsdRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsdService {

    private static final Logger logger = LoggerFactory.getLogger(UsdService.class);

    private final UsdRepository usdRepository;

    @Autowired
    public UsdService(UsdRepository usdRepository) {
        this.usdRepository = usdRepository;
    }

    public List<Usd> getAllUsd() {
        try {
            return usdRepository.findAll();
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
        return new ArrayList<>();
    }
}
