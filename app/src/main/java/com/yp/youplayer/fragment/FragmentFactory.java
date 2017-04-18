package com.yp.youplayer.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.util.SparseArrayCompat;

/**
 * fragment管理器
 * Created by Administrator on 2016/8/18.
 */
public class FragmentFactory {


    // 初始化缓存的HashMap
    private static final SparseArrayCompat<Fragment> mFragmentsvip_1 = new SparseArrayCompat<>();
    private static final SparseArrayCompat<Fragment> mFragmentsvip_2 = new SparseArrayCompat<>();
    private static final SparseArrayCompat<Fragment> mFragmentsvip_3 = new SparseArrayCompat<>();
    private static final SparseArrayCompat<Fragment> mFragmentsvip_4 = new SparseArrayCompat<>();
    private static final SparseArrayCompat<Fragment> mFragmentsvip_5 = new SparseArrayCompat<>();
    private static final SparseArrayCompat<Fragment> mFragmentsvip_6 = new SparseArrayCompat<>();
    private static final SparseArrayCompat<Fragment> mFragmentsvip_7 = new SparseArrayCompat<>();

    public static Fragment createvip1Fragment(int i) {  //普通
        Fragment fragment = mFragmentsvip_1.get(i);
        if (fragment == null) {
            switch (i) {
                case 0:
                    fragment = YouBoFragment.newInstance();
                    break;
                case 1:
                    fragment = LzFragment.newInstance(0);
                    break;
                case 2:
                    fragment = PianKuFragment.newInstance();
                    break;
                case 3:
                    fragment = ZhuanJiFragment.newInstance();
                    break;
                case 4:
                    fragment = UserFragment.newInstance();
                    break;
            }
            mFragmentsvip_1.put(i, fragment);
        }

        return fragment;
    }

    public static Fragment createVip2Fragment(int i) {  //黄金
        mFragmentsvip_1.clear();
        Fragment fragment = mFragmentsvip_2.get(i);
        if (fragment == null) {
            switch (i) {
                case 0:
                    fragment = LzFragment.newInstance(0);
                    break;
                case 1:
                    fragment = LzFragment.newInstance(1);
                    break;
                case 2:
                    fragment = PianKuFragment.newInstance();
                    break;
                case 3:
                    fragment = ZhuanJiFragment.newInstance();
                    break;
                case 4:
                    fragment = UserFragment.newInstance();
                    break;
            }
            mFragmentsvip_2.put(i, fragment);
        }

        return fragment;
    }

    public static Fragment createVip3Fragment(int i) { //钻石
        mFragmentsvip_2.clear();
        Fragment fragment = mFragmentsvip_3.get(i);
        if (fragment == null) {
            switch (i) {
                case 0:
                    fragment = LzFragment.newInstance(0);
                    break;
                case 1:
                    fragment = LzFragment.newInstance(1);
                    break;
                case 2:
                    fragment = PianKuFragment.newInstance();
                    break;
                case 3:
                    fragment = ZhuanJiFragment.newInstance();
                    break;
                case 4:
                    fragment = UserFragment.newInstance();
                    break;
            }
            mFragmentsvip_3.put(i, fragment);
        }

        return fragment;
    }

    public static Fragment createVip4Fragment(int i) {  //蓝钻
        mFragmentsvip_3.clear();
        Fragment fragment = mFragmentsvip_4.get(i);
        if (fragment == null) {
            switch (i) {
                case 0:
                    fragment = LzFragment.newInstance(0);
                    break;
                case 1:
                    fragment = LzFragment.newInstance(1);
                    break;
                case 2:
                    fragment = PianKuFragment.newInstance();
                    break;
                case 3:
                    fragment = ZhuanJiFragment.newInstance();
                    break;
                case 4:
                    fragment = UserFragment.newInstance();
                    break;
            }
            mFragmentsvip_4.put(i, fragment);
        }
        return fragment;
    }

    public static Fragment createVip5Fragment(int i) {  //黑金
        mFragmentsvip_4.clear();
        Fragment fragment = mFragmentsvip_5.get(i);
        if (fragment == null) {
            switch (i) {
                case 0:
                    fragment = LzFragment.newInstance(0);
                    break;
                case 1:
                    fragment = LzFragment.newInstance(1);
                    break;
                case 2:
                    fragment = PianKuFragment.newInstance();
                    break;
                case 3:
                    fragment = ZhuanJiFragment.newInstance();
                    break;
                case 4:
                    fragment = UserFragment.newInstance();
                    break;
            }
            mFragmentsvip_5.put(i, fragment);
        }
        return fragment;
    }

    public static Fragment createVip6Fragment(int i) {  //至尊
        mFragmentsvip_5.clear();
        Fragment fragment = mFragmentsvip_6.get(i);
        if (fragment == null) {
            switch (i) {
                case 0:
                    fragment = LzFragment.newInstance(0);
                    break;
                case 1:
                    fragment = PianKuFragment.newInstance();
                    break;
                case 2:
                    fragment = ZhuanJiFragment.newInstance();
                    break;
                case 3:
                    fragment = UserFragment.newInstance();
                    break;
            }
            mFragmentsvip_6.put(i, fragment);
        }
        return fragment;
    }

    public static Fragment createVip7Fragment(int i) {  //终极
        mFragmentsvip_6.clear();
        Fragment fragment = mFragmentsvip_7.get(i);
        if (fragment == null) {
            switch (i) {
                case 0:
                    fragment = ZjFragment.newInstance();
                    break;
                case 1:
                    fragment = UserFragment.newInstance();
                    break;
            }
            mFragmentsvip_7.put(i, fragment);
        }
        return fragment;
    }

    public static void clear() {
        if (mFragmentsvip_1 != null) {
            mFragmentsvip_1.clear();
        }
        if (mFragmentsvip_2 != null) {
            mFragmentsvip_2.clear();
        }
        if (mFragmentsvip_3 != null) {
            mFragmentsvip_3.clear();
        }
        if (mFragmentsvip_4 != null) {
            mFragmentsvip_4.clear();
        }
        if (mFragmentsvip_5 != null) {
            mFragmentsvip_5.clear();
        }
        if (mFragmentsvip_6 != null) {
            mFragmentsvip_6.clear();
        }
        if (mFragmentsvip_7 != null) {
            mFragmentsvip_7.clear();
        }
    }
}
