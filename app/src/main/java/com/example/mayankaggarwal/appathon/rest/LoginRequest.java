package com.example.mayankaggarwal.appathon.rest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by mayankaggarwal on 12/02/17.
 */

public class LoginRequest {

    @SerializedName("name")
    @Expose
    public String name;

    @SerializedName("link")
    @Expose
    public String link;

    @SerializedName("price")
    @Expose
    public String price;

    @SerializedName("description")
    @Expose
    public String descripition;
}
