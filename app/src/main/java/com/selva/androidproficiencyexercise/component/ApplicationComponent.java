package com.selva.androidproficiencyexercise.component;

import android.app.Application;
import android.content.Context;

import com.selva.androidproficiencyexercise.activity.MainActivity;
import com.selva.androidproficiencyexercise.module.AppModule;
import com.selva.androidproficiencyexercise.module.CountryDetailsModule;
import com.selva.androidproficiencyexercise.module.NetworkModule;
import com.selva.androidproficiencyexercise.qualifier.ApplicationContext;
import com.selva.androidproficiencyexercise.qualifier.ApplicationObject;

import javax.inject.Inject;

import dagger.Component;

@Component(modules = {AppModule.class, NetworkModule.class, CountryDetailsModule.class})
public interface ApplicationComponent {

    @ApplicationContext
    Context getContext();

    @ApplicationObject
    Application getApplication();

    void inject(MainActivity activity);

}
