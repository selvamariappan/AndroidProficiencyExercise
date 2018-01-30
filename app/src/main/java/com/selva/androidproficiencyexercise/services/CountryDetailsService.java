package com.selva.androidproficiencyexercise.services;


import com.selva.androidproficiencyexercise.model.CountryData;
import com.selva.androidproficiencyexercise.mvp.CountryDataMVP;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class CountryDetailsService {

    private final CountryDataMVP.Model countryDataModel;

    @Inject
    public CountryDetailsService(CountryDataMVP.Model countryDataModel) {
        this.countryDataModel = countryDataModel;
    }

    public Subscription getCountryDataDetails(final GetCountryDataCallback countryDataCallback) {


        return countryDataModel.getCountryData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends CountryData>>() {
                    @Override
                    public Observable<? extends CountryData> call(Throwable throwable) {
                        return Observable.error(throwable);
                    }
                })
                .subscribe(new Subscriber<CountryData>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        countryDataCallback.onError(e.getMessage());
                    }

                    @Override
                    public void onNext(CountryData countryDataModel) {
                        countryDataCallback.onSuccess(countryDataModel);
                    }
                });
    }


    public interface GetCountryDataCallback {
        void onSuccess(CountryData countryDataModel);

        void onError(String networkError);
    }
}
