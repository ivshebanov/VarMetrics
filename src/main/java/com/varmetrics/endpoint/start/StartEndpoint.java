package com.varmetrics.endpoint.start;

import com.varmetrics.service.WriteUsdAndEur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class StartEndpoint {

    private final WriteUsdAndEur writeUsdAndEur;

    @Value("${spring.profiles.active}")
    private String profile;

    @Autowired
    public StartEndpoint(WriteUsdAndEur writeUsdAndEur) {
        this.writeUsdAndEur = writeUsdAndEur;
    }

    @GetMapping
    public String main(Model model) {

        model.addAttribute("isDevMode", "1DEV".equals(profile));

        return "index";
    }

    @GetMapping("/interrupt")
    public String interrupt() {
        return writeUsdAndEur.interrupt();
    }
}
