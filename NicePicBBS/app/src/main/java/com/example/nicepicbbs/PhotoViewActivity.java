package com.example.nicepicbbs;

import android.os.Bundle;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.nicepicbbs.adapter.ImagePagerAdapter;
import com.example.nicepicbbs.view.HackyViewPager;

import java.util.List;

public class PhotoViewActivity extends FragmentActivity {
    public static final String EXTRA_IMAGE_INDEX = "image_index";
    public static final String EXTRA_IMAGE_URLS = "image_urls";

    private HackyViewPager mPager;
    private int pagerPosition;
    private TextView indicator;
    private List<String> urls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photoview);
        mPager = findViewById(R.id.pager);
        indicator = findViewById(R.id.indicator);
        pagerPosition = getIntent().getIntExtra(EXTRA_IMAGE_INDEX, 0);
        urls = getIntent().getStringArrayListExtra(EXTRA_IMAGE_URLS);
        CharSequence text = getString(R.string.viewpager_indicator, pagerPosition+1, urls.size());
        indicator.setText(text);

        initView();
    }

    private void initView(){
        ImagePagerAdapter mAdapter = new ImagePagerAdapter(getSupportFragmentManager(), urls);
        mPager.setAdapter(mAdapter);
        mPager.setCurrentItem(pagerPosition);
        // 更新下标
        mPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageSelected(int arg0) {
                CharSequence text = getString(R.string.viewpager_indicator, arg0 + 1, urls.size());
                indicator.setText(text);
            }

        });
    }






}
