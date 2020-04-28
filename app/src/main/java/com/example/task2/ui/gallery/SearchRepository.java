package com.example.task2.ui.gallery;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.task2.api.Api;
import com.example.task2.apiCallFiles.Gallery;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchRepository {

    public MutableLiveData<Gallery> requestImages(Map<String,String> pass_value) {
        final MutableLiveData<Gallery> liveData = new MutableLiveData<>();
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();

        Api api = retrofit.create(Api.class);
        Call<Gallery> call = api.getAnswers(pass_value);

        call.enqueue(new Callback<Gallery>() {
            @Override
            public void onResponse(Call<Gallery> call, Response<Gallery> response) {
                Log.i("msg",response.toString());
                liveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Gallery> call, Throwable t) {
                Log.i("err",t.toString());
            }
        });
        return liveData;
    }
}
