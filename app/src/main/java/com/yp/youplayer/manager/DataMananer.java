package com.yp.youplayer.manager;

import android.content.Context;

import com.yp.youplayer.entity.BaseEntity;
import com.yp.youplayer.entity.UseDataEntity;
import com.yp.youplayer.util.ACache;
import com.yp.youplayer.util.GsonUtil;

/**
 * Created by Administrator on 2016/11/16.
 */
public class DataMananer {

    public ACache aCache;
    private Context context;

    public final String CACHE_IDEN = "youbo";

    private int time = 1000 * 60 * 60 * 24 * 5;   //缓存数据保存五天


    public DataMananer(Context context) {
        this.context = context;
    }


    /**
     * 保存缓存信息
     *
     * @param
     */
    public void saveCacheData(BaseEntity t) {
        aCache = ACache.get(context);
        aCache.put(CACHE_IDEN, GsonUtil.toJson(t), time);
    }


    /**
     * 获取缓存的新闻信息
     */
    public UseDataEntity getCacheData() {
        aCache = ACache.get(context);
        String acahe_str = aCache.getAsString(CACHE_IDEN);
        UseDataEntity newsEntity = null;
        if (acahe_str != null) {
            newsEntity = GsonUtil.fromJson(acahe_str, UseDataEntity.class);
        }
        return newsEntity;
    }

    public void cleanCache() {
        aCache.clear();

    }
}
