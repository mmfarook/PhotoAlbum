package com.example.photoalbum.ui.detail;


import android.os.Bundle;

import com.example.photoalbum.R;

import javax.inject.Inject;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import dagger.android.AndroidInjection;
import dagger.android.support.DaggerAppCompatActivity;

public class DetailActivity extends DaggerAppCompatActivity {

    @Inject
    DetailFragment mDetailFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.content_detail);
        if (fragment == null) {
            fragment = mDetailFragment;
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.content_detail, fragment);
            fragmentTransaction.commit();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
