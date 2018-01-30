package com.selva.androidproficiencyexercise.mvp;


import com.selva.androidproficiencyexercise.model.CountryData;

import retrofit2.http.GET;
import rx.Observable;



public interface CountryDataMVP {

    interface View {

        void showLoader();

        void hideLoader();

        void onFailure(String errorMessage);

        void onCountryDataLoaded(CountryData countryDataModel);

        void setTitle(String title);

    }

    interface Presenter {

        void rxUnsubscribe();

        void initCountryData();

        void setView(CountryDataMVP.View view);

    }

    interface Model {
        @GET("s/2iodh4vg0eortkl/facts.json")
        Observable<CountryData> getCountryData();



    }
}
