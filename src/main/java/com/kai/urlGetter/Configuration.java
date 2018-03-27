package com.kai.urlGetter;

import java.util.ArrayList;

public class Configuration {

    private ArrayList<String> desiredCategories;
    private ArrayList<String> forbiddenCategories;

    public Configuration() {
        desiredCategories = new ArrayList<String>();
        forbiddenCategories = new ArrayList<String>();
    }

    public Configuration(ArrayList<String> desiredCategories, ArrayList<String> forbiddenCategories) {
        this.desiredCategories = desiredCategories;
        this.forbiddenCategories = forbiddenCategories;
    }

    public void addDesiredCategory(String category) {
        desiredCategories.add(category);
    }

    public void removeDesiredCategory(String oldCategory) {
        for (String category : desiredCategories) {
            if (category.equals(oldCategory))
                desiredCategories.remove(category);
        }
    }

    public void addForbiddenCategory(String category) {
        forbiddenCategories.add(category);
    }

    public void removeForbiddenCategory(String oldCategory) {
        for (String category : desiredCategories) {
            if (category.equals(oldCategory))
                desiredCategories.remove(category);
        }
    }

    public ArrayList<String> getDesiredCategories() {
        return desiredCategories;
    }

    public ArrayList<String> getForbiddenCategories() {
        return forbiddenCategories;
    }

    public boolean isEmpty() {
        return desiredCategories.isEmpty();
    }
}
