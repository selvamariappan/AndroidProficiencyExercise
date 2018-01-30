
package com.selva.androidproficiencyexercise.model;

import android.text.TextUtils;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Row {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("imageHref")
    @Expose
    private String imageHref;

    public String getTitle() {
        if (TextUtils.isEmpty(title)) {
            return "";
        }
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        if (TextUtils.isEmpty(description)) {
            return "";
        }
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getImageHref() {
        if (TextUtils.isEmpty(imageHref)) {
            return "";
        }
        return imageHref;
    }

    public void setImageHref(String imageHref) {
        this.imageHref = imageHref;
    }


}
