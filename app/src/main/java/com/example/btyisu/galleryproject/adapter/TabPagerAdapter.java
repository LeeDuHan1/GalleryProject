package com.example.btyisu.galleryproject.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.btyisu.galleryproject.LiveFragment;
import com.example.btyisu.galleryproject.VodFragment;

public class TabPagerAdapter extends FragmentPagerAdapter{
    int tabCount;

    public TabPagerAdapter(FragmentManager fm, int count){
        super(fm);
        this.tabCount = count;
    }

    @Override
    public int getCount() {
        return tabCount;
    }
    @Override
    public Fragment getItem(int position) {
        switch (position) {
//            case 0:
//                Tab1Fragment tab1Fragment = new Tab1Fragment();
//                return tab1Fragment;
//            case 1:
//                Tab2Fragment tab2Fragment = new Tab2Fragment();
//                return tab2Fragment;
            case 0:
                LiveFragment liveFragment = new LiveFragment();
                return liveFragment;
            case 1:
                VodFragment vodFragment = new VodFragment();
                return vodFragment;
            default:
                return null;
        }
    }
}
