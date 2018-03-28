package com.kai.urlGetter;

import java.util.ArrayList;

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

    public Url(String baseUrl, Configuration config) {
        this.baseUrl = baseUrl;
        this.pageNumber = 0;
        this.config = config;
    }

    public void setConfig(Configuration config) {
        this.config = config;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    private ArrayList<String> getDesiredCategories() {
        return config.getDesiredCategories();
    }

    @Override
    public String toString() {
        StringBuilder completeUrl = new StringBuilder();
        completeUrl.append(baseUrl);

        if (!config.isEmpty()) {
            completeUrl.append("/posts?");
            addRespectivePageNumber(completeUrl);
            addCategories(completeUrl);
        }

        return completeUrl.toString();
    }

    private void addRespectivePageNumber(StringBuilder completeUrl) {
        if (pageNumber != 0) {
            completeUrl.append("page=");
            completeUrl.append(pageNumber);
            completeUrl.append('&');
        }
    }

    private void addCategories(StringBuilder incompleteUrl) {
        incompleteUrl.append("tags=");
        ArrayList<String> categories = getDesiredCategories();
        for (String category: categories) {
            if (categories.indexOf(category) != 0)
                incompleteUrl.append('+');
            incompleteUrl.append(category);
        }
    }
}
