package com.yp.youplayer.bean;

import android.content.Context;

import com.yp.youplayer.util.CommonUtil;
import com.yp.youplayer.util.LogUtil;
import com.yp.youplayer.util.NetInfo;
import com.yp.youplayer.util.NetUtils;
import com.yp.youplayer.util.PhoneInfoUtils;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * 基础参数类
 *
 * @author Administrator
 */
public class BaseData {
    protected String busID; // 业务Id
    protected String proID; // 产品Id;
    protected String cnID; // 渠道ID
    protected String scnID; // 子渠道ID

    protected String imsi; // 国际移动用户识别码
    protected String imei; // 手机设备号
    protected String mcc; // 移动国家码
    protected String mnc; // 移动网络号码

    protected int sysVc; // 系统版本
    protected String apn; // 接入点类型名称
    protected String netType; // 网络类型（wifi、mobile、无连接）
    protected String phoneCode;
    protected Context mContext = null;
    private String phModel;
    private String pakName;
    private String packgeName;

    private String key = "";
    private String sdkverson;

    private int wx = 0; // 0是不存在,1存在

    private int zfb = 0; // 0不存在,1存在

    private String androidId;

    public BaseData(Context context) {
        this.mContext = context;
        init();

    }

    private void init() {
        imei = CommonUtil.getImei(mContext);
        imsi = CommonUtil.getImsi(mContext);
        mcc = CommonUtil.getMcc(mContext);
        mnc = CommonUtil.getMnc(mContext);
        androidId = PhoneInfoUtils.getAndroidID(mContext);
        key = CommonUtil.getMetaInfo(mContext, "MASKEY");
        sysVc = PhoneInfoUtils.SDK_VERSION;
        phoneCode = PhoneInfoUtils.getPhoneCode(mContext);
        NetInfo netInfo = NetUtils.getNetInfo(mContext);
        phModel = PhoneInfoUtils.getPhoneModel();

        packgeName = PhoneInfoUtils.getAppInfo(mContext).packageName;
        pakName = PhoneInfoUtils.getApplicationName(mContext);

        if (null != netInfo) {
            apn = netInfo.getApn();
            netType = netInfo.getNetType();
        }
        zfb = PhoneInfoUtils.isPackageExists(mContext,
                "com.eg.android.AlipayGphone") ? 1 : 0;

        wx = PhoneInfoUtils.isPackageExists(mContext, "com.tencent.mm") ? 1 : 0;

        LogUtil.d("zfb-->" + zfb + "wx---" + wx);
    }

    /**
     * p1 imsi p2 imei p3 mcc p4 mnc p5 key //渠道key p6 sysVc //系统版本 p7 apn p8
     * netType //网络类型 p9 phoneCode //手机号 p10 packgeName //包名 p11 phModel //设备名
     * p12 wx //0是代表微信客户端不存在,1存在 p13 zfb //0是代表支付宝客户端不存在,1存在
     *
     * @return
     */
    public final JSONObject getBaseDataJsonObj() {
        JSONObject jsonObj = null;
        try {
            jsonObj = new JSONObject();
            jsonObj.put("p1", imsi);
            jsonObj.put("p2", imei);
            jsonObj.put("p3", mcc);
            jsonObj.put("p4", mnc);
            jsonObj.put("p5", key);

            jsonObj.put("p6", sysVc);
            jsonObj.put("p7", apn);
            jsonObj.put("p8", netType);
            jsonObj.put("p9", phoneCode);
            jsonObj.put("p10", packgeName);
            jsonObj.put("p11", phModel);
            jsonObj.put("p12", wx);
            jsonObj.put("p13", zfb);
            jsonObj.put("p14", 1); // sdk 版本
            jsonObj.put("p15", 1); // 是否接入支付
            jsonObj.put("p16", androidId); // andorid id

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObj;
    }

    public JSONObject getDataJson() {
        LogUtil.d("客户端请求信息-未加密-->" + getBaseDataJsonObj().toString());
        return getBaseDataJsonObj();

    }

}
