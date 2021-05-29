package com.varmetrics.configure.service.company.headHunter;

import com.varmetrics.service.DaemonThreadFactory;
import com.varmetrics.service.company.headHunter.ExecuteHeadHunter;
import com.varmetrics.service.company.headHunter.HeadHunterState;
import com.varmetrics.service.company.headHunter.PooledExecuteHeadHunter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;

@Configuration
public class HeadHunterConfigure {

    @Bean
    public PooledExecuteHeadHunter pooledExecuteHeadHunter(Supplier<ExecuteHeadHunter> executeHeadHunter,
                                                           HeadHunterState headHunterState,
                                                           ExecutorService executorServiceDaemonThread) {

        return new PooledExecuteHeadHunter(executeHeadHunter, headHunterState, executorServiceDaemonThread);
    }

    @Bean
    public Supplier<ExecuteHeadHunter> executeHeadHunter(HeadHunterState headHunterState) {
        return () -> new ExecuteHeadHunter(headHunterState);
    }

    @Bean
    public HeadHunterState headHunterState() {
        return new HeadHunterState();
    }

    @Bean
    public ExecutorService executorService() {
        return Executors.newCachedThreadPool();
    }

    @Bean
    public ExecutorService executorServiceDaemonThread() {
        return Executors.newFixedThreadPool(3, new DaemonThreadFactory());
    }
}
