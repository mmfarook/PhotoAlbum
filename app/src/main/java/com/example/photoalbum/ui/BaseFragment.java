package com.example.photoalbum.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.photoalbum.model.Photos;
import com.example.photoalbum.ui.main.MainViewModel;
import com.example.photoalbum.ui.main.MainViewModelFactory;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import dagger.android.support.DaggerFragment;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public abstract class BaseFragment extends DaggerFragment {

    private static final String TAG = BaseFragment.class.getName();

    protected ProgressBar mProgressBar;

    protected MainViewModel mainViewModel;

    @Inject
    MainViewModelFactory mainViewModelFactory;

    private final CompositeDisposable mSubscription = new CompositeDisposable();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mainViewModel = ViewModelProviders.of(this, mainViewModelFactory).get(MainViewModel.class);
    }

    @Override
    public void onResume() {
        super.onResume();
        bindViewModel();

    }

    private void bindViewModel() {
        mSubscription.clear();
        mProgressBar.setVisibility(View.VISIBLE);
        mSubscription.add(
                mainViewModel.getPhotos()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                (Photos photos) -> {
                                    mProgressBar.setVisibility(View.GONE);
                                    onResult(photos);
                                },
                                (Throwable e) -> {
                                    mProgressBar.setVisibility(View.GONE);
                                    Log.e(TAG, e.toString());
                                }
                        ));

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbindViewModel();
    }

    private void unbindViewModel() {
        mSubscription.dispose();
        mSubscription.clear();
    }

    protected abstract void onResult(Photos photos);

}
