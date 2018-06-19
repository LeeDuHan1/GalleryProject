package com.example.btyisu.galleryproject.data;

import android.text.TextUtils;

import com.example.btyisu.galleryproject.list.ContentTypeManager;

import java.util.ArrayList;


public class ListHeaderGroup extends Group {
    @Override
    public int getViewType() {
        if (TextUtils.isEmpty(getTitle())) {
            return ContentTypeManager.LIST_HEADER;
        } else {
            return ContentTypeManager.LIST_EMPTY_HEADER;
        }
    }

    public ListHeaderGroup() {
        setContents(new ArrayList<Content>());
    }
}
