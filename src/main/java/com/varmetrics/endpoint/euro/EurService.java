package com.varmetrics.endpoint.euro;

import com.varmetrics.dao.model.Eur;
import com.varmetrics.dao.repository.EurRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EurService {

    private static final Logger logger = LoggerFactory.getLogger(EurService.class);

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
