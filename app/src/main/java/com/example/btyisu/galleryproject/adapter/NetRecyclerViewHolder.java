package com.example.btyisu.galleryproject.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.btyisu.galleryproject.NetImageDialogFragment;
import com.example.btyisu.galleryproject.R;
import com.example.btyisu.galleryproject.data.Content;

import java.util.ArrayList;

public class NetRecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public ImageView imageView;
    private Context context;
    private ArrayList<Content> contents;
    private static long mImagePreClickTime = 0;
    public NetRecyclerViewHolder(View view, Context context, ArrayList<Content> contents){
        super(view);
        imageView = (ImageView) view.findViewById(R.id.network_imageview);
        imageView.setOnClickListener(this);
        this.context = context;
        this.contents = contents;
    }

    /**
     *
     * @param view
     */
    @Override
    public void onClick(View view){
        long mImageCurrentClickTime = System.currentTimeMillis();
        long mTimeCheck = mImageCurrentClickTime - mImagePreClickTime;

        if(mTimeCheck > 2000) {
            FragmentManager mNetImageDialogFragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
            NetImageDialogFragment netImageDialogFragment = new NetImageDialogFragment();
            Bundle bundle = new Bundle();
            bundle.putString("url", contents.get(getAdapterPosition()).getThumbnail());
            bundle.putString("title", contents.get(getAdapterPosition()).getTitle());
            bundle.putString("userNick", contents.get(getAdapterPosition()).getUserNick());
            bundle.putString("viewCnt", contents.get(getAdapterPosition()).getViewCount());
            bundle.putString("currentTime", String.valueOf(mImageCurrentClickTime));
            netImageDialogFragment.setArguments(bundle);
            netImageDialogFragment.show(mNetImageDialogFragmentManager, "test");
            mImagePreClickTime = System.currentTimeMillis();
        }
    }

}
