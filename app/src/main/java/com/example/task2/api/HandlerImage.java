package com.example.task2.api;

import androidx.lifecycle.MutableLiveData;

import com.example.task2.apiCallFiles.Gallery;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HandlerImage {

    String METHOD = "flickr.photos.getRecent";
    int PER_PAGE=20;
    int PAGE=1;
    String API_KEY = "6f102c62f41998d151e5a1b48713cf13";
    String FORMAT="json";
    int JSONCALL=1;
    String EXTRAS="url_s";

    public MutableLiveData<Gallery> requestImages(){
        final MutableLiveData<Gallery> liveData =new MutableLiveData<>();
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit= new Retrofit.Builder().baseUrl("https://api.flickr.com/services/rest/")
                .addConverterFactory(GsonConverterFactory.create(gson)).build();
        Api api=retrofit.create(Api.class);
        Call<Gallery> call=api.getAnswers(METHOD,PER_PAGE, PAGE, API_KEY, FORMAT, JSONCALL, EXTRAS);
        call.enqueue(new Callback<Gallery>() {
            @Override
            public void onResponse(Call<Gallery> call, Response<Gallery> response) {
                liveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Gallery> call, Throwable t) {

            }
        });
        return liveData;
    }
    //--------------------------------------------------------------------------------------------------------------------------------------
    public MutableLiveData<Gallery> requestNewImages()
    {
        final MutableLiveData<Gallery> liveData =new MutableLiveData<>();
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit= new Retrofit.Builder().baseUrl("https://api.flickr.com/services/rest/")
                .addConverterFactory(GsonConverterFactory.create(gson)).build();
        Api api=retrofit.create(Api.class);
        Call<Gallery> call=api.getAnswers(METHOD,PER_PAGE, PAGE, API_KEY, FORMAT, JSONCALL, EXTRAS);
        call.enqueue(new Callback<Gallery>() {
            @Override
            public void onResponse(Call<Gallery> call, Response<Gallery> response) {
                liveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Gallery> call, Throwable t) {

            }
        });
        return liveData;
    }
}
