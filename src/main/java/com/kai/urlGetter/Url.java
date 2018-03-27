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

    public Configuration getConfig() {
        return config;
    }

    public void setConfig(Configuration config) {
        this.config = config;
    }

    public int getPageNumber() {
        return pageNumber;
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
            addCategories(completeUrl);
        }

        return completeUrl.toString();
    }

    private void addCategories(StringBuilder incompleteUrl) {
        incompleteUrl.append("tags=");
        ArrayList<String> categories = getDesiredCategories();
        for (int i=0; i<categories.size(); i++) {
            if (i != 0)
                incompleteUrl.append("+");
            incompleteUrl.append(categories.get(i));
        }
    }
}
