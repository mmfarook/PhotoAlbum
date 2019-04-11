package com.example.photoalbum.service;

import com.example.photoalbum.model.Photos;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface PhotoService {
    @GET("/mmfarook/PhotoAlbum_Server/db")
    Observable<Photos> getPhotos();
}
