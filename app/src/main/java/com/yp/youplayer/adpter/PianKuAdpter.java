package com.yp.youplayer.adpter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;
import com.yp.youplayer.bean.VideoBean;
import com.ypsyyzip.mlidbvgnr.R;

import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2016/11/16.
 */
public class PianKuAdpter extends BaseRecyAdpter {


    private List<VideoBean> list;

    public PianKuAdpter(Context context, RecyclerView recyclerView, List<VideoBean> list) {
        super(context, recyclerView);
        this.list = list;
    }


    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new holder(mLayoutInflater.inflate(R.layout.item_pianku, parent, false));
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class holder extends BaseViewHolder {
        @BindView(R.id.pianku_video_img)
        SimpleDraweeView pdVideoImg;


        public holder(View itemView) {
            super(itemView);
        }

        @Override
        public void fillView(final int position) {
            VideoBean videoBean = list.get(position);
            if (list != null) {

                String url = videoBean.getImgurl();
                if (!url.isEmpty())
                    pdVideoImg.setImageURI(Uri.parse(url));
//                String path = FrescoUtil.isExist(url);
//                if (path.isEmpty()) {
//                    pdVideoImg.setImageURI(Uri.parse(url));
//                } else {
//                    pdVideoImg.setImageURI("file://" + path);
//                }
//                FrescoUtil.savePicture(url, InitApplication.context);
            }
        }
    }

}
