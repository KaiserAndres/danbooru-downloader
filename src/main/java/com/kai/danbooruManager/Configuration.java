package com.kai.danbooruManager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Vector;

public class Configuration {

    private ArrayList<String> desiredCategories;
    private ArrayList<String> forbiddenCategories;
    private String imageTarget;
    private Vector<Integer> min_res;
    private Vector<Integer> max_res;

    public final int WIDTH = 0;
    public final int HEIGHT = 1;

    public Configuration() {
        desiredCategories = new ArrayList<>();
        forbiddenCategories = new ArrayList<>();
        imageTarget = "./";
        this.min_res = new Vector<>();
        this.max_res = new Vector<>();
    }

    public void setMin_res(int pos, int value) {
        this.min_res.add(pos, value);
    }

    public void setMax_res(int pos, int value) {
        this.max_res.add(pos, value);
    }

    String getImageTarget() {
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

    ArrayList<String> getDesiredCategories() {
        return desiredCategories;
    }

    boolean isEmpty() {
        return desiredCategories.isEmpty();
    }

    boolean validate(Post p) {
        boolean res = true;
        for (String category : p.getTags())
            res = res && !forbiddenCategories.contains(category);

        if (!min_res.isEmpty())
            res = res && p.getWidth() >= min_res.get(WIDTH) && p.getHeight() >= min_res.get(HEIGHT);

        if (!max_res.isEmpty())
            res = res && p.getWidth() <= max_res.get(WIDTH) && p.getHeight() <= max_res.get(HEIGHT);

        return res;
    }
}
