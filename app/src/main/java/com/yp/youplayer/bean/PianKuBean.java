package com.yp.youplayer.bean;

import android.content.Context;

/**
 * Created by Administrator on 2016/12/5.
 */

public class PianKuBean extends BaseData {


    private String imgurl;

    private int id;

    public PianKuBean(Context context) {
        super(context);
    }

    public PianKuBean(Context context, int id, String imgurl) {
        super(context);
        this.id = id;
        this.imgurl = imgurl;
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
}
