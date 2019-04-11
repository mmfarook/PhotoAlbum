package com.example.photoalbum.di.module;



import com.example.photoalbum.di.scopes.ActivityScoped;
import com.example.photoalbum.ui.detail.DetailActivity;
import com.example.photoalbum.ui.main.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityContributorModule {

    @ActivityScoped
    @ContributesAndroidInjector (modules = {FragmentModule.class})
    abstract MainActivity contributeMainActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = {FragmentModule.class})
    abstract DetailActivity contributeDetailActivity();
}
