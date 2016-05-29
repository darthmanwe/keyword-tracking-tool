package com.snovelli.seo.ktt.config;

public final class Constants {

    public static final String SPRING_PROFILE_DEVELOPMENT = "dev";
    public static final String SPRING_PROFILE_HEROKU = "heroku";
    public static final String SPRING_PROFILE_UNIT_TESTING = "JUNIT";

    private Constants() {
        throw new UnsupportedOperationException("Y U NO INSTANTIATE THIS!");
    }
}
