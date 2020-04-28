package com.example.task2.ui.gallery;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.task2.R;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class GalleryFragment extends Fragment {

    private SearchView search_btn;
    private RecyclerView shown_images;
    private Map<String ,String> pass_value = new HashMap<>();
    private SearchAdapter searchAdapter;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private long timeSinceLastRequest;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gallery,container,false);
        search_btn = view.findViewById(R.id.search_btn);
        shown_images = view.findViewById(R.id.shown_images);
        shown_images.setHasFixedSize(true);
        shown_images.setLayoutManager(new LinearLayoutManager(getActivity()));
        timeSinceLastRequest = System.currentTimeMillis();
        pass_value.put("method","flickr.photos.search");
        pass_value.put("api_key","6f102c62f41998d151e5a1b48713cf13");
        pass_value.put("format","json");
        pass_value.put("nojsoncallback","1");
        pass_value.put("extras","url_s");
        pass_value.put("text","");

        Observable<String> observable = Observable
                .create((ObservableOnSubscribe<String>) emitter -> search_btn.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        if(!emitter.isDisposed()){
                            emitter.onNext(newText);
                        }
                        return false;
                    }
                }))
                .debounce(300, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io());

        observable.subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                compositeDisposable.add(d);
            }

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onNext(String s) {
                Log.i("msg",s);
                if(!s.isEmpty()){
                    pass_value.replace("text",s);
                    getActivity().runOnUiThread(() -> {
                        GalleryViewModel galleryViewModel = new GalleryViewModel(pass_value);
                        galleryViewModel.getImages().observe(getActivity(), gallery -> {
                            searchAdapter = new SearchAdapter(getContext(), gallery);
                            shown_images.setAdapter(searchAdapter);
                        });
                    });
                }
            }


            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
        return view;
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        compositeDisposable.clear();
    }
}
