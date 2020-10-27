package service.currency;

import com.varmetrics.model.Eur;
import com.varmetrics.model.Usd;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class JdbcConnectionDevTest extends SpringDevContextTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void dataSourceCheck() {
        jdbcTemplate.execute("SELECT 1 FROM DUAL");
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
