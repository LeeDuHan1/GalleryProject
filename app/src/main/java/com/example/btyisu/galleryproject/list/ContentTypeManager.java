package com.example.btyisu.galleryproject.list;

import com.example.btyisu.galleryproject.data.Group;

import java.util.concurrent.ConcurrentHashMap;


public class ContentTypeManager {
    /*
    Type Constants
     */
    public static final int GROUP_HEADER = 1;
    public static final int LIST_LIVE = 2;
    public static final int LIST_VOD = 3;
    public static final int LIST_EXTEND_VOD = 4;
    public static final int LIST_RANK = 5;
    public static final int LIST_SPACE = 6;
    public static final int GRID_LIVE = 7;
    public static final int GRID_VOD = 8;
    public static final int SEASONAL = 9;
    public static final int BANNER = 10;
    public static final int MEMO = 11;
    public static final int SLIDE = 12;
    public static final int SLIDE_VOD = 13;
    public static final int SLIDE_LIVE = 14;
    public static final int SLIDE_LATER_VOD = 15;
    public static final int TABLE = 16;
    public static final int TABLE_ROW = 17;
    public static final int LIST_BJ = 18;
    public static final int LIST_SUBSCRIPTION = 19;
    public static final int SINGLE_LIST_IMAGE = 20;
    public static final int GRID_LATER = 21;
    public static final int SLIDE_ANIMATION = 22;
    public static final int LIST_FAVORITE_BJ = 23;


    public static final int FORU_CONTENT = 40;
    public static final int FORU_ITEM = 41;
    public static final int FORU_CATEGORY = 42;
    public static final int FORU_SLIDE = 43;
    public static final int FORU_CATEGORY_EMPTY = 44;
    public static final int FORU_CONTENT_EMPTY = 45;
    public static final int FORU_HASHTAG_EMPTY = 46;
    public static final int FORU_MODULE_DELETE = 47;
    public static final int FORU_CONTENT_DELETE = 48;
    public static final int FORU_HASHTAG = 49;

    public static final int LIST_EMPTY_HEADER = 98;
    public static final int LIST_HEADER = 99;
    public static final int GROUP_UNKNOWN = 100;
    public static final int CONTENT_UNKNOWN = 101;

    /*
    Singleton class and method
     */
    private static class ContentTypeManagerHolder {
        private static final ContentTypeManager INSTANCE = new ContentTypeManager();
    }

    public static ContentTypeManager getInstance() {
        return ContentTypeManagerHolder.INSTANCE;
    }

    /*
    Components
     */
    private ConcurrentHashMap<String, Integer> mGroupTypeMap;
    private ConcurrentHashMap<String, ConcurrentHashMap<String, Integer>> mContentTypeTable;

    /*
    Constructor
     */
    private ContentTypeManager() {
        mGroupTypeMap = new ConcurrentHashMap<>();
        mContentTypeTable = new ConcurrentHashMap<>();

        mGroupTypeMap.put("banner", ContentTypeManager.BANNER);
        mGroupTypeMap.put("seasonal", ContentTypeManager.SEASONAL);
        mGroupTypeMap.put("slide", ContentTypeManager.SLIDE);
        mGroupTypeMap.put("list", ContentTypeManager.GROUP_HEADER);
        mGroupTypeMap.put("grid", ContentTypeManager.GROUP_HEADER);
        mGroupTypeMap.put("tailor", ContentTypeManager.GROUP_HEADER);
        mGroupTypeMap.put("memo", ContentTypeManager.GROUP_HEADER);
        mGroupTypeMap.put("list_extend", ContentTypeManager.GROUP_HEADER);
        mGroupTypeMap.put("table", ContentTypeManager.TABLE);
        mGroupTypeMap.put("single", ContentTypeManager.GROUP_HEADER);
        mGroupTypeMap.put("poster", ContentTypeManager.SLIDE);

        ConcurrentHashMap<String, Integer> slideMap = new ConcurrentHashMap<>();
        slideMap.put("vod", ContentTypeManager.SLIDE_VOD);
        slideMap.put("live", ContentTypeManager.SLIDE_LIVE);
        slideMap.put("later",ContentTypeManager.SLIDE_LATER_VOD);
        slideMap.put("animation", ContentTypeManager.SLIDE_ANIMATION);

        ConcurrentHashMap<String, Integer> listMap = new ConcurrentHashMap<>();
        listMap.put("vod", ContentTypeManager.LIST_VOD);
        listMap.put("live", ContentTypeManager.LIST_LIVE);
        listMap.put("rank", ContentTypeManager.LIST_RANK);
        listMap.put("bj", ContentTypeManager.LIST_BJ);
        listMap.put("favorite", ContentTypeManager.LIST_FAVORITE_BJ);
        listMap.put("subscription", ContentTypeManager.LIST_SUBSCRIPTION);

        ConcurrentHashMap<String, Integer> gridMap = new ConcurrentHashMap<>();
        gridMap.put("vod", ContentTypeManager.GRID_VOD);
        gridMap.put("live", ContentTypeManager.GRID_LIVE);
        gridMap.put("later", ContentTypeManager.GRID_LATER);

        ConcurrentHashMap<String, Integer> listExtendMap = new ConcurrentHashMap<>();
        listExtendMap.put("vod", ContentTypeManager.LIST_EXTEND_VOD);

        ConcurrentHashMap<String, Integer> memoMap = new ConcurrentHashMap<>();
        memoMap.put("memo", ContentTypeManager.MEMO);

        ConcurrentHashMap<String, Integer> tailorMap = new ConcurrentHashMap<>();
        tailorMap.put("tailor", ContentTypeManager.LIST_LIVE);

        ConcurrentHashMap<String, Integer> tableMap = new ConcurrentHashMap<>();
        tableMap.put("table", ContentTypeManager.TABLE_ROW);

        ConcurrentHashMap<String, Integer> SingleListMap = new ConcurrentHashMap<>();
        SingleListMap.put("image", ContentTypeManager.SINGLE_LIST_IMAGE);

        mContentTypeTable.put("slide", slideMap);
        mContentTypeTable.put("list", listMap);
        mContentTypeTable.put("grid", gridMap);
        mContentTypeTable.put("list_extend", listExtendMap);
        mContentTypeTable.put("memo", memoMap);
        mContentTypeTable.put("tailor", tailorMap);
        mContentTypeTable.put("table", tableMap);
        mContentTypeTable.put("single", SingleListMap);
        mContentTypeTable.put("poster", slideMap);

    }

    public int getGroupType(String alignType) {
        Integer type = mGroupTypeMap.get(alignType);

        if (type != null) {
            return type;
        }

        return GROUP_UNKNOWN;
    }

    public int getContentType(String alignType, String contentType) {
        ConcurrentHashMap<String, Integer> map = mContentTypeTable.get(alignType);

        if (map != null) {
            Integer type = map.get(contentType);

            if (type != null) {
                return type;
            }
        }

        return CONTENT_UNKNOWN;
    }

    public static void calcType(Group group) {
        int groupType = getInstance().getGroupType(group.getAlignType());
        int contentType = getInstance().getContentType(group.getAlignType(), group.getContentType());

        group.setViewType(groupType, contentType);
    }
}
