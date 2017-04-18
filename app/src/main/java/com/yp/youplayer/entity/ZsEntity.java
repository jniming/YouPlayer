package com.yp.youplayer.entity;

import com.yp.youplayer.bean.VideoBean;

import java.util.List;

/**
 * Created by Administrator on 2016/11/21.
 */
public class ZsEntity {

    private List<VideoBean> zslist;
    private List<VideoBean> lzlist;

    public ZsEntity(List<VideoBean> lzlist, List<VideoBean> zslist) {
        this.lzlist = lzlist;
        this.zslist = zslist;
    }

    public List<VideoBean> getLzlist() {
        return lzlist;
    }

    public void setLzlist(List<VideoBean> lzlist) {
        this.lzlist = lzlist;
    }

    public List<VideoBean> getZslist() {
        return zslist;
    }

    public void setZslist(List<VideoBean> zslist) {
        this.zslist = zslist;
    }
}
