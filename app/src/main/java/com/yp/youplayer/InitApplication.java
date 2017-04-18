package com.yp.youplayer;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.wyzf.tppay.plg.pay.WyzfTPPay;


/**
 * Created by Administrator on 2016/11/14.
 */
public class InitApplication extends Application {
    public static Context context;
    public static String cacheDir = "";   //缓存目录

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        Fresco.initialize(context);
        setCacheDir();
        Log.d("zjm", "hahahhahaha ,i am log");
        WyzfTPPay.getInstance().init(context,20946010,"mars");
    }

    public void setCacheDir() {
        /**
         * 如果存在SD卡则将缓存写入SD卡,否则写入手机内存
         */
        try {
            if (getApplicationContext().getExternalCacheDir() != null && ExistSDCard()) {   //如果sd 卡存在,设置sd卡为缓存目录
                cacheDir = getApplicationContext().getExternalCacheDir().toString();
            } else {
                cacheDir = getApplicationContext().getCacheDir().toString();
            }
        } catch (Exception e) {
            cacheDir = getApplicationContext().getCacheDir().toString();
        }


    }

    private boolean ExistSDCard() {
        if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }

    public static Context getAppContext() {
        return context;
    }
}
