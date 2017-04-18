package com.yp.youplayer.Persenter;

import android.content.Context;

import com.yp.youplayer.Rxhttpnet.RetrofitUtil;
import com.yp.youplayer.Rxhttpnet.RxApi;
import com.yp.youplayer.bean.OrderBean;
import com.yp.youplayer.entity.UseDataEntity;
import com.yp.youplayer.intefence.OrderResult;
import com.yp.youplayer.util.GsonUtil;
import com.yp.youplayer.util.LogUtil;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/11/22.
 */
public class PayPersenter {
    private RxApi api;

    private UseDataEntity useentity;
    private Context context;
    private OrderResult resultData;

    public PayPersenter(OrderResult resultData) {
        api = RetrofitUtil.createApi();
        this.resultData = resultData;
    }


    public void ReqRxData(String req) {
//        RequestBody body=
//                RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), req);

        api.getPayData3(req)
                .map(new Func1<String, OrderBean>() {
                    @Override
                    public OrderBean call(String s) {
                        return GsonUtil.fromJson(s, OrderBean.class);
                    }
                })
                .subscribeOn(Schedulers.io())    //表示时间发生的线程
                .observeOn(AndroidSchedulers.mainThread())    //表示时间消费的线程
                .subscribe(new Observer<OrderBean>() {
                               @Override
                               public void onCompleted() {

                               }

                               @Override
                               public void onError(Throwable e) {
                                   LogUtil.d("数据返回出错--" + e.getMessage());
                                   resultData.OrderResult(null);
                               }

                               @Override
                               public void onNext(OrderBean useentity) {
                                   LogUtil.d("数据返回成功");
                                   resultData.OrderResult(useentity);
                               }
                           }

                );
    }


}
