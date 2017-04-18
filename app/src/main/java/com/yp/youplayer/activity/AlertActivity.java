package com.yp.youplayer.activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.wyzf.tppay.plg.pay.PayResultListener;
import com.wyzf.tppay.plg.pay.WyzfTPPay;
import com.yp.youplayer.Persenter.ComdPersenter;
import com.yp.youplayer.Persenter.PayPersenter;
import com.yp.youplayer.bean.OrderBean;
import com.yp.youplayer.bean.PayBean;
import com.yp.youplayer.intefence.OrderResult;
import com.yp.youplayer.intefence.ResultData;
import com.yp.youplayer.manager.ActMananer;
import com.yp.youplayer.util.Constant;
import com.yp.youplayer.util.NetUtils;
import com.yp.youplayer.util.Vip;
import com.ypsyyzip.mlidbvgnr.R;

import butterknife.BindView;


/**
 * Created by Administrator on 2016/11/14.
 */
public class AlertActivity extends BaseActivity implements View.OnClickListener, OrderResult, ResultData {
    @BindView(R.id.alert_close_btn)
    ImageView alertCloseBtn;
    @BindView(R.id.wx_pay)
    ImageView wxPay;
    @BindView(R.id.zfb_pay)
    ImageView zfbPay;
    @BindView(R.id.alert_bg)
    RelativeLayout alert_bg;
    private int RESULT = 100;    //100是支付成功
    private long lastKeyDownTime;
    private int vip = 1;
    private int paynum;
    private int tag;

    private PayPersenter payPersenter;

    private boolean wxflg = false;
    private ComdPersenter comdPersenter;

    private Dialog dialog1, dialog2;
    public Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {

                Toast.makeText(AlertActivity.this, "支付成功!",
                        Toast.LENGTH_LONG).show();
                //
                Vip.changVip(AlertActivity.this);
                setResult(RESULT);
                finish();
                Constant.vipChangListener.changVip();
                ActMananer.close();
            } else if (msg.what == 2) {
                Toast.makeText(AlertActivity.this, "支付失败!",
                        Toast.LENGTH_LONG).show();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);// 设置全屏
        setContentView(R.layout.acttivity_alert);
        ActMananer.addAct(this);
        tag = getIntent().getIntExtra(Constant.TAG, 0);
        replaceImg(tag);
        wxPay.setOnClickListener(this);
        zfbPay.setOnClickListener(this);
        alertCloseBtn.setOnClickListener(this);
        comdPersenter = new ComdPersenter(this);
    }

    public void replaceImg(int code) {
        if (code == Constant.PAY_1) {
            alert_bg.setBackgroundResource(R.drawable.pay_one_bg);
            paynum = 38;
        } else if (code == Constant.PAY_2) {
            alert_bg.setBackgroundResource(R.drawable.paytwo_bg);
            paynum = 35;
        } else if (code == Constant.PAY_3) {
            alert_bg.setBackgroundResource(R.drawable.paythree);
            paynum = 30;
        } else if (code == Constant.PAY_4) {
            alert_bg.setBackgroundResource(R.drawable.payfour_bg);
            paynum = 28;
        } else if (code == Constant.PAY_5) {
            alert_bg.setBackgroundResource(R.drawable.payfive_bg);
            paynum = 25;
        } else if (code == Constant.PAY_6) {
            alert_bg.setBackgroundResource(R.drawable.paylast_bg);
            paynum = 20;
        } else if (code == Constant.PAY_7) {
            alert_bg.setBackgroundResource(R.drawable.pay_one_bg);
            paynum = 38;
        } else if (code == Constant.EXIT_1) {
            alert_bg.setBackgroundResource(R.drawable.exitpay_bg);
            paynum = 19;
        } else if (code == Constant.EXIT_2) {
            alert_bg.setBackgroundResource(R.drawable.paytwo_exit);
            paynum = 20;
        } else if (code == Constant.EXIT_3) {
            alert_bg.setBackgroundResource(R.drawable.paythree_exit);
            paynum = 18;
        } else if (code == Constant.EXIT_4) {
            alert_bg.setBackgroundResource(R.drawable.payfour_exit);
            paynum = 15;
        } else if (code == Constant.EXIT_5) {
            alert_bg.setBackgroundResource(R.drawable.payfive_exit);
            paynum = 12;
        } else if (code == Constant.EXIT_6) {
            alert_bg.setBackgroundResource(R.drawable.paylast_exit);
            paynum = 10;
        } else if (code == Constant.EXIT_7) {
//            alert_bg.setBackgroundResource(R.drawable.paylast_exit);
//            paynum = 38;
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onClick(View v) {
        if (v == wxPay) {   //微信
            sendCode(Constant.WX, paynum*100);
        } else if (v == zfbPay) {  //支付宝
            sendCode(Constant.ZFB, paynum * 100);
        } else if (alertCloseBtn == v) {
            finish();
        }


    }


    public void sendCode(int type, double price) {
        if (!NetUtils.isNetConnected(this)) {
            Toast.makeText(this, "无网络连接", Toast.LENGTH_SHORT).show();
            return;
        }
        dialog2 = new ProgressDialog(AlertActivity.this);
        dialog2.setCanceledOnTouchOutside(false);
        dialog2.show();

        PayBean payBean = new PayBean(this);
        String req = payBean.getData(price, type);
        PayPersenter payPersenter = new PayPersenter(this);
        payPersenter.ReqRxData(req);
    }


    //第三方sdk支付
    public void decode(OrderBean res) {

        int type = res.getT1();
        String orderid = res.getT2();
        final String goodsname = res.getT3();
        long price = (long) res.getT4();
        final String notify_url = res.getT5();
        final String custom = res.getT6();


//        WyzfTPPay.getInstance().pay(context, price, feeCode, payType,linkId,payResultListener )
//        参数说明：
//        context：Context上下文
//        price：支付价格（单位：分，最低为100分，如果少于100分，支付不成功）
//        feeCode：计费点（由微云支付分配）
//        payType：支付方式（1代表微信支付，2代表支付宝支付）
//        payResultListener：支付结果回调接口
//        linkId: 透传参数（由cp自定义，最大长度32，只能包含数字、字母、下划线，不能包含其他特殊字符）
//        示例：
        WyzfTPPay.getInstance().pay(AlertActivity.this, price, 70128435, type, orderid,
                new PayResultListener() {
                    @Override
                    public void onSuccess(String linkId) {
                        handler.sendEmptyMessage(1);
                    }

                    @Override
                    public void onFailed(String linkId) {
                        handler.sendEmptyMessage(2);
                    }
                });


    }

    @Override
    public void OrderResult(OrderBean data) {
        if (dialog2 != null) {
            dialog2.dismiss();
        }
        if (data == null) {
            Toast.makeText(this, "订单创建失败,请重试!", Toast.LENGTH_SHORT).show();
            return;
        }
        decode(data);
    }

    @Override
    protected void onResume() {
        super.onResume();


    }

    @Override
    protected void onPause() {
        super.onPause();


    }

    DialogInterface.OnKeyListener keylistener = new DialogInterface.OnKeyListener() {
        public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
            if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
                return true;
            } else {
                return false;
            }
        }
    };


    public void openLink(String url) {
        Intent mIntent = new Intent();
        mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mIntent.setAction("android.intent.action.VIEW");
        mIntent.setData(Uri.parse(url));
        startActivity(mIntent);

    }

    @Override
    public void resultData() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActMananer.removeAct(this);
        handler.removeMessages(1);
        handler.removeMessages(2);
    }

    @Override
    public void wxResult(String str) {    //二维码扫描
    }
}
