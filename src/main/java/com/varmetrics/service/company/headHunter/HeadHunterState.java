package com.varmetrics.service.company.headHunter;

import java.util.concurrent.atomic.AtomicInteger;

public class HeadHunterState {

    private final String urlFormat = "http://hh.ru/search/vacancy?text=%s&page=%d";
    private volatile AtomicInteger pageLastNumber = new AtomicInteger();
    private String searchString;

    public int getPageLastNumber() {
        return pageLastNumber.get();
    }

    public int getAndDecrement() {
        return pageLastNumber.getAndDecrement();
    }

    public void setPageLastNumber(int pageLastNumber) {
        this.pageLastNumber.set(pageLastNumber);
    }

    public String getSearchString() {
        return searchString;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }

    public String getUrlFormat() {
        return urlFormat;
    }
}
