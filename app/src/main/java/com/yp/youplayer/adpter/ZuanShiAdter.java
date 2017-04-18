package com.yp.youplayer.adpter;


import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.yp.youplayer.bean.VideoBean;
import com.ypsyyzip.mlidbvgnr.R;

import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2016/11/14.
 */
public class ZuanShiAdter extends BaseRecyAdpter {

    private List<VideoBean> beanList;
    private int temp = 0;

    public ZuanShiAdter(Context context, List<VideoBean> beanList, RecyclerView recyclerView, int temp) {
        super(context, recyclerView);
        this.beanList = beanList;
        this.temp = temp;
    }


    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new holder(mLayoutInflater.inflate(R.layout.item_zuanshi, parent, false));
    }

    @Override
    public int getItemCount() {
        return beanList == null ? 0 : beanList.size();
    }

    public class holder extends BaseViewHolder {
        @BindView(R.id.zs_img)
        SimpleDraweeView zsImg;
        @BindView(R.id.zs_txt)
        TextView zsTxt;
        @BindView(R.id.item_right_img)
        ImageView item_right_img;

        public holder(View itemView) {
            super(itemView);
            ((CardView) itemView).setCardBackgroundColor(Color.parseColor("#FFFFFF"));

        }

        @Override
        public void fillView(final int position) {
            VideoBean videoBean = beanList.get(position);
            if (beanList != null) {
                String murl = videoBean.getImgurl();
                if (!murl.isEmpty())
                    zsImg.setImageURI(Uri.parse(murl));
//                String path = FrescoUtil.isExist(url);
//                if (path.isEmpty()) {
//                    zsImg.setImageURI(Uri.parse(url));
//                } else {
//                    zsImg.setImageURI("file://" + path);
//                }
//                FrescoUtil.savePicture(url, InitApplication.context);
                zsTxt.setText(videoBean.getName());
            }
            if (temp == 0) {
                item_right_img.setImageResource(R.drawable.ic_vip_small);
            } else if (temp == 1) {
                item_right_img.setImageResource(R.drawable.vip_zuan);
            }

        }


    }
}
