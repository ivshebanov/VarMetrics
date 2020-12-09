package com.varmetrics.service.company;

import com.varmetrics.dao.model.Vacancy;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;

@Component
public class HeadHunter extends Company {

    @Override
    public List<Vacancy> getVacancies(String searchString) {
        Vacancy vacancy = new Vacancy();
        vacancy.setTitle("Разработчик java");
        vacancy.setCompanyName("Сбер. IT");
        vacancy.setSalary("от 300 000 руб.");
        vacancy.setLocation("Москва");
        vacancy.setSiteName("hh.ru");
        vacancy.setUrl("https://hh.ru/vacancy/40740811?query=java");
        vacancy.setDateVacancy("3 декабря");
        vacancy.setDate(ZonedDateTime.now(ZoneId.of("Europe/Moscow")));
        return Collections.singletonList(vacancy);
    }
}
