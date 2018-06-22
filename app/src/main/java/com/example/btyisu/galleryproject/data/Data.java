package com.example.btyisu.galleryproject.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data {

    @SerializedName("has_more_list")
    private boolean hasMoreList;
    @SerializedName("groups")
    private List<Group> groups;

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("is_exist_favorites")
    private boolean isExistFavorites;

    public boolean hasMoreList() {
        return hasMoreList;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public boolean isExistFavorites() {
        return isExistFavorites;
    }

    public void setExistFavorites(boolean isExistFavorites) {
        this.isExistFavorites = isExistFavorites;
    }
}
