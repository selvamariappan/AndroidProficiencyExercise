package com.selva.androidproficiencyexercise.utils;


import android.content.Context;
import android.net.ConnectivityManager;
import android.text.TextUtils;

import com.selva.androidproficiencyexercise.model.CountryData;
import com.selva.androidproficiencyexercise.model.Row;

import java.util.Iterator;
import java.util.List;

public class AppUtils {
    /**
     * Method to remove EmptyData  from response
     *
     * @param countryDataModel Response Model Object
     */
    public static CountryData removeEmptyData(CountryData countryDataModel) {

        if (countryDataModel == null) {
            return null;
        }
        List<Row> rows = countryDataModel.getRows();
        for (Iterator<Row> it = rows.iterator(); it.hasNext(); ) {
            Row row = it.next();
            if (checkIsAllEmpty(row)) {
                it.remove();
            }
        }

        countryDataModel.setRows(rows);
        return countryDataModel;
    }

    /**
     * method to check row is Empty or nor
     *
     * @param row row obj from response
     */
    public static boolean checkIsAllEmpty(Row row) {
        if (row == null || (isEmpty(row.getTitle()) && isEmpty(row.getDescription())
                && isEmpty(row.getImageHref()))) {
            return true;
        }
        return false;
    }


    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }


    /**
     * Check Network Connection available or not
     *
     * @param context Context
     */
    public static boolean isOnline(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (cm != null) {
            return cm.getActiveNetworkInfo() != null &&
                    cm.getActiveNetworkInfo().isConnectedOrConnecting();
        } else {
            return false;
        }
    }


}
