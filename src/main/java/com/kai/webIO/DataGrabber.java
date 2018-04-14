package com.kai.webIO;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

public class DataGrabber {

    private URL origin;
    private String userAgent;
    private InputStream reader;

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
        final int buffSize = 1024;
        byte buff[] = new byte[buffSize];
        int bytesRead = reader.read(buff);
        while(bytesRead != -1) {
            destinationStream.write(buff, 0, bytesRead);
            bytesRead = reader.read(buff);
        }

        destinationStream.close();
    }

    public void disconnect() throws IOException {
        if (reader != null)
            reader.close();
    }

}
