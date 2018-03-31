package com.kai.danbooruManager;

import org.jsoup.nodes.Element;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Map;
import static java.lang.Integer.parseInt;

public class Post {

    private int id;
    private String[] tags;
    private String fileExtention;
    private int width;
    private int height;
    private URL source;
    private URL fileUrl;
    private URL largeFileUrl;


    public Post(Element element, String baseUrl) {
        Map<String, String> elementData = element.dataset();
        id = parseInt(elementData.get("id"));
        width = parseInt(elementData.get("width"));
        height = parseInt(elementData.get("height"));
        try {
            source = new URL(elementData.get("source"));
            fileUrl = new URL(baseUrl + elementData.get("file-url"));
            largeFileUrl = new URL(baseUrl + elementData.get("large-file-url"));
        } catch (MalformedURLException e) {
            source = null;
            fileUrl = null;
            largeFileUrl = null;
        }
        tags = elementData.get("tags").split(" ");
        fileExtention = elementData.get("file-ext");
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", tags=" + (tags == null ? null : Arrays.asList(tags)) + '\n' +
                ", width=" + width +
                ", height=" + height +
                ", source='" + source + '\'' +
                ", fileUrl='" + fileUrl + '\'' +
                ", largeFileUrl='" + largeFileUrl + '\'' +
                '}';
    }

    public URL getFileUrl() {
        return fileUrl;
    }

    public int getId() {
        return id;
    }

    public String getFileExtention() {
        return fileExtention;
    }

    public String[] getTags() {
        return tags;
    }
}
