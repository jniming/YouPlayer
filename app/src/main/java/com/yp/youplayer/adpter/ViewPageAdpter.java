package com.yp.youplayer.adpter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;

import com.yp.youplayer.fragment.FragmentFactory;
import com.yp.youplayer.util.Constant;


/**
 * Created by Administrator on 2016/9/28.
 */
public class ViewPageAdpter extends FragmentStatePagerAdapter {

    private int vip;

    public ViewPageAdpter(FragmentManager fm, int vip) {
        super(fm);
        this.vip = vip;
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        if (vip == Constant.VIP_1) {
            return FragmentFactory.createvip1Fragment(position);
        } else if (vip == Constant.VIP_2) {
            return FragmentFactory.createVip2Fragment(position);
        } else if (vip == Constant.VIP_3) {
            return FragmentFactory.createVip3Fragment(position);
        } else if (vip == Constant.VIP_4) {
            return FragmentFactory.createVip4Fragment(position);
        } else if (vip == Constant.VIP_5) {
            return FragmentFactory.createVip5Fragment(position);
        } else if (vip == Constant.VIP_6) {
            return FragmentFactory.createVip6Fragment(position);
        } else if (vip == Constant.VIP_7) {
            return FragmentFactory.createVip7Fragment(position);
        }
        return null;
    }


    @Override
    public int getCount() {
        if (vip == Constant.VIP_1) {
            return Constant.VIP_STR_1.length;
        } else if (vip == Constant.VIP_2) {
            return Constant.VIP_STR_2.length;
        } else if (vip == Constant.VIP_3) {
            return Constant.VIP_STR_3.length;
        } else if (vip == Constant.VIP_4) {
            return Constant.VIP_STR_4.length;
        } else if (vip == Constant.VIP_5) {
            return Constant.VIP_STR_5.length;
        } else if (vip == Constant.VIP_6) {
            return Constant.VIP_STR_6.length;
        } else if (vip == Constant.VIP_7) {
            return Constant.VIP_STR_7.length;
        }
        return 0;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (vip == Constant.VIP_1) {
            return Constant.VIP_STR_1[position];
        } else if (vip == Constant.VIP_2) {
            return Constant.VIP_STR_2[position];
        } else if (vip == Constant.VIP_3) {
            return Constant.VIP_STR_3[position];
        } else if (vip == Constant.VIP_4) {
            return Constant.VIP_STR_4[position];
        } else if (vip == Constant.VIP_5) {
            return Constant.VIP_STR_5[position];
        } else if (vip == Constant.VIP_6) {
            return Constant.VIP_STR_6[position];
        } else if (vip == Constant.VIP_7) {
            return Constant.VIP_STR_7[position];
        }
        return "TAB";
    }

    @Override
    public int getItemPosition(Object object) {
        // TODO Auto-generated method stub
        return PagerAdapter.POSITION_NONE;
    }
}
