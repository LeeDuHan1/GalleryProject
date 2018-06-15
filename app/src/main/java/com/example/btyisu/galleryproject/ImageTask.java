package com.example.btyisu.galleryproject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class ImageTask extends AsyncTask<String, String, Bitmap> {
    private Bitmap bitmap;
    private Context context;
    private int size;
    private  RecyclerViewHolder holder;
    private final WeakReference imageViewReference;
    public ImageTask(RecyclerViewHolder holder,Context context, int size){
        this.holder = holder;
        this.context = context;
        this.size = size;
        this.imageViewReference = new WeakReference(holder.imageView);
    }
    @Override
    protected Bitmap doInBackground(String... arg) {
        Uri uri = Uri.parse(arg[0]);
        BitmapUtil bitmapUtil = new BitmapUtil();
        bitmap = bitmapUtil.getBitmapFromUri(uri, size);
        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        if (imageViewReference != null && bitmap != null) {
            final ImageView imageView = (ImageView)imageViewReference.get();
            if (imageView != null) {
                imageView.setImageBitmap(bitmap);
            }
        }
    }
}
