package com.example.btyisu.galleryproject;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {
    private String[] dataSet;
    private Context context;

    public RecyclerAdapter(String[] dataSet, Context context){
        this.dataSet = dataSet;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return dataSet.length;
    }

    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_text_view, parent, false);
        RecyclerViewHolder vh = new RecyclerViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerViewHolder holder, final int position) {
        Log.d("bind","d");
        holder.textview.setText(dataSet[position]);
        holder.textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, holder.textview.getText(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
