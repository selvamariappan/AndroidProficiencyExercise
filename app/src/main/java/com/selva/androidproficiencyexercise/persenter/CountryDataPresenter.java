package com.selva.androidproficiencyexercise.persenter;

import android.content.Context;

import com.selva.androidproficiencyexercise.bimanager.CountryDataBussinessManager;
import com.selva.androidproficiencyexercise.model.CountryData;
import com.selva.androidproficiencyexercise.mvp.CountryDataMVP;
import com.selva.androidproficiencyexercise.qualifier.ApplicationContext;
import com.selva.androidproficiencyexercise.services.CountryDetailsService;
import com.selva.androidproficiencyexercise.utils.AppUtils;

import javax.inject.Inject;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;


public class CountryDataPresenter implements CountryDataMVP.Presenter {
    private CountryDetailsService service;
    private CountryDataMVP.View countryDataView;
    private CompositeSubscription subscriptions;
    private Context context;


    @Inject
    public CountryDataPresenter(@ApplicationContext Context context, CountryDetailsService service) {
        this.service = service;
        this.context = context;
        this.subscriptions = new CompositeSubscription();
    }

    /**
     * Method to init the api call
     * @param isForceNetworkcall flag for force api call or not
     */
    public void getCountryData(boolean isForceNetworkcall) {
        if (!isForceNetworkcall) {
            countryDataView.showLoader();
        }
        Subscription subscription = service.getCountryDataDetails(new CountryDetailsService.GetCountryDataCallback() {

            @Override
            public void onSuccess(CountryData countryDataModel) {
                countryDataView.hideLoader();
                handleSuccessData(countryDataModel);
            }

            @Override
            public void onError(String networkError) {
                countryDataView.hideLoader();
                handleFailureData(networkError);
            }

        });

        subscriptions.add(subscription);
    }

    /**
     * Method to show error message to ui
     * @param networkError - error message
     */
    private void handleFailureData(String networkError) {
        countryDataView.onFailure(CountryDataBussinessManager.getErrorMessage(context,networkError));
    }

    /**
     * Method to handle Success message
     * @param countryDataModel - api response
     */
    private void handleSuccessData(CountryData countryDataModel) {
        if (countryDataModel != null) {
            countryDataModel = AppUtils.removeEmptyData(countryDataModel);
            countryDataView.onCountryDataLoaded(countryDataModel);
            countryDataView.setTitle(countryDataModel.getTitle());
        } else {
            countryDataView.onFailure("Please Try agian Later");
        }
    }

    @Override
    public void rxUnsubscribe() {
        subscriptions.unsubscribe();
    }

    @Override
    public void initCountryData() {
        if (subscriptions.hasSubscriptions()) {
            subscriptions.clear();
        }
    }

    @Override
    public void setView(CountryDataMVP.View view) {
        countryDataView = view;
    }
}
