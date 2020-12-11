package com.varmetrics.service.company;

import com.varmetrics.dao.model.Vacancy;

import java.util.List;

public interface Vacancies {

    List<Vacancy> getVacancies(String searchString);
}
