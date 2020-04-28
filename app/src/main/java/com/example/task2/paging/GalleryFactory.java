package com.example.task2.paging;

import com.example.task2.api.Api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GalleryFactory {

    public static final String BASE_URL = "https://api.flickr.com/";
    public static GalleryFactory mInstance;
    Retrofit retrofit;


    public GalleryFactory() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized GalleryFactory getInstance() {
        if (mInstance == null) {
            mInstance = new GalleryFactory();
        }
        return mInstance;
    }

    public Api getApi() {
        return retrofit.create(Api.class);
    }
}
