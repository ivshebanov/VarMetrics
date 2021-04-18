package com.varmetrics.service.сurrency;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class CurrencyFromYandex implements Currency {


    @Override
    public double getUsd() throws IOException {

        Elements elements = getYandex()
                .select("div.b-inline.inline-stocks__item.inline-stocks__item_id_2002.hint__item.inline-stocks__part");
        String courseUsd = elements.select("span.inline-stocks__value_inner").text();

        double courseUsdDouble = Double.parseDouble(courseUsd.replace(",", "."));

        return courseUsdDouble;
    }

    @Override
    public double getEur() throws IOException {

        Elements elements = getYandex()
                .select("div.b-inline.inline-stocks__item.inline-stocks__item_id_2000.hint__item.inline-stocks__part");
        String courseEur = elements.select("span.inline-stocks__value_inner").text();

        double courseEurDouble = Double.parseDouble(courseEur.replace(",", "."));

        return courseEurDouble;
    }

    private Document getYandex() throws IOException {
        return Jsoup
                .connect("https://yandex.ru/")
                .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.72 Safari/537.36")
                .referrer("http://www.google.com")
                .timeout(20000)
                .get();
    }
}
