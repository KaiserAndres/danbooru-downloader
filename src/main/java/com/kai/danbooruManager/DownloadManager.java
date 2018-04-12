package com.kai.danbooruManager;

import java.io.File;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import org.apache.commons.io.FileUtils;

public class DownloadManager {

    private Configuration cfg;
    private ArrayDeque<Post> postQueue;

    public DownloadManager(Configuration cfg) {
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
        if (!savedImage.exists()) {
            try {
                FileUtils.copyURLToFile(postToDownload.getFileUrl(), savedImage);
            } catch (IOException e) {
                System.out.println(e.toString());
                postQueue.add(postToDownload);
            }
        }
    }

    public int getPostCount() { return  postQueue.size(); }

    public boolean hasElements() {
        return !postQueue.isEmpty();
    }
}
