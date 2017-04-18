package com.yp.youplayer.entity;

import com.yp.youplayer.bean.VideoBean;

import java.util.List;

/**
 * Created by Administrator on 2016/11/21.
 */
public class ZzEntity {

    private List<VideoBean> zzlist;

    public ZzEntity(List<VideoBean> zzlist) {
        this.zzlist = zzlist;
    }

    public List<VideoBean> getZzlist() {
        return zzlist;
    }

    public void setZzlist(List<VideoBean> zzlist) {
        this.zzlist = zzlist;
    }
}
