package com.example.nicepicbbs.adapter;

import android.content.Context;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.nicepicbbs.R;
import com.example.nicepicbbs.model.Comment;
import com.example.nicepicbbs.model.CommunityContent;
import com.example.nicepicbbs.view.ResizableImageView;
import com.example.nicepicbbs.view.SpacesItemDecoration;

import java.util.List;

public class BBSAdapter extends RecyclerView.Adapter<BBSAdapter.BaseViewHolder>{

    private List<CommunityContent> mContentList;
    private OnItemClickListener mOnItemClickListener;
    private static final int ONE_PIC = 0;
    private static final int NINE_PIC = 1;
    private Context mContext;


    static class BaseViewHolder extends RecyclerView.ViewHolder {
        ImageView mUserHead;
        TextView mUserName;
        TextView mVipLevel;
        TextView mPowerLevel;
        TextView contentText;
        //评论
        TextView mLike;
        TextView mComment;
        ImageView mShare;
        LinearLayout container;

        public BaseViewHolder(View view) {
            super(view);
            mUserHead = view.findViewById(R.id.user_head);
            mUserName = view.findViewById(R.id.user_name);
            mVipLevel = view.findViewById(R.id.vip_level);
            mPowerLevel = view.findViewById(R.id.power_level);
            contentText = view.findViewById(R.id.content_text);
            mLike = view.findViewById(R.id.like_text);
            mComment = view.findViewById(R.id.comment_text);
            mShare = view.findViewById(R.id.share_btn);
            container = view.findViewById(R.id.comment_container);
        }
    }

    static class NineViewHolder extends BaseViewHolder{
        //图片
        RecyclerView contentNinePic;
        public NineViewHolder(View view) {
            super(view);
            contentNinePic = view.findViewById(R.id.nine_recyclerView);
        }
    }

    static class OneViewHolder extends BaseViewHolder{
        //图片
        ResizableImageView contentOnePic;
        public OneViewHolder(View view) {
            super(view);
            contentOnePic = view.findViewById(R.id.one_image_view);
        }
    }

    public BBSAdapter(Context context, List<CommunityContent> friendList,OnItemClickListener onItemClickListener) {
        this.mContext = context;
        this.mOnItemClickListener = onItemClickListener;
        this.mContentList = friendList;
    }

    @Override
    public int getItemViewType(int position) {
        if(mContentList.get(position).getContentPicUrls().size() == 1){
            return ONE_PIC;
        }
        return NINE_PIC;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if(viewType == ONE_PIC){
            view = LayoutInflater.from(mContext).inflate(R.layout.item_bbs_one,parent,false);
            return new OneViewHolder(view);
        }
        //NINE_PIC
        view = LayoutInflater.from(mContext).inflate(R.layout.item_bbs_nine,parent,false);
        NineViewHolder viewHolder = new NineViewHolder(view);
        GridLayoutManager layoutManager = new GridLayoutManager(mContext,3);
        viewHolder.contentNinePic.setLayoutManager(layoutManager);
        viewHolder.contentNinePic.addItemDecoration(new SpacesItemDecoration(7));
        return viewHolder;
    }

    private TextView getCommentTextView(Comment comment){
        TextView textView = new TextView(mContext);
        textView.setTextAppearance(R.style.CommentText);
        textView.setPadding(0,5,0,5);
        String text = comment.getName()+": "+comment.getText();
        SpannableString spannableString = new SpannableString(text);

        ForegroundColorSpan black = new ForegroundColorSpan(ContextCompat.getColor(mContext, R.color.black));
        ForegroundColorSpan gray = new ForegroundColorSpan(ContextCompat.getColor(mContext, R.color.mc_gray_80));
        spannableString.setSpan(black, 0, comment.getName().length()+1, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(gray,comment.getName().length()+2,text.length(),Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        textView.setText(spannableString);
        return textView;
    }

    private void addComment(LinearLayout container,List<Comment> comments){
        container.removeAllViews();
        if(comments == null || comments.size() == 0){
            container.setVisibility(View.GONE);
            return;
        }
        container.setVisibility(View.VISIBLE);
        for(Comment c:comments){
            container.addView(getCommentTextView(c));
        }
    }


    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        final CommunityContent content = mContentList.get(position);
        holder.mUserName.setText(content.getName());
        holder.mVipLevel.setText(content.getVipLevel());
        holder.mPowerLevel.setText(content.getPowerLevel());
        holder.contentText.setText(content.getText());

        Glide.with(mContext)
                .load(content.getHeadUrl())
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into(holder.mUserHead);

        if(holder instanceof OneViewHolder){
            Glide.with(mContext)
                    .load(content.getContentPicUrls().get(0))
                    .into(((OneViewHolder) holder).contentOnePic);
            ((OneViewHolder) holder).contentOnePic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(mOnItemClickListener != null){
                        mOnItemClickListener.onItemClick(view,content.getContentPicUrls(),0);
                    }
                }
            });
        }
        else if(holder instanceof NineViewHolder){
            NicePicAdapter adapter = new NicePicAdapter(content.getContentPicUrls(),mOnItemClickListener);
            ((NineViewHolder) holder).contentNinePic.setAdapter(adapter);
        }
        addComment(holder.container,content.getComments());
    }

    @Override
    public int getItemCount() {
        return mContentList == null ? 0 : mContentList.size();
    }

}
