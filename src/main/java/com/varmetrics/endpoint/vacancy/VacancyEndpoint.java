package com.varmetrics.endpoint.vacancy;

import com.varmetrics.dao.model.Vacancy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.apache.commons.lang3.Validate.notNull;

@RestController
@RequestMapping("/vacancies")
public class VacancyEndpoint {

    private final VacancyService vacancyService;

    @Autowired
    public VacancyEndpoint(VacancyService vacancyService) {
        this.vacancyService = notNull(vacancyService, "vacancyService must not be null");
    }

    @GetMapping
    public List<Vacancy> getAllVacancies() {
        return vacancyService.getAllVacancies();
    }

    @GetMapping("/delete")
    public void deleteAllVacancies() {
        vacancyService.deleteAllVacancies();
    }

    @GetMapping("/scan")
    public List<Vacancy> scanAndGetAllVacancies(@RequestParam String searchString) {
        return vacancyService.scanAndGetAllVacancies(searchString);
    }
}
