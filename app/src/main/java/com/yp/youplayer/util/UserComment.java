package com.yp.youplayer.util;


import com.ypsyyzip.mlidbvgnr.R;

import java.text.SimpleDateFormat;
import java.util.Random;



/**
 * Created by Administrator on 2016/11/15.
 */
public class UserComment {

    public static String[] Name = new String[]{"残花败柳", "一夜不下来", "情迷夜总会", "杀手不太冷", "洛阳冠希哥", "不像活又不想死", "雁过窗外寒", "刀疤杰克", "不爱几个人怎知爱", "老衲要射了", "我不是她", "无敌帅帅鸡", "条野太郎", "心中无码"};

    public static String[] Msg = new String[]{"这种福利多来些", "自从用了这个app,腰都直不起来了", "女主好主动,我怎么没遇到", "顶顶顶", "这个软件还算有点良心", "终于找到一个可以发泄的地方了", "我的大雕早已饥渴难耐", "额,还好吧", "3分40秒亮了", "天下AV何其多,不用快进看不完", "四川成都有约的吗", "一周纸巾都不知道用了多少了", "需要服务的加我微信哦", "老衲顶得住", "画质好棒速度真快", "看的心里痒痒的", "情何以堪", "片子还可以,但能不能多分几个分类啊", "姐终于找到一个发泄的地方了", "带坏我这个纯情少年", "我要找个女朋友"};

    public static int[] IMG_ID = new int[]{
            R.drawable.a1, R.drawable.a2, R.drawable.a3, R.drawable.a4, R.drawable.a5,
            R.drawable.a6, R.drawable.a7, R.drawable.a8, R.drawable.a9, R.drawable.a10,
            R.drawable.a11, R.drawable.a12, R.drawable.a13, R.drawable.a14, R.drawable.a15,
            R.drawable.a16, R.drawable.a17, R.drawable.a18, R.drawable.a19, R.drawable.a20,
            R.drawable.a21, R.drawable.a23, R.drawable.a24, R.drawable.a25, R.drawable.a29,
            R.drawable.a30, R.drawable.a31, R.drawable.a32, R.drawable.a33, R.drawable.a34,
            R.drawable.a35, R.drawable.a36, R.drawable.a38, R.drawable.a39, R.drawable.a40,
            R.drawable.a41, R.drawable.a42, R.drawable.a43, R.drawable.a44, R.drawable.a45,
            R.drawable.a46, R.drawable.a47, R.drawable.a49, R.drawable.a50, R.drawable.a51,
            R.drawable.a52, R.drawable.a53, R.drawable.a55, R.drawable.a56, R.drawable.a57,
            R.drawable.a58, R.drawable.a61, R.drawable.a62, R.drawable.a63, R.drawable.a64,
            R.drawable.a77, R.drawable.a78,};


    public static String getName() {
        int len = Name.length - 1;
        Random rand = new Random();
        int randNum = rand.nextInt(len);
        return Name[randNum];

    }

    public static String getMsg() {
        int len = Msg.length - 1;
        Random rand = new Random();
        int randNum = rand.nextInt(len);
        return Msg[randNum];

    }

    public static int getImgUrl() {
        int len = IMG_ID.length - 1;
        Random rand = new Random();
        int randNum = rand.nextInt(len);
        return IMG_ID[randNum];
    }

    public static String getDistence() {  //获取距离
        Random rand = new Random();
        int randNum = rand.nextInt(100);
        Random rand2 = new Random();
        int randNum2 = rand2.nextInt(10);
        return randNum + "." + randNum2;
    }

    public static int getTp() {
        Random rand = new Random();
        int randNum = rand.nextInt(1000) + 50;
        return randNum;
    }

    public static int getTime() {
        Random rand = new Random();
        int randNum = rand.nextInt(30);
        return randNum;
    }

    public static String getNowTime() {
        Random rand = new Random();
        int randNum = rand.nextInt(24 * 2 * 60 * 60 * 1000);
        long time = System.currentTimeMillis() - randNum;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String t = format.format(time);
        return t;
    }

}
