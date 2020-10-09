package com.varmetrics.configure;

import com.varmetrics.repository.EurRepository;
import com.varmetrics.repository.UsdRepository;
import com.varmetrics.service.WriteUsdAndEur;
import com.varmetrics.service.сurrency.CurrencyFromYandex;
import com.varmetrics.service.сurrency.Currency;
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
