package com.yp.youplayer.util;

import android.content.Context;
import android.content.res.AssetManager;

import com.yp.youplayer.entity.UseDataEntity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 数据管理器
 * Created by Administrator on 2016/11/18.
 */
public class DataUtil {

    private static DataUtil dataUtil;

    public static DataUtil getIntence() {
        if (dataUtil == null) {
            dataUtil = new DataUtil();

        }
        return dataUtil;
    }

    public UseDataEntity getEntity(Context context) {
        String res = readAssertResource(context, "res");
        String str = null;
        UseDataEntity useDataEntity = null;
        try {
            str = AESUtils.decode(res);
            LogUtil.d("解密后字符串" + str);
            useDataEntity = GsonUtil.fromJson(str, UseDataEntity.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return useDataEntity;
    }


    public static String readAssertResource(Context context, String strAssertFileName) {
        AssetManager assetManager = context.getAssets();
        String strResponse = "";
        try {
            InputStream ims = assetManager.open(strAssertFileName);
            strResponse = getStringFromInputStream(ims);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return strResponse;
    }

    private static String getStringFromInputStream(InputStream a_is) {
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();
        String line;
        try {
            br = new BufferedReader(new InputStreamReader(a_is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                }
            }
        }
        return sb.toString();
    }
}
