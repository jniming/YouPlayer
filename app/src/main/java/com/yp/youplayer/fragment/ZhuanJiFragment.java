package com.yp.youplayer.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.yp.youplayer.InitApplication;
import com.yp.youplayer.adpter.PianKuAdpter;
import com.yp.youplayer.bean.VideoBean;
import com.yp.youplayer.entity.UseDataEntity;
import com.yp.youplayer.manager.DataMananer;
import com.yp.youplayer.manager.FullyLinearLayoutManager;
import com.yp.youplayer.util.DataUtil;
import com.yp.youplayer.util.Vip;
import com.ypsyyzip.mlidbvgnr.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ZhuanJiFragment extends BaseFragment implements AdapterView.OnItemClickListener {


    @BindView(R.id.pianku_recyview)
    RecyclerView pindaoRecyview;
    @BindView(R.id.zhuanji_text)
    TextView tv;
    private PianKuAdpter adter;
    private List<VideoBean> mlist = new ArrayList<>();

    public void initData() {
        DataMananer dataMananer = new DataMananer(InitApplication.context);
        UseDataEntity entity = dataMananer.getCacheData();
        if (entity == null) {
            entity = DataUtil.getIntence().getEntity(InitApplication.context);
        }
        mlist.addAll(entity.getPtdata().getZjlist());
    }

    public static ZhuanJiFragment newInstance() {
        Bundle args = new Bundle();
        ZhuanJiFragment fragment = new ZhuanJiFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initViewsAndEvents() {
        tv.setText("购买会员,开通终极劲爆专辑区");
        initData();
        initAdpter();
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_pian_ku;
    }

    public void initAdpter() {
        adter = new PianKuAdpter(InitApplication.getAppContext(), pindaoRecyview, mlist);
        pindaoRecyview.setLayoutManager(new FullyLinearLayoutManager(InitApplication.getAppContext()));
        pindaoRecyview.setAdapter(adter);
        adter.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Vip.upVIP(getActivity());


    }
}
