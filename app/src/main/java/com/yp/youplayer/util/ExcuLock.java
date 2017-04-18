package com.yp.youplayer.util;

/**
 * Created by Administrator on 2017/3/11.
 */

public class ExcuLock {

    private static boolean lock = false;

    private static int times = 0; // 计数器

    public static boolean isLock() {
        if (lock) {
            times++;
        }
        if (times > 10) {
            unLock();
        }
        return lock;
    }

    public static void lock() {
        lock = true;
        times = 0;
    }

    public static void unLock() {
        lock = false;
        times = 0;
    }
}
