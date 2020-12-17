import com.varmetrics.dao.model.Eur;
import com.varmetrics.dao.model.Usd;
import com.varmetrics.dao.model.Vacancy;
import com.varmetrics.dao.repository.EurRepository;
import com.varmetrics.dao.repository.UsdRepository;
import com.varmetrics.dao.repository.VacancyRepository;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import context.SpringDevContextTest;

import java.util.List;

public class JdbcConnectionDevTest extends SpringDevContextTest {

    @Autowired
    public VacancyRepository vacancyRepository;

    @Autowired
    public UsdRepository usdRepository;

    @Autowired
    public EurRepository eurRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void dataSourceCheck() {
        jdbcTemplate.execute("SELECT 1 FROM DUAL");
    }

    @Test
    public void vacancyRepositoryCheck() {
        List<Vacancy> vacancyRepositoryAll = vacancyRepository.findAll();

        Assert.assertNotNull(vacancyRepositoryAll);
        Assert.assertNotNull(vacancyRepositoryAll.get(0));
    }

    @Test
    public void usdRepositoryCheck() {
        List<Usd> usdRepositoryAll = usdRepository.findAll();

        Assert.assertNotNull(usdRepositoryAll);
        Assert.assertNotNull(usdRepositoryAll.get(0));
    }

    @Test
    public void eurRepositoryCheck() {
        List<Eur> eurRepositoryAll = eurRepository.findAll();

        Assert.assertNotNull(eurRepositoryAll);
        Assert.assertNotNull(eurRepositoryAll.get(0));
    }
}
