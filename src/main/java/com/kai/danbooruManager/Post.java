package com.kai.danbooruManager;

import org.jsoup.nodes.Element;

import java.util.Arrays;
import java.util.Map;
import static java.lang.Integer.parseInt;

public class Post {

    private int id;
    private String[] tags;
    private int width;
    private int height;
    private String source;
    private String fileUrl;
    private String largeFileUrl;

    public Post(Element element, String baseUrl) {
        Map<String, String> elementData = element.dataset();
        id = parseInt(elementData.get("id"));
        width = parseInt(elementData.get("width"));
        height = parseInt(elementData.get("height"));
        source = elementData.get("source");
        fileUrl = baseUrl + elementData.get("file-url");
        largeFileUrl = baseUrl + elementData.get("large-file-url");
        tags = elementData.get("tags").split(" ");
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

    public String getFileUrl() {
        return fileUrl;
    }
}
