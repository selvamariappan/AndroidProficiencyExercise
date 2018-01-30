package com.selva.androidproficiencyexercise.module;

import com.selva.androidproficiencyexercise.mvp.CountryDataMVP;

import java.io.IOException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;


@Module
public class CountryDetailsModule {

    @Provides
    public CountryDataMVP.Model provideApiService(Retrofit retrofit) {
        return retrofit.create(CountryDataMVP.Model.class);
    }

}
