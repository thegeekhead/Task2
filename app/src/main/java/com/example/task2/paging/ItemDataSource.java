package com.example.task2.paging;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.example.task2.apiCallFiles.Gallery;
import com.example.task2.apiCallFiles.Photo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemDataSource extends PageKeyedDataSource<Integer, Photo> {

    public static int PAGESIZE = 20;
    private static int FIRSTPAGE = 1;
    String METHOD = "flickr.photos.getRecent";
    int PER_PAGE=20;
    int PAGE=1;
    String API_KEY = "6f102c62f41998d151e5a1b48713cf13";
    String FORMAT="json";
    int JSONCALL=1;
    String EXTRAS="url_s";



    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull final LoadInitialCallback<Integer, Photo> callback) {

            GalleryFactory.getInstance().getApi()
                .getAnswers(METHOD,PER_PAGE, PAGE, API_KEY, FORMAT, JSONCALL, EXTRAS )
                .enqueue(new Callback<Gallery>() {
                    @Override
                    public void onResponse(Call<Gallery> call, Response<Gallery> response) {
                        if (response.body() != null) {
                            List<Photo> photoList=response.body().getPhotos().getPhoto();
                            callback.onResult(photoList,null,PAGE+1);
                        }
                    }
                    @Override
                    public void onFailure(Call<Gallery> call, Throwable t){


                    }
                });
    }

    @Override
    public void loadBefore(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Photo> callback) {

        GalleryFactory.getInstance()
                .getApi()
                .getAnswers(METHOD,PER_PAGE,params.key,API_KEY,FORMAT,JSONCALL,EXTRAS)
                .enqueue(new Callback<Gallery>() {
                    @Override
                    public void onResponse(Call<Gallery> call, Response<Gallery> response){


                        Integer key=(params.key>1) ? params.key-1 : null;



                        if (response.body() != null){

                            List<Photo> photoList=response.body().getPhotos().getPhoto();
                            callback.onResult(photoList,key);
                        }

                    }
                    @Override
                    public void onFailure(Call<Gallery> call, Throwable t){


                    }
                });

    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Photo> callback) {

        GalleryFactory.getInstance()
                .getApi()
                .getAnswers(METHOD,PER_PAGE,params.key,API_KEY,FORMAT,JSONCALL,EXTRAS)
                .enqueue(new Callback<Gallery>() {
                    @Override
                    public void onResponse(Call<Gallery> call, Response<Gallery> response){


                        if (response.body() != null){

                            List<Photo> photoList=response.body().getPhotos().getPhoto();
                            callback.onResult(photoList,PAGE+1);
                        }

                    }
                    @Override
                    public void onFailure(Call<Gallery> call, Throwable t){


                    }
                });

    }
}
