package com.example.btyisu.galleryproject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class BitmapUtil {
    private static final String TAG = "BitmapUtil";

    public Bitmap getBitmapFromUri(Context context, Uri uri, int size){
        InputStream imageStream = null;
        try{
            imageStream = context.getContentResolver().openInputStream(uri);
        }catch (FileNotFoundException e){
            Log.e(TAG, e.getMessage());
            return null;
        }
        BitmapFactory.Options bitOptions = new BitmapFactory.Options();
        bitOptions.inJustDecodeBounds = false;
        bitOptions.inPurgeable = true;

        Bitmap bitmap = BitmapFactory.decodeStream(imageStream);
        Bitmap resized = Bitmap.createScaledBitmap(bitmap, size, size, true);
        return resized;
    }

    public Bitmap StringToBitmap(String str){
        try{
            byte [] encodeByte= Base64.decode(str,Base64.DEFAULT);
            ByteArrayInputStream inStream = new ByteArrayInputStream(encodeByte);
            Bitmap bitmap = BitmapFactory.decodeStream(inStream);
            return bitmap;
        }catch(Exception e){
            e.getMessage();
            return null;
        }
    }

    public String BitmapToString(Bitmap bitmap){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        String temp = Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
    }
}
