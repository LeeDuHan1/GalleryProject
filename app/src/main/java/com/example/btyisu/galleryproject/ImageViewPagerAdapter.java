package com.example.btyisu.galleryproject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;

public class ImageViewPagerAdapter extends FragmentPagerAdapter {
    private int imagePosition;
    private ArrayList<String> dataSet;

    public ImageViewPagerAdapter(FragmentManager fm, ArrayList<String> dataSet, int imagePosition){
        super(fm);
        this.dataSet = dataSet;
        this.imagePosition = imagePosition;
    }

    @Override
    public int getCount() {
        return imagePosition;
    }

    @Override
    public Fragment getItem(int position) {
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("dataSet",dataSet);
        bundle.putInt("position",position);

        ImageViewFragment imageViewFragment = new ImageViewFragment();
        imageViewFragment.setArguments(bundle);
        Log.d("ImageViewPagerAdapter: ", String.valueOf(position));
        return imageViewFragment;
    }
}
