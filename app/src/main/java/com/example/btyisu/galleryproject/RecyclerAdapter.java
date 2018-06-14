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
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {
    private ArrayList<String> dataSet = new ArrayList<>();
    private Context context;

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
        RecyclerViewHolder vh = new RecyclerViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerViewHolder holder, final int position) {
        Log.d("bind","d");
        StringBuilder filePath = new StringBuilder("file://");
        filePath.append(dataSet.get(position));
        Uri uri = Uri.parse(filePath.toString());
        InputStream imageStream = null;
        try {
            imageStream = this.context.getContentResolver().openInputStream(uri);
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
        BitmapFactory.Options bitOptions = new BitmapFactory.Options();
        bitOptions.inJustDecodeBounds = true;

        int imageWidth = bitOptions.outWidth;
        int imageHeight = bitOptions.outHeight;
        String imageType = bitOptions.outMimeType;

        int targetW = holder.imageView.getMeasuredWidth();
        int targetH = holder.imageView.getMeasuredHeight();

//        int scaleFactor = Math.min(imageWidth/targetW, imageHeight/targetH);
        bitOptions.inJustDecodeBounds = false;
//        bitOptions.inSampleSize = scaleFactor;
        bitOptions.inPurgeable = true;

        Bitmap bitmap = BitmapFactory.decodeStream(imageStream);
        holder.imageView.setImageBitmap(bitmap);
    }

    public void dataAdd(int position, String data){
        this.dataSet.add(position,data);
        notifyItemInserted(position);
    }
    public void dataDelete(int position){
        this.dataSet.remove(position);
    }
}
