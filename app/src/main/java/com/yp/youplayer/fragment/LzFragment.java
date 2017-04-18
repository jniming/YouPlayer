package com.yp.youplayer.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;

import com.yp.youplayer.InitApplication;
import com.yp.youplayer.activity.TopplayerActivity;
import com.yp.youplayer.activity.WuMplayerActivity;
import com.yp.youplayer.adpter.ZuanShiAdter;
import com.yp.youplayer.bean.VideoBean;
import com.yp.youplayer.entity.UseDataEntity;
import com.yp.youplayer.manager.DataMananer;
import com.yp.youplayer.manager.FullyGridLayoutManager;
import com.yp.youplayer.util.Constant;
import com.yp.youplayer.util.DataUtil;
import com.yp.youplayer.util.Vip;
import com.ypsyyzip.mlidbvgnr.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2016/11/16.
 */
public class LzFragment extends BaseFragment implements AdapterView.OnItemClickListener {
    @BindView(R.id.pindao_recyview)
    RecyclerView pindaoRecyview;
    private ZuanShiAdter adter;
    private List<VideoBean> mlist = new ArrayList<>();
    private int temp = 0;


    public void initData() {
        DataMananer dataMananer = new DataMananer(InitApplication.context);
        UseDataEntity entity = dataMananer.getCacheData();
        int vip = Vip.getVip(InitApplication.context);
        mlist.clear();
        if (entity == null) {
            entity = DataUtil.getIntence().getEntity(InitApplication.context);
        }
        if (temp == 0) {
            switch (vip) {
                case Constant.VIP_1:
                    mlist.addAll(entity.getPtdata().getWmlist());
                    break;
                case Constant.VIP_2:
                    mlist.addAll(entity.getHjdata().getWmlist());
                    break;
                case Constant.VIP_3:
                    mlist.addAll(entity.getZsdata().getZslist());
                    break;
                case Constant.VIP_4:
                    mlist.addAll(entity.getLzdata().getLzlist());
                    break;
                case Constant.VIP_5:
                    mlist.addAll(entity.getHedata().getHelist());
                    break;
                case Constant.VIP_6:
                    mlist.addAll(entity.getZzdata().getZzlist());
                    break;
                case Constant.VIP_7:
                    mlist.addAll(entity.getZjdata().getZjlist());
                    break;

            }
        } else if (temp == 1) {
            switch (vip) {
                case Constant.VIP_1:
                    mlist.addAll(entity.getPtdata().getWmlist());
                    break;
                case Constant.VIP_2:
                    mlist.addAll(entity.getHjdata().getZslist());
                    break;
                case Constant.VIP_3:
                    mlist.addAll(entity.getZsdata().getLzlist());
                    break;
                case Constant.VIP_4:
                    mlist.addAll(entity.getLzdata().getHelist());
                    break;
                case Constant.VIP_5:
                    mlist.addAll(entity.getHedata().getZzlist());
                    break;
                case Constant.VIP_6:
//                        mlist.addAll(entity.getZzdata().getZjlist());
                    break;
                case Constant.VIP_7:
//                        mlist.addAll(entity.getZjdata().getZjlist());
                    break;

            }
        }
    }

    public static LzFragment newInstance(int temp) {
        Bundle args = new Bundle();
        LzFragment fragment = new LzFragment();
        args.putInt("temp", temp);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected void initViewsAndEvents() {
        temp = getArguments().getInt("temp");
        initData();
        initAdpter();
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_pindao;
    }

    public void initAdpter() {
        adter = new ZuanShiAdter(InitApplication.getAppContext(), mlist, pindaoRecyview, temp);
        pindaoRecyview.setLayoutManager(new FullyGridLayoutManager(InitApplication.getAppContext(), 2));
        pindaoRecyview.setAdapter(adter);
        adter.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        int vip = Vip.getVip(getActivity());
        if (vip == Constant.VIP_1) {
            Intent intent = new Intent(getActivity(), WuMplayerActivity.class);
            intent.putExtra(Constant.PlayFLG, mlist.get(position).getImgurl());
            intent.putExtra(Constant.PlayFLG_1, mlist.get(position).getName());
            intent.putExtra(Constant.TAG, Constant.PAY_2);
            startActivity(intent);
            return;
        }

//        if (vip == Constant.VIP_5) {
//            Intent intent = new Intent(getActivity(), TopplayerActivity.class);
//            intent.putExtra(Constant.PlayFLG_2, mlist.get(position).getVideurl());
//            intent.putExtra(Constant.PlayFLG, mlist.get(position).getImgurl());
//            intent.putExtra(Constant.PlayFLG_1, mlist.get(position).getName());
//            startActivity(intent);
//            return;
//        }

        if (vip == Constant.VIP_6) {
            Intent intent = new Intent(getActivity(), WuMplayerActivity.class);
            intent.putExtra(Constant.PlayFLG, mlist.get(position).getImgurl());
            intent.putExtra(Constant.PlayFLG_1, mlist.get(position).getName());
            intent.putExtra(Constant.TAG, Constant.PAY_2);
            startActivity(intent);
            return;
        }


        if (temp == 0) {
            Intent intent = new Intent(getActivity(), TopplayerActivity.class);
            intent.putExtra(Constant.PlayFLG_2, mlist.get(position).getVideurl());
            intent.putExtra(Constant.PlayFLG, mlist.get(position).getImgurl());
            intent.putExtra(Constant.PlayFLG_1, mlist.get(position).getName());
            startActivity(intent);
        } else {
            Intent intent = new Intent(getActivity(), WuMplayerActivity.class);
            intent.putExtra(Constant.PlayFLG, mlist.get(position).getImgurl());
            intent.putExtra(Constant.PlayFLG_1, mlist.get(position).getName());
            intent.putExtra(Constant.TAG, Constant.PAY_2);
            startActivity(intent);
        }
    }
}
