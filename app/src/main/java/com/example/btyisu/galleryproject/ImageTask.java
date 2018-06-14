//package com.example.btyisu.galleryproject;
//
//import android.content.Context;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.net.Uri;
//import android.os.AsyncTask;
//import android.support.v7.widget.RecyclerView;
//
//import java.io.FileNotFoundException;
//import java.io.InputStream;
//import java.util.ArrayList;
//
//public class ImageTask extends AsyncTask<Integer, Integer, Bitmap> {
//    private int data = 0;
//    private Context context;
//    private String keyword;
//    private ArrayList<String> dataSet;
//    private RecyclerView.Adapter recyclerAdapter;
//    private RecyclerView recyclerView;
//
//    public ImageTask(Context context, String keyword, RecyclerView recyclerView){
//        this.context = context;
//        this.keyword = keyword;
//        this.recyclerView = recyclerView;
//    }
//    @Override
//    protected Bitmap doInBackground(Integer... params) {
//        data = params[0];
//        GetFileList getFileList = new GetFileList(context,keyword);
//        this. dataSet = getFileList.getTitleList();
//        StringBuilder filePath = new StringBuilder("file://");
//        for(int i =0; i<getFileList.getTitleList().size();i++){
//            filePath.append(dataSet.get(i));
//        }
//        Uri uri = Uri.parse(filePath.toString());
//        InputStream imageStream = null;
//        try {
//            imageStream = this.context.getContentResolver().openInputStream(uri);
//        }catch (FileNotFoundException e){
//            e.printStackTrace();
//        }
//        BitmapFactory.Options bitOptions = new BitmapFactory.Options();
//        bitOptions.inJustDecodeBounds = true;
//
//        bitOptions.inJustDecodeBounds = false;
////        bitOptions.inSampleSize = scaleFactor;
//        bitOptions.inPurgeable = true;
//
//        Bitmap bitmap = BitmapFactory.decodeStream(imageStream);
//        return bitmap;
//    }
//
//    @Override
//    protected void onPostExecute(Bitmap bitmap) {
//        recyclerAdapter = new RecyclerAdapter(dataSet,context);
//        recyclerView.setAdapter(recyclerAdapter);
//    }
//}
