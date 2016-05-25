package com.snovelli.seo.ktt.model;

/**
 * Created by Salvatore on 25/05/2016.
 */
public enum DeviceType {
    DESKTOP("DESKTOP"), MOBILE("MOBILE");

    private final String name;

    DeviceType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
