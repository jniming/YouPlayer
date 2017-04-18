package com.yp.youplayer.Rxhttpnet;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Administrator on 2016/11/14.
 */
public interface RxApi {

    @POST("ypl/index2")
    Observable<String> getYouBoData(@Body String data);

    @POST("pay_result")
    Observable<String> getWxPayResult(@Body String data);
//
//    @POST("pay_order_dx")
//    Observable<String> getPayData(@Body String reqdata);

//    @POST("ypl/pay_order")
//    Observable<String> getPayData2(@Body String reqdata);

//    //社交支付
//    @POST("ping/apk_pay")
//    Observable<String> getPayData3(@Body String reqdata);
    //微云支付
    @POST("wy/pay_order")
    Observable<String> getPayData3(@Body String reqdata);

}
