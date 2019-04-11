package com.example.photoalbum.di.module;




import com.example.photoalbum.di.scopes.AppScoped;
import com.example.photoalbum.interceptors.RequestInterceptor;
import com.example.photoalbum.repository.PhotoRepository;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import androidx.annotation.NonNull;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class RetrofitModule {
    @Provides
    @AppScoped
    PhotoRepository provideForecastRepository(Retrofit retrofit) {
        return new PhotoRepository(retrofit);
    }

    @Provides
    @AppScoped
    @NonNull
    RequestInterceptor providesRequestInterceptor() {
        return new RequestInterceptor();
    }

    @Provides
    @AppScoped
    OkHttpClient provideOkHttpClient(RequestInterceptor requestInterceptor) {
        OkHttpClient.Builder httpClient =
                new OkHttpClient.Builder();
        httpClient.addInterceptor(requestInterceptor);
        return httpClient.build();
    }

    @Provides
    @AppScoped
    Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .baseUrl("https://my-json-server.typicode.com/")
                .build();
    }
}
