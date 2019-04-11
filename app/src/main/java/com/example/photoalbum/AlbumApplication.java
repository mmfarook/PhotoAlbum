package com.example.photoalbum;


import android.content.Context;
import android.util.DisplayMetrics;

import com.example.photoalbum.di.component.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

public class AlbumApplication extends DaggerApplication {

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().application(this).build();
    }

    public static int calculateNoOfColumns(Context context, float columnWidth) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float screenWidth = displayMetrics.widthPixels;
        return (int) (screenWidth / columnWidth);
    }
}
