package com.bb.mvvmmovie.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bb.mvvmmovie.R;
import com.bb.mvvmmovie.model.Result;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        Activity context;
        ArrayList<Result> resultArrayList;

        public RecyclerViewAdapter(Activity context, ArrayList<Result> resultArrayList) {
            this.context = context;
            this.resultArrayList = resultArrayList;
        }


        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View rootView = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
            return new RecyclerViewViewHolder(rootView);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

            Result result = resultArrayList.get(position);
            RecyclerViewViewHolder viewHolder= (RecyclerViewViewHolder) holder;

            viewHolder.txtView_title.setText(result.getTitle());
            viewHolder.txtView_description.setText(result.getOverview());
        }

        @Override
        public int getItemCount() {
            return resultArrayList.size();
        }

        class RecyclerViewViewHolder extends RecyclerView.ViewHolder {
            ImageView imgView_icon;
            TextView txtView_title;
            TextView txtView_description;

            public RecyclerViewViewHolder(@NonNull View itemView) {
                super(itemView);
                imgView_icon = itemView.findViewById(R.id.imgView_icon);
                txtView_title = itemView.findViewById(R.id.txtView_title);
                txtView_description = itemView.findViewById(R.id.txtView_description);


            }
        }
    }
