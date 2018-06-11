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

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private String[] dataSet;
    private Context context;
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textview;

        public ViewHolder(View view){
            super(view);
            textview = (TextView) view.findViewById(R.id.textView3);
        }
    }


    public RecyclerAdapter(String[] dataSet, Context context){
        this.dataSet = dataSet;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return dataSet.length;
    }
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_text_view, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        Log.d("bind","d");
        holder.textview.setText(dataSet[position]);
        holder.textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, holder.textview.getText(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
