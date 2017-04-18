package com.yp.youplayer.activity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.yp.youplayer.util.Constant;
import com.yp.youplayer.util.LogUtil;
import com.ypsyyzip.mlidbvgnr.R;

import butterknife.BindView;

/**
 * Created by Administrator on 2016/11/14.
 */
public class PlayerActivity extends BaseActivity {

    @BindView(R.id.videoview)
    VideoView videoview;
    @BindView(R.id.pro_layout)
    LinearLayout proLayout;
    private String videoUrl;

    private RelativeLayout bottom_layout;

    private SeekBar seekBar;

    private TextView time;
    private ImageView player;
    private long endTime;
    private String endtimestr;

    private boolean iscom;   //是否播放完成
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                long index = setProess();

                sendEmptyMessageDelayed(1, 1000);
            }


        }
    };


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);// 设置全屏
        setContentView(R.layout.activity_player);


        bottom_layout = (RelativeLayout) findViewById(R.id.botom_layout);
        seekBar = (SeekBar) findViewById(R.id.player_progess);
        player = (ImageView) findViewById(R.id.ic_player);
        time = (TextView) findViewById(R.id.ic_end_time);

        time.setText("00:00/" + endtimestr);
        player.setSelected(false);
        seekBar.setMax(1000);
        seekBar.setProgress(0);

        videoUrl = getIntent().getStringExtra(Constant.PlayFLG_2);
        videoview.setVideoURI(Uri.parse(videoUrl));

        videoview.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {   //资源加载完成
                endTime = videoview.getDuration() * 10 + 40 * 60 * 1000;
                endtimestr = getTime(endTime);


                proLayout.setVisibility(View.GONE);
                videoview.requestFocus();
                handler.sendEmptyMessage(1);
            }
        });

        videoview.setOnInfoListener(new MediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(MediaPlayer mp, int what, int extra) {
                LogUtil.d("触发信息事件-.>");
                if (mp.isPlaying()) {
                    LogUtil.d("视频播放中");
                    player.setSelected(true);
                } else {
                    LogUtil.d("视频停止");
                    player.setSelected(false);
                }


                return false;
            }
        });


        videoview.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {   //视频播放完成的时候
                handler.removeMessages(1);
                finish();


            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                handler.removeMessages(1);    //开始移动
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(PlayerActivity.this, "你不是会员,无法快进哦", Toast.LENGTH_SHORT).show();
                handler.sendEmptyMessage(1);
            }
        });
        player.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (videoview.isPlaying()) {
                    videoview.pause();
                    player.setSelected(false);
                } else {
                    videoview.start();
                    player.setSelected(true);
                }

            }
        });
        videoview.canPause();
        videoview.requestFocus();
        videoview.start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        videoview.resume();
    }

    @Override
    protected void onStop() {
        super.onStop();
        handler.removeMessages(1);
    }

    @Override
    protected void onPause() {
        super.onPause();
        videoview.pause();
    }

    public long setProess() {

        long du = videoview.getCurrentPosition();


        long end = endTime;


        LogUtil.d("进度-->" + (du / end) * 1000);
        int pr = (int) (du / 1000 * 2);
        LogUtil.d("进度-->" + pr);
        seekBar.setProgress(pr);
        LogUtil.d("进度条-->" + seekBar.getProgress());

        int min = (int) (du / 60000);
        int mm = (int) (du % 60000 / 1000);
        String min2 = "";
        if (min < 10) {
            min2 = "0" + min;

        } else {

            min2 = "" + min;
        }
        String mm2 = "";
        if (mm < 10) {
            mm2 = "0" + mm;
        } else {
            mm2 = "" + mm;
        }


        LogUtil.d("分-秒->" + min2 + ":" + mm2);

        time.setText(min2 + ":" + mm2 + "/" + endtimestr);
        return du;

    }

    public String getTime(long ite) {

        int min = (int) (ite / 60000);
        int mm = (int) (ite % 60000 / 1000);


        String min2 = "";
        if (min < 10) {
            min2 = "0" + min;

        } else {

            min2 = "" + min;
        }
        String mm2 = "";
        if (mm < 10) {
            mm2 = "0" + mm;
        } else {
            mm2 = "" + mm;
        }


        return min2 + ":" + mm2;

    }


}
