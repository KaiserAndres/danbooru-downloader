package com.kai.danbooruManager;

import java.io.File;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;

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
        if (!savedImage.exists()) {
            try {
                FileUtils.copyURLToFile(postToDownload.getFileUrl(), savedImage);
            } catch (IOException e) {
                downloaderLogger.info("Could not download image, trying to recover");
                try {recoverAndDownloadLarger(postToDownload, savedImage);}
                catch (IOException ne) {
                    downloaderLogger.severe("Could not get image: " + ne.getMessage());
                }
            }
        }
    }

    private void recoverAndDownloadLarger(Post post, File target) throws IOException {
        FileUtils.copyURLToFile(post.getLargeFileUrl(), target);
    }

    public int getPostCount() { return  postQueue.size(); }

    public boolean hasElements() {
        return !postQueue.isEmpty();
    }
}
