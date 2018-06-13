package com.example.btyisu.galleryproject;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabPagerAdapter extends FragmentPagerAdapter{
    int tab_count;

    public TabPagerAdapter(FragmentManager fm, int count){
        super(fm);
        this.tab_count = count;
    }

    @Override
    public int getCount() {
        return tab_count;
    }
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                Tab1Fragment tab1Fragment = new Tab1Fragment();
                return tab1Fragment;
            case 1:
                Tab2Fragment tab2Fragment = new Tab2Fragment();
                return tab2Fragment;
            default:
                return null;
        }
    }
}
