package context;

import com.varmetrics.Application;
import com.varmetrics.endpoint.vacancy.VacancyEndpoint;
import com.varmetrics.endpoint.vacancy.VacancyService;
import com.varmetrics.service.company.headHunter.ExecuteHeadHunter;
import com.varmetrics.service.company.headHunter.HeadHunterState;
import com.varmetrics.service.company.headHunter.PooledExecuteHeadHunter;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ExecutorService;
import java.util.function.Supplier;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@ActiveProfiles("DEV")
public class SpringDevContext {

    @Autowired
    private VacancyEndpoint vacancyEndpoint;
    @Autowired
    private VacancyService vacancyService;
    @Autowired
    private Supplier<ExecuteHeadHunter> executeHeadHunter;
    @Autowired
    private PooledExecuteHeadHunter pooledExecuteHeadHunter;
    @Autowired
    private HeadHunterState headHunterState;
    @Autowired
    private ExecutorService executorService;
    @Autowired
    private ExecutorService executorServiceDaemonThread;

    @Test
    public void testInit() {
        Assert.assertNotNull(vacancyEndpoint);
        Assert.assertNotNull(vacancyService);
        Assert.assertNotNull(executeHeadHunter);
        Assert.assertNotNull(pooledExecuteHeadHunter);
        Assert.assertNotNull(headHunterState);
        Assert.assertNotNull(executorService);
        Assert.assertNotNull(executorServiceDaemonThread);
    }
}
