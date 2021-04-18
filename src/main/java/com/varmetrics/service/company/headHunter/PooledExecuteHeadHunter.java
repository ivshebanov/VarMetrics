package com.varmetrics.service.company.headHunter;

import com.varmetrics.dao.model.Vacancy;
import com.varmetrics.service.DaemonThreadFactory;
import com.varmetrics.service.company.Company;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Supplier;

import static com.varmetrics.VarMetricsLogEvent.VAR_METRICS_2;
import static com.varmetrics.VarMetricsLogEvent.VAR_METRICS_5;
import static com.varmetrics.VarMetricsLogEvent.VAR_METRICS_ERROR_1;
import static com.varmetrics.VarMetricsLogEvent.VAR_METRICS_ERROR_2;
import static com.varmetrics.VarMetricsLogEvent.VAR_METRICS_ERROR_3;
import static com.varmetrics.VarMetricsLogEvent.VAR_METRICS_ERROR_4;

@Component
public class PooledExecuteHeadHunter extends Company {

    private static final Logger logger = LoggerFactory.getLogger(PooledExecuteHeadHunter.class);
    private final ExecutorService executorService = Executors.newFixedThreadPool(3, new DaemonThreadFactory());
    private final Supplier<ExecuteHeadHunter> executeHeadHunter;
    private final HeadHunterState headHunterState;

    @Autowired
    public PooledExecuteHeadHunter(Supplier<ExecuteHeadHunter> executeHeadHunter,
                                   HeadHunterState headHunterState) {

        this.executeHeadHunter = executeHeadHunter;
        this.headHunterState = headHunterState;
    }

    @Override
    public List<Vacancy> call() throws Exception {
        this.headHunterState.setPageLastNumber(getPageLastNumber());
        logger.debug(VAR_METRICS_2.getText(), headHunterState.getPageLastNumber());

        List<Vacancy> resultList = new LinkedList<>();
        List<Future<List<Vacancy>>> submits = new LinkedList<>();

        try {
            for (int i = 0; i < 3; i++) {
                ExecuteHeadHunter executeHeadHunter = this.executeHeadHunter.get();
                submits.add(executorService.submit(executeHeadHunter));
            }

            for (Future<List<Vacancy>> submit: submits) {
                resultList.addAll(submit.get());
            }
        } catch (InterruptedException ex) {
            logger.debug(VAR_METRICS_ERROR_1.getText(), ex.getMessage());
        } catch (Exception ex) {
            logger.debug(VAR_METRICS_ERROR_2.getText(), ex.getMessage());
        } finally {
            this.headHunterState.setPageLastNumber(0);
        }
        logger.debug(VAR_METRICS_5.getText(), resultList.size());
        return resultList;
    }

    private int getPageLastNumber() {

        String url = String.format(headHunterState.getUrlFormat(), headHunterState.getSearchString(), 0);
        Document landingPage = getDocument(url);
        //TODO: логировать url, landingPage == null
        if (landingPage == null) return 0;

        Elements pageSelection = landingPage.select("span.pager-item-not-in-short-range > span.pager-item-not-in-short-range > a");
        //TODO: логировать pageSelection.size()
        if (pageSelection.size() == 0) return 0;
        String pageLastString = pageSelection.text();
        //TODO: логировать pageLastString
        return Integer.parseInt(pageLastString);
    }

    private Document getDocument(String url) {
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

    private String replaceSpaceWithPlus(String searchString) {
        return searchString.replace(" ", "+");
    }

    @Override
    public void setSearchString(String searchString) {
        this.headHunterState.setSearchString(replaceSpaceWithPlus(searchString));
    }

    @Override
    public String toString() {
        return "HeadHunter";
    }
}
