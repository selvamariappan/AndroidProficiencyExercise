package com.selva.androidproficiencyexercise;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.google.gson.annotations.Expose;
import com.selva.androidproficiencyexercise.bimanager.CountryDataBussinessManager;
import com.selva.androidproficiencyexercise.persenter.CountryDataPresenter;
import com.selva.androidproficiencyexercise.utils.AppUtils;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class CountryDataInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.selva.androidproficiencyexercise", appContext.getPackageName());
    }

    @Test
    public void testErrorMessage_test1() throws Exception{
        Context appContext = InstrumentationRegistry.getTargetContext();
        if(AppUtils.isOnline(appContext)) {
            assertEquals("Sorry", CountryDataBussinessManager.getErrorMessage(appContext, "Sorry"));
        }

    }

    @Test
    public void testErrorMessage_test2() throws Exception{
        Context appContext = InstrumentationRegistry.getTargetContext();
        if(!AppUtils.isOnline(appContext)) {
            assertEquals("Please Switch On Network", CountryDataBussinessManager.getErrorMessage(appContext, "Sorry"));
        }
    }

}
