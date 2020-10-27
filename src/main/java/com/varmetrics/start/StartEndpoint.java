package com.varmetrics.start;

import com.varmetrics.service.WriteUsdAndEur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/start")
public class StartEndpoint {

    private final WriteUsdAndEur writeUsdAndEur;

    @Autowired
    public StartEndpoint(WriteUsdAndEur writeUsdAndEur) {
        this.writeUsdAndEur = writeUsdAndEur;
    }

    @GetMapping("/set-usd")
    public void setUsd() {
        writeUsdAndEur.runWrite();
    }

    @GetMapping("/interrupt")
    public String interrupt() {
        return writeUsdAndEur.interrupt();
    }
}
