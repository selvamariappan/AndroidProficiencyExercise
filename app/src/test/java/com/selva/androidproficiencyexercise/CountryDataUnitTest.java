package com.selva.androidproficiencyexercise;

import com.selva.androidproficiencyexercise.component.ApplicationComponent;
import com.selva.androidproficiencyexercise.model.CountryData;
import com.selva.androidproficiencyexercise.model.Row;
import com.selva.androidproficiencyexercise.module.NetworkModule;
import com.selva.androidproficiencyexercise.utils.AppUtils;

import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * CountryData local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class CountryDataUnitTest {

    @Test
    public void removeEmpty_test1(){
        assertSame(null,AppUtils.removeEmptyData(null));
    }

    @Test
    public void removeEmpty_test2(){
        CountryData countryData = new CountryData();
        assertSame(0,AppUtils.removeEmptyData(countryData).getRows().size());
    }

    @Test
    public void removeEmpty_test3(){
        CountryData countryData = new CountryData();
        List<Row> rows = new ArrayList<>();
        Row row = new Row();
        row.setTitle("Title");
        row.setDescription("DEscr");
        row.setImageHref("fijaofj");
        rows.add(row);
        rows.add(row);
        Row row1 = new Row();
        row1.setTitle(null);
        row1.setDescription("");
        row1.setImageHref("");
        rows.add(row1);
        countryData.setRows(rows);
        assertSame(2,AppUtils.removeEmptyData(countryData).getRows().size());
    }

    @Test
    public void isEmpty_test(){
        assertSame(true,AppUtils.isEmpty(null));
    }

    @Test
    public void isEmpty_test2(){
        assertSame(true,AppUtils.isEmpty(""));
    }

    @Test
    public void isEmpty_test3(){
        assertSame(false,AppUtils.isEmpty("hh"));
    }

    @Test
    public void _test(){
        assertSame(true,AppUtils.checkIsAllEmpty(null));
    }

    @Test
    public void checkRowEmpty_test2(){
        Row row = new Row();
        row.setTitle("Title");
        row.setDescription("DEscr");
        row.setImageHref("fijaofj");
        assertSame(false,AppUtils.checkIsAllEmpty(row));
    }

    @Test
    public void checkRowEmpty_test3(){
        Row row = new Row();
        row.setTitle("");
        row.setDescription("");
        row.setImageHref(null);
        assertSame(true,AppUtils.checkIsAllEmpty(row));
    }

}