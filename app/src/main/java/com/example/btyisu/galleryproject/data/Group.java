package com.example.btyisu.galleryproject.data;

import android.text.TextUtils;

import com.example.btyisu.galleryproject.list.ContentTypeManager;
import com.example.btyisu.galleryproject.list.SectionData;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;



public class Group implements SectionData<Content> {

    @SerializedName("align_type")
    private String alignType;

    @SerializedName("content_type")
    private String contentType;
    @SerializedName("contents")
    private List<Content> contents;

    @SerializedName("group_id")
    private String groupId;

    @SerializedName("is_last")
    private boolean isLast;

    @SerializedName("is_show_title")
    private boolean isShowTitle;

    @SerializedName("show_more")
    private String showMore;

    @SerializedName("title")
    private String title;

    @SerializedName("sub_title")
    private String subTitle;

    @SerializedName("is_auto_play")
    private Boolean isAutoPlay;

    @SerializedName("type")
    private String type;

    @SerializedName("update_time")
    private String updateTime;

    @SerializedName("is_special_category")
    private String isSpecialCategory;

    @SerializedName("group_info")
    private String groupInfo;

    private boolean isAllAlarm;
    private String groupTitle;

    public Group() {
    }

    public Group(Group group) {
        alignType = group.alignType;
        contentType = group.contentType;
        contents = group.contents;
        groupId = group.groupId;
        isLast = group.isLast;
        isShowTitle = group.isShowTitle;
        showMore = group.showMore;
        title = group.title;
        subTitle = group.subTitle;
        isAutoPlay = group.isAutoPlay;
        type = group.type;
        updateTime = group.updateTime;
        isSpecialCategory = group.isSpecialCategory;
        groupInfo = group.groupInfo;
        isAllAlarm = group.isAllAlarm;
        groupTitle = group.groupTitle;
    }

    public String getAlignType() {
        return alignType;
    }

    public void setAlignType(String alignType) {
        this.alignType = alignType;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public List<Content> getContents() {
        if (contents == null) {
            contents = new ArrayList<>();
        }

        return contents;
    }

    public void setContents(List<Content> contents) {
        this.contents = contents;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public boolean isLast() {
        return isLast;
    }

    public void setLast(boolean last) {
        isLast = last;
    }

    public boolean isShowTitle() {
        return isShowTitle;
    }

    public void setShowTitle(boolean showTitle) {
        isShowTitle = showTitle;
    }

    public String getShowMore() {
        return showMore;
    }

    public void setShowMore(String showMore) {
        this.showMore = showMore;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public Boolean isAutoPlay() {
        return isAutoPlay;
    }

    public void setAutoPlay(Boolean toAutoPlay) {
        isAutoPlay = toAutoPlay;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public boolean isSpecialCategory(){return TextUtils.equals(isSpecialCategory, "Y");}

    public String getGroupInfo() {
        return groupInfo;
    }

    public void setGroupInfo(String groupInfo) {
        this.groupInfo = groupInfo;
    }

    public boolean isAllAlarm() {
        return isAllAlarm;
    }

    public void setAllAlarm(boolean isAllAlarm) {
        this.isAllAlarm = isAllAlarm;
    }

    public String getGroupTitle() {
        return groupTitle;
    }

    public void setGroupTitle(String groupTitle) {
        this.groupTitle = groupTitle;
    }
    @Override
    public Content get(int position) {
        if (position < contents.size()) {
            return contents.get(position);
        } else if (position == contents.size()) {
            return new ListSpaceContent();
        } else {
            return new UnknownContent();
        }
    }

    @Override
    public int itemViewCount() {
        if (mGroupType == ContentTypeManager.BANNER || mGroupType == ContentTypeManager.SEASONAL || mGroupType == ContentTypeManager.SLIDE || mGroupType == ContentTypeManager.TABLE) {
            return 0;
        } else if (mContentType == ContentTypeManager.LIST_LIVE || mContentType == ContentTypeManager.LIST_VOD || mContentType == ContentTypeManager.MEMO || mContentType == ContentTypeManager.LIST_RANK || mContentType == ContentTypeManager.LIST_BJ
                || mContentType == ContentTypeManager.LIST_SUBSCRIPTION || mContentType == ContentTypeManager.SINGLE_LIST_IMAGE || mContentType == ContentTypeManager.LIST_FAVORITE_BJ) {
            return contents.size() + 1;
        } else {
            return contents.size();
        }
    }

    @Override
    public int size() {
        return contents.size();
    }

    /*
    View Type
    */
    protected int mGroupType;
    protected int mContentType;

    @Override
    public int getViewType() {
        return mGroupType;
    }

    public void setViewType(int groupType, int contentType) {
        mGroupType = groupType;
        mContentType = contentType;

        for (Content c: contents) {
            c.setViewType(mContentType);
        }
    }

    /*
    레이아웃 처리관련해서 예외적으로 탑 마진을 넣어줘야할때 지정
     */
    protected boolean isNeedTopMargin;

    public boolean isNeedTopMargin() {
        return isNeedTopMargin;
    }

    public void setNeedTopMargin(boolean toNeedTopMargin) {
        isNeedTopMargin = toNeedTopMargin;
    }

    public static Group createEmptyGroup() {
        Group group = new Group();

        group.contents = new ArrayList<>();

        return group;
    }
}
