//package com.example.btyisu.galleryproject.LocalImage;
//
//import android.content.Context;
//import android.database.Cursor;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.net.Uri;
//import android.provider.MediaStore;
//import android.util.Log;
//
//import java.io.FileNotFoundException;
//import java.io.InputStream;
//import java.util.ArrayList;
//
//public class GetFileList{
//    private ArrayList<String> result = new ArrayList<>();
//    private Context context;
//    private String keyWord;
//
//    public GetFileList(Context context, String word){
//        this.keyWord = word;
//        this.context = context;
//    }
//
//    public ArrayList<String> getTitleList(){
//            // 이미지 meta data를 포함하고 있는 파일의 경로와 이름을 가져온다.
//            Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
//            String[] projection = {MediaStore.MediaColumns.DATA, MediaStore.MediaColumns.DISPLAY_NAME};
//
//
//            Cursor cursor = context.getContentResolver().query(uri, projection, null, null, MediaStore.MediaColumns.DATE_ADDED.concat(" desc"));
//            int columIndex = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
//            int columDisplayName = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DISPLAY_NAME);
//
//            while(cursor.moveToNext()){
//                String absolutePath = cursor.getString(columIndex);
//                String nameOfFile = cursor.getString(columDisplayName);
//                if(nameOfFile.contains(keyWord)){
//                    result.add(absolutePath);
//                }
//            }
//        return result;
//    }
//}