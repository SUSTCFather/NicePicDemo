package com.example.nicepicbbs.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;


import com.example.nicepicbbs.fragment.ImageDetailFragment;

import java.util.List;

public class ImagePagerAdapter extends FragmentStatePagerAdapter {
    public List<String> fileList;

    public ImagePagerAdapter(FragmentManager fm, List<String> fileList) {
        super(fm);
        this.fileList = fileList;
    }

    @Override
    public int getCount() {
        return fileList == null ? 0 : fileList.size();
    }

    @Override
    public Fragment getItem(int position) {
        String url = fileList.get(position);
        return ImageDetailFragment.newInstance(url);
    }
}
