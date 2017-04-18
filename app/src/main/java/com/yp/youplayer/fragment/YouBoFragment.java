package com.yp.youplayer.fragment;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ScrollView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerClickListener;
import com.youth.banner.loader.ImageLoader;
import com.yp.youplayer.InitApplication;
import com.yp.youplayer.activity.TopplayerActivity;
import com.yp.youplayer.activity.WuMplayerActivity;
import com.yp.youplayer.adpter.YouboAdter;
import com.yp.youplayer.bean.VideoBean;
import com.yp.youplayer.entity.UseDataEntity;
import com.yp.youplayer.manager.DataMananer;
import com.yp.youplayer.manager.FullyGridLayoutManager;
import com.yp.youplayer.util.Constant;
import com.yp.youplayer.util.DataUtil;
import com.ypsyyzip.mlidbvgnr.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2016/11/14.
 */
public class YouBoFragment extends BaseFragment implements AdapterView.OnItemClickListener {

    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.yb_recyview)
    RecyclerView ybRecyview;

    @BindView(R.id.youbo_scoll)
    ScrollView youbo_scoll;

    private List<VideoBean> mlist = new ArrayList<>();
    private List<String> bennerImg = new ArrayList<>();
    private List<String> bennertitle = new ArrayList<>();


    private YouboAdter adter;


    public static YouBoFragment newInstance() {
        Bundle args = new Bundle();
        YouBoFragment fragment = new YouBoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void initViewsAndEvents() {
        initData();
        initBanner();
        initAdpter();
    }

    // 暂停图片请求
    public static void imagePause() {
        Fresco.getImagePipeline().pause();
    }

    // 恢复图片请求
    public static void imageResume() {
        Fresco.getImagePipeline().resume();
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_youbo;
    }


    public void initData() {
        DataMananer dataMananer = new DataMananer(InitApplication.context);
        UseDataEntity entity = dataMananer.getCacheData();
        if (entity == null) {
            entity = DataUtil.getIntence().getEntity(InitApplication.context);
        }
        List<VideoBean> list = entity.getPtdata().getBrlist();
        for (VideoBean li : list) {
            bennerImg.add(li.getImgurl());
            bennertitle.add(li.getName());
        }
        mlist.addAll(entity.getPtdata().getYblist());

    }


    public void initBanner() {

        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);   //显示圆形指示器
//        banner.isAutoPlay(true);   //设置是否轮播
        banner.setBannerAnimation(Transformer.Tablet);
        banner.setImages(bennerImg);
        banner.setBannerTitles(bennertitle);
        banner.setImageLoader(new FrescoLoader());
        banner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Constant.Benner = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        banner.setOnBannerClickListener(new OnBannerClickListener() {
            @Override
            public void OnBannerClick(int position) {
                Intent intent = new Intent(getActivity(), WuMplayerActivity.class);
                intent.putExtra(Constant.PlayFLG, bennerImg.get(position - 1));
                intent.putExtra(Constant.PlayFLG_1, bennertitle.get(position - 1));
                startActivity(intent);

            }
        });
        banner.start();
    }

    public void initAdpter() {
        adter = new YouboAdter(InitApplication.getAppContext(), mlist, ybRecyview);
        ybRecyview.setLayoutManager(new FullyGridLayoutManager(InitApplication.getAppContext(), 2));
        ybRecyview.setAdapter(adter);
        adter.setOnItemClickListener(this);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.d("allen", "-------------");
        Intent intent = new Intent(getActivity(), TopplayerActivity.class);
        intent.putExtra(Constant.PlayFLG_2, mlist.get(position).getVideurl());
        intent.putExtra(Constant.PlayFLG, mlist.get(position).getImgurl());
        intent.putExtra(Constant.PlayFLG_1, mlist.get(position).getName());
        startActivity(intent);
    }


    /**
     * 自定义bnner图片加载框架
     */
    class FrescoLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            if (path != null) {
                imageView.setImageURI(Uri.parse((String) path));
            }
        }

        @Override
        public ImageView createImageView(Context context) {
            SimpleDraweeView simpleDraweeView = (SimpleDraweeView) LayoutInflater.from(context).inflate(R.layout.item_benner, null);
            return simpleDraweeView;
        }

    }


}
