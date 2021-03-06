package com.kai.danbooruManager;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.logging.Logger;

public class DanbooruPage {

    private Logger pageLogger;
    private ArrayList<Post> posts;
    private Url url;
    private Document doc;

    public DanbooruPage(Url pageUrl) {
        pageLogger = Logger.getLogger(pageUrl.toString());
        posts = new ArrayList<>();
        this.url = pageUrl;
        this.doc = null;
    }

    private void downloadPage() throws IOException{
        try {
            doc = Jsoup.connect(url.toString()).get();
        } catch (SocketTimeoutException e) {
            downloadPage();
        } catch (IOException e) {
            pageLogger.severe("Unable to downloadPage page: " + url.toString());
            throw e;
        }
    }

    private void populatePosts() {
        for (Element article : doc.body().getElementsByTag("article"))
            try {
                posts.add(new Post(article, url.getBaseUrl()));
            } catch (MalformedURLException e) {
                pageLogger.info("Unable to download image: " + e.toString() + " skipping post.");
            }
    }

    public void processPage() {
        try {
            downloadPage();
        } catch (IOException e) {
            // Page was not downloaded, skipping
            return;
        }
        populatePosts();
    }

    public ArrayList<Post> getPosts() {
        return posts;
    }
}
