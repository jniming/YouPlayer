package com.yp.youplayer.manager;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/6.
 */

public class ActMananer {

    private static List<Activity> activities = new ArrayList<>();


    public static void addAct(Activity activity) {
        activities.add(activity);
    }


    public static void close() {
        for (Activity act : activities) {
            act.finish();
        }
        activities.clear();
    }

    public static void removeAct(Activity activity) {
        if (activities != null && activities.size() != 0)
            activities.remove(activity);
    }
}
