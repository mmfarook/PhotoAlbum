package com.example.photoalbum.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.photoalbum.R;
import com.example.photoalbum.model.Photo;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;


public class PhotoAdapter extends ListAdapter<Photo, PhotoAdapter.PhotoViewHolder> {

    private final static DiffUtil.ItemCallback<Photo> diffCallback = new DiffUtil.ItemCallback<Photo>() {
        @Override
        public boolean areItemsTheSame(Photo oldItem, Photo newItem) {
            return oldItem.getId().equals(newItem.getId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Photo oldItem, @NonNull Photo newItem) {
            return areItemsTheSame(oldItem, newItem);

        }
    };

    private final ItemClickListener mItemClickListener;

    public interface ItemClickListener {
        void onItemClick(int position);
    }

    public PhotoAdapter(ItemClickListener itemClickListener) {
        super(diffCallback);
        mItemClickListener = itemClickListener;

    }

    class PhotoViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imageView;
        private final TextView titleView;

        PhotoViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.photo_view);
            titleView = itemView.findViewById(R.id.title_view);
        }

        void bindTo(final int position, Photo photo) {
            if (mItemClickListener != null) {
                imageView.setOnClickListener((View v)-> mItemClickListener.onItemClick(position));
            }
            if (titleView != null) {
                titleView.setText(photo.getName());
            }
            Picasso.get().load(photo.getUrl()).placeholder(R.drawable.placeholder_image).into(imageView);
        }
    }

    @NonNull
    @Override
    public PhotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.photo_item, parent, false);
        return new PhotoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoViewHolder mholder, int position) {
        Photo photo = getItem(position);
        mholder.bindTo(position, photo);
    }
}
