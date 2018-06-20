package com.example.btyisu.galleryproject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.btyisu.galleryproject.Volley.MyVolley;

import java.util.ArrayList;

public class NetRecyclerAdapter extends RecyclerView.Adapter<NetRecyclerViewHolder> {
    private ArrayList<String> dataSet = new ArrayList<>();
    private Context context;
    private int imageSize = 700;

    public NetRecyclerAdapter(Context context){
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public NetRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_network_image_view, parent, false);
        NetRecyclerViewHolder vh = new NetRecyclerViewHolder(v, context, dataSet);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final NetRecyclerViewHolder holder, final int position) {
        ViewGroup.LayoutParams params = holder.imageView.getLayoutParams();
        params.width = imageSize;
        params.height = imageSize;
        holder.imageView.setLayoutParams(params);
        holder.imageView.setImageUrl(dataSet.get(position), MyVolley.getInstance(context).getImageLoader());
//        Glide.with(context)
//                .load(dataSet.get(position))
//                .into(holder.imageView);
    }

    public void dataAdd(int position, String data){
        this.dataSet.add(position,data);
        notifyItemInserted(position);
    }
    public void dataDelete(int position){
        this.dataSet.remove(position);
    }

}

