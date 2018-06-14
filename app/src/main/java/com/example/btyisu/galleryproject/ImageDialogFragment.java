package com.example.btyisu.galleryproject;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

public class ImageDialogFragment extends DialogFragment {
    private ImageView imageView;
    public ImageDialogFragment(){

    }

    @Override
    public void setArguments(@Nullable Bundle args) {
        super.setArguments(args);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.image_dialog_fragment, container, false);
        imageView = (ImageView) view.findViewById(R.id.dialogImageView);
        Bundle extra = getArguments();
        extra.getString("uri");
        Uri uri = Uri.parse(extra.getString("uri"));
        imageView.setImageURI(uri);

        ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
        layoutParams.width = 700;
        layoutParams.height = 700;

        imageView.setLayoutParams(layoutParams);

        return view;
    }
}
