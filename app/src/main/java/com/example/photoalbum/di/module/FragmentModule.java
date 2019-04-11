package com.example.photoalbum.di.module;


import com.example.photoalbum.di.scopes.FragmentScoped;
import com.example.photoalbum.ui.detail.DetailFragment;
import com.example.photoalbum.ui.main.MainFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentModule {
    @FragmentScoped
    @ContributesAndroidInjector
    abstract MainFragment mainFragment();

    @FragmentScoped
    @ContributesAndroidInjector
    abstract DetailFragment detailFragment();
}
