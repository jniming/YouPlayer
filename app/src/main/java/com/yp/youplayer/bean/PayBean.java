package com.yp.youplayer.bean;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2016/11/22.
 */
public class PayBean extends BaseData {

    public PayBean(Context context) {
        super(context);
    }

    public String getData(double price, int type) {
        JSONObject json = getDataJson();
        try {
            json.put("p17", price);
            json.put("p18", type);
            return json.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return "";
    }
}
