package com.varmetrics.configure;

import com.varmetrics.service.company.superJob.PooledExecuteSuperJob;
import org.springframework.context.annotation.Bean;

public class SuperJobConfigure {

    @Bean
    public PooledExecuteSuperJob superJob() {
        return new PooledExecuteSuperJob();
    }
}
