package com.example.btyisu.galleryproject;

import android.Manifest;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.btyisu.galleryproject.adapter.TabPagerAdapter;


public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int memClass = ((ActivityManager) getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE)).getMemoryClass();
        int maxMemory = (int)(Runtime.getRuntime().maxMemory()/1024);

        Toast.makeText(getApplicationContext(),String.valueOf(maxMemory),Toast.LENGTH_SHORT).show();
        int permissionCheck = ContextCompat.checkSelfPermission( this, Manifest.permission.READ_EXTERNAL_STORAGE);
        if ( permissionCheck == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions( this, new String[] {  Manifest.permission.READ_EXTERNAL_STORAGE  },11 );
        }

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
//        tabLayout.addTab(tabLayout.newTab().setText("Food"));
//        tabLayout.addTab(tabLayout.newTab().setText("Music"));
        tabLayout.addTab(tabLayout.newTab().setText("Live"));
        tabLayout.addTab(tabLayout.newTab().setText("VOD"));

        viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter adapter = new TabPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());

        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
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
