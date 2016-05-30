package com.snovelli.seo.ktt.service;

import com.snovelli.seo.ktt.domain.DeviceType;
import com.snovelli.seo.ktt.domain.KeywordPosition;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

public interface KeywordTrackingService {

    List<KeywordPosition> performRequest(
            LocalDate start,
            LocalDate end,
            String query,
            Locale locale,
            DeviceType deviceType) throws IOException;
}
