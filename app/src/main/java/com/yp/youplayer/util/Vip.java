package com.yp.youplayer.util;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.yp.youplayer.activity.AlertActivity;

/**
 * Created by Administrator on 2016/11/15.
 */
public class Vip {

    private static String key = "vip";
    private static String name = "Uservip";

    private static String vpn_key = "vpn";
    private static String vpn_name = "vpnname";


    public static void saveVip(Context context, int vip) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = sharedPreferences.edit();
        ed.putInt(key, vip);
        ed.commit();
    }


    public static int getVip(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        int id = sharedPreferences.getInt(key, Constant.VIP_1);
        return id;
    }

    public static void saveVpn(Context context, int vip) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(vpn_name, Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = sharedPreferences.edit();
        ed.putInt(vpn_key, vip);
        ed.commit();
    }


    public static int getVpn(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(vpn_name, Context.MODE_PRIVATE);
        int id = sharedPreferences.getInt(vpn_key, 0);
        return id;
    }


    public static void upVIP(Context context) {
        int vip = getVip(context);
        Intent intent = new Intent(context, AlertActivity.class);
        if (vip == Constant.VIP_1) {
            intent.putExtra(Constant.TAG, Constant.PAY_1);
        }
        if (vip == Constant.VIP_2) {
            intent.putExtra(Constant.TAG, Constant.PAY_2);
        }
        if (vip == Constant.VIP_3) {
            intent.putExtra(Constant.TAG, Constant.PAY_3);
        }
        if (vip == Constant.VIP_4) {
            intent.putExtra(Constant.TAG, Constant.PAY_4);
        }
        if (vip == Constant.VIP_5) {
            intent.putExtra(Constant.TAG, Constant.PAY_5);
        }
        if (vip == Constant.VIP_6) {
            intent.putExtra(Constant.TAG, Constant.PAY_6);
        }
        if (vip == Constant.VIP_7) {
            intent.putExtra(Constant.TAG, Constant.PAY_7);
        }
        context.startActivity(intent);

    }

    public static void upExitVIP(Context context) {
        int vip = Vip.getVip(context);
        Intent intent = new Intent(context, AlertActivity.class);
        if (vip == Constant.VIP_1) {
            intent.putExtra(Constant.TAG, Constant.EXIT_1);
        }
        if (vip == Constant.VIP_2) {
            intent.putExtra(Constant.TAG, Constant.EXIT_2);
        }
        if (vip == Constant.VIP_3) {
            intent.putExtra(Constant.TAG, Constant.EXIT_3);
        }
        if (vip == Constant.VIP_4) {
            intent.putExtra(Constant.TAG, Constant.EXIT_4);
        }
        if (vip == Constant.VIP_5) {
            intent.putExtra(Constant.TAG, Constant.EXIT_5);
        }
        if (vip == Constant.VIP_6) {
            intent.putExtra(Constant.TAG, Constant.EXIT_6);
        }
        if (vip == Constant.VIP_7) {
            intent.putExtra(Constant.TAG, Constant.EXIT_7);
        }
        Toast.makeText(context, "限时礼包", Toast.LENGTH_SHORT).show();
        context.startActivity(intent);

    }


    public static void changVip(Context context) {
        int vip = Vip.getVip(context);
        if (vip == Constant.VIP_1) {
            saveVip(context, Constant.VIP_2);
        }
        if (vip == Constant.VIP_2) {
            saveVip(context, Constant.VIP_3);
        }
        if (vip == Constant.VIP_3) {
            saveVip(context, Constant.VIP_4);
        }
        if (vip == Constant.VIP_4) {
            saveVip(context, Constant.VIP_5);
        }
        if (vip == Constant.VIP_5) {
            saveVip(context, Constant.VIP_6);
        }
        if (vip == Constant.VIP_6) {
            saveVip(context, Constant.VIP_7);
        }
        if (vip == Constant.VIP_7) {
            saveVip(context, Constant.VIP_2);
        }

    }


}
