package com.example.koleg.threes.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.pnpdevelopers.patryk.threes.util.PreferenceManager;



public class SplashActivity extends AppCompatActivity {
    private PreferenceManager prefManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //MobileAds.initialize(this,"ca-app-pub-9589942427963055~6195596040");
        prefManager = new PreferenceManager(this);
        if (!prefManager.isFirstTimeLaunch()) {
            startActivity(new Intent(this, StartActivity.class));
            finish();
        } else {
            startActivity(new Intent(this, TutorialActivity.class));
            finish();
        }
    }
}