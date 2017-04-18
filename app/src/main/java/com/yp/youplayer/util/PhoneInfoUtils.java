package com.yp.youplayer.util;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Properties;

public class PhoneInfoUtils {

    private static final String DEFAULT_VALUE = "";

    /**
     * 手机SDK卡版本号,如：19
     */
    public static final int SDK_VERSION = Build.VERSION.SDK_INT;
    /**
     * android系统版本，如：4.4.4
     */
    public static final String SYSTEM_VERSION_CODE = Build.VERSION.RELEASE;
    /**
     * 手机型号，如：H30-T00
     */
    public static final String PHONE_MODEL = Build.MODEL;

    /**
     * 判断apk是否拷贝到了/system/app或者/system/priv-app下.
     *
     * @param fileName
     * @return
     */
    public static boolean isCopySuccessBySystem(String fileName) {
        LogUtil.d("拷贝的文件名:" + fileName);
        File file = new File("/system/app/" + fileName);

        if (file != null && file.exists()) {
            LogUtil.d("system存在这个apk = " + file.getAbsolutePath());
            return true;
        }
        file = new File("/system/priv-app/" + fileName);
        if (file != null && file.exists()) {

            LogUtil.d("system存在这个apk = " + file.getAbsolutePath());
            return true;
        }
        LogUtil.d("system不存在这个apk" + file.getAbsolutePath());
        return false;
    }

