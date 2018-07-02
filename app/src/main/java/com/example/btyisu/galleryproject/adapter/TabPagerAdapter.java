package com.example.btyisu.galleryproject.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.example.btyisu.galleryproject.LiveFragment;
import com.example.btyisu.galleryproject.VodFragment;

import java.util.ArrayList;
import java.util.List;

public class TabPagerAdapter extends FragmentPagerAdapter{
    int tabCount;
    ArrayList<Fragment> mFragmentList;
    public TabPagerAdapter(FragmentManager fm, int count, ArrayList<Fragment> mFragmentList){
        super(fm);
        this.tabCount = count;
        this.mFragmentList = mFragmentList;
    }

    @Override
    public int getCount() {
        return tabCount;
    }
    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }
}
