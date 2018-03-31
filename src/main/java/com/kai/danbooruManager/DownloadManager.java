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
        postQueue = new ArrayDeque<Post>();
    }

    public void addPost(Post p) {
        postQueue.add(p);
    }

    public void addPosts(ArrayList<Post> posts) {
        postQueue.addAll(posts);
    }

    public void download() {
        Post postToDownload = postQueue.pop();
        if (cfg.validate(postToDownload)) {
            String fileName = postToDownload.getId() + "." + postToDownload.getFileExtention();
            File savedImage = new File(fileName);
            try {
                FileUtils.copyURLToFile(postToDownload.getFileUrl(), savedImage);
            } catch (IOException e) {
                postQueue.add(postToDownload);
            }
        }
    }

    public boolean isEmpty() {
        return postQueue.isEmpty();
    }
}
