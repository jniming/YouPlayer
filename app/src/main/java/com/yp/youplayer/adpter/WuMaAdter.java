package com.yp.youplayer.adpter;


import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.yp.youplayer.InitApplication;
import com.yp.youplayer.bean.VideoBean;
import com.yp.youplayer.util.FrescoUtil;
import com.ypsyyzip.mlidbvgnr.R;

import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2016/11/14.
 */
public class WuMaAdter extends BaseRecyAdpter {
    private List<VideoBean> beanList;

    public WuMaAdter(Context context, List<VideoBean> beanList, RecyclerView recyclerView) {
        super(context, recyclerView);
        this.beanList = beanList;
    }


    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new holder(mLayoutInflater.inflate(R.layout.item_wuma, parent, false));
    }

    @Override
    public int getItemCount() {
        return beanList == null ? 0 : beanList.size();
    }

    public class holder extends BaseViewHolder {
        @BindView(R.id.wuma_video_img)
        SimpleDraweeView wumaVideoImg;
        @BindView(R.id.wuma_video_txt)
        TextView wumaVideoTxt;


        public holder(View itemView) {
            super(itemView);
        }

        @Override
        public void fillView(final int position) {
            VideoBean videoBean = beanList.get(position);
            if (beanList != null) {
                String url = videoBean.getImgurl();
                String path = FrescoUtil.isExist(url);
                if (path.isEmpty()) {
                    wumaVideoImg.setImageURI(Uri.parse(url));
                } else {
                    wumaVideoImg.setImageURI("file://" + path);
                }
                FrescoUtil.savePicture(url, InitApplication.context);
                wumaVideoTxt.setText(videoBean.getName());
            }


        }


    }
}
