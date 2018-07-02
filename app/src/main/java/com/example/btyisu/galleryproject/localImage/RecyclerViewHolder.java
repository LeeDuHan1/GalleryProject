//package com.example.btyisu.galleryproject.LocalImage;
//
//import android.content.Context;
//import android.os.Bundle;
//import android.support.v4.app.FragmentManager;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.RecyclerView;
//import android.view.View;
//import android.widget.ImageView;
//
//import com.example.btyisu.galleryproject.R;
//
//import java.util.ArrayList;
//
//public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
//    public ImageView imageView;
//    private Context context;
//    private ArrayList<String> dataSet;
//    public RecyclerViewHolder(View view, Context context, ArrayList<String> dataSet){
//        super(view);
//        imageView = (ImageView) view.findViewById(R.id.gridImageView);
//        imageView.setOnClickListener(this);
//        this.context = context;
//        this.dataSet = dataSet;
//    }
//
//    @Override
//    public void onClick(View view){
//        Bundle bundle = new Bundle();
//        bundle.putString("uri",dataSet.get(getAdapterPosition()));
//
//        FragmentManager fragmentManager = ((AppCompatActivity)context).getSupportFragmentManager();
//        ImageDialogFragment imageDialogFragment = new ImageDialogFragment();
//        imageDialogFragment.setArguments(bundle);
//        imageDialogFragment.show(fragmentManager,"test");
//    }
//
//}
