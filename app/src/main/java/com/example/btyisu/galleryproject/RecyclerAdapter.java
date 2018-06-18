package com.example.btyisu.galleryproject;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {
    private ArrayList<String> dataSet;
    private Context context;
    private Bitmap bitmap;
    private Uri uri;
    private StringBuilder filePath;
    private int imageSize = 700;

    public RecyclerAdapter(ArrayList<String> dataSet, Context context){
        this.dataSet = dataSet;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_text_view, parent, false);
        RecyclerViewHolder vh = new RecyclerViewHolder(v, context, dataSet);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerViewHolder holder, final int position) {
        filePath = new StringBuilder("file://");
        filePath.append(dataSet.get(position));
        ImageTask imageTask = new ImageTask(holder, context, imageSize);
        imageTask.execute(filePath.toString());
    }

    public void dataAdd(int position, String data){
        this.dataSet.add(position,data);
        notifyItemInserted(position);
    }
    public void dataDelete(int position){
        this.dataSet.remove(position);
    }

}
