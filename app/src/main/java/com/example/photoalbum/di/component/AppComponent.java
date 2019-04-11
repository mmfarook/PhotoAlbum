package com.example.photoalbum.di.component;


import com.example.photoalbum.di.module.ActivityContributorModule;
import com.example.photoalbum.di.module.AppModule;
import com.example.photoalbum.di.module.RetrofitModule;
import com.example.photoalbum.di.module.ViewModelModule;
import com.example.photoalbum.di.scopes.AppScoped;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import dagger.android.support.DaggerApplication;

@AppScoped
@Component(modules = {AndroidSupportInjectionModule.class,
        AppModule.class,
        ActivityContributorModule.class,
        ViewModelModule.class,
        RetrofitModule.class})
public interface AppComponent extends AndroidInjector<DaggerApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(DaggerApplication application);

        AppComponent build();
    }

}