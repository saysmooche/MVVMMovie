package com.bb.mvvmmovie.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bb.mvvmmovie.R;
import com.bb.mvvmmovie.model.RetroMovie;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

        private List<RetroMovie> movieList;
        private Context context;

        public CustomAdapter(Context context,List<RetroMovie> movieList){
            this.context = context;
            this.movieList = movieList;
        }

        class CustomViewHolder extends RecyclerView.ViewHolder {

            public final View mView;

            TextView txtTitle;
            private ImageView coverImage;

            CustomViewHolder(View itemView) {
                super(itemView);
                mView = itemView;

                txtTitle = mView.findViewById(R.id.title);
                coverImage = mView.findViewById(R.id.imgView_icon);
            }
        }

        @Override
        public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View view = layoutInflater.inflate(R.layout.item, parent, false);
            return new CustomViewHolder(view);
        }

        @Override
        public void onBindViewHolder(CustomViewHolder holder, int position) {
            holder.txtTitle.setText(movieList.get(position).getTitle());

            Picasso.Builder builder = new Picasso.Builder(context);
            builder.downloader(new OkHttp3Downloader(context));
            builder.build().load(movieList.get(position).getPosterPath())
                    .placeholder((R.drawable.ic_launcher_background))
                    .error(R.drawable.ic_launcher_background)
                    .into(holder.coverImage);

        }

        @Override
        public int getItemCount() {
            return movieList.size();
        }
    }
