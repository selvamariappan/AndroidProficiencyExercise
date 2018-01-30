package com.selva.androidproficiencyexercise.module;

import android.app.Application;
import android.content.Context;


import com.selva.androidproficiencyexercise.qualifier.ApplicationContext;
import com.selva.androidproficiencyexercise.qualifier.ApplicationObject;

import dagger.Module;
import dagger.Provides;


@Module
public class AppModule {
    Application mApplication;

    public AppModule(Application mApplication) {
        this.mApplication = mApplication;
    }

    @Provides
    @ApplicationObject
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @ApplicationContext
    Context provideContext(){
        return mApplication;
    }
}
