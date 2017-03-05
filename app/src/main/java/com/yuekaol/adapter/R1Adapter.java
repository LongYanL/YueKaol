package com.yuekaol.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yuekaol.Bean;
import com.yuekaol.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 类功能
 * Zhulei---
 * 2017/3/4
 */


public class R1Adapter extends RecyclerView.Adapter<R1Adapter.MyViewHolder> {

    Context context;
    ArrayList<Bean.RsBean> list = new ArrayList<>();
    OnRVitemClickListener onRVitemClickListener;

    public R1Adapter(Context context){

        this.context = context;

    }

    public void addList(List<Bean.RsBean> rs) {
        this.list.clear();
        this.list.addAll(rs);
        this.notifyDataSetChanged();
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        MyViewHolder myViewHolder = new MyViewHolder(LayoutInflater.from(
                context).inflate(R.layout.item1, parent, false));

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.textView.setText(list.get(position).getDirName());
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRVitemClickListener.onClickListener(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView textView;

        public MyViewHolder(View itemView) {
            super(itemView);

            textView = (TextView) itemView.findViewById(R.id.item1_tv);

        }
    }

    public interface OnRVitemClickListener{
        void onClickListener(int position);
    }

    public void setOnRVitemClickListener(OnRVitemClickListener onRVitemClickListener){

        this.onRVitemClickListener = onRVitemClickListener;


    }

}
