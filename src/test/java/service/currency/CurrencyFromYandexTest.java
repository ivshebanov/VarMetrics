package service.currency;

import com.varmetrics.service.сurrency.Currency;
import com.varmetrics.service.сurrency.CurrencyFromYandex;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class CurrencyFromYandexTest {

    private final Currency currency = new CurrencyFromYandex();

    @Test
    public void getUsdTest() throws IOException {

        double usd = currency.getUsd();

        Assert.assertTrue(usd != 0);
    }

    @Test
    public void getEurTest() throws IOException {

        double eur = currency.getEur();

        Assert.assertTrue(eur != 0);
    }
}
