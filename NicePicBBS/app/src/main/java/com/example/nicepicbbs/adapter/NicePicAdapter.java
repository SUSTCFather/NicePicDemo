package com.example.nicepicbbs.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.nicepicbbs.R;
import com.example.nicepicbbs.view.FixImageView;

import java.util.List;

public class NicePicAdapter extends RecyclerView.Adapter<NicePicAdapter.ViewHolder> {

    private List<String> urls;
    private OnItemClickListener mOnItemClickListener;


    static class ViewHolder extends RecyclerView.ViewHolder{

        FixImageView mImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.nine_pic_view);

        }
    }

    public NicePicAdapter(List<String> urls,OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
        this.urls = urls;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_nice_pic,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Glide.with(holder.mImageView.getContext())
                .load(urls.get(position))
                .into(holder.mImageView);
        holder.mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mOnItemClickListener != null){
                    mOnItemClickListener.onItemClick(view, urls, position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return urls.size();
    }

}
