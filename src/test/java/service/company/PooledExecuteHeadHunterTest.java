package service.company;

import com.varmetrics.dao.model.Vacancy;
import com.varmetrics.service.company.headHunter.ExecuteHeadHunter;
import com.varmetrics.service.company.headHunter.HeadHunterState;
import com.varmetrics.service.company.headHunter.PooledExecuteHeadHunter;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class PooledExecuteHeadHunterTest {

//    @Test
//    public void getVacanciesTest() {
//        // GIVEN
//        PooledExecuteHeadHunter pooledExecuteHeadHunter = new PooledExecuteHeadHunter(ExecuteHeadHunter::new);
//
//        // WHEN
//        List<Vacancy> vacancies = pooledExecuteHeadHunter.getVacancies("Java Москва");
//
//        // THEN
//        Assert.assertNotNull(vacancies);
//        Assert.assertNotNull(vacancies.get(0));
//        Assert.assertNotNull(vacancies.get(0).getTitle());
//        Assert.assertNotNull(vacancies.get(0).getCompanyName());
//        Assert.assertNotNull(vacancies.get(0).getSiteName());
//        Assert.assertNotNull(vacancies.get(0).getUrl());
//        Assert.assertNotNull(vacancies.get(0).getDate());
//    }
}
