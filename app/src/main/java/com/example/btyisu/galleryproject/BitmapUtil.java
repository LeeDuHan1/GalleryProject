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

    // Uri로 Bitmap을 가져오고, 원하는 크기로 리사이즈한다.
    public Bitmap getBitmapFromUri(Uri uri, int size){
        BitmapFactory.Options bitOptions = new BitmapFactory.Options();
        bitOptions.inJustDecodeBounds = false;
        bitOptions.inPurgeable = true;

        Bitmap bitmap = BitmapFactory.decodeFile(uri.getPath(),bitOptions);
        Bitmap resized = Bitmap.createScaledBitmap(bitmap, size, size, true);
        return resized;
    }

//    public int calculateSampleSize(BitmapFactory.Options options, int reqwidth, int reqHeight){
//        int height = options.outHeight;
//        int width = options.outWidth;
//        int inSampleSize =1;
//
//        if(height > reqHeight || width > reqwidth){
//            int halfHeight = height / 2;
//            int halfWidth = width / 2;
//
//            while((halfHeight / inSampleSize) > reqHeight && (halfWidth / inSampleSize) > reqwidth){
//                inSampleSize *= 2;
//            }
//        }
//        return inSampleSize;
//    }
//
//    public Bitmap decodeSampleBitmapFromFile(String filePath, int reqWidth, int reqHeight){
//        BitmapFactory.Options options = new BitmapFactory.Options();
//        options.inJustDecodeBounds = true;
//        BitmapFactory.decodeFile(filePath, options);
//
//        options.inSampleSize = calculateSampleSize(options, reqWidth, reqHeight);
//
//        options.inJustDecodeBounds = false;
//        return BitmapFactory.decodeFile(filePath, options);
//    }
//
//
//
//    public Bitmap makeStringToBitmap(String str){
//        try{
//            byte [] encodeByte= Base64.decode(str,Base64.DEFAULT);
//            ByteArrayInputStream inStream = new ByteArrayInputStream(encodeByte);
//            Bitmap bitmap = BitmapFactory.decodeStream(inStream);
//            return bitmap;
//        }catch(Exception e){
//            e.getMessage();
//            return null;
//        }
//    }
//
//    public String makeBitmapToString(Bitmap bitmap){
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
//        byte[] b = baos.toByteArray();
//        String temp = Base64.encodeToString(b, Base64.DEFAULT);
//        return temp;
//    }
}
