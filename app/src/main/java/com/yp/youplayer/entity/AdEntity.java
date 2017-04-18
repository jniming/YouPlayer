package com.yp.youplayer.entity;

import com.yp.youplayer.bean.AdBean;
import com.yp.youplayer.bean.TcBean;

import java.util.List;

/**
 * Created by Administrator on 2017/4/1.
 */

public class AdEntity {

    private List<AdBean> dxlist;
    private List<TcBean> tclist;

    public List<AdBean> getDxlist() {
        return dxlist;
    }

    public void setDxlist(List<AdBean> dxlist) {
        this.dxlist = dxlist;
    }

    public List<TcBean> getTclist() {
        return tclist;
    }

    public void setTclist(List<TcBean> tclist) {
        this.tclist = tclist;
    }
}
