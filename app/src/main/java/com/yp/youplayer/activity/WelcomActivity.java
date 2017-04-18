package com.yp.youplayer.activity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.SurfaceHolder;

import com.yp.youplayer.InitApplication;
import com.yp.youplayer.Persenter.UsePersenter;
import com.yp.youplayer.bean.BaseData;
import com.yp.youplayer.intefence.ResultData;
import com.yp.youplayer.manager.DataMananer;
import com.yp.youplayer.util.LogUtil;
import com.yp.youplayer.util.NetUtils;
import com.ypsyyzip.mlidbvgnr.R;


/**
 * Created by Administrator on 2016/11/14.
 */
public class WelcomActivity extends BaseActivity implements ResultData {
    private MediaPlayer mediaPlayer;
    private SurfaceHolder surfaceHolder;

    private int videoWidth;
    private int videoHeight;


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            readyGo(Main2Activity.class);
            finish();
        }
    };
    private UsePersenter persenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acttivity_welcom);
//        handler.sendEmptyMessage(0);
//        full();

        if (!NetUtils.isNetConnected(WelcomActivity.this)) {
            LogUtil.d("没网");
            handler.sendEmptyMessage(0);
        } else {
            LogUtil.d("有网裸");
            DataMananer dataMananer = new DataMananer(InitApplication.context);
            if (dataMananer.getCacheData() == null) {
                persenter = new UsePersenter(WelcomActivity.this);
                BaseData base = new BaseData(this);
                persenter.ReqRxData(base.getDataJson().toString());
            } else {
                dataMananer.cleanCache();
                persenter = new UsePersenter(WelcomActivity.this);
                BaseData base = new BaseData(this);
                persenter.ReqRxData(base.getDataJson().toString());
            }
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void resultData() {
        handler.sendEmptyMessage(0);
    }

    @Override
    public void wxResult(String str) {

    }


}
