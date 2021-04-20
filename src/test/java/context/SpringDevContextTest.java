package context;

import com.varmetrics.Application;
import com.varmetrics.endpoint.vacancy.VacancyEndpoint;
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
    private VacancyEndpoint vacancyEndpoint;

    @Test
    public void testInit() {
        Assert.assertNotNull(vacancyEndpoint);
    }
}
