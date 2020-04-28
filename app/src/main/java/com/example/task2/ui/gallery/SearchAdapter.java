package com.example.task2.ui.gallery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.task2.R;
import com.example.task2.api.Api;
import com.example.task2.apiCallFiles.Gallery;
import com.example.task2.apiCallFiles.Photo;
import com.example.task2.paging.ItemViewModel;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ItemViewHolder> {

    private Context mCtx;
    private Gallery gallery;

    public SearchAdapter(Context mCtx, Gallery gallery) {
        this.mCtx = mCtx;
        this.gallery = gallery;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.recyclerview_search, viewGroup, false);
        return new ItemViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int i) {
        Photo photo = gallery.getPhotos().getPhoto().get(i);
        Glide.with(mCtx)
                .load(photo.getUrlS())
                .into(holder.imageView);
        holder.textView.setText(photo.getTitle());
    }

    @Override
    public int getItemCount() {
        return gallery.getPhotos().getPhoto().size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView);

        }
    }
}
