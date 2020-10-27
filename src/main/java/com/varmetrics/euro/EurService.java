package com.varmetrics.euro;

import com.varmetrics.model.Eur;
import com.varmetrics.repository.EurRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EurService {

    private static final Logger logger = LogManager.getLogger(EurService.class);

    private final EurRepository eurRepository;

    @Autowired
    public EurService(EurRepository eurRepository) {
        this.eurRepository = eurRepository;
    }

    public List<Eur> getAllEur() {
        try {
            return eurRepository.findAll();
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
        return new ArrayList<>();
    }
}
