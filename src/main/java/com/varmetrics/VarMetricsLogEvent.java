package com.varmetrics;

public enum VarMetricsLogEvent {

    //VacancyService
    VAR_METRICS_0("Поиск вакансий: {}"),
    VAR_METRICS_1("Найдено вакансий: {}"),

    //HeadHunter
    VAR_METRICS_2("Количество страниц с вакансиями: {}"),
    VAR_METRICS_3("Сканируем страницу №: {}, url: {}"),
    VAR_METRICS_4("Найдено: {} вакансий на странице №: {}"),
    VAR_METRICS_5("На hh найдено: {} вакансии"),
    VAR_METRICS_6("Время ожидания истекло"),
    VAR_METRICS_7("Не удалось получить страницу вакансий"),
    ;

    private final String title;

    VarMetricsLogEvent(String title) {
        this.title = title;
    }

    public String getCode() {
        return this.name();
    }

    public String getText() {
        return String.format("%s: %s", getCode(), title);
    }
}
