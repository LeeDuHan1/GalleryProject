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
                FoodFragment foodFragment = new FoodFragment();
                return foodFragment;
            case 1:
                MusicFragment musicFragment = new MusicFragment();
                return musicFragment;
            default:
                return null;
        }
    }
}
