package com.example.task2.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.task2.R;
import com.example.task2.apiCallFiles.Gallery;
import com.example.task2.apiCallFiles.Photo;
import com.example.task2.ui.home.HomeFragment;
import com.google.gson.annotations.SerializedName;

public class GalleryAdapter extends PagedListAdapter<Photo, GalleryAdapter.ItemViewHolder> {

    HomeFragment mCtx;
    @SerializedName(value = "photo")
    private Gallery gallery;

    public GalleryAdapter(HomeFragment mCtx) {
        super(DIFF_CALLBACK);
        this.mCtx = mCtx;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recyclerview_item, parent,false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {

        Photo photo = getItem(position);//gallery.getPhotos().getPhoto().get(position);

            Glide.with(mCtx)
                    .load(photo.getUrlS())
                    .into(holder.imageView);


            holder.textView.setText(photo.getTitle());

    }

    private static DiffUtil.ItemCallback<Photo> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Photo>() {
                @Override
                public boolean areItemsTheSame(@NonNull Photo oldItem, @NonNull Photo newItem) {
                    return oldItem.equals(newItem);
                }

                //@SuppressLint("DiffUtilEquals")
                @Override
                public boolean areContentsTheSame(@NonNull Photo oldItem, @NonNull Photo newItem) {
                    return true;
                }
            };


    //---------------------------------------------------------------
    public interface GalleryAdapterListener {

        void onGallerySelected(Gallery gallery);
    }
    //---------------------------------------------------------------

    class ItemViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textView;

        public ItemViewHolder(View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            textView = (TextView) itemView.findViewById(R.id.textView);
        }
    }
}
