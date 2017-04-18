package com.yp.youplayer.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;

import com.yp.youplayer.InitApplication;
import com.yp.youplayer.activity.PlayerActivity;
import com.yp.youplayer.adpter.ZuanShiAdter;
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
 * Created by Administrator on 2016/11/16.
 */
public class ZjFragment extends BaseFragment implements AdapterView.OnItemClickListener {
    @BindView(R.id.pindao_recyview)
    RecyclerView pindaoRecyview;
    private ZuanShiAdter adter;
    private List<VideoBean> mlist = new ArrayList<>();


    public void initData() {
        DataMananer dataMananer = new DataMananer(InitApplication.context);
        UseDataEntity entity = dataMananer.getCacheData();
        mlist.clear();
        if (entity == null) {
            entity = DataUtil.getIntence().getEntity(InitApplication.context);
        }
        mlist.addAll(entity.getZjdata().getZjlist());
    }

    public static ZjFragment newInstance() {
        Bundle args = new Bundle();
        ZjFragment fragment = new ZjFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected void initViewsAndEvents() {
        initData();
        initAdpter();
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_pindao;
    }

    public void initAdpter() {
        adter = new ZuanShiAdter(InitApplication.getAppContext(), mlist, pindaoRecyview, 0);
        pindaoRecyview.setLayoutManager(new FullyGridLayoutManager(InitApplication.getAppContext(), 3));
        pindaoRecyview.setAdapter(adter);
        adter.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getActivity(), PlayerActivity.class);
        intent.putExtra(Constant.PlayFLG_2, mlist.get(position).getVideurl());
        startActivityForResult(intent, 0);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        Vip.upVIP(getActivity());
    }
}
