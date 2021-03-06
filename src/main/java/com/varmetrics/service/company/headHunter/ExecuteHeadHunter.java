package com.varmetrics.service.company.headHunter;

import com.varmetrics.dao.model.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;

import static com.varmetrics.VarMetricsEvent.VAR_METRICS_10;
import static com.varmetrics.VarMetricsEvent.VAR_METRICS_11;
import static com.varmetrics.VarMetricsEvent.VAR_METRICS_3;
import static com.varmetrics.VarMetricsEvent.VAR_METRICS_4;
import static com.varmetrics.VarMetricsEvent.VAR_METRICS_ERROR_3;
import static com.varmetrics.VarMetricsEvent.VAR_METRICS_ERROR_4;
import static org.apache.commons.lang3.Validate.notNull;

public class ExecuteHeadHunter implements Callable<List<Vacancy>> {

    private static final Logger logger = LoggerFactory.getLogger(ExecuteHeadHunter.class);
    private final HeadHunterState headHunterState;
    private volatile boolean isShutdown = false;

    public ExecuteHeadHunter(HeadHunterState headHunterState) {
        this.headHunterState = notNull(headHunterState, "headHunterState must not be null");
    }

    @Override
    public List<Vacancy> call() throws Exception {
        List<Vacancy> resultList = new LinkedList<>();
        String urlFormat = headHunterState.getUrlFormat();
        int pageNumber;

        while ((pageNumber = headHunterState.getAndDecrement()) >= 0 && !isShutdown) {
            String url = String.format(urlFormat, headHunterState.getSearchString(), pageNumber);
            logger.debug(VAR_METRICS_3.getText(), pageNumber, url);
            Document landingPage = getDocument(url);
            if (landingPage == null) {
                logger.debug(VAR_METRICS_10.getText(), url);
                break;
            }

            Elements vacancies = landingPage.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy vacancy-serp__vacancy_standard");
            if (vacancies == null || vacancies.isEmpty()) {
                logger.debug(VAR_METRICS_11.getText());
                break;
            }
            logger.debug(VAR_METRICS_4.getText(), vacancies.size(), pageNumber);
            for (Element vacancyEl : vacancies) {
                if (vacancyEl == null) {
                    break;
                }
                Vacancy vacancy = getVacancy(vacancyEl);
                resultList.add(vacancy);
            }
        }

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
        vacancy.setSiteName("hh.ru");
        vacancy.setUrl(isNull(title) ? "" : title.attr("href"));
        vacancy.setDateVacancy(getTextOrEmpty(dateVacancy));
        vacancy.setDate(ZonedDateTime.now(ZoneId.of("Europe/Moscow")));
        return vacancy;
    }

    private synchronized Document getDocument(String url) {
        try {
            return Jsoup
                    .connect(url)
                    .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.72 Safari/537.36")
                    .referrer("http://google.ru")
                    .timeout(20000)
                    .get();
        } catch (SocketTimeoutException ex) {
            logger.error(VAR_METRICS_ERROR_3.getText(), ex.getMessage());
        } catch (IOException ex) {
            logger.error(VAR_METRICS_ERROR_4.getText(), ex.getMessage());
        }
        return null;
    }

    private String getTextOrEmpty(Element element) {
        return isNull(element) ? "" : element.text();
    }

    private boolean isNull(Object obj) {
        return obj == null;
    }

    public void shutdown() {
        isShutdown = true;
    }
}
