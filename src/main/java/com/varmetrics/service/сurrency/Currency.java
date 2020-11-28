package com.varmetrics.service.сurrency;

import java.io.IOException;

public interface Currency {

    double getUsd() throws IOException;

    double getEur() throws IOException;
}
