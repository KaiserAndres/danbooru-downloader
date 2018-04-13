package com.kai.danbooruManager;

import java.io.File;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.logging.Logger;

import com.kai.webIO.DataGrabber;

public class DownloadManager {

    private Configuration cfg;
    private ArrayDeque<Post> postQueue;
    private Logger downloaderLogger;


    public DownloadManager(Configuration cfg) {
        downloaderLogger = Logger.getLogger(getClass().getName());
        this.cfg = cfg;
        postQueue = new ArrayDeque<>();
    }

    public void addPosts(ArrayList<Post> posts) {
        posts.stream()
                .filter(p -> cfg.validate(p))
                .forEach(post -> postQueue.add(post));
    }

    public void download() {
        Post postToDownload = postQueue.pop();
        String fileName = cfg.getImageTarget() + postToDownload.getId() + "." + postToDownload.getFileExtention();
        File savedImage = new File(fileName);
        DataGrabber dg = new DataGrabber(postToDownload.getFileUrl(),
                "Mozilla/5.0 (X11; Linux x86_64; rv:10.0) Gecko/20100101 Firefox/10.0");
        if (!savedImage.exists()) {
            try {
                dg.connect();
                dg.download(savedImage);
                dg.disconnect();
            } catch (IOException e) {
                downloaderLogger.info("Could not download image, trying to recover");
                dg.setOrigin(postToDownload.getLargeFileUrl());
                try {
                    dg.connect();
                    dg.download(savedImage);
                    dg.disconnect();
                } catch (IOException ee) {
                    System.out.println("Welp, idk what to do now");
                }
            }
        }
    }

    public int getPostCount() { return  postQueue.size(); }

    public boolean hasElements() {
        return !postQueue.isEmpty();
    }
}
