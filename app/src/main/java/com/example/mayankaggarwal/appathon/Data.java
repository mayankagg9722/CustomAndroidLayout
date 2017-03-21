package com.example.mayankaggarwal.appathon;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.mayankaggarwal.appathon.rest.ApiClient;
import com.example.mayankaggarwal.appathon.rest.ApiInterface;
import com.example.mayankaggarwal.appathon.rest.LoginRequest;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by mayankaggarwal on 12/02/17.
 */

public class Data {

    public static void updateAttendance(final Activity activity, final InputStream is, final String name, final String price, final String desc, final UpdateCallback updateCallback) {
        GetAttendance getAttendance = new GetAttendance(is, name, price, desc, updateCallback);
        getAttendance.execute(activity);
    }


    public static class GetAttendance extends AsyncTask<Activity, Void, Integer> {

        UpdateCallback updateCallback;
        Cloudinary cloudinary;
        InputStream is;
        String name;
        String price;
        String desc;

        GetAttendance(InputStream is, String name, String price, String desc, UpdateCallback updateCallback) {
            this.is = is;
            this.name = name;
            this.price = price;
            this.desc = desc;
            this.updateCallback = updateCallback;
        }

        @Override
        protected Integer doInBackground(Activity... params) {
            final Activity activity = params[0];
            Map config = new HashMap();
            config.put("cloud_name", "dcgslof1v");
            config.put("api_key", "931346753328684");
            config.put("api_secret", "pNqgkD40iYDAKsP4wez6Ha3IqAM");
            cloudinary = new Cloudinary(config);

            try {
                String random_number=UUID.randomUUID().toString();
                cloudinary.uploader().upload(is, ObjectUtils.asMap("public_id",random_number));
                String link = cloudinary.url().generate(random_number);
//                Log.d("tagg", cloudinary.url().generate("sample_remote.jpg"));
                ApiInterface apiInterface = new ApiClient().getClient(activity).create(ApiInterface.class);
                LoginRequest loginRequest = new LoginRequest();
                loginRequest.name = name;
                loginRequest.price = price;
                loginRequest.descripition = desc;
                loginRequest.link = link;
                final Call<JsonObject> attendance = apiInterface.pushData(loginRequest);
                attendance.enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
//                        Log.d("tagg", response.body().toString());
                        activity.finish();
                        activity.startActivity(new Intent(activity, SuccessProduct.class));
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {

                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
// //               Log.d("tagg", "exceptionthrowm");
//                updateCallback.onFailure();
            }
            return 0;
        }

        @Override
        protected void onPostExecute(Integer integer) {
//            Log.d("tagg","out of async");
            updateCallback.onUpdate();
        }
    }


    public interface UpdateCallback {
        void onUpdate();

        void onFailure();
    }
}
