package com.example.btyisu.galleryproject;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class ImageTask extends AsyncTask<String, Void, String> {
    private Context context;
    private String keyword;
    private ArrayList<String> dataSet;

    public ImageTask(Context context, String keyword){
        this.context = context;
        this.keyword = keyword;
    }
    @Override
    protected String doInBackground(String... strings) {
        GetFileList getFileList = new GetFileList(context,keyword);
        this. dataSet = getFileList.getTitleList();


        for(int i =0; i<getFileList.getTitleList().size();i++){
            StringBuilder filePath = new StringBuilder("file://");
            filePath.append(dataSet.get(i));
        }
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        recyclerView.setAdapter(recyclerAdapter);
    }
}
