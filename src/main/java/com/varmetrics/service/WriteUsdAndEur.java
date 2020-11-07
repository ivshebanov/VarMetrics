package com.varmetrics.service;

import com.varmetrics.dao.model.Eur;
import com.varmetrics.dao.model.Usd;
import com.varmetrics.dao.repository.EurRepository;
import com.varmetrics.dao.repository.UsdRepository;
import com.varmetrics.service.сurrency.Currency;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

public class WriteUsdAndEur {

    private static final Logger logger = LogManager.getLogger(WriteUsdAndEur.class);

    private final UsdRepository usdRepository;
    private final EurRepository eurRepository;
    private final Currency currency;
    private boolean interrupt = true;

    @Autowired
    public WriteUsdAndEur(UsdRepository usdRepository, EurRepository eurRepository, Currency currency) {
        this.usdRepository = usdRepository;
        this.eurRepository = eurRepository;
        this.currency = currency;
        runWrite();
    }

    public void runWrite() {

        Executors.newSingleThreadExecutor(new DaemonThreadFactory()).execute(() -> {

            while (interrupt) {
                try {
                    saveUsd();
                    saveEur();
                    TimeUnit.HOURS.sleep(24);
                } catch (Exception ex) {
                    logger.error(ex.getMessage(), ex);
                }
            }

        });
    }

    private void saveUsd() throws IOException {

        usdRepository.save(new Usd(ZonedDateTime.now(), currency.getUsd()));
    }

    private void saveEur() throws IOException {

        eurRepository.save(new Eur(ZonedDateTime.now(), currency.getEur()));
    }

    public String interrupt() {
        this.interrupt = !this.interrupt;
        return "OK";
    }

    class DaemonThreadFactory implements ThreadFactory {
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setDaemon(true);
            return thread;
        }
    }
}
