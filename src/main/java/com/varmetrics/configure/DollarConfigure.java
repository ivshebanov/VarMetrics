package com.varmetrics.configure;

import com.varmetrics.repository.EurRepository;
import com.varmetrics.repository.UsdRepository;
import com.varmetrics.service.WriteUsdAndEur;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DollarConfigure {

    @Bean
    public WriteUsdAndEur writeUsdAndEur(UsdRepository usdRepository, EurRepository eurRepository){
        return new WriteUsdAndEur(usdRepository, eurRepository);
    }
}
