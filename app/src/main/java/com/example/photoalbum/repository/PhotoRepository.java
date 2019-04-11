package com.example.photoalbum.repository;

import com.example.photoalbum.model.Photos;
import com.example.photoalbum.service.PhotoService;

import javax.inject.Inject;

import io.reactivex.Observable;
import retrofit2.Retrofit;

public class PhotoRepository {

    private final Retrofit retrofit;

    @Inject
    public PhotoRepository(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    public Observable<Photos> getPhotos() {
        PhotoService photoService = retrofit.create(PhotoService.class);
        return photoService.getPhotos();
    }
}
