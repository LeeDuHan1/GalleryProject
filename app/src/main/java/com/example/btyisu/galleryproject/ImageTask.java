package com.example.btyisu.galleryproject;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;

public class ImageTask extends AsyncTask<String, Void, String> {
    private String keyword;
    private Context context;
    private RecyclerAdapter recyclerAdapter;
    private RecyclerView recyclerView;
    public ImageTask(Context context, RecyclerAdapter adapter, RecyclerView recyclerView, String keyword){
        this.context = context;
        this.keyword = keyword;
        this.recyclerAdapter = adapter;
        this.recyclerView = recyclerView;
    }
    @Override
    protected String doInBackground(String... strings) {
        GetFileList getFileList = new GetFileList(context,"flower");
        for(int i =0; i<getFileList.getTitleList().size();i++){
            recyclerAdapter.dataAdd(recyclerAdapter.getItemCount(),getFileList.getTitleList().get(i));
        }
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        recyclerView.setAdapter(recyclerAdapter);
    }
}
