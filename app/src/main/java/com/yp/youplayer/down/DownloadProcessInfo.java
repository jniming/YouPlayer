package com.yp.youplayer.down;

public class DownloadProcessInfo {
    private String url;
    private int downloadedLen;
    private int fileLen;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getDownloadedLen() {
        return downloadedLen;
    }

    public void setDownloadedLen(int downloadedLen) {
        this.downloadedLen = downloadedLen;
    }

    public int getFileLen() {
        return fileLen;
    }

    public void setFileLen(int fileLen) {
        this.fileLen = fileLen;
    }

}
