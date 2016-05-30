package com.snovelli.seo.ktt.service;

import com.google.api.services.webmasters.Webmasters;
import com.google.api.services.webmasters.model.*;
import com.snovelli.seo.ktt.domain.DeviceType;
import com.snovelli.seo.ktt.domain.KeywordPosition;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE;


public class GoogleKeywordTrackingService implements KeywordTrackingService {

    private static final String DATE = "date";
    private static final String PAGE = "page";
    private static final String COUNTRY = "country";
    private static final String DEVICE = "device";
    private final String websiteURL;
    private final Webmasters apiClient;
    private final List<String> keys = Arrays.asList(DATE, PAGE, COUNTRY, DEVICE);


    public GoogleKeywordTrackingService(String websiteURL, Webmasters apiClient) {
        this.websiteURL = websiteURL;
        this.apiClient = apiClient;
    }


    @Override
    public List<KeywordPosition> performRequest(
            LocalDate start,
            LocalDate end,
            String query,
            Locale locale,
            DeviceType deviceType) throws IOException {

        SearchAnalyticsQueryRequest queryRequest = new SearchAnalyticsQueryRequest();


        queryRequest
                .setStartDate(start.format(ISO_LOCAL_DATE))
                .setEndDate(end.format(ISO_LOCAL_DATE))
                .setDimensionFilterGroups(buildFilterGroups(query, locale, deviceType))
                .setDimensions(keys);


        Webmasters.Searchanalytics searchAnalytics = apiClient.searchanalytics();


        Webmasters.Searchanalytics.Query saQuery = searchAnalytics.query(websiteURL, queryRequest);


        SearchAnalyticsQueryResponse retponse = saQuery.execute();

        if (retponse.getRows() == null) return Collections.emptyList();


        return retponse.getRows().stream().map(this::toKeywordPosition).collect(Collectors.toList());

    }


    private KeywordPosition toKeywordPosition(ApiDataRow apiDataRow) {

        return new KeywordPosition(
                apiDataRow.getClicks(),
                apiDataRow.getCtr(),
                apiDataRow.getImpressions(),
                apiDataRow.getPosition(),
                apiDataRow.getKeys().get(keys.indexOf(DATE)),
                apiDataRow.getKeys().get(keys.indexOf(PAGE)),
                apiDataRow.getKeys().get(keys.indexOf(COUNTRY)),
                apiDataRow.getKeys().get(keys.indexOf(DEVICE)));
    }


    private List<ApiDimensionFilterGroup> buildFilterGroups(String query, Locale locale, DeviceType deviceType) {


        ApiDimensionFilterGroup filter = new ApiDimensionFilterGroup();
        filter.setFilters(Arrays.asList(
                new ApiDimensionFilter().setDimension("query").setOperator("equals").setExpression(query),
                new ApiDimensionFilter().setDimension("country").setExpression(locale.getISO3Country())
                //,new ApiDimensionFilter().setDimension("device").setExpression(deviceType.getName())
        ));


        return Collections.singletonList(filter);
    }

}