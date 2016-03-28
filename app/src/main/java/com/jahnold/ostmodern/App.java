package com.jahnold.ostmodern;

import android.app.Application;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by matthewarnold on 28/03/2016.
 *
 */
public class App extends Application {

    private static Retrofit retrofit;

    public static Retrofit getRetrofit() {return retrofit;}

    @Override
    public void onCreate() {
        super.onCreate();
        setupRetrofit();
    }

    private void setupRetrofit() {

        OkHttpClient client = new OkHttpClient();

        retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl("http://feature-code-test.skylark-cms.qa.aws.ostmodern.co.uk:8000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
