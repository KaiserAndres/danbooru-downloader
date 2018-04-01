package com.kai;

import com.kai.danbooruManager.*;

public class Main {
    public static void main(String[] args) {
        Configuration cfg = new Configuration();
        cfg.addDesiredCategory("fujiwara_no_mokou");
        cfg.addForbiddenCategory("comic");
        cfg.addForbiddenCategory("nude");
        cfg.setImageTarget("/Users/kaiser/Pictures/test/mokou");

        DownloadManager manager = new DownloadManager(cfg);

        Url myUrl = new Url("https://hijiribe.donmai.us", cfg);

        for (int i=10; i<13; i++) {
            System.out.println("Checking page Nº"+i);
            myUrl.setPageNumber(i);
            DanbooruPage page = new DanbooruPage(myUrl);
            page.downloadPage();
            page.populatePosts();

            manager.addPosts(page.getPosts());
        }

        while (!manager.isEmpty()) { manager.download(); }
    }
}
