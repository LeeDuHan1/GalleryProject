package com.example.btyisu.galleryproject.data;


import com.example.btyisu.galleryproject.list.ContentTypeManager;

public class ListSpaceContent extends Content {

    @Override
    public int getViewType() {
        return ContentTypeManager.LIST_SPACE;
    }
}

