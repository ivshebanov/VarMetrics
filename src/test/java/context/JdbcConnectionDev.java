package context;

import com.varmetrics.dao.model.Vacancy;
import com.varmetrics.dao.repository.VacancyRepository;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class JdbcConnectionDev extends SpringDevContext {

    @Autowired
    public VacancyRepository vacancyRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void dataSourceCheck() {
        jdbcTemplate.execute("SELECT 1 FROM DUAL");
    }

    @Test
    public void vacancyRepositoryCheck() {
        // WHEN
        List<Vacancy> vacancyRepositoryAll = vacancyRepository.findAll();

        // THEN
        Assert.assertNotNull(vacancyRepositoryAll);
        Assert.assertNotNull(vacancyRepositoryAll.get(0));
    }
}
