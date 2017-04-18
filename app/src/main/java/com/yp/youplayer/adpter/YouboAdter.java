package com.yp.youplayer.adpter;


import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.yp.youplayer.bean.VideoBean;
import com.ypsyyzip.mlidbvgnr.R;

import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2016/11/14.
 */
public class YouboAdter extends BaseRecyAdpter implements View.OnClickListener, View.OnLongClickListener {
    private List<VideoBean> beanList;


    public YouboAdter(Context context, List<VideoBean> beanList, RecyclerView recyclerView) {
        super(context, recyclerView);
        this.beanList = beanList;
    }


    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new holder(mLayoutInflater.inflate(R.layout.item_video, parent, false));
    }

    @Override
    public int getItemCount() {
        return beanList == null ? 0 : beanList.size();
    }


    public class holder extends BaseViewHolder {
        @BindView(R.id.video_img)
        SimpleDraweeView videoImg;
        @BindView(R.id.video_txt)
        TextView videoTxt;

        public holder(View itemView) {
            super(itemView);
            ((CardView) itemView).setCardBackgroundColor(Color.parseColor("#FFFFFF"));
        }

        @Override
        public void fillView(final int position) {
            VideoBean videoBean = beanList.get(position);
            if (beanList != null) {
                String url = videoBean.getImgurl();
//                String path = FrescoUtil.isExist(url);
                videoImg.setImageURI(Uri.parse(url));
//                if (path.isEmpty()) {
//                }else{
//                    videoImg.setImageURI("file://"+path);
//                }
//                FrescoUtil.savePicture(url, InitApplication.context);
                videoTxt.setText(videoBean.getName());
            }

        }
    }
}
