package com.yp.youplayer.bean;

/**
 * Created by Administrator on 2016/11/14.
 */
public class BenerBean {
    private String imgurl;

    private String name;

    private String videourl;

    public BenerBean(String imgurl, String name, String videourl) {
        this.imgurl = imgurl;
        this.name = name;
        this.videourl = videourl;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVideourl() {
        return videourl;
    }

    public void setVideourl(String videourl) {
        this.videourl = videourl;
    }
}
