package com.yp.youplayer.Persenter;

import android.content.Context;

import com.yp.youplayer.Rxhttpnet.RetrofitUtil;
import com.yp.youplayer.Rxhttpnet.RxApi;
import com.yp.youplayer.entity.AdEntity;
import com.yp.youplayer.entity.UseDataEntity;
import com.yp.youplayer.intefence.ResultData;
import com.yp.youplayer.manager.DataMananer;
import com.yp.youplayer.util.GsonUtil;
import com.yp.youplayer.util.LogUtil;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

import static com.yp.youplayer.util.LogUtil.d;

/**
 * Created by Administrator on 2016/11/21.
 */
public class UsePersenter {

    //    private Subscription subscription;
    private RxApi api;

    private UseDataEntity useentity;
    private Context context;
    private ResultData resultData;
    private Subscription sub;

    public UsePersenter(ResultData resultData) {
        api = RetrofitUtil.createApi();
        this.resultData = resultData;
    }


    public void ReqRxData(String req) {
        api.getYouBoData(req)
                .map(new Func1<String, UseDataEntity>() {
                    @Override
                    public UseDataEntity call(String s) {
                        d("返回数据-->" + s);
                        return GsonUtil.fromJson(s, UseDataEntity.class);
                    }
                })
                .subscribeOn(Schedulers.io())    //表示时间发生的线程
                .observeOn(AndroidSchedulers.mainThread())    //表示时间消费的线程
                .subscribe(new Observer<UseDataEntity>() {
                               @Override
                               public void onCompleted() {
                               }

                               @Override
                               public void onError(Throwable e) {
                                   d("数据返回出错--" + e.getMessage());
                                   resultData.resultData();
                               }

                               @Override
                               public void onNext(UseDataEntity useentity) {
                                   d("数据返回成功");
                                   AdEntity adEntity = useentity.getAddata();
                                   LogUtil.d("获取到的弹窗数据个数-->" + adEntity.getTclist().size());


                                   if (useentity != null) {
                                       DataMananer dataMananer = new DataMananer(context);
                                       dataMananer.saveCacheData(useentity);
                                       resultData.resultData();
                                   }
                               }
                           }
                );
    }
}
