package com.example.btyisu.galleryproject;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class SpacesItemDecorationFirst extends RecyclerView.ItemDecoration {


public SpacesItemDecorationFirst(){

        }

@Override
public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.right = 0;
        }
}
