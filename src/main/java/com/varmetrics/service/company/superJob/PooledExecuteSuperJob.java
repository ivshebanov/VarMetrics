package com.varmetrics.service.company.superJob;

import com.varmetrics.dao.model.Vacancy;
import com.varmetrics.service.company.Company;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class PooledExecuteSuperJob extends Company {

    private static final Logger logger = LoggerFactory.getLogger(PooledExecuteSuperJob.class);


    @Override
    public String toString() {
        return "SuperJob";
    }

    @Override
    public void setSearchString(String searchString) {

    }

    @Override
    public List<Vacancy> call() throws Exception {
        return null;
    }
}
