package com.yp.youplayer.entity;

import com.yp.youplayer.bean.VideoBean;

import java.util.List;

/**
 * Created by Administrator on 2016/11/21.
 */
public class PtEntity {   //普通会员

    private List<VideoBean> brlist;   //轮播
    private List<VideoBean> yblist;
    private List<VideoBean> wmlist;
    private List<VideoBean> pklist;
    private List<VideoBean> zjlist;


    public PtEntity() {
    }

    public PtEntity(List<VideoBean> brlist, List<VideoBean> pklist, List<VideoBean> wmlist, List<VideoBean> yblist, List<VideoBean> zjlist) {
        this.brlist = brlist;
        this.pklist = pklist;
        this.wmlist = wmlist;
        this.yblist = yblist;
        this.zjlist = zjlist;
    }

    public List<VideoBean> getBrlist() {
        return brlist;
    }

    public void setBrlist(List<VideoBean> brlist) {
        this.brlist = brlist;
    }

    public List<VideoBean> getPklist() {
        return pklist;
    }

    public void setPklist(List<VideoBean> pklist) {
        this.pklist = pklist;
    }

    public List<VideoBean> getWmlist() {
        return wmlist;
    }

    public void setWmlist(List<VideoBean> wmlist) {
        this.wmlist = wmlist;
    }

    public List<VideoBean> getYblist() {
        return yblist;
    }

    public void setYblist(List<VideoBean> yblist) {
        this.yblist = yblist;
    }

    public List<VideoBean> getZjlist() {
        return zjlist;
    }

    public void setZjlist(List<VideoBean> zjlist) {
        this.zjlist = zjlist;
    }
}
