package com.yp.youplayer.util;

import android.util.Log;

/**
 * Created by Administrator on 2016/10/25.
 */
public class LogUtil {
    private static String TAG = "allen";

    //todo 打包时关闭
    private static boolean show_log = false;

    public static void d(String str) {
        if (show_log)
            Log.d(TAG, str);
    }

}