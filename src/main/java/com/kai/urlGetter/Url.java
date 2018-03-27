package com.kai.urlGetter;

public class Url {

    private String baseUrl;
    private int pageNumber;
    private Configuration config;

    public Url(String baseUrl, int pageNumber, Configuration config) {
        this.baseUrl = baseUrl;
        this.pageNumber = pageNumber;
        this.config = config;
    }

    public Url(String baseUrl) {
        this.baseUrl = baseUrl;
        this.pageNumber = 0;
        this.config = new Configuration();
    }

    public Url(String baseUrl, int pageNumber) {
        this.baseUrl = baseUrl;
        this.pageNumber = pageNumber;
        this.config = new Configuration();
    }

    @Override
    public String toString() {
        StringBuffer completeUrl = new StringBuffer();
        completeUrl.append(baseUrl);

        if (!config.isEmpty()) {
            completeUrl.append("/posts?"); //TODO add page url generation.
        }

        return completeUrl.toString();
    }
}
