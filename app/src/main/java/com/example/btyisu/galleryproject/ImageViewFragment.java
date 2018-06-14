package com.example.btyisu.galleryproject;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

public class ImageViewFragment extends Fragment {
    private ArrayList<String> dataSet;
    private int position;
    private ImageView imageView;
    private int imageSize = 1200;
    public ImageViewFragment(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.image_view_fragment, container, false);
        imageView = (ImageView) view.findViewById(R.id.fragment_imageView);
        ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
        layoutParams.width = imageSize;
        layoutParams.height = imageSize;
        imageView.setLayoutParams(layoutParams);

        Bundle extra = getArguments();
        dataSet = extra.getStringArrayList("dataSet");
        position = extra.getInt("position");

        StringBuilder filePath = new StringBuilder("file://");
        Uri uri = Uri.parse(filePath.append(extra.getString("uri")).toString());

        BitmapUtil bitmapUtil = new BitmapUtil();
        imageView.setImageBitmap(bitmapUtil.getBitmapFromUri(getActivity(), uri, imageSize));
        return view;
    }
}