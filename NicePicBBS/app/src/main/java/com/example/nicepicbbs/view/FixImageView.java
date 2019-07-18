package com.example.nicepicbbs.view;

import android.content.Context;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatImageView;

public class FixImageView extends AppCompatImageView {

    public FixImageView(Context context) {
        super(context);
    }

    public FixImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 强制宽高一致
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
        int width = MeasureSpec.getSize(widthMeasureSpec);
        setMeasuredDimension(width, width);
    }

}
