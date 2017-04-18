package com.yp.youplayer.adpter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yp.youplayer.util.UserComment;
import com.ypsyyzip.mlidbvgnr.R;

import butterknife.BindView;

/**
 * Created by Administrator on 2016/11/15.
 */
public class CommentAdpter extends BaseRecyAdpter {


    public CommentAdpter(Context context, RecyclerView recyclerView) {
        super(context, recyclerView);
    }


    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new holder(mLayoutInflater.inflate(R.layout.item_usercomment, parent, false));
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class holder extends BaseViewHolder {
        @BindView(R.id.userimgurl)
        ImageView userimgurl;
        @BindView(R.id.tx)
        LinearLayout tx;
        @BindView(R.id.img)
        ImageView img;
        @BindView(R.id.disten_text)
        TextView distenText;
        @BindView(R.id.comment_msg)
        TextView commentMsg;
        @BindView(R.id.username)
        TextView username;
        @BindView(R.id.now_time)
        TextView now_time;
        @BindView(R.id.ti)
        TextView ti;
        @BindView(R.id.zzz_text)
        TextView zzz_text;
        @BindView(R.id.ritght)
        RelativeLayout ritght;
        @BindView(R.id.zimg)
        ImageView zimg;

        public holder(View itemView) {
            super(itemView);
        }

        @Override
        public void fillView(final int position) {
//                userimgurl.setImageURI(Uri.parse(videoBean.getImgurl()));
            userimgurl.setImageResource(UserComment.getImgUrl());
            username.setText(UserComment.getName() + "");
            commentMsg.setText(UserComment.getMsg() + "");
            ti.setText(UserComment.getDistence() + "km");
            now_time.setText(UserComment.getNowTime() + "");
            zzz_text.setText(UserComment.getTp() + "");
            distenText.setText(UserComment.getTime() + "分钟");

        }


    }


}
