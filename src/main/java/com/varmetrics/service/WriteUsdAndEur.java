package com.varmetrics.service;

import com.varmetrics.model.Eur;
import com.varmetrics.model.Usd;
import com.varmetrics.repository.EurRepository;
import com.varmetrics.repository.UsdRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.concurrent.TimeUnit;

public class WriteUsdAndEur {

    private static final Logger logger = LogManager.getLogger(WriteUsdAndEur.class);

    private final UsdRepository usdRepository;
    private final EurRepository eurRepository;
    private boolean interrupt = true;

    @Autowired
    public WriteUsdAndEur(UsdRepository usdRepository, EurRepository eurRepository) {
        this.usdRepository = usdRepository;
        this.eurRepository = eurRepository;
    }

    public void runWrite() {
        while (interrupt) {
            try {
                setUsd();
                setEur();
                TimeUnit.SECONDS.sleep(10);
            } catch (Exception ex) {
                logger.error(ex.getMessage(), ex);
            }
        }
    }

    public String interrupt() {
        this.interrupt = !this.interrupt;
        return "OK";
    }

    private void setUsd() throws IOException {

        Elements elements = getYandex()
                .select("div.b-inline.inline-stocks__item.inline-stocks__item_id_2002.hint__item.inline-stocks__part");
        String courseUsd = elements.select("span.inline-stocks__value_inner").text();

        double courseUsdDouble = Double.parseDouble(courseUsd.replace(",", "."));

        Usd usd = new Usd();
        usd.setCourse(courseUsdDouble);
        usd.setDate(ZonedDateTime.now());
        usdRepository.save(usd);
    }

    private void setEur() throws IOException {

        Elements elements = getYandex()
                .select("div.b-inline.inline-stocks__item.inline-stocks__item_id_2000.hint__item.inline-stocks__part");
        String courseEur = elements.select("span.inline-stocks__value_inner").text();

        double courseEurDouble = Double.parseDouble(courseEur.replace(",", "."));

        Eur eur = new Eur();
        eur.setCourse(courseEurDouble);
        eur.setDate(ZonedDateTime.now());
        eurRepository.save(eur);
    }

    private Document getYandex() throws IOException {
        return Jsoup
                .connect("https://yandex.ru/")
                .userAgent("Chrome/4.0.249.0 Safari/532.5")
                .referrer("http://www.google.com")
                .get();
    }
}
