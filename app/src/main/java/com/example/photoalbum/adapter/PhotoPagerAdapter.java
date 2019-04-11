package com.example.photoalbum.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.photoalbum.R;
import com.example.photoalbum.model.Photo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;


public class PhotoPagerAdapter extends PagerAdapter {

    private final LayoutInflater mLayoutInflater;
    private List<Photo> mPhotoList = new ArrayList<>();

    public PhotoPagerAdapter(Context context) {
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setImageList(List<Photo> photos) {
        this.mPhotoList = photos;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mPhotoList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View itemView = mLayoutInflater.inflate(R.layout.fullscreen_photo_item, container, false);

        Photo photo = mPhotoList.get(position);
        ImageView imageView = itemView.findViewById(R.id.photo_view);
        Picasso.get().load(photo.getUrl()).into(imageView);
        TextView titleView = itemView.findViewById(R.id.title_view);
        titleView.setText(photo.getName());
        container.addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout) object);
    }

}