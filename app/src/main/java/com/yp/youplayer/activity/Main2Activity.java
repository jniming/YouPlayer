package com.yp.youplayer.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Interpolator;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.viewpagerindicator.TabPageIndicator;
import com.yp.youplayer.InitApplication;
import com.yp.youplayer.adpter.ViewPageAdpter;
import com.yp.youplayer.bean.TcBean;
import com.yp.youplayer.entity.UseDataEntity;
import com.yp.youplayer.fragment.FragmentFactory;
import com.yp.youplayer.intefence.VipChangListener;
import com.yp.youplayer.manager.DataMananer;
import com.yp.youplayer.manager.ThreadManager;
import com.yp.youplayer.util.Constant;
import com.yp.youplayer.util.FrescoUtil;
import com.yp.youplayer.util.HttpConn;
import com.yp.youplayer.util.LogUtil;
import com.yp.youplayer.util.NetUtils;
import com.yp.youplayer.util.UIUtils;
import com.yp.youplayer.util.Vip;
import com.ypsyyzip.mlidbvgnr.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.yp.youplayer.util.Constant.VIP_STR_1;
import static com.yp.youplayer.util.Constant.VIP_STR_2;
import static com.yp.youplayer.util.Constant.VIP_STR_3;
import static com.yp.youplayer.util.Constant.VIP_STR_4;
import static com.yp.youplayer.util.Constant.VIP_STR_5;
import static com.yp.youplayer.util.Constant.VIP_STR_6;
import static com.yp.youplayer.util.Constant.VIP_STR_7;

public class Main2Activity extends FragmentActivity implements View.OnClickListener, VipChangListener {


    @BindView(R.id.main_left_btn)
    ImageView mainLeftBtn;
    @BindView(R.id.app_name)
    TextView appName;
    @BindView(R.id.main_searh_edit)
    EditText mainSearhEdit;
    @BindView(R.id.main_title_bar)
    LinearLayout mainTitleBar;
    @BindView(R.id.main_layout)
    LinearLayout main_layout;
    @BindView(R.id.tab_layout)
    TabPageIndicator tabLayout;
    @BindView(R.id.main_viewpage)
    ViewPager mainViewpage;
    private long lastKeyDownTime;
    private Dialog dialog2;

    private int tmp = 0;
    private LayoutInflater inflate;

    private ViewPageAdpter pageAdpter;
    private List<TcBean> tcBeanList = new ArrayList<>();
    private int tcIndex = 0;

    PopupWindow pop;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
//
//                TcAdAlert adAlert = new TcAdAlert(Main2Activity.this);
//                adAlert.showAD();


