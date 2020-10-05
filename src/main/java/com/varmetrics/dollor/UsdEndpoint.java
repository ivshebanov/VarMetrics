package com.varmetrics.dollor;

import com.varmetrics.model.Usd;
import com.varmetrics.service.WriteUsdAndEur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/usd")
public class UsdEndpoint {

    private final UsdService usdService;
    private final WriteUsdAndEur writeUsdAndEur;

    @Autowired
    public UsdEndpoint(UsdService usdService, WriteUsdAndEur writeUsdAndEur) {
        this.usdService = usdService;
        this.writeUsdAndEur = writeUsdAndEur;
    }

    @GetMapping("/all")
    public List<Usd> getAllUsd() {
        return usdService.getAllUsd();
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
