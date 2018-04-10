package com.kai.danbooruManager;

import java.util.ArrayList;
import java.util.Collection;

public class Configuration {

    private ArrayList<String> desiredCategories;
    private ArrayList<String> forbiddenCategories;
    private String imageTarget;

    public Configuration() {
        desiredCategories = new ArrayList<String>();
        forbiddenCategories = new ArrayList<String>();
        imageTarget = "./";
    }

    public Configuration(ArrayList<String> desiredCategories, ArrayList<String> forbiddenCategories) {
        this.desiredCategories = desiredCategories;
        this.forbiddenCategories = forbiddenCategories;
        imageTarget = "./";
    }

    public String getImageTarget() {
        return imageTarget;
    }

    public void setImageTarget(String imageTarget) {
        this.imageTarget = imageTarget + "/";
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

    public void addForbiddenCategories(Collection<String> categories) { forbiddenCategories.addAll(categories); }

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

    public boolean validate(Post p) {
        boolean res = true;
        for (String category : p.getTags()) {
            res = res && !forbiddenCategories.contains(category);
        }
        return res;
    }
}
