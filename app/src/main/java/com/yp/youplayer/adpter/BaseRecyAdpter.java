package com.yp.youplayer.adpter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/9/27.
 */
public abstract class BaseRecyAdpter extends RecyclerView.Adapter<BaseRecyAdpter.BaseViewHolder> implements View.OnClickListener, View.OnLongClickListener {

    public LayoutInflater mLayoutInflater;

    public Context mContext;

    private RecyclerView recyclerView;
    //点击
    AdapterView.OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public BaseRecyAdpter(Context context, RecyclerView recyclerView) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        this.recyclerView = recyclerView;
    }


    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.fillView(position);
    }


    public abstract class BaseViewHolder extends RecyclerView.ViewHolder {

        public BaseViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        /**
         * 给item填充数据
         *
         * @param position
         */
        public abstract void fillView(int position);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position, List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
        holder.itemView.setOnClickListener(this);
        holder.itemView.setOnLongClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (onItemClickListener != null) {
            onItemClickListener.onItemClick(null, v, recyclerView.getChildAdapterPosition(v), 0);
        }
    }

    @Override
    public boolean onLongClick(View v) {
        return false;
    }
}
