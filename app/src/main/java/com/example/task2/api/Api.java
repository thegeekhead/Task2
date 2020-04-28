package com.example.task2.api;

import com.example.task2.apiCallFiles.Gallery;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface Api {

    String BASE_URL="https://api.flickr.com/";

    String API_KEY="6f102c62f41998d151e5a1b48713cf13";

    @GET("services/rest/")
    Call<Gallery> getAnswers(@Query("method")String method,
                             @Query("per_page") int page,
                             @Query("page") int per_page,
                             @Query("api_key") String api_key,
                             @Query("format") String format,
                             @Query("nojsoncallback") int nojsoncallback,
                             @Query("extras") String url_s);

    @GET("services/rest/")
    Call<Gallery> getAnswers(@QueryMap Map<String, String> options);
}
