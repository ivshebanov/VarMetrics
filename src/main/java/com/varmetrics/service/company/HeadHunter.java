package com.varmetrics.service.company;

import com.varmetrics.dao.model.Vacancy;
import com.varmetrics.service.DaemonThreadFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static com.varmetrics.VarMetricsLogEvent.VAR_METRICS_2;
import static com.varmetrics.VarMetricsLogEvent.VAR_METRICS_3;
import static com.varmetrics.VarMetricsLogEvent.VAR_METRICS_4;
import static com.varmetrics.VarMetricsLogEvent.VAR_METRICS_5;
import static com.varmetrics.VarMetricsLogEvent.VAR_METRICS_6;
import static com.varmetrics.VarMetricsLogEvent.VAR_METRICS_7;
import static com.varmetrics.VarMetricsLogEvent.VAR_METRICS_8;
import static com.varmetrics.VarMetricsLogEvent.VAR_METRICS_9;

@Component
public class HeadHunter extends Company {

    private static final Logger logger = LoggerFactory.getLogger(HeadHunter.class);
    private static final String URL_FORMAT = "http://hh.ru/search/vacancy?text=%s&page=%d";
    private static final String SITE_NAME = "hh.ru";

    @Override
    public List<Vacancy> getVacancies(String searchString) {

        ExecutorService executorService = Executors.newFixedThreadPool(3, new DaemonThreadFactory());
        List<Vacancy> resultList = Collections.synchronizedList(new LinkedList<>());
        AtomicInteger pageNumber = new AtomicInteger(0);
        AtomicInteger pageLastNumber = new AtomicInteger(getPageLastNumber(searchString));

        logger.debug(VAR_METRICS_2.getText(), pageLastNumber);

        try {
            for (int i = 0; i < 3; i++) {
                executorService.execute(() -> {
                    List<Vacancy> resultListLocal = new LinkedList<>();
                    int pageNumberLocal = 0;
                    int pageLastNumberLocal = pageLastNumber.get();

                    while (pageNumberLocal != pageLastNumberLocal) {
                        pageNumberLocal = pageNumber.getAndIncrement();
                        String url = String.format(URL_FORMAT, replaceSpaceWithPlus(searchString), pageNumberLocal);
                        logger.debug(VAR_METRICS_3.getText(), pageNumberLocal, url);
                        Document landingPage = getDocument(url);
                        if (landingPage == null) break;

                        Elements vacancies = landingPage.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy");
                        if (vacancies == null || vacancies.isEmpty()) break;
                        logger.debug(VAR_METRICS_4.getText(), vacancies.size(), pageNumberLocal);
                        for (Element vacancyEl : vacancies) {
                            if (vacancyEl == null) break;
                            Vacancy vacancy = getVacancy(vacancyEl);
                            resultListLocal.add(vacancy);
                        }
                    }
                    resultList.addAll(resultListLocal);
                });
            }

            executorService.shutdown();
            while (!executorService.awaitTermination(1L, TimeUnit.MINUTES)) {
                System.out.println("Not yet. Still waiting for termination");
            }
        } catch (InterruptedException e) {
            logger.debug(VAR_METRICS_8.getText());
        } catch (Exception e) {
            logger.debug(VAR_METRICS_9.getText());
        } finally {
            executorService.shutdownNow();
        }
        logger.debug(VAR_METRICS_5.getText(), resultList.size());
        return resultList;
    }

    private synchronized Vacancy getVacancy(Element element) {
        Element title = element.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-title").first();
        Element companyName = element.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-employer").first();
        Element salary = element.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-compensation").first();
        Element location = element.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-address").first();
        Element dateVacancy = element.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-date").first();
        Element logo = element.getElementsByAttributeValue("class", "vacancy-serp-item__logo").first();

        Vacancy vacancy = new Vacancy();
        vacancy.setTitle(getTextOrEmpty(title));
        vacancy.setCompanyName(getTextOrEmpty(companyName));
        vacancy.setCompanyLogo(isNull(logo) ? "" : logo.attr("src"));
        vacancy.setSalary(getTextOrEmpty(salary));
        vacancy.setLocation(getTextOrEmpty(location));
        vacancy.setSiteName(SITE_NAME);
        vacancy.setUrl(isNull(title) ? "" : title.attr("href"));
        vacancy.setDateVacancy(getTextOrEmpty(dateVacancy));
        vacancy.setDate(ZonedDateTime.now(ZoneId.of("Europe/Moscow")));
        return vacancy;
    }

    private int getPageLastNumber(String searchString) {

        String url = String.format(URL_FORMAT, replaceSpaceWithPlus(searchString), 0);
        Document landingPage = getDocument(url);
        if (landingPage == null) return 0;

        Elements pageSelection = landingPage.getElementsByAttributeValue("data-qa", "pager-page");
        Element pageLastElement = pageSelection.get(pageSelection.size() - 1);
        String pageLastString = pageLastElement.attr("data-page");
        return Integer.parseInt(pageLastString);
    }

    private Document getDocument(String url) {
        try {
            return Jsoup
                    .connect(url)
                    .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.365")
                    .referrer("http://google.ru")
                    .timeout(20000)
                    .get();
        } catch (SocketTimeoutException e) {
            logger.error(e.getMessage(), VAR_METRICS_6.getText());
        } catch (IOException e) {
            logger.error(e.getMessage(), VAR_METRICS_7.getText());
        }
        return null;
    }

    private String replaceSpaceWithPlus(String searchString) {
        return searchString.replace(" ", "+");
    }

    private String getTextOrEmpty(Element element) {
        return isNull(element) ? "" : element.text();
    }

    private boolean isNull(Object obj) {
        return obj == null;
    }

    @Override
    public String toString() {
        return "HeadHunter";
    }
}
