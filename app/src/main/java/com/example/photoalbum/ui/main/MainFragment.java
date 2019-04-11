package com.example.photoalbum.ui.main;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.photoalbum.AlbumApplication;
import com.example.photoalbum.R;
import com.example.photoalbum.adapter.PhotoAdapter;
import com.example.photoalbum.di.scopes.ActivityScoped;
import com.example.photoalbum.model.Photos;
import com.example.photoalbum.ui.BaseFragment;
import com.example.photoalbum.ui.detail.DetailActivity;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

@ActivityScoped
public class MainFragment extends BaseFragment implements PhotoAdapter.ItemClickListener {

    private PhotoAdapter mPhotoAdapter;

    @Inject
    public MainFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        RecyclerView recyclerView = root.findViewById(R.id.recycler_view);
        mProgressBar = root.findViewById(R.id.progress_bar);
        int noOfColumns = AlbumApplication.calculateNoOfColumns(getActivity(), getResources().getDimensionPixelSize(R.dimen.columnWidth));
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), noOfColumns));
        mPhotoAdapter = new PhotoAdapter(this);
        recyclerView.setAdapter(mPhotoAdapter);
        return root;
    }

    @Override
    public void onItemClick(int position) {
        if (getActivity() != null) {
            Intent intent = new Intent(getActivity(), DetailActivity.class);
            mainViewModel.setSelectedPosition(position);
            startActivity(intent);
        }
    }

    @Override
    protected void onResult(Photos photos) {
        mPhotoAdapter.submitList(photos.getPhotos());
    }
}
