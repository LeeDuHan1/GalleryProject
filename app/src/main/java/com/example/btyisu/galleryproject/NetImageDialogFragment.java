package com.example.btyisu.galleryproject;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.volley.toolbox.NetworkImageView;
import com.example.btyisu.galleryproject.Volley.MyVolley;

public class NetImageDialogFragment extends DialogFragment {
    private NetworkImageView imageView;
    private int imageSize = 1200;
    public NetImageDialogFragment(){}

    @Override
    public void setArguments(@Nullable Bundle args) {
        super.setArguments(args);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.net_image_dialog_fragment, container, false);
        imageView = (NetworkImageView) view.findViewById(R.id.netDialogImageView);

        ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
        layoutParams.width = imageSize;
        layoutParams.height = imageSize;
        imageView.setLayoutParams(layoutParams);


        Bundle extra = getArguments();
        extra.getString("url");

        imageView.setImageUrl(extra.getString("url"), MyVolley.getInstance(getContext()).getImageLoader());
        return view;
    }
}
