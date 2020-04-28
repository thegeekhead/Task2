package com.example.task2.api;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.task2.apiCallFiles.Gallery;

public class ImgModel extends ViewModel {

    private HandlerImage handlerImage;
    private MutableLiveData<Gallery> liveData;
    private MutableLiveData<Gallery> liveData2;
    static final String TAG="ImgModel";
    public ImgModel() {
        handlerImage = new HandlerImage();
    }

    public LiveData<Gallery> getImages() {
        if (liveData == null) {
            liveData = handlerImage.requestImages();
            Log.e(TAG, "getImages: "+liveData);
        }
        return liveData;
    }

    //-------------------------------------------------------------------------------------------------------------------------------------
    public LiveData<Gallery> getMore()
    {
        liveData2=handlerImage.requestNewImages();
        return liveData2;
    }
}
