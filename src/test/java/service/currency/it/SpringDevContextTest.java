package service.currency.it;

import com.varmetrics.Application;
import com.varmetrics.repository.EurRepository;
import com.varmetrics.repository.UsdRepository;
import com.varmetrics.service.WriteUsdAndEur;
import com.varmetrics.service.сurrency.Currency;
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
    public UsdRepository usdRepository;

    @Autowired
    public EurRepository eurRepository;

    @Autowired
    private Currency currencyFromYandex;

    @Autowired
    private WriteUsdAndEur writeUsdAndEur;


    @Test
    public void testInit() {
        Assert.assertNotNull(usdRepository);
        Assert.assertNotNull(eurRepository);
        Assert.assertNotNull(currencyFromYandex);
        Assert.assertNotNull(writeUsdAndEur);
    }
}
