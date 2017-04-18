package com.yp.youplayer.util;

import com.yp.youplayer.intefence.CloseListener;
import com.yp.youplayer.intefence.VipChangListener;

/**
 * Created by Administrator on 2016/11/14.
 */
public class Constant {

    public static String APPKEY = "6460DF8B394A2158CD687420EA57933689F8DE85166C6536";
    public static int GOOD_ID = 369;

    public static int EXIT_1 = 10;   //优惠到黄金
    public static int EXIT_2 = 20;   //优惠到黄金
    public static int EXIT_3 = 30;   //优惠到黄金
    public static int EXIT_4 = 40;   //优惠到黄金
    public static int EXIT_5 = 50;   //优惠到黄金
    public static int EXIT_6 = 60;   //优惠到黄金
    public static int EXIT_7 = 70;   //优惠到黄金


    public static int PAY_1 = 1;  //普通
    public static int PAY_2 = 2;   //黄金
    public static int PAY_3 = 3;   //钻石
    public static int PAY_4 = 4;   //蓝钻
    public static int PAY_5 = 5;   //黑金
    public static int PAY_6 = 6;   //至尊
    public static int PAY_7 = 7;   //终极


    public static String TAG = "alert_show";     //支付弹窗标识


    public static final int VIP_1 = 1;    //普通会员
    public static final int VIP_2 = 2;    //黄金会员
    public static final int VIP_3 = 3;    //砖石会员
    public static final int VIP_4 = 4;    //蓝钻
    public static final int VIP_5 = 5;    //黑金
    public static final int VIP_6 = 6;    //至尊
    public static final int VIP_7 = 7;    //至尊


    public static final int ZFB = 2;    //至尊
    public static final int WX = 1;    //至尊


    public static boolean AD_ALERT = true;    //弹窗


    public static int PAY_FLG_EXIT = 1000;    //退出


    public static VipChangListener vipChangListener;
    public static CloseListener closeListener;


    public static String PlayFLG = "imgurl";
    public static String PlayFLG_1 = "imgname";
    public static String PlayFLG_2 = "videourl";

    public static String[] VIP_STR_1 = new String[]{"体验区", "推荐", "片库", "专辑", "我的"};
    public static String[] VIP_STR_2 = new String[]{"黄金区", "钻石区", "片库", "专辑", "我的"};
    public static String[] VIP_STR_3 = new String[]{"钻石区", "蓝钻区", "片库", "专辑", "我的"};
    public static String[] VIP_STR_4 = new String[]{"蓝钻区", "黑金区", "片库", "专辑", "我的"};
    public static String[] VIP_STR_5 = new String[]{"黑金区", "至尊", "片库", "专辑", "我的"};
    public static String[] VIP_STR_6 = new String[]{"至尊", "片库", "专辑", "我的"};
    public static String[] VIP_STR_7 = new String[]{"终极会员", "我的"};


    public static int Benner = -1;
}
