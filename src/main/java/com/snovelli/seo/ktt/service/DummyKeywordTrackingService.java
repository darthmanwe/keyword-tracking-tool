package com.snovelli.seo.ktt.service;

import com.snovelli.seo.ktt.domain.DeviceType;
import com.snovelli.seo.ktt.domain.KeywordPosition;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

/**
 * Created by Salvatore on 29/05/2016.
 */
public class DummyKeywordTrackingService implements KeywordTrackingService {
    @Override
    public List<KeywordPosition> performRequest(LocalDate start, LocalDate end, String query, Locale locale, DeviceType deviceType) throws IOException {
        throw new AssertionError("This is a dummy, you should not call this.");
    }
}
