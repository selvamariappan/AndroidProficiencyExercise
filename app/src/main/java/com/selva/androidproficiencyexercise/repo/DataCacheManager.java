package com.selva.androidproficiencyexercise.repo;

import com.selva.androidproficiencyexercise.model.CountryData;


public class DataCacheManager {

    private DataCacheManager() {

    }

    public static DataCacheManager dataCacheManager;

    public static DataCacheManager getDataCacheManager() {
        if (null == dataCacheManager) {
            dataCacheManager = new DataCacheManager();
        }
        return dataCacheManager;
    }

    public CountryData countryData;

    public CountryData getCountryData() {
        return countryData;
    }

    public void setCountryData(CountryData countryData) {
        this.countryData = countryData;
    }

    public boolean isDataAvaliable() {
        return null != countryData && countryData.getRows().size() > 0;
    }
}
