package com.yp.youplayer.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.yp.youplayer.adpter.CommentAdpter;
import com.yp.youplayer.manager.ActMananer;
import com.yp.youplayer.manager.FullyLinearLayoutManager;
import com.yp.youplayer.util.Constant;
import com.yp.youplayer.util.Vip;
import com.ypsyyzip.mlidbvgnr.R;

import butterknife.BindView;

/**
 * Created by Administrator on 2016/11/15.
 */
public class TopplayerActivity extends BaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener {


    @BindView(R.id.ic_player)
    ImageView icPlayer;
    @BindView(R.id.ic_info_tobig)
    ImageView icInfoTobig;
    @BindView(R.id.wuma_send_btn)
    ImageView wumaSendBtn;
    @BindView(R.id.wuma_vvvideo_img)
    ImageView video_img;
    @BindView(R.id.ic_start_item)
    ImageView icStartItem;
    @BindView(R.id.wuma_play_edit)
    EditText wumaPlayEdit;
    @BindView(R.id.common_logo_play)
    ImageView commonLogoPlay;
    @BindView(R.id.play_title)
    TextView play_title;
    @BindView(R.id.back)
    LinearLayout back;
    @BindView(R.id.video_layout)
    RelativeLayout videoLayout;
    @BindView(R.id.wuma_user_recyview)
    RecyclerView wumaUserRecyview;
    @BindView(R.id.centen_img1)
    SimpleDraweeView centenImg1;
    @BindView(R.id.centen_img2)
    SimpleDraweeView centenImg2;
    @BindView(R.id.centen_img3)
    SimpleDraweeView centenImg3;
    @BindView(R.id.scollview)
    ScrollView scollview;

    private int paytag;
    private CommentAdpter commentAdpter;

    private String videourl = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wuma_play);
        ActMananer.addAct(this);
        wumaSendBtn.setOnClickListener(this);
        icPlayer.setOnClickListener(this);
        icInfoTobig.setOnClickListener(this);
        videoLayout.setOnClickListener(this);
        back.setOnClickListener(this);
        initAdpter();
        initView();
        scollview.smoothScrollTo(0, 20);
        scollview.setFocusable(true);

    }


    public void initView() {
        String url = getIntent().getStringExtra(Constant.PlayFLG);
        String name = getIntent().getStringExtra(Constant.PlayFLG_1);
        videourl = getIntent().getStringExtra(Constant.PlayFLG_2);
        paytag = getIntent().getIntExtra(Constant.TAG, Constant.PAY_1);
        play_title.setText(name + "");
//        String path = FrescoUtil.isExist(url);
        video_img.setImageURI(Uri.parse(url));
//        if (path.isEmpty()) {
//        } else {
//            video_img.setImageURI(Uri.parse("file://" + path));
//        }
//        FrescoUtil.savePicture(url, InitApplication.context);

        centenImg1.setImageURI("http://rel.yanwod.com:9300/rstp/res/img/1477219363593.jpg");
        centenImg2.setImageURI("http://rel.yanwod.com:9300/rstp/res/img/1477219363511.jpg");
        centenImg3.setImageURI("http://rel.yanwod.com:9300/rstp/res/img/1477219365870.jpg");

        centenImg3.setOnClickListener(this);
        centenImg2.setOnClickListener(this);
        centenImg1.setOnClickListener(this);

    }

    public void initAdpter() {
        commentAdpter = new CommentAdpter(this, wumaUserRecyview);
        wumaUserRecyview.setLayoutManager(new FullyLinearLayoutManager(this));
        wumaUserRecyview.setAdapter(commentAdpter);
        commentAdpter.setOnItemClickListener(this);
    }


    @Override
    public void onClick(View v) {

        if (v == wumaSendBtn) {
            if (wumaPlayEdit.getText().toString().isEmpty()) {
                Toast.makeText(this, "内容不能为空", Toast.LENGTH_SHORT).show();
                return;
            }
            Toast.makeText(this, "你的评论已提交,请等待审核", Toast.LENGTH_SHORT).show();
            wumaPlayEdit.setText("");
        }
        if (v == icPlayer) {
        }
        if (v == icInfoTobig) {
        }
        if (v == back) {
            finish();

        }
        if (v == videoLayout) {
            Intent intent = new Intent(this, PlayerActivity.class);
            intent.putExtra(Constant.PlayFLG_2, videourl);
            startActivityForResult(intent, 100);
        }
        if (v == centenImg3) {
            Vip.upVIP(this);
        }
        if (v == centenImg2) {
            Vip.upVIP(this);
        }
        if (v == centenImg1) {
            Vip.upVIP(this);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 100) {

        } else {
            Vip.upVIP(this);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, "非VIP会员无法查看用户信息", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActMananer.removeAct(this);
    }
}




