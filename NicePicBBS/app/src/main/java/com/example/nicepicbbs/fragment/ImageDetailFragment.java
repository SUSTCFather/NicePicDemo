package com.example.nicepicbbs.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.nicepicbbs.R;
import com.github.chrisbanes.photoview.OnPhotoTapListener;
import com.github.chrisbanes.photoview.PhotoView;


public class ImageDetailFragment extends Fragment {
    private String mImageUrl;
    private PhotoView mImageView;
    private ProgressBar progressBar;

    public static ImageDetailFragment newInstance(String imageUrl) {
        ImageDetailFragment f = new ImageDetailFragment();

        Bundle args = new Bundle();
        args.putString("url", imageUrl);
        f.setArguments(args);

        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mImageUrl = getArguments() != null ? getArguments().getString("url") : null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_photoview, container, false);
        mImageView = v.findViewById(R.id.detail_image);
        mImageView.setOnPhotoTapListener(new OnPhotoTapListener() {
            @Override
             public void onPhotoTap(ImageView view, float x, float y) {
                getActivity().finish();
             }
        });

        mImageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Toast.makeText(getActivity(), "保存", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(getActivity() != null){
            Glide.with(getActivity())
                    .load(mImageUrl)
                    .into(mImageView);
        }
    }


}
