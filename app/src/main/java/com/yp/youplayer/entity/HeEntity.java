package com.yp.youplayer.entity;

import com.yp.youplayer.bean.VideoBean;

import java.util.List;

/**
 * Created by Administrator on 2016/11/21.
 */
public class HeEntity {

    private List<VideoBean> helist;
    private List<VideoBean> zzlist;

    public HeEntity(List<VideoBean> helist, List<VideoBean> zzlist) {
        this.helist = helist;
        this.zzlist = zzlist;
    }

    public List<VideoBean> getHelist() {
        return helist;
    }

    public void setHelist(List<VideoBean> helist) {
        this.helist = helist;
    }

    public List<VideoBean> getZzlist() {
        return zzlist;
    }

    public void setZzlist(List<VideoBean> zzlist) {
        this.zzlist = zzlist;
    }
}
