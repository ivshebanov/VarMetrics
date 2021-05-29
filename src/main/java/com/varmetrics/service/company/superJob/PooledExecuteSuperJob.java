package com.varmetrics.service.company.superJob;

import com.varmetrics.dao.model.Vacancy;
import com.varmetrics.service.company.Company;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.LinkedList;
import java.util.List;

import static com.varmetrics.VarMetricsEvent.VAR_METRICS_6;

public class PooledExecuteSuperJob extends Company {

    private static final Logger logger = LoggerFactory.getLogger(PooledExecuteSuperJob.class);

    private String searchString;

    @Override
    public List<Vacancy> call() throws Exception {

        List<Vacancy> resultList = new LinkedList<>();

        Vacancy vacancy = new Vacancy();
        vacancy.setTitle("Java developer");
        vacancy.setCompanyName("Сбер для экспертов");
        vacancy.setSalary("до 350 000 руб.");
        vacancy.setLocation("Москва, Кутузовская");
        vacancy.setSiteName("Superjob.ru");
        vacancy.setUrl("https://www.superjob.ru/vakansii/razrabotchik-java-34590555.html");
        vacancy.setDateVacancy("3 декабря");
        vacancy.setDate(ZonedDateTime.now(ZoneId.of("Europe/Moscow")));

        resultList.add(vacancy);

        logger.debug(VAR_METRICS_6.getText(), resultList.size());
        return resultList;
    }

    @Override
    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }

    @Override
    public String toString() {
        return "SuperJob";
    }
}
