package context;

import com.varmetrics.Application;
import com.varmetrics.endpoint.dollor.UsdEndpoint;
import com.varmetrics.endpoint.euro.EurEndpoint;
import com.varmetrics.endpoint.vacancy.VacancyEndpoint;
import com.varmetrics.service.сurrency.WriteUsdAndEur;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@ActiveProfiles("DEV")
public class SpringDevContextTest {

    @Autowired
    private WriteUsdAndEur writeUsdAndEur;

    @Autowired
    private EurEndpoint eurEndpoint;

    @Autowired
    private UsdEndpoint usdEndpoint;

    @Autowired
    private VacancyEndpoint vacancyEndpoint;

    @Test
    public void testInit() {
        Assert.assertNotNull(writeUsdAndEur);
        Assert.assertNotNull(eurEndpoint);
        Assert.assertNotNull(usdEndpoint);
        Assert.assertNotNull(vacancyEndpoint);
    }
}
