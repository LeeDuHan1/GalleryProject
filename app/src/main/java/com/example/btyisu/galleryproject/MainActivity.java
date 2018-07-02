package com.example.btyisu.galleryproject;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.btyisu.galleryproject.adapter.TabPagerAdapter;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private ViewPager mViewPager = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int mPermissionCheck = ContextCompat.checkSelfPermission( this, Manifest.permission.READ_EXTERNAL_STORAGE);
        if ( mPermissionCheck == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions( this, new String[] {  Manifest.permission.READ_EXTERNAL_STORAGE  },11 );
        }

        TabLayout mTabLayout = (TabLayout) findViewById(R.id.tab_layout);
        mTabLayout.addTab(mTabLayout.newTab().setText("Live"));
        mTabLayout.addTab(mTabLayout.newTab().setText("VOD"));
//        mTabLayout.addTab(mTabLayout.newTab().setText("Live"));
//        mTabLayout.addTab(mTabLayout.newTab().setText("VOD"));

        ArrayList<Fragment> mFragmentList = new ArrayList<Fragment>();
        mFragmentList.add(LiveFragment.getInstance());
        mFragmentList.add(VodFragment.getInstance());
//        mFragmentList.add(LiveFragment.getInstance());
//        mFragmentList.add(VodFragment.getInstance());

        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setOffscreenPageLimit(mTabLayout.getTabCount());

        final PagerAdapter adapter = new TabPagerAdapter(getSupportFragmentManager(), mTabLayout.getTabCount(), mFragmentList);

        mViewPager.setAdapter(adapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
        if(savedInstanceState != null)
        {
            int cnt = savedInstanceState.getInt("num");
        }


    }
}
