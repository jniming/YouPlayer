package com.yp.youplayer.bean;

/**
 * Created by Administrator on 2016/11/22.
 */
public class OrderBean {


//    t1 paytype        //支付方式
//    t2 orderid       //订单id
//    t3 goodsname     //商品名称
//    t4 price          //价格
//    t5 notify_url      //异步地址
//    t6 custom         //透传参数


    private int t1;
    private String t2;
    private String t3;
    private double t4;
    private String t5;
    private String t6;

    public OrderBean(int t1, String t2, String t3, double t4, String t5, String t6) {
        this.t1 = t1;
        this.t2 = t2;
        this.t3 = t3;
        this.t4 = t4;
        this.t5 = t5;
        this.t6 = t6;
    }

    public OrderBean() {
    }

    public int getT1() {
        return t1;
    }

    public void setT1(int t1) {
        this.t1 = t1;
    }

    public String getT2() {
        return t2;
    }

    public void setT2(String t2) {
        this.t2 = t2;
    }

    public String getT3() {
        return t3;
    }

    public void setT3(String t3) {
        this.t3 = t3;
    }

    public double getT4() {
        return t4;
    }

    public void setT4(double t4) {
        this.t4 = t4;
    }

    public String getT5() {
        return t5;
    }

    public void setT5(String t5) {
        this.t5 = t5;
    }

    public String getT6() {
        return t6;
    }

    public void setT6(String t6) {
        this.t6 = t6;
    }
}
