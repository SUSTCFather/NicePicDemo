package com.example.githubdemo;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.githubdemo.view.IKNinePhotoViewAdapter;
import com.example.githubdemo.view.IKNinePhotoViewHolder;


import java.util.Random;


/**
 * Created by Idtk on 2017/3/9.
 * Blog : http://www.idtkm.com
 * GitHub : https://github.com/Idtk
 * 描述 :
 */

public class MyAdapter extends IKNinePhotoViewAdapter<MyAdapter.MyHolder> {

    private Context mContext;
    private int count;

    public MyAdapter(Context context) {
        super();
        mContext = context;
        count = new Random().nextInt(9);
    }

    @Override
    public MyHolder createView(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_rv_item, parent, false);
        MyHolder viewHolder = new MyHolder(view);
        return viewHolder;
    }

    @Override
    public void displayView(final MyHolder holder, final int position) {
        Glide.with(mContext)
                .load( "http://img4.imgtn.bdimg.com/it/u=3276179142,1686381254&fm=26&gp=0.jpg")
                .apply(new RequestOptions().placeholder(R.mipmap.ic_launcher))
                .into(holder.mImageView);

        holder.getItemView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("click", position + "");
            }
        });
    }

    @Override
    public int getItemCount() {
        return count;
    }

    class MyHolder extends IKNinePhotoViewHolder {

        ImageView mImageView;

        public MyHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.nine_pic);

        }
    }

}
