package com.example.shikh.application;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by shikh on 23-06-2018.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    int images[];
    String names[];
    String authNames[];
    Context context;

    public MyAdapter(int images[], String names[], String authNames[], Context context) {
        this.images = images;
        this.names= names;
        this.authNames = authNames;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li = LayoutInflater.from(parent.getContext());
        return new ViewHolder(li.inflate(R.layout.dash_items_mylib, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.BindView(position);
    }

    @Override
    public int getItemCount() {
        return images.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgView;
        TextView lib_bookauth;
        TextView lib_bookname;

        public ViewHolder(View itemView) {
            super(itemView);
            imgView = itemView.findViewById(R.id.imgView);
            lib_bookauth = itemView.findViewById(R.id.lib_bookauth);
            lib_bookname = itemView.findViewById(R.id.lib_bookname);
        }

        public void BindView(int loc) {
            imgView.setImageResource(images[loc]);
            lib_bookname.setText(names[loc]);
            lib_bookauth.setText(authNames[loc]);
        }
    }
}
