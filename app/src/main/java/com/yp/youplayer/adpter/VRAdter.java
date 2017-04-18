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
public class VRAdter extends BaseRecyAdpter {

    private List<VideoBean> beanList;

    public VRAdter(Context context, List<VideoBean> beanList, RecyclerView recyclerView) {
        super(context, recyclerView);
        this.beanList = beanList;
    }


    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new holder(mLayoutInflater.inflate(R.layout.item_vr, parent, false));
    }

    @Override
    public int getItemCount() {
        return beanList == null ? 0 : beanList.size();
    }

    public class holder extends BaseViewHolder {
        @BindView(R.id.vr_video_img)
        SimpleDraweeView vrVideoImg;
        @BindView(R.id.vr_video_txt)
        TextView vrVideoTxt;


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
                    vrVideoImg.setImageURI(Uri.parse(url));
                } else {
                    vrVideoImg.setImageURI("file://" + path);
                }
                FrescoUtil.savePicture(url, InitApplication.context);
                vrVideoTxt.setText(videoBean.getName());
            }


        }


    }
}
