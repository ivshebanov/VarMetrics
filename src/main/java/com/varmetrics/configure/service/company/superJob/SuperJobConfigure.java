package com.varmetrics.configure.service.company.superJob;

import com.varmetrics.service.company.superJob.PooledExecuteSuperJob;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SuperJobConfigure {

    @Bean
    public PooledExecuteSuperJob superJob() {
        return new PooledExecuteSuperJob();
    }
}
