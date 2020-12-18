package com.varmetrics.service.company;

import com.varmetrics.dao.model.Vacancy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;

@Component
public class SuperJob extends Company {

    private static final Logger logger = LoggerFactory.getLogger(SuperJob.class);

    @Override
    public List<Vacancy> getVacancies(String searchString) {
        Vacancy vacancy = new Vacancy();
        vacancy.setTitle("Java developer");
        vacancy.setCompanyName("Сбер для экспертов");
        vacancy.setSalary("до 350 000 руб.");
        vacancy.setLocation("Москва, Кутузовская");
        vacancy.setSiteName("Superjob.ru");
        vacancy.setUrl("https://www.superjob.ru/vakansii/razrabotchik-java-34590555.html");
        vacancy.setDateVacancy("3 декабря");
        vacancy.setDate(ZonedDateTime.now(ZoneId.of("Europe/Moscow")));
        return Collections.singletonList(vacancy);
    }

    @Override
    public String toString() {
        return "SuperJob";
    }
}
