package com.varmetrics.configure;

import com.varmetrics.service.company.headHunter.ExecuteHeadHunter;
import com.varmetrics.service.company.headHunter.HeadHunterState;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;

@Configuration
public class HeadHunterConfigure {

    @Bean
    public Supplier<ExecuteHeadHunter> executeHeadHunter(HeadHunterState headHunterState) {
        return () -> new ExecuteHeadHunter(headHunterState);
    }

    @Bean
    public ExecutorService executorService() {
        return Executors.newCachedThreadPool();
    }

    @Bean
    public HeadHunterState headHunterState() {
        return new HeadHunterState();
    }
}
