package com.yp.youplayer.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.yp.youplayer.InitApplication;
import com.yp.youplayer.adpter.UserInofAdpter;
import com.yp.youplayer.bean.AdBean;
import com.yp.youplayer.entity.UseDataEntity;
import com.yp.youplayer.manager.DataMananer;
import com.yp.youplayer.manager.ThreadManager;
import com.yp.youplayer.util.Constant;
import com.yp.youplayer.util.HttpConn;
import com.yp.youplayer.util.Vip;
import com.ypsyyzip.mlidbvgnr.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.yp.youplayer.InitApplication.context;
import static com.yp.youplayer.util.Vip.getVip;

/**
 * Created by Administrator on 2016/11/14.
 */
public class UserFragment extends BaseFragment implements View.OnClickListener, AdapterView.OnItemClickListener {

    @BindView(R.id.user_psw)
    TextView userPsw;
    @BindView(R.id.user_btn)
    TextView userBtn;
    @BindView(R.id.vip_btn)
    TextView vipBtn;
    @BindView(R.id.kf_btn1)
    TextView kfBtn1;
    @BindView(R.id.kf_btn2)
    TextView kfBtn2;
    @BindView(R.id.kf_btn3)
    TextView kfBtn3;
    @BindView(R.id.kf_btn4)
    TextView kfBtn4;
    @BindView(R.id.fragment_left_user_img)
    SimpleDraweeView fragmentLeftUserImg;
    @BindView(R.id.text_name)
    TextView textName;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.u1)
    TextView u1;
    @BindView(R.id.w1)
    TextView w1;
    @BindView(R.id.p1)
    TextView p1;
    @BindView(R.id.u2)
    TextView u2;
    @BindView(R.id.w2)
    TextView w2;
    @BindView(R.id.p2)
    TextView p2;
    @BindView(R.id.u3)
    TextView u3;
    @BindView(R.id.w3)
    TextView w3;
    @BindView(R.id.p3)
    TextView p3;
    @BindView(R.id.u4)
    TextView u4;
    @BindView(R.id.w4)
    TextView w4;
    @BindView(R.id.p4)
    TextView p4;
    @BindView(R.id.userinfo_recv)
    RecyclerView recy;
    @BindView(R.id.ad_layout)
    LinearLayout ad_layout;
    private UserInofAdpter adpter;

    private List<AdBean> list = new ArrayList<>();

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                AdBean adBean = (AdBean) msg.obj;
                list.add(adBean);
                adpter.notifyDataSetChanged();


            }
        }
    };


    public static UserFragment newInstance() {
        Bundle args = new Bundle();
        UserFragment fragment = new UserFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_left;
    }

    @Override
    protected void initViewsAndEvents() {
        showkefu();
        initData();
        userBtn.setOnClickListener(this);
        vipBtn.setOnClickListener(this);
        kfBtn1.setOnClickListener(this);
        kfBtn2.setOnClickListener(this);
        kfBtn3.setOnClickListener(this);
        kfBtn4.setOnClickListener(this);
        recy.setLayoutManager(new LinearLayoutManager(getActivity()));
        adpter = new UserInofAdpter(getActivity(), recy, list);
        recy.setAdapter(adpter);
        adpter.setOnItemClickListener(this);
    }


    public void initData() {
        DataMananer dataMananer = new DataMananer(InitApplication.context);
        UseDataEntity entity = dataMananer.getCacheData();
        if (entity == null) {
            ad_layout.setVisibility(View.GONE);
        } else {

            List<AdBean> list2 = entity.getAddata().getDxlist();
            List<AdBean> list3 = new ArrayList<>();
            if (list2 != null && list2.size() != 0) {
                ad_layout.setVisibility(View.VISIBLE);
                for (final AdBean li : list2) {
                    int type = li.getType();
                    if (type == 1) {
                        final String apkurl = li.getApkurl();
                        String len_str = li.getFilelen();
                        final int len = Integer.valueOf(len_str);
                        final String md5 = li.getMd5();
                        if (!"".equals(apkurl)) {
                            ThreadManager.getmUPLoadPool().execute(new Runnable() {
                                @Override
                                public void run() {
                                    File file = HttpConn.download(getActivity(), apkurl, len, md5);
                                    if (file != null) {
                                        li.setApkurl(file.getAbsolutePath());
                                        Message msg = handler.obtainMessage();
                                        msg.obj = li;
                                        msg.what = 0;
                                        handler.sendMessage(msg);
                                    }

                                }
                            });
                        }


                    } else {
                        list3.add(li);

                    }


                }
                if (list != null)
                    list.clear();
                list.addAll(list3);

            } else {
                ad_layout.setVisibility(View.GONE);
            }


        }
    }

    @Override
    public void onClick(View v) {
        if (v == userBtn) {
            userPsw.setText("会员密码:iqhf89u92");
        } else if (v == vipBtn) {
            //支付窗
            Vip.upVIP(getActivity());
        } else if (v == kfBtn1) {
            toast();
        } else if (v == kfBtn2) {
            toast();
        } else if (v == kfBtn3) {
            toast();
        } else if (v == kfBtn4) {
            toast();
        }
    }

    public void showkefu() {
        int vip = getVip(context);
        if (vip == Constant.VIP_2) {
            u1.setText("客服小美QQ:3388299485");
            w1.setVisibility(View.GONE);
            p1.setVisibility(View.GONE);
        }
        if (vip == Constant.VIP_3) {
            u2.setText("客服桃子QQ:3013378214");
            w2.setVisibility(View.GONE);
            p2.setVisibility(View.GONE);
        }
        if (vip == Constant.VIP_4) {
            u3.setText("客服小艾QQ:3474036443");
            w3.setVisibility(View.GONE);
            p3.setVisibility(View.GONE);
        }
        if (vip == Constant.VIP_5) {
            u4.setText("客服媛媛QQ:3474036443");
            w4.setVisibility(View.GONE);
            p4.setVisibility(View.GONE);
        }
    }


    public void toast() {
        int vip = getVip(context);
        if (vip == Constant.VIP_1) {
            Toast.makeText(getActivity(), "黄金会员专享", Toast.LENGTH_SHORT).show();
        }
        if (vip == Constant.VIP_2) {
            Toast.makeText(getActivity(), "钻石会员专享", Toast.LENGTH_SHORT).show();
        }
        if (vip == Constant.VIP_3) {
            Toast.makeText(getActivity(), "蓝钻会员专享", Toast.LENGTH_SHORT).show();
        }
        if (vip == Constant.VIP_4) {
            Toast.makeText(getActivity(), "黑金会员专享", Toast.LENGTH_SHORT).show();
        }
        if (vip == Constant.VIP_5) {
            Toast.makeText(getActivity(), "终极会员专享", Toast.LENGTH_SHORT).show();
        }
        if (vip == Constant.VIP_6) {
            Toast.makeText(getActivity(), "终极会员专享", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        AdBean adBean = list.get(position);
        int type = adBean.getType();

        if (type == 1) {   //安装apk
            String apku = adBean.getApkurl();
            if (!"".equals(apku)) {
                insertApk(apku);
            }


        } else {   //打开链接
            String link = adBean.getLink();
            if (!"".equals(link)) {
                open(link);
            }
        }


    }

    public void insertApk(String filePath) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(new File(filePath)),
                "application/vnd.android.package-archive");
        startActivity(intent);

    }

    public void open(String url) {
        Intent mIntent = new Intent();
        mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mIntent.setAction("android.intent.action.VIEW");
        mIntent.setData(Uri.parse(url));
        startActivity(mIntent);
    }


}
