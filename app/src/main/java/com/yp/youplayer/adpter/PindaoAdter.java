package com.yp.youplayer.adpter;


import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;
import com.yp.youplayer.InitApplication;
import com.yp.youplayer.bean.PdBean;
import com.yp.youplayer.util.FrescoUtil;
import com.ypsyyzip.mlidbvgnr.R;

import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2016/11/14.
 */
public class PindaoAdter extends BaseRecyAdpter {
    private List<PdBean> beanList;

    public PindaoAdter(Context context, List<PdBean> beanList, RecyclerView recyclerView) {
        super(context, recyclerView);
        this.beanList = beanList;
    }


    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new holder(mLayoutInflater.inflate(R.layout.item_pindao, parent, false));
    }

    @Override
    public int getItemCount() {
        return beanList == null ? 0 : beanList.size();
    }

    public class holder extends BaseViewHolder {
        @BindView(R.id.pindao_video_img)
        SimpleDraweeView pindao_video_img;


        public holder(View itemView) {
            super(itemView);
        }

        @Override
        public void fillView(final int position) {
            PdBean videoBean = beanList.get(position);
            if (beanList != null) {
                String url = videoBean.getImgurl();
                String path = FrescoUtil.isExist(url);
                if (path.isEmpty()) {
                    pindao_video_img.setImageURI(Uri.parse(url));
                } else {
                    pindao_video_img.setImageURI("file://" + path);
                }
                FrescoUtil.savePicture(url, InitApplication.context);
            }


        }


    }
}
