package com.yp.youplayer.util;

import java.util.Observable;

/**
 * Created by Administrator on 2016/11/22.
 */
public class ObOject extends Observable {
    private int vip;

    public void setVip(int vip) {

        if (this.vip != vip)
            setChanged();
        this.vip = vip;
        notifyObservers();
    }

}
