package com.example.btyisu.galleryproject;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {
    private ArrayList<String> dataSet = new ArrayList<>();
    private Context context;

    public RecyclerAdapter( Context context){
//        this.dataSet = dataSet;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_text_view, parent, false);
        RecyclerViewHolder vh = new RecyclerViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerViewHolder holder, final int position) {
        Log.d("bind","d");
        Uri uri = Uri.parse(dataSet.get(position));
        holder.imageView.setImageURI(uri);
    }

    public void dataAdd(int position, String data){
        this.dataSet.add(position,data);
        notifyItemInserted(position);
    }
    public void dataDelete(int position){
        this.dataSet.remove(position);
    }
}
