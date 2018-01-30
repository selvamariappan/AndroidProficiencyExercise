package com.selva.androidproficiencyexercise;

import android.app.Application;

import com.selva.androidproficiencyexercise.component.ApplicationComponent;
import com.selva.androidproficiencyexercise.component.DaggerApplicationComponent;
import com.selva.androidproficiencyexercise.module.AppModule;
import com.selva.androidproficiencyexercise.module.NetworkModule;


public class AndroidProficiencyApp extends Application {


    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent.builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule())
                .build();
    }

    public ApplicationComponent getApplicationComponent(){
        return applicationComponent;
    }
}
