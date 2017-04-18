package com.yp.youplayer.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/11/16.
 */
public class PdBean {

    private String name;

    private String imgurl;

    private String videurl;

    private int vip;
    private int type;

    private int id;

    private List<VideoBean> dtlist;


    public PdBean(List<VideoBean> dtlist, int id, String imgurl, String name, int type, String videurl, int vip) {
        this.dtlist = dtlist;
        this.id = id;
        this.imgurl = imgurl;
        this.name = name;
        this.type = type;
        this.videurl = videurl;
        this.vip = vip;
    }

    public PdBean() {
    }

    public List<VideoBean> getDtlist() {
        return dtlist;
    }

    public void setDtlist(List<VideoBean> dtlist) {
        this.dtlist = dtlist;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getVideurl() {
        return videurl;
    }

    public void setVideurl(String videurl) {
        this.videurl = videurl;
    }

    public int getVip() {
        return vip;
    }

    public void setVip(int vip) {
        this.vip = vip;
    }
}
