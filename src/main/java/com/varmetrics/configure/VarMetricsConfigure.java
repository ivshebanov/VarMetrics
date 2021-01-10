package com.varmetrics.configure;

import com.varmetrics.dao.repository.EurRepository;
import com.varmetrics.dao.repository.UsdRepository;
import com.varmetrics.service.сurrency.WriteUsdAndEur;
import com.varmetrics.service.сurrency.Currency;
import com.varmetrics.service.сurrency.CurrencyFromYandex;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VarMetricsConfigure {

    @Bean
    public WriteUsdAndEur writeUsdAndEur(UsdRepository usdRepository, EurRepository eurRepository, Currency currency) {
        return new WriteUsdAndEur(usdRepository, eurRepository, currency);
    }

    @Bean
    public Currency currencyFromYandex() {
        return new CurrencyFromYandex();
    }
}
