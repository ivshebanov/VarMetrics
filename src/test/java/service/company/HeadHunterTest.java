package service.company;

import com.varmetrics.dao.model.Vacancy;
import com.varmetrics.service.company.HeadHunter;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class HeadHunterTest {

    @Test
    public void getVacanciesTest() {
        // GIVEN
        HeadHunter headHunter = new HeadHunter();

        // WHEN
        List<Vacancy> vacancies = headHunter.getVacancies("Java Москва");

        // THEN
        Assert.assertNotNull(vacancies);
        Assert.assertNotNull(vacancies.get(0));
        Assert.assertNotNull(vacancies.get(0).getTitle());
        Assert.assertNotNull(vacancies.get(0).getCompanyName());
        Assert.assertNotNull(vacancies.get(0).getSiteName());
        Assert.assertNotNull(vacancies.get(0).getUrl());
        Assert.assertNotNull(vacancies.get(0).getDateVacancy());
        Assert.assertNotNull(vacancies.get(0).getDate());
    }
}
