package com.example.task2.ui.home;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.task2.R;
import com.example.task2.apiCallFiles.Gallery;
import com.example.task2.apiCallFiles.Photo;
import com.example.task2.paging.ItemViewModel;
import com.example.task2.view.GalleryAdapter;

public class HomeFragment extends Fragment {

    RecyclerView recyclerView;
    //Boolean isScrolling = false;
    //int curretItems, totalItems, scrollOutItems;
    LinearLayoutManager manager;
    //ProgressBar progressbar;
    //GalleryAdapter galleryAdapter;

    ItemViewModel itemViewModel;
    GalleryAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        //progressbar = (ProgressBar) progressbar.findViewById(R.id);

        GalleryAdapter adapter = new GalleryAdapter(this);

        itemViewModel = ViewModelProviders.of(this).get(ItemViewModel.class);

        manager = new LinearLayoutManager(getActivity());
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);



        //itemViewModel = ViewModelProviders.of(this).get(ItemViewModel.class);


//-----------------------------------------------------------
        //final TextView textView = root.findViewById(R.id.text_home);
        itemViewModel.itemPagedList.observe(getViewLifecycleOwner(), new Observer<PagedList<Photo>>() {
            @Override
            public void onChanged(@Nullable PagedList<Photo> photos)  {
                adapter.submitList(photos);
            }
        });

        recyclerView.setAdapter(adapter);
        return view;
        //----------------------------------------
        //return view;
    }


}
