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
        // WHEN
        double usd = currency.getUsd();

        // THEN
        Assert.assertTrue(usd != 0);
    }

    @Test
    public void getEurTest() throws IOException {
        // WHEN
        double eur = currency.getEur();

        // THEN
        Assert.assertTrue(eur != 0);
    }
}
