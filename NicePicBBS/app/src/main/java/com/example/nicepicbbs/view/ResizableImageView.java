package com.example.nicepicbbs.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatImageView;

public class ResizableImageView extends AppCompatImageView {

    public ResizableImageView(Context context) {
        super(context);
    }

    public ResizableImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 强制宽适应屏幕，高按比例缩放
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec){

        int width = MeasureSpec.getSize(widthMeasureSpec) - getPaddingLeft() - getPaddingRight();
        Drawable d = getDrawable();

        if(d != null){
            //高度根据使得图片的宽度充满屏幕计算而得
            double radio = (double)d.getIntrinsicHeight() / (double)d.getIntrinsicWidth();
            int height = (int) Math.ceil(radio * width);
            height = height + getPaddingTop() + getPaddingBottom();
            setMeasuredDimension(width, height);
        }else{
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

}