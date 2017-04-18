package com.yp.youplayer.adpter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;
import com.yp.youplayer.InitApplication;
import com.yp.youplayer.bean.AdBean;
import com.yp.youplayer.util.FrescoUtil;
import com.ypsyyzip.mlidbvgnr.R;

import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/4/1.
 */

public class UserInofAdpter extends BaseRecyAdpter {

    private List<AdBean> list;

    public UserInofAdpter(Context context, RecyclerView recyclerView, List<AdBean> list) {
        super(context, recyclerView);
        this.list = list;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new UserInofAdpter.holder(mLayoutInflater.inflate(R.layout.userinf_item, parent, false));
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class holder extends BaseViewHolder {
        @BindView(R.id.userinf_img)
        SimpleDraweeView pindao_video_img;


        public holder(View itemView) {
            super(itemView);
        }

        @Override
        public void fillView(final int position) {
            AdBean videoBean = list.get(position);
            if (videoBean != null) {
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
