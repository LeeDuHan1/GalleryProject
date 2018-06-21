package com.example.btyisu.galleryproject;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.btyisu.galleryproject.Volley.MyVolley;
import com.example.btyisu.galleryproject.data.Content;
import com.example.btyisu.galleryproject.data.Group;

import java.util.ArrayList;

public class NetRecyclerAdapter extends RecyclerView.Adapter<NetRecyclerViewHolder> {
    private ArrayList<Content> contents = new ArrayList<>();
    private Context context;
    private int imageSize = 700;
    private int layoutId = 0;
    public NetRecyclerAdapter(Context context, int layoutId){
        this.context = context;
        this.layoutId = layoutId;
    }

    @Override
    public int getItemCount() {
        return contents.size();
    }

    public NetRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        NetRecyclerViewHolder vh = new NetRecyclerViewHolder(v, context, contents);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final NetRecyclerViewHolder holder, final int position) {
        ViewGroup.LayoutParams params = holder.imageView.getLayoutParams();
        params.width = imageSize;
        params.height = imageSize;
        holder.imageView.setLayoutParams(params);
        holder.imageView.setImageUrl(contents.get(position).getThumbnail(), MyVolley.getInstance(context).getImageLoader());

//        holder.mViewCntText.setText(mViewCntString);
//        Glide.with(context)
//                .load(dataSet.get(position))
//                .into(holder.imageView);
    }

    public void dataAdd(int position, Content content){
        this.contents.add(position,content);
        notifyItemInserted(position);
    }
    public void dataDelete(int position){
        this.contents.remove(position);
    }

}

