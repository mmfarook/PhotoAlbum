package com.example.photoalbum.ui.main;


import com.example.photoalbum.di.scopes.AppScoped;
import com.example.photoalbum.model.Photos;
import com.example.photoalbum.repository.PhotoRepository;

import javax.inject.Inject;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.Observable;

@AppScoped
public class MainViewModel extends ViewModel {

    private final PhotoRepository photoRepository;

    private final MutableLiveData<Integer> mSelectedPosition = new MutableLiveData<>();

    @Inject
    public MainViewModel(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    public Observable<Photos> getPhotos() {
        return photoRepository.getPhotos();
    }

    public MutableLiveData<Integer> getSelectedPosition() {
        return mSelectedPosition;
    }

    public void setSelectedPosition(int position) {
        mSelectedPosition.postValue(position);
    }


}
