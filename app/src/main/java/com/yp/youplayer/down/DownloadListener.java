package com.yp.youplayer.down;

public interface DownloadListener {

    /**
     * 开始下载
     *
     * @param id
     * @param url
     * @param downloaded
     * @param total
     * @param downloadInfo
     */
    public void onDownloadStart(String url, String savePath, long downloadedSize, long totalSize, String fileName);


    /**
     * 下载中
     *
     * @param id
     * @param url
     * @param downloaded
     * @param total
     * @param downloadInfo
     */
    public void onDownloading(String url, String savePath, long downloadedSize, long totalSize, String fileName);

    /**
     * 下载成功
     *
     * @param url
     * @param savePath
     * @param total
     * @param downloadInfo
     */
    public void onDownloadSuccess(String url, String savePath, String fileName, long totalSize);

    /**
     * 下载失败
     *
     * @param url
     * @param savePath
     * @param type
     * @param reason
     * @param downloadInfo
     */
    public void onDownloadFail(String url, String savePath, String fileName, String reason);

}
