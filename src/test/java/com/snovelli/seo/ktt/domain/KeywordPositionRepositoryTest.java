package com.snovelli.seo.ktt.domain;

import com.snovelli.seo.Application;
import com.snovelli.seo.ktt.config.Constants;
import com.snovelli.seo.ktt.repository.KeywordPositionRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@ActiveProfiles(Constants.SPRING_PROFILE_UNIT_TESTING)
@WebAppConfiguration
public class KeywordPositionRepositoryTest {


    @Autowired
    private KeywordPositionRepository repository;


    @Test
    public void testInMemoryDatabase() {
        KeywordPosition saved = repository.save(new KeywordPosition());
        assertThat(repository.findOne(saved.getId()), notNullValue());
    }

}