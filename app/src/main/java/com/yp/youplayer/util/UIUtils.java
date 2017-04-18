package com.yp.youplayer.util;

import android.content.Context;
import android.content.res.Resources;

public class UIUtils {

    /**
     * dip转换px
     */
    public static int dip2px(Context context, int dip) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dip * scale + 0.5f);
    }

    /**
     * pxz转换dip
     */
    public static int px2dip(Context context, int px) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }

    /**
     * 获取资源
     */
    public static Resources getResources(Context context) {

        return context.getResources();
    }

}
