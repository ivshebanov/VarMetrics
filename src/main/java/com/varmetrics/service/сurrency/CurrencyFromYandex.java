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
                .userAgent("Chrome/4.0.249.0 Safari/532.5")
                .referrer("http://www.google.com")
                .get();
    }
}
