package com.selva.androidproficiencyexercise.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.selva.androidproficiencyexercise.R;
import com.selva.androidproficiencyexercise.adaptor.CountryDataAdaptor;
import com.selva.androidproficiencyexercise.component.ApplicationComponent;
import com.selva.androidproficiencyexercise.component.DaggerApplicationComponent;
import com.selva.androidproficiencyexercise.model.CountryData;
import com.selva.androidproficiencyexercise.module.AppModule;
import com.selva.androidproficiencyexercise.module.CountryDetailsModule;
import com.selva.androidproficiencyexercise.module.NetworkModule;
import com.selva.androidproficiencyexercise.mvp.CountryDataMVP;
import com.selva.androidproficiencyexercise.persenter.CountryDataPresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, CountryDataMVP.View, View.OnClickListener {
    @BindView(R.id.itemsRecyclerView)
    RecyclerView recyclerViewForItems;

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.progressView)
    ProgressBar progressBar;

    @BindView(R.id.layoutError)
    RelativeLayout errorRelativeLayout;

    @BindView(R.id.refresh_button)
    AppCompatButton refreshButton;

    @BindView(R.id.error_textView)
    AppCompatTextView errorAppCompatTextView;

    ApplicationComponent applicationComponent;

    @Inject
    CountryDataPresenter countryDataPresenter;

    private CountryData countryDataModel;

    private CountryDataAdaptor countryDataAdaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        applicationComponent = DaggerApplicationComponent.builder()
                .appModule(new AppModule(getApplication()))
                .networkModule(new NetworkModule())
                .countryDetailsModule(new CountryDetailsModule())
                .build();
        applicationComponent.inject(this);
        ButterKnife.bind(this);
        countryDataPresenter.initCountryData();
        countryDataPresenter.setView(this);
        swipeRefreshLayout.setOnRefreshListener(this);
        refreshButton.setOnClickListener(this);
        countryDataPresenter.getCountryData(false);
    }

    /**
     * To Force Api Call trigger by Swipe down event or click from Refresh button
     */
    @Override
    public void onRefresh() {
        countryDataPresenter.getCountryData(true);
    }


    /**
     * Method to Show Progress bar
     */
    @Override
    public void showLoader() {
        errorRelativeLayout.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    /**
     * Method to hide Progress bar
     */
    @Override
    public void hideLoader() {
        progressBar.setVisibility(View.GONE);
        swipeRefreshLayout.setRefreshing(false);
    }

    /**
     * Handle Failure Message
     * @param errorMessage - Failure Error Message
     */
    @Override
    public void onFailure(String errorMessage) {
        swipeRefreshLayout.setVisibility(View.GONE);
        errorRelativeLayout.setVisibility(View.VISIBLE);
        errorAppCompatTextView.setText(String.valueOf(errorMessage));
    }

    @Override
    public void onCountryDataLoaded(CountryData countryDataModel) {
        swipeRefreshLayout.setVisibility(View.VISIBLE);
        errorRelativeLayout.setVisibility(View.GONE);
        this.countryDataModel = countryDataModel;
        if (countryDataAdaptor == null) {
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
            recyclerViewForItems.setLayoutManager(mLayoutManager);
            countryDataAdaptor = new CountryDataAdaptor(this, this.countryDataModel);
            recyclerViewForItems.setAdapter(countryDataAdaptor);
        } else {
            countryDataAdaptor.notifyDataSetChanged();
        }
    }

    /**
     * Method to Set Title For Action Bar
     * @param title - Title of Screen Fetch from api
     */
    @Override
    public void setTitle(String title) {
        if (getSupportActionBar() == null || TextUtils.isEmpty(title)) {
            return;
        }
        getSupportActionBar().setTitle(title);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        countryDataPresenter.rxUnsubscribe();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.refresh_button) {
            errorRelativeLayout.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
            onRefresh();
        }
    }
}
