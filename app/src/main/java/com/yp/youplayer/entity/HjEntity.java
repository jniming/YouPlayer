package com.yp.youplayer.entity;

import com.yp.youplayer.bean.VideoBean;

import java.util.List;

/**
 * Created by Administrator on 2016/11/21.
 */
public class HjEntity {

    private List<VideoBean> wmlist;
    private List<VideoBean> zslist;

    public HjEntity() {
    }

    public HjEntity(List<VideoBean> wmlist, List<VideoBean> zslist) {
        this.wmlist = wmlist;
        this.zslist = zslist;
    }

    public List<VideoBean> getWmlist() {
        return wmlist;
    }

    public void setWmlist(List<VideoBean> wmlist) {
        this.wmlist = wmlist;
    }

    public List<VideoBean> getZslist() {
        return zslist;
    }

    public void setZslist(List<VideoBean> zslist) {
        this.zslist = zslist;
    }
}
