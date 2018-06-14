package com.example.btyisu.galleryproject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class ImageTask extends AsyncTask<String, String, Bitmap> {
    private Bitmap bitmap;
    private Context context;
    private Uri uri;
    private int size;
    private  RecyclerViewHolder holder;

    public ImageTask(RecyclerViewHolder holder,Context context, Uri uri, int size){
        this.holder = holder;
        this.context = context;
        this.uri = uri;
        this.size = size;
    }
    @Override
    protected Bitmap doInBackground(String... arg) {
        BitmapUtil bitmapUtil = new BitmapUtil();
        bitmap = bitmapUtil.getBitmapFromUri(context,uri,size);
        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        holder.imageView.setImageBitmap(bitmap);
    }
}
