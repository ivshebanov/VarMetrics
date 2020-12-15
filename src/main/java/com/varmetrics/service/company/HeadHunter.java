package com.varmetrics.service.company;

import com.varmetrics.dao.model.Vacancy;
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
import java.util.LinkedList;
import java.util.List;

import static com.varmetrics.VarMetricsLogEvent.VAR_METRICS_2;
import static com.varmetrics.VarMetricsLogEvent.VAR_METRICS_3;
import static com.varmetrics.VarMetricsLogEvent.VAR_METRICS_4;
import static com.varmetrics.VarMetricsLogEvent.VAR_METRICS_5;
import static com.varmetrics.VarMetricsLogEvent.VAR_METRICS_6;
import static com.varmetrics.VarMetricsLogEvent.VAR_METRICS_7;

@Component
public class HeadHunter extends Company {

    private static final Logger logger = LoggerFactory.getLogger(HeadHunter.class);
    private static final String URL_FORMAT = "http://hh.ru/search/vacancy?text=%s&page=%d";
    private static final String SITE_NAME = "hh.ru";

    @Override
    public List<Vacancy> getVacancies(String searchString) {
        List<Vacancy> resultList = new LinkedList<>();
        int pageNumber = 0;
        int pageLastNumber = getPageLastNumber(searchString);
        logger.debug(VAR_METRICS_2.getText(), pageLastNumber);

        while (pageNumber != pageLastNumber) {
            String url = String.format(URL_FORMAT, replaceSpaceWithPlus(searchString), pageNumber);
            logger.debug(VAR_METRICS_3.getText(), pageNumber, url);
            Document landingPage = getDocument(url);
            if (landingPage == null) break;

            Elements vacancies = landingPage.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy");
            if (vacancies == null || vacancies.isEmpty()) break;
            logger.debug(VAR_METRICS_4.getText(), vacancies.size(), pageNumber);
            for (Element vacancyEl : vacancies) {
                if (vacancyEl == null) break;
                Vacancy vacancy = getVacancy(vacancyEl);
                resultList.add(vacancy);
            }
            pageNumber++;
        }
        logger.debug(VAR_METRICS_5.getText(), resultList.size());
        return resultList;
    }

    private Vacancy getVacancy(Element element) {
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
                    .userAgent("Chrome/4.0.249.0 Safari/532.5")
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
}
