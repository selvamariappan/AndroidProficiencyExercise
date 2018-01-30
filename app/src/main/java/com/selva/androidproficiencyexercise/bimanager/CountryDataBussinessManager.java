package com.selva.androidproficiencyexercise.bimanager;

import android.content.Context;

import com.selva.androidproficiencyexercise.utils.AppUtils;



public class CountryDataBussinessManager {

    /**
     * Method to determine error message to show user
     * @param context context of Application
     * @param networkError fallback error message
     * @return error message
     */
    public static String getErrorMessage(Context context , String networkError) {
        if (AppUtils.isOnline(context)) {
            return networkError;
        } else {
            return "Please Switch On Network";
        }
    }
}
