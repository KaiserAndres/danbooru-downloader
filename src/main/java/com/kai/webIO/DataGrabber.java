package com.kai.webIO;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

public class DataGrabber {

    private URL origin;
    private String userAgent;
    private InputStream reader;

    private final int BUFF_SIZE = 1024;

    public DataGrabber(URL origin, String userAgent) {
        this.origin = origin;
        this.userAgent = userAgent;
        reader = null;
    }

    public URL getOrigin() {
        return origin;
    }

    public void setOrigin(URL origin) {
        this.origin = origin;
        reader = null;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
        reader = null;
    }

    public void connect() throws IOException {
        URLConnection conn = origin.openConnection();
        conn.setRequestProperty("User-Agent", userAgent);
        reader = conn.getInputStream();
    }

    public void download(File destination) throws IOException {
        if (reader == null)
            return;
        FileOutputStream destinationStream = new FileOutputStream(destination);
        byte buff[] = new byte[BUFF_SIZE];
        int s = reader.read(buff);
        while(s != -1) {
            destinationStream.write(buff, 0, s);
            s = reader.read(buff);
        }

        destinationStream.close();
    }

    public void disconnect() throws IOException {
        if (reader != null)
            reader.close();
    }

}
