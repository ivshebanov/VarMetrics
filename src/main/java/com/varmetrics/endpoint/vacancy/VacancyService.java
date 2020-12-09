package com.varmetrics.endpoint.vacancy;

import com.varmetrics.dao.model.Vacancy;
import com.varmetrics.dao.repository.VacancyRepository;
import com.varmetrics.service.company.Company;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VacancyService {

    private static final Logger logger = LogManager.getLogger(VacancyService.class);

    private final VacancyRepository vacancyRepository;
    private final List<Company> companyList;

    @Autowired
    public VacancyService(VacancyRepository vacancyRepository, List<Company> companyList) {
        this.vacancyRepository = vacancyRepository;
        this.companyList = companyList;
    }

    public List<Vacancy> getAllVacancies() {
        try {
            return vacancyRepository.findAll();
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
        return new ArrayList<>();
    }

    public List<Vacancy> scanAndGetAllVacancies(String searchString) {
        try {
            scanVacancies(searchString);
            return vacancyRepository.findAll();
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
        return new ArrayList<>();
    }

    private void scanVacancies(String searchString) {
        vacancyRepository.deleteAll();
        companyList.forEach(company -> vacancyRepository.saveAll(company.getVacancies(searchString)));
    }
}
