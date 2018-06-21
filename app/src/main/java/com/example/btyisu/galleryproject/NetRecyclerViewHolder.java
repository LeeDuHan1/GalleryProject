package com.example.btyisu.galleryproject;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.example.btyisu.galleryproject.data.Content;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class NetRecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public NetworkImageView imageView;

    private Context context;
    private ArrayList<Content> contents;
    public NetRecyclerViewHolder(View view, Context context, ArrayList<Content> contents){
        super(view);
        imageView = (NetworkImageView) view.findViewById(R.id.network_imageview);
        imageView.setOnClickListener(this);

        this.context = context;
        this.contents = contents;
    }

    @Override
    public void onClick(View view){
        Bundle bundle = new Bundle();
        bundle.putString("url",contents.get(getAdapterPosition()).getThumbnail());
        bundle.putString("title",contents.get(getAdapterPosition()).getTitle());
        bundle.putString("userNick",contents.get(getAdapterPosition()).getUserNick());
        bundle.putString("viewCnt",contents.get(getAdapterPosition()).getViewCount());

        FragmentManager fragmentManager = ((AppCompatActivity)context).getSupportFragmentManager();
        NetImageDialogFragment netImageDialogFragment = new NetImageDialogFragment();
        netImageDialogFragment.setArguments(bundle);
        netImageDialogFragment.show(fragmentManager,"test");
    }

}
