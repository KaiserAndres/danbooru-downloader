package com.kai.danbooruManager;

import java.util.ArrayDeque;
import java.util.ArrayList;

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
        if (cfg.validate(postToDownload))
            System.out.println(postToDownload.getFileUrl());
    }

    public boolean isEmpty() {
        return postQueue.isEmpty();
    }
}