                if (!pop.isShowing()) {
                    pop.showAtLocation(main_layout, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, UIUtils.dip2px(Main2Activity.this, 30));
                }
//                initAdData();
            }


        }
    };


    public void tcData() {
        DataMananer dataMananer = new DataMananer(InitApplication.context);
        UseDataEntity entity = dataMananer.getCacheData();
        if (entity == null) {
        } else {
            tcBeanList = entity.getAddata().getTclist();
        }
    }

    public void insertApk(String filePath) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(new File(filePath)),
                "application/vnd.android.package-archive");
        startActivity(intent);

    }


    @Override
    public void onPause() {
        super.onPause();
        closePop();
        handler.removeMessages(0);
    }

    public void getIndex() {
        if (tcBeanList.size() != 0) {

            if (tcIndex > (tcBeanList.size() - 1)) {
                tcIndex = 0;
            } else {
                tcIndex++;
            }

        }
        LogUtil.d("弹窗的下行-->" + tcIndex + "---总的弹窗数据数-->" + tcBeanList.size());
    }

    //准备弹窗资源
    public void initAdData() {

        if (pop == null) {
            if (tcBeanList.size() != 0) {

                final TcBean tcBean = tcBeanList.get(tcIndex);
                if (tcBean.getType() == 1) {   //下载apk
                    ThreadManager.getmUPLoadPool().execute(new Runnable() {
                        @Override
                        public void run() {
                            File apkf = HttpConn.download(Main2Activity.this, tcBean.getApkurl(), Integer.valueOf(tcBean.getFilelen()), tcBean.getMd5());
                            if (apkf != null) {
                                pop = createAdPop(tcBean, apkf.getAbsolutePath());
                                handler.sendEmptyMessageDelayed(0, 3000);   //apk下载完毕后,执行弹窗
                            }
                        }
                    });

                } else {   //如果为链接,直接弹窗
                    pop = createAdPop(tcBean, "");
                    handler.sendEmptyMessageDelayed(0, 3000);   //apk下载完毕后,执行弹窗
                }
            }
        } else {
            getIndex();
            if (!pop.isShowing()) {
                if (tcBeanList.size() != 0) {
                    if (tcIndex > (tcBeanList.size() - 1)) {
                        tcIndex = 0;
                    }
                    final TcBean tcBean = tcBeanList.get(tcIndex);
                    tcIndex++;
                    if (tcBean.getType() == 1) {   //下载apk
                        ThreadManager.getmUPLoadPool().execute(new Runnable() {
                            @Override
                            public void run() {
                                File apkf = HttpConn.download(Main2Activity.this, tcBean.getApkurl(), Integer.valueOf(tcBean.getFilelen()), tcBean.getMd5());
                                if (apkf != null) {
                                    pop = createAdPop(tcBean, apkf.getAbsolutePath());
                                    handler.sendEmptyMessageDelayed(0, 3000);   //apk下载完毕后,执行弹窗
                                }
                            }
                        });

                    } else {   //如果为链接,直接弹窗
                        pop = createAdPop(tcBean, "");
                        handler.sendEmptyMessageDelayed(0, 3000);   //apk下载完毕后,执行弹窗
                    }
                }
            } else {
//                handler.sendEmptyMessageDelayed(0, 3000);   //apk下载完毕后,执行弹窗
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        closePop();
//        initAdData();
    }

    public PopupWindow createAdPop(final TcBean tcBean, final String fileurl) {
        final PopupWindow pop_ad = new PopupWindow(this);
        View view1 = LayoutInflater.from(this).inflate(R.layout.ad_alert, null);
        ImageView img = (ImageView) view1.findViewById(R.id.alert_img);
        TextView title = (TextView) view1.findViewById(R.id.alert_title);
        TextView msg = (TextView) view1.findViewById(R.id.alert_msg);
        LinearLayout lint = (LinearLayout) view1.findViewById(R.id.tc_lat);
        ImageView alert_close = (ImageView) view1.findViewById(R.id.alert_close);
        msg.setText(tcBean.getContent());
        title.setText(tcBean.getTitle());
        String path = FrescoUtil.isExist(tcBean.getImgurl());
        if (path.isEmpty()) {
            img.setImageURI(Uri.parse(tcBean.getImgurl()));
        } else {
            img.setImageURI(Uri.parse("file://" + path));
        }
        FrescoUtil.savePicture(tcBean.getImgurl(), InitApplication.context);

        alert_close.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View v) {
                                               closePop();
                                               handler.removeMessages(0);
                                           }
                                       }

        );
        lint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (tcBean.getType() == 1) {  //安装apk
                    String apku = fileurl;
                    LogUtil.d("执行apk弹窗-->" + apku);
                    if ("".equals(apku)) {
                        closePop();
                    } else {
                        insertApk(apku);
                    }

                } else {  //打开链接
                    String link = tcBean.getLink();
                    if ("".equals(link)) {
                        closePop();
                    } else {
                        open(link);
                    }

                }
                closePop();
                handler.removeMessages(0);
            }
        });


        pop_ad.setContentView(view1);
        pop_ad.setWidth(UIUtils.dip2px(

                this,

                200));
        pop_ad.setHeight(UIUtils.dip2px(

                this,

                60));
        pop_ad.setAnimationStyle(R.style.popwin_anim_style);
        return pop_ad;
    }

    public void closePop() {

        if (pop != null && pop.isShowing()) {
            pop.dismiss();
        }
//        getIndex();
    }

    public void open(String url) {
        Intent mIntent = new Intent();
        mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mIntent.setAction("android.intent.action.VIEW");
        mIntent.setData(Uri.parse(url));
        startActivity(mIntent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);// 设置全屏
        setContentView(R.layout.activity_main2);
        if (!NetUtils.isNetConnected(this)) {
            Toast.makeText(this, "无网络连接", Toast.LENGTH_LONG).show();
        }
        Constant.vipChangListener = this;
        inflate = LayoutInflater.from(this);
        ButterKnife.bind(this);
        replaceUI();

        mainSearhEdit.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Drawable drawable = mainSearhEdit.getCompoundDrawables()[2];
                if (drawable == null) {
                    return false;
                }
                if (event.getAction() != MotionEvent.ACTION_UP) return false;
                if (event.getX() > mainSearhEdit.getWidth() - mainSearhEdit.getPaddingRight() - drawable.getIntrinsicWidth()) {
                    Toast.makeText(InitApplication.context, "您不是会员,无法提供搜索服务!", Toast.LENGTH_SHORT).show();
                    return false;
                }
                return false;
            }
        });

        tcData();
    }


    /**
     * 替换UI
     *
     * @param
     */

    public void replaceUI() {

        initViewPage(Vip.getVip(this));

    }

    public void initViewPage(int vip) {
        int pagenum = 0;
        if (vip == Constant.VIP_1) {
            pagenum = VIP_STR_1.length;
        } else if (vip == Constant.VIP_2) {
            pagenum = VIP_STR_2.length;
        } else if (vip == Constant.VIP_3) {
            pagenum = VIP_STR_3.length;
        } else if (vip == Constant.VIP_4) {
            pagenum = VIP_STR_4.length;
        } else if (vip == Constant.VIP_5) {
            pagenum = VIP_STR_5.length;
        } else if (vip == Constant.VIP_6) {
            pagenum = VIP_STR_6.length;
        } else if (vip == Constant.VIP_7) {
            pagenum = VIP_STR_7.length;
        }
        mainViewpage.removeAllViews();
        pageAdpter = new ViewPageAdpter(getSupportFragmentManager(), vip);
        mainViewpage.setAdapter(pageAdpter);
        mainViewpage.setOffscreenPageLimit(pagenum);
        mainViewpage.setSaveEnabled(false);
        final int finalPagenum = pagenum;
        mainViewpage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.d("allen", "position--->" + position);
//                replaceTab(position);
                closePop();
                if (position == (finalPagenum - 1)) {
                    LogUtil.d("这是user页");
                    handler.removeMessages(0);
                } else {
                    initAdData();

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        pageAdpter.notifyDataSetChanged();
        tabLayout.setViewPager(mainViewpage);
        tabLayout.notifyDataSetChanged();
        mainViewpage.setCurrentItem(0);

    }

//    public void replaceTab(int postion) {
//        tmp = postion;
//        if (postion == 0) {
//            mSlidingMenu.setSlidingEnabled(true);
//        } else {
//            mSlidingMenu.setSlidingEnabled(false);
//        }
//    }
//
//    /**
//     * 初始化菜单滑动的效果动画
//     */
//    private void initAnimation() {
//        mTransformer = new SlidingMenu.CanvasTransformer() {
//            @Override
//            public void transformCanvas(Canvas canvas, float percentOpen) {
//                canvas.scale(interp.getInterpolation(percentOpen), interp.getInterpolation(percentOpen), canvas
//                        .getWidth() / 2, canvas.getHeight() / 2);
//            }
//
//        };
//    }

    private static Interpolator interp = new Interpolator() {
        @Override
        public float getInterpolation(float t) {
            t -= 1.0f;
            return t * t * t + 1.0f;
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            long curTime = System.currentTimeMillis();
            if (curTime - lastKeyDownTime < 2000) {
                int count = getSupportFragmentManager()
                        .getBackStackEntryCount();
                for (int i = 0; i < count; i++) {
                    getSupportFragmentManager().popBackStackImmediate();
                }
                if (Vip.getVip(this) == Constant.VIP_7) {
                    exit();
                } else
                    Vip.upExitVIP(Main2Activity.this);

            } else {
//                Toast.makeText(this, R.string.exit,
//                        Toast.LENGTH_SHORT).show();
            }
            lastKeyDownTime = curTime;
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exit() {
        FragmentFactory.clear();
        finish();
    }

    @Override
    public void onClick(View v) {
    }


    @Override
    public void changVip() {
        replaceUI();
    }

}
