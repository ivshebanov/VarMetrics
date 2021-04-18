package com.varmetrics.service.company;

import com.varmetrics.dao.model.Vacancy;

import java.util.List;
import java.util.concurrent.Callable;

public abstract class Company implements Callable<List<Vacancy>> {

    public abstract void setSearchString(String searchString);
}