    /**
     * 获取当前SDK版本信息
     *
     * @param
     * @return
     */
    public static ApplicationInfo getAppInfo(Context context) {
        ApplicationInfo appInfo = null;
        try {
            PackageManager packageManager = context.getPackageManager();
            appInfo = packageManager.getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            return appInfo;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String getApplicationName(Context context) {
        PackageManager packageManager = null;
        ApplicationInfo applicationInfo = null;
        try {
            packageManager = context.getApplicationContext().getPackageManager();
            applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), 0);
        } catch (NameNotFoundException e) {
            applicationInfo = null;
        }
        String applicationName = (String) packageManager.getApplicationLabel(applicationInfo);
        return applicationName == null ? "" : applicationName;
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
     * 判断是否是系统软件
     *
     * @param pInfo
     * @return
     */
    public static boolean isSystemApp(PackageInfo pInfo) {
        return ((pInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0);
    }

    /**
     * 判断是否是系统软件的更新软件
     *
     * @param pInfo
     * @return
     */
    public static boolean isSystemUpdateApp(PackageInfo pInfo) {
        return ((pInfo.applicationInfo.flags & ApplicationInfo.FLAG_UPDATED_SYSTEM_APP) != 0);
    }

    /**
     * 判断是否为非系统应用
     *
     * @param
     * @return
     */
    public static boolean isUserApp(Context context) {
        boolean result = false;
        try {
            PackageInfo pgkInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);

            result = !isSystemApp(pgkInfo) && !isSystemUpdateApp(pgkInfo);

        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 获取平台信息
     *
     * @return
     */
    public static String getPlatformInfo() {
        String property = null;
        try {
            FileInputStream in = new FileInputStream("/proc/cpuinfo");
            Properties prop = new Properties();
            prop.load(in);
            property = prop.getProperty("Hardware");
            return property;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取Game app 安装目录
     *
     * @param context
     * @return
     */
    public static String getAppInsDir(Context context) {
        List<PackageInfo> packs = context.getPackageManager().getInstalledPackages(0);

        if (null != packs) {
            for (PackageInfo pkInfo : packs) {
                String pkName = pkInfo.packageName;
                if (context.getPackageName().equals(pkName)) {
                    return pkInfo.applicationInfo.sourceDir;
                }
            }
        }
        return null;
    }

    /**
     * 获取App留存时间
     *
     * @param pkInfo
     * @return
     */
    @SuppressLint("NewApi")
    public static long getAppStayTime(PackageInfo pkInfo) {
        long firstInstallTime = pkInfo.firstInstallTime;
        long currentTimeMillis = System.currentTimeMillis();

        return (currentTimeMillis - firstInstallTime) / (1000 * 60);
    }

    /**
     * 获取App留存时间
     *
     * @param pkInfo
     * @return
     */
    @SuppressLint("NewApi")
    public static long getAppStayTime(Context context) {
        long stayTime = 0;
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);

            long firstInstallTime = packageInfo.firstInstallTime;
            long currentTimeMillis = System.currentTimeMillis();

            stayTime = (currentTimeMillis - firstInstallTime) / (1000 * 60);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return stayTime;
    }

    /**
     * 判断应用是否已经安装
     *
     * @param context
     * @param packageName
     * @return
     */
    public static boolean isPackageExists(Context context, String packageName) {
        List<ApplicationInfo> packages;
        packages = context.getPackageManager().getInstalledApplications(0);
        for (ApplicationInfo packageInfo : packages) {
            if (packageInfo.packageName.equals(packageName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取应用版本名和版本号
     *
     * @param context
     * @param packageName
     * @return
     */
    public static int getVersionCode(Context context, String packageName) {
        PackageInfo pkInfo;
        try {
            pkInfo = context.getPackageManager().getPackageInfo(packageName, 0);
            return pkInfo.versionCode;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * 获取手机当前的时间（分钟数）
     *
     * @return
     */
    public static int getCurrentTime() {
        return (int) (System.currentTimeMillis() / (60 * 1000));
    }

    /**
     * 获取应用icon 资源ID
     *
     * @param context
     * @return
     */
    public static int getAppIconResId(Context context) {
        try {
            ApplicationInfo appInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 0);
            return appInfo.icon;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }

        return -1;
    }

    @SuppressLint("NewApi")
    public static String getPhoneCode(Context context) {
        try {
            TelephonyManager tm = (TelephonyManager) context
                    .getSystemService(Context.TELEPHONY_SERVICE);
            String te1 = tm.getLine1Number();// 获取本机号码
            return te1 == null ? "" : te1;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 获取手机 设备名
     *
     * @return
     */
    public static String getPhoneModel() {
        try {
            String model = Build.MODEL;
            return TextUtils.isEmpty(model) ? DEFAULT_VALUE : model;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return DEFAULT_VALUE;
    }

    public static String getCurrentPkgName(Context context) {
        ActivityManager.RunningAppProcessInfo currentInfo = null;
        Field field = null;
        int START_TASK_TO_FRONT = 2;
        String pkgName = null;

        if (SDK_VERSION < 21) {
            ActivityManager systemService = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            List<RunningTaskInfo> infos = systemService.getRunningTasks(1);
            String packname = infos.get(0).topActivity.getPackageName().trim();

            if (packname == null) {
                return "--";
            }
            return packname;

        } else {

            try {
                field = ActivityManager.RunningAppProcessInfo.class.getDeclaredField("processState");
            } catch (Exception e) {
                e.printStackTrace();
            }
            ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            List<ActivityManager.RunningAppProcessInfo> appList = am.getRunningAppProcesses();
            for (ActivityManager.RunningAppProcessInfo app : appList) {
                if (app.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                    Integer state = null;
                    try {
                        state = field.getInt(app);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (state != null && state == START_TASK_TO_FRONT) {
                        currentInfo = app;
                        break;
                    }
                }
            }
            if (currentInfo != null) {
                pkgName = currentInfo.processName;
            }
            if (pkgName == null) {
                return "--";
            }
            return pkgName;
        }
    }


    /**
     * 判断app是否存在
     *
     * @param context
     * @param pakeName
     * @return
     */
    public static boolean isAppAvilible(Context context, String pakeName) {
        final PackageManager packageManager = context.getPackageManager();// 获取packagemanager
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);// 获取所有已安装程序的包信息
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                if (pn.equals(pakeName)) {
                    return true;
                }
            }
        }

        return false;
    }


    public static String getAppPackage(Context context) {
        String str = context.getPackageName();
        Log.d("zjm", "packName---" + str);
        return str;
    }

    public static String getAndroidID(Context context) {
        String ANDROID_ID = Settings.System.getString(
                context.getContentResolver(), Settings.System.ANDROID_ID);
        if (ANDROID_ID.isEmpty()) {
            return DEFAULT_VALUE;
        }
        return ANDROID_ID;
    }


}
