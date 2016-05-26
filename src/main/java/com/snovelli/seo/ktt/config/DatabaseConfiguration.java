package com.snovelli.seo.ktt.config;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Profile(Constants.SPRING_PROFILE_DEVELOPMENT)
@EnableJpaRepositories("com.snovelli.seo.ktt.repository")
public class DatabaseConfiguration { }
