package com.kai.danbooruManager;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.logging.Logger;

public class DanbooruPage {

    private Logger pageLogger;
    private ArrayList<Post> posts;
    private Url url;
    private Document doc;

    public DanbooruPage(Url pageUrl) {
        pageLogger = Logger.getLogger(pageUrl.toString());
        posts = new ArrayList<Post>();
        this.url = pageUrl;
        this.doc = null;
    }

    public void downloadPage() {
        try {
            doc = Jsoup.connect(url.toString()).get();
        } catch (IOException e) {
            pageLogger.severe("Unable to downloadPage page: " + url.toString());
        }
    }

    public void populatePosts() {
        Element body = doc.body();
        Elements detectedArticles = body.getElementsByTag("article");
        for (Element article : detectedArticles) {
            try {
                posts.add(new Post(article, url.getBaseUrl()));
            } catch (MalformedURLException e) {
                System.out.println(e.toString());
            }
        }
    }

    public ArrayList<Post> getPosts() {
        return posts;
    }
}
