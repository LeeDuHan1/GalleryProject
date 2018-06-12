package com.example.btyisu.galleryproject;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;

public class GetFileList{
    private Context context;
    private String keyWord;

    public GetFileList(Context context, String word){
        this.keyWord = word;
        this.context = context;
    }

    public ArrayList<String> getTitleList(){
        ArrayList<String> result = new ArrayList<>();
            Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
            String[] projection = {MediaStore.MediaColumns.DATA, MediaStore.MediaColumns.DISPLAY_NAME};

            Cursor cursor = context.getContentResolver().query(uri, projection, null, null, MediaStore.MediaColumns.DATE_ADDED.concat(" desc"));
            int columIndex = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
            int columDisplayName = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DISPLAY_NAME);

            while(cursor.moveToNext()){
                String absolutePath = cursor.getString(columIndex);
                String nameOfFile = cursor.getString(columDisplayName);
                if(nameOfFile.contains(keyWord)){
                    result.add(absolutePath);
                }
            }
        return result;
    }
}