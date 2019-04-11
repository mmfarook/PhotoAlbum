package com.example.photoalbum.ui.detail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.photoalbum.R;
import com.example.photoalbum.adapter.PhotoPagerAdapter;
import com.example.photoalbum.di.scopes.ActivityScoped;
import com.example.photoalbum.model.Photos;
import com.example.photoalbum.ui.BaseFragment;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;

@ActivityScoped
public class DetailFragment extends BaseFragment {

    private ViewPager mViewPager;
    private PhotoPagerAdapter mmFullscreenPhotoAdapter;

    @Inject
    public DetailFragment() {

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_detail, container, false);
        mViewPager = root.findViewById(R.id.pager);
        mProgressBar = root.findViewById(R.id.progress_bar);
        mmFullscreenPhotoAdapter = new PhotoPagerAdapter(getActivity());
        mViewPager.setAdapter(mmFullscreenPhotoAdapter);
        return root;
    }

    @Override
    protected void onResult(Photos photos) {
        mmFullscreenPhotoAdapter.setImageList(photos.getPhotos());
        mainViewModel.getSelectedPosition().observe(this, (Integer position) -> mViewPager.setCurrentItem(position, false)
        );
    }
}
