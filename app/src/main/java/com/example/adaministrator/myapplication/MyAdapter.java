package com.example.adaministrator.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Adaministrator on 2017/9/2.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<String> datas;
    private Context context;
    private ActionListener actionListener;

    public List<String> getDatas() {
        return datas;
    }

    public void addItem(int pos, String txt) {
        datas.add(pos, txt);
        //        notifyItemInserted(pos);
        notifyDataSetChanged();
    }

    public void removeItem(int pos) {
        datas.remove(pos);
        //        notifyItemRemoved(pos);
        notifyDataSetChanged();
    }

    public void update(int pos, String newTxt) {
        datas.set(pos, newTxt);
        notifyItemChanged(pos);
        //        notifyItemChanged();
    }

    public MyAdapter(Context context, List<String> list) {
        this.context = context;
        this.datas = list;
    }

    public void setActionListener(ActionListener listener) {
        this.actionListener = listener;
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyAdapter.ViewHolder holder, final int position) {
        holder.mTvContent.setText(datas.get(position));
        //手动更改高度，不同位置的高度有所不同
        holder.mTvContent.setHeight(160 + (position % 3) * 30);
        if (position % 3 == 0) {
            holder.mTvContent.setBackgroundResource(R.drawable.tv_bg_2);
        } else if (position % 3 == 1) {
            holder.mTvContent.setBackgroundResource(R.drawable.tv_bg_1);
        } else {
            holder.mTvContent.setBackgroundResource(R.drawable.tv_bg_3);
        }

        holder.mTvContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position % 3 == 0) {
                    if (actionListener != null) {
                        actionListener.add(position);
                    }
                } else if (position % 3 == 1) {
                    if (actionListener != null) {
                        actionListener.remove(position);
                    }
                } else {
                    if (actionListener != null) {
                        actionListener.update(position);
                    }
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTvContent;

        public ViewHolder(View view) {
            super(view);
            mTvContent = (TextView) view.findViewById(R.id.tv_item_content);
        }
    }

    interface ActionListener {
        void add(int position);

        void remove(int position);

        void update(int position);
    }
}
