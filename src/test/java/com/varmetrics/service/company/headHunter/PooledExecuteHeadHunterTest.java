package com.varmetrics.service.company.headHunter;

import com.varmetrics.dao.model.Vacancy;
import com.varmetrics.service.DaemonThreadFactory;
import com.varmetrics.service.company.Company;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class PooledExecuteHeadHunterTest {

    @Test
    void call() throws ExecutionException, InterruptedException {
        // GIVEN

        final ExecutorService executorService = Executors.newCachedThreadPool();
        final ExecutorService executorDaemonThread = Executors.newFixedThreadPool(3, new DaemonThreadFactory());
        final HeadHunterState headHunterState = new HeadHunterState();

        Company headHunter = new PooledExecuteHeadHunter(() -> new ExecuteHeadHunter(headHunterState),
                                                         headHunterState,
                                                         executorDaemonThread);

        // WHEN
        final Future<List<Vacancy>> submit = executorService.submit(headHunter);

        final List<Vacancy> vacancies = submit.get();

        // THEN
        Assert.assertNotNull(vacancies);
        Assert.assertNotNull(vacancies.get(0));
        Assert.assertNotNull(vacancies.get(0).getTitle());
        Assert.assertNotNull(vacancies.get(0).getCompanyName());
        Assert.assertNotNull(vacancies.get(0).getSiteName());
        Assert.assertNotNull(vacancies.get(0).getUrl());
        Assert.assertNotNull(vacancies.get(0).getDate());
    }
}