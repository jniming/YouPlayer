package com.yp.youplayer.entity;

import com.yp.youplayer.bean.VideoBean;

import java.util.List;

/**
 * Created by Administrator on 2016/11/21.
 */
public class LzEntity {

    private List<VideoBean> lzlist;
    private List<VideoBean> helist;

    public LzEntity(List<VideoBean> helist, List<VideoBean> lzlist) {
        this.helist = helist;
        this.lzlist = lzlist;
    }

    public List<VideoBean> getHelist() {
        return helist;
    }

    public void setHelist(List<VideoBean> helist) {
        this.helist = helist;
    }

    public List<VideoBean> getLzlist() {
        return lzlist;
    }

    public void setLzlist(List<VideoBean> lzlist) {
        this.lzlist = lzlist;
    }
}
