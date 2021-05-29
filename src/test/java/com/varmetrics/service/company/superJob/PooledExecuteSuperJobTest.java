package com.varmetrics.service.company.superJob;


import com.varmetrics.dao.model.Vacancy;
import com.varmetrics.service.company.Company;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class PooledExecuteSuperJobTest {

    @Test
    public void call() throws ExecutionException, InterruptedException {
        // GIVEN

        final ExecutorService executorService = Executors.newCachedThreadPool();
        Company superJob = new PooledExecuteSuperJob();

        // WHEN
        final Future<List<Vacancy>> submit = executorService.submit(superJob);

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