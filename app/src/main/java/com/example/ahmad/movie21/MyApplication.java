package com.example.ahmad.movie21;

import android.app.Application;

import com.squareup.okhttp.OkHttpClient;

/**
 * Created by ahmad on 09/08/17.
 */

public class MyApplication extends Application {

    private static OkHttpClient okHttpClient;
    public static final String BASE_URL = "https://api.themoviedb.org/3/movie/550?api_key=b26733daf3a5f7fd722800d1110e79b8";

    @Override
    public void onCreate() {
        super.onCreate();

        okHttpClient = new OkHttpClient();

    }

    public static OkHttpClient getClient() {
        return okHttpClient;
    }
}
