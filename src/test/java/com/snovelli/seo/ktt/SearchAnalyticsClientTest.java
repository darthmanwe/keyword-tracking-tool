package com.snovelli.seo.ktt;

import com.google.api.services.webmasters.Webmasters;
import com.google.api.services.webmasters.model.SearchAnalyticsQueryResponse;
import com.snovelli.seo.KeywordTrackingToolApplication;
import com.snovelli.seo.ktt.model.DeviceType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.time.Period;
import java.util.Locale;

import static org.junit.Assert.fail;

/**
 * Created by Salvatore on 25/05/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = KeywordTrackingToolApplication.class)
public class SearchAnalyticsClientTest {

    @Value("${website.url}")
    String websiteURL;

    @Mock
    Webmasters apiClient;


    SearchAnalyticsClient sut;

    @Before
    public void init() {
        sut = new SearchAnalyticsClient(websiteURL, apiClient);
    }


    @Test
    public void performRequestTest() throws Exception {

        SearchAnalyticsQueryResponse searchAnalyticsQueryResponse =
                sut.performRequest(
                        Period.between(LocalDate.of(2016, 4, 1), LocalDate.of(2016, 5, 1)),
                        "test query",
                        Locale.ITALY,
                        DeviceType.DESKTOP);

        System.out.println(searchAnalyticsQueryResponse.toPrettyString());

        fail();

    }


}