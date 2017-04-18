package com.yp.youplayer.bean;

/**
 * Created by Administrator on 2017/4/1.
 */

public class AdBean {
    private int type;
    private String link;
    private String imgurl;
    private String apkurl;
    private String filelen;
    private String md5;

    public String getApkurl() {
        return apkurl;
    }

    public void setApkurl(String apkurl) {
        this.apkurl = apkurl;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getFilelen() {
        return filelen;
    }

    public void setFilelen(String filelen) {
        this.filelen = filelen;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }
}
