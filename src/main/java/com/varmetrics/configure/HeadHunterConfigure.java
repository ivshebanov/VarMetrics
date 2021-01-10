package com.varmetrics.configure;

import com.varmetrics.service.company.headHunter.ExecuteHeadHunter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Supplier;

@Configuration
public class HeadHunterConfigure {

    @Bean
    public Supplier<ExecuteHeadHunter> executeHeadHunter() {
        return ExecuteHeadHunter::new;
    }
}
