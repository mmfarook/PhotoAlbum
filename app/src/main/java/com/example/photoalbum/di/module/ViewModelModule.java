package com.example.photoalbum.di.module;

import com.example.photoalbum.di.scopes.AppScoped;
import com.example.photoalbum.ui.main.MainViewModel;
import com.example.photoalbum.ui.main.MainViewModelFactory;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel.class)
    abstract ViewModel bindMessageViewModel(MainViewModel messageViewModel);

    @Binds
    @AppScoped
    abstract ViewModelProvider.Factory bindViewModelFactory(MainViewModelFactory factory);
}