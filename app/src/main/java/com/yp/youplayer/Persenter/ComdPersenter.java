package com.yp.youplayer.Persenter;

import android.content.Context;

import com.yp.youplayer.Rxhttpnet.RetrofitUtil;
import com.yp.youplayer.Rxhttpnet.RxApi;
import com.yp.youplayer.bean.ResultBean;
import com.yp.youplayer.entity.UseDataEntity;
import com.yp.youplayer.intefence.ResultData;
import com.yp.youplayer.util.GsonUtil;
import com.yp.youplayer.util.LogUtil;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * 查询是否wx支付成功
 * Created by Administrator on 2016/11/22.
 */
public class ComdPersenter {
    private RxApi api;

    private UseDataEntity useentity;
    private Context context;
    private ResultData resultData;

    public ComdPersenter(ResultData resultData) {
        api = RetrofitUtil.createApi();
        this.resultData = resultData;
    }


    public void ReqRxData(String req) {

        api.getWxPayResult(req)
                .map(new Func1<String, ResultBean>() {
                    @Override
                    public ResultBean call(String s) {

                        return GsonUtil.fromJson(s, ResultBean.class);
                    }
                })
                .subscribeOn(Schedulers.io())    //表示时间发生的线程
                .observeOn(AndroidSchedulers.mainThread())    //表示时间消费的线程
                .subscribe(new Observer<ResultBean>() {
                               @Override
                               public void onCompleted() {

                               }

                               @Override
                               public void onError(Throwable e) {
                                   LogUtil.d("数据返回出错--" + e.getMessage());
                                   resultData.wxResult(null);
                               }

                               @Override
                               public void onNext(ResultBean useentity) {
                                   LogUtil.d("据返回成功");
                                   if (useentity != null) {
                                       resultData.wxResult(useentity.getCode());
                                   } else {
                                       resultData.wxResult(null);
                                   }
                               }
                           }

                );
    }


}
