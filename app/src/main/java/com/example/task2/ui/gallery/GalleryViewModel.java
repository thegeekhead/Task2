package com.example.task2.ui.gallery;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.task2.apiCallFiles.Gallery;

import java.util.Map;

public class GalleryViewModel extends ViewModel {

    private SearchRepository searchRepository;
    private Map<String,String> pass_value;
    private MutableLiveData<Gallery> liveData;

    public GalleryViewModel(Map<String,String> pass_value) {
        searchRepository = new SearchRepository();
        this.pass_value = pass_value;
    }

    public MutableLiveData<Gallery> getImages(){
        if(liveData==null){
            liveData = searchRepository.requestImages(pass_value);
        }
        return liveData;
    }
}