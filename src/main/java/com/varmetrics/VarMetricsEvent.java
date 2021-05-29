package com.varmetrics;

public enum VarMetricsEvent {

    //LogEvent
    VAR_METRICS_0("Поиск вакансий по запросу: {}, на сайтах: {}"),
    VAR_METRICS_1("Найдено вакансий: {} за: {} секунд"),
    VAR_METRICS_2("Количество страниц с вакансиями: {}"),
    VAR_METRICS_3("Сканируем страницу №: {}, url: {}"),
    VAR_METRICS_4("Найдено: {} вакансий на странице №: {}"),
    VAR_METRICS_5("На hh найдено: {} вакансии"),
    VAR_METRICS_6("На SuperJob найдено: {} вакансии"),
    VAR_METRICS_7("Найдено компаний: {}, запущено потоков: {}"),
    VAR_METRICS_8("Запущено дочерних потоков в PooledExecuteHeadHunter: {}"),
    VAR_METRICS_9("Колличество страниц pageSelection: {}"),
    VAR_METRICS_10("Не удалось создать страницу landingPage url: {}"),
    VAR_METRICS_11("Елемент vacancies не найден"),

    //ERROR
    VAR_METRICS_ERROR_1("Ошибка завершения потока: {}"),
    VAR_METRICS_ERROR_2("Неизвестная ошибка: {}"),
    VAR_METRICS_ERROR_3("Время ожидания истекло: {}"),
    VAR_METRICS_ERROR_4("Не удалось получить страницу вакансий: {}"),
    ;

    private final String title;

    VarMetricsEvent(String title) {
        this.title = title;
    }

    public String getCode() {
        return this.name();
    }

    public String getText() {
        return String.format("%s: %s", getCode(), title);
    }
}
