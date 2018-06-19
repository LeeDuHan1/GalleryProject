package com.example.btyisu.galleryproject.data;

import android.text.TextUtils;

import com.example.btyisu.galleryproject.list.ItemData;
import com.example.btyisu.galleryproject.utils.StringUtils;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Content implements ItemData {

    // 동영상의 경우 리턴값에 station_no, bbs_no, title_no 값이 없는 경우, scheme값에서 추출하여 사용 가능.
    // 라이브의 경우 리턴값에 broad_no 값이 없는 경우, scheme값에서 추출하여 사용 가능.
    // 선행작업자들하고 협의가 된 사항이라곤 하는데... 원칙에 맞다고 생각되지 않는다(Riccardo).

    @SerializedName("auth")
    private String auth;

    @SerializedName("broad_type")
    private int broadType;

    @SerializedName("duration")
    private long duration;

    @SerializedName("file_type")
    private String fileType;

    @SerializedName("grade")
    private int grade;

    @SerializedName("memo_cnt")
    private int memoCount;

    @SerializedName("recommend_cnt")
    private int recommendCount;

    @SerializedName("reg_date")
    private String regDate;

    @SerializedName("scheme")
    private String scheme;

    @SerializedName("thumbnail")
    private String thumbnail;

    @SerializedName("title")
    private String title;

    @SerializedName("title_no")
    private int titleNumber;

    @SerializedName("user_id")
    private String userId;

    @SerializedName("user_nick")
    private String userNick;

    @SerializedName("view_cnt")
    private int viewCount;

    @SerializedName("is_password")
    private String isPassword;

    @SerializedName("bbs_no")
    private String bbsNo;

    @SerializedName("station_no")
    private String stationNo;

    @SerializedName("broad_no")
    private String broadNo;

    @SerializedName("category")
    private String category;


    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public String getStationNo() {
        return stationNo;
    }

    public void setStationNo(String stationNo) {
        this.stationNo = stationNo;
    }

    public String getBbsNo() {
        return bbsNo;
    }

    public void setBbsNo(String bbsNo) {
        this.bbsNo = bbsNo;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public int getBroadType() {
        return broadType;
    }

    public void setBroadType(int broadType) {
        this.broadType = broadType;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getMemoCount() {
        return StringUtils.addComma(String.valueOf(memoCount));
    }

    public void setMemoCount(int memoCount) {
        this.memoCount = memoCount;
    }

    public String getRecommendCount() {
        return StringUtils.addComma(String.valueOf(recommendCount));
    }

    public void setRecommendCount(int recommendCount) {
        this.recommendCount = recommendCount;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getTitle() {
        if (title == null) {
            return "";
        } else {
            return title;
        }
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTitleNumber() {
        return titleNumber;
    }

    public void setTitleNumber(int titleNumber) {
        this.titleNumber = titleNumber;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserNick() {
        return userNick;
    }

    public void setUserNick(String userNick) {
        this.userNick = userNick;
    }

    public String getViewCount() {
        return StringUtils.addComma(String.valueOf(viewCount));
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public boolean isPassword() {
        return TextUtils.equals(isPassword, "1") || TextUtils.equals(isPassword, "true");
    }

    public void setIsPassword(String isPassword) {
        this.isPassword = isPassword;
    }

    public String getBroadNo() {
        return broadNo;
    }

    public void setBroadNo(String broadNo) {
        this.broadNo = broadNo;
    }

    /*
    VOD
     */
    @SerializedName("timestamp")
    private String timestamp;

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @SerializedName("ucc_type")
    private String ucc_type;

    public String getUcc_type() {
        return ucc_type;
    }

    public void setUcc_type(String ucc_type) {
        this.ucc_type = ucc_type;
    }
    /*
        Banner
         */
    @SerializedName("img_url")
    private String imageUrl;

    @SerializedName("bg_color")
    private String backgroundColor;

    @SerializedName("start_date")
    private long startDate;

    @SerializedName("end_date")
    private long endDate;

    @SerializedName("img_height")
    private int imageHeight;

    @SerializedName("alarm_idx")
    private String alarmIndex;

    @SerializedName("live_alarm")
    private String live_alarm;

    @SerializedName("is_google")
    private int isGoogle;

    @SerializedName("is_onestore")
    private int is_onestore;

    @SerializedName("is_samsung")
    private int isSamsung;

    public boolean isGoogle(){return isGoogle == 1;}

    public boolean isOnstore() {return is_onestore == 1;}

    public boolean isSamsung() {return isSamsung == 1;}

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public long getStartDate() {
        return startDate;
    }

    public void setStartDate(long startDate) {
        this.startDate = startDate;
    }

    public long getEndDate() {
        return endDate;
    }

    public void setEndDate(long endDate) {
        this.endDate = endDate;
    }

    public int getImageHeight() {
        return imageHeight;
    }

    public void setImageHeight(int imageHeight) {
        this.imageHeight = imageHeight;
    }

    public String getAlarmIndex() {
        return alarmIndex;
    }

    public String getLiveAlarm(){return live_alarm;}

    /*
    Vod Memo
     */
    @SerializedName("station_logo")
    private String stationLogo;

    @SerializedName("comment")
    private String comment;

    @SerializedName("like_cnt")
    private int likeCount;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getStationLogo() {
        return stationLogo;
    }

    public void setStationLogo(String stationLogo) {
        this.stationLogo = stationLogo;
    }

    public String getLikeCount() {
        return StringUtils.addComma(String.valueOf(likeCount));
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    /*
    Favorite
     */
    public static final int TYPE_NO_PUSH = -1;

    @SerializedName("is_push")
    private int isPush = TYPE_NO_PUSH;

    public boolean isPush() {
        return isPush == 1;
    }

    public int getPush() {
        return isPush;
    }

    public void setPush(boolean isPush){this.isPush = isPush ? 1 : 0;}

    public void changeIsPush() {
        if (isPush == 1) {
            isPush = 0;
        } else {
            isPush = 1;
        }
    }

    /*
    빠져들만한 방송
     */
    @SerializedName("logic")
    private String logic;

    @SerializedName("is_last")
    private String isLast = "";

    public String getLogic() {
        return logic;
    }

    public String getIsLast() {
        return isLast;
    }

    /*
    애니메이션
     */
    @SerializedName("season_title")
    private String seasonTitle;

    public String getSeasonTitle() {
        if (seasonTitle != null) {
            return seasonTitle;
        } else {
            return "";
        }
    }

    public void setSeasonTitle(String seasonTitle) {
        this.seasonTitle = seasonTitle;
    }

    /*
    랭크
     */
    @SerializedName("bj_id")
    private String bjId;

    @SerializedName("bj_nick")
    private String bjNick;

    @SerializedName("total_rank")
    private int totalRank;

    @SerializedName("total_rank_last")
    private int totalRankLast;

    public String getBjId() {
        return bjId;
    }

    public String getBjNick() {
        return bjNick;
    }

    public int getTotalRank() {
        return totalRank;
    }

    public int getTotalRankLast() {
        return totalRankLast;
    }

    /*
    BJ 검색
     */
    @SerializedName("station_name")
    private String stationName;

    @SerializedName("favorite_cnt")
    private String favoriteCount;

    @SerializedName("fanclub_cnt")
    private String fanClubCount;

    @SerializedName("ranking")
    private String ranking;

    @SerializedName("medals")
    private List<String> medals;

    public String getStationName() {
        return stationName;
    }

    public String getFavoriteCount() {
        return favoriteCount;
    }

    public String getFanClubCount() {
        return fanClubCount;
    }

    public String getRanking() {
        return ranking;
    }

    public List<String> getMedals() {
        return medals;
    }

    /*
    구독
     */
    @SerializedName("subscription_date")
    private String subscriptionData;

    public String getSubscriptionData() {
        return subscriptionData;
    }

    @SerializedName("payment_count")
    private String paymentCount;

    public String getPaymentCount() {
        return paymentCount;
    }

    @SerializedName("is_app_aos")
    private String isAppAos;

    public String isAppAos() {
        return isAppAos;
    }

    @SerializedName("is_broading")
    private boolean isBroading;

    public boolean isBroading() {
        return isBroading;
    }

    @SerializedName("accumulate_date")
    private String accumulateDate;

    public String getAccumulateDate() {
        return accumulateDate;
    }

    @SerializedName("subscription_user_nick")
    private String subscriptionNickname;

    public String getSubscriptionNickname() {
        return subscriptionNickname;
    }

    @SerializedName("log")
    private String log;

    public String getLog() {
        return log;
    }

    /*
    ViewType
     */
    private int mViewType;

    @Override
    public int getViewType() {
        return mViewType;
    }

    public void setViewType(int contentType) {
        mViewType = contentType;
    }

    /*
        나중에 다시 보기
     */
    /**
     * 시청한 방송.
     */
    @SerializedName("view_check")
    private boolean viewCheck;

    public static final String LATER_TYPE_VOD = "VOD";
    public static final String LATER_TYPE_LIVE = "LIVE";
    /**
     * 나중에 보기 리스트 타입(LIVE/VOD).
     */
    @SerializedName("type")
    private String type;

    public boolean isViewCheck() {
        return viewCheck;
    }

    public void setViewCheck(boolean viewCheck) {
        this.viewCheck = viewCheck;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @SerializedName("is_fan")
    private int isFan;

    @SerializedName("is_subscription")
    private int isSubscription;

    public int isFan() {
        return isFan;
    }

    public void setIsFan(int isFan) {
        this.isFan = isFan;
    }

    public int isSubscription() {
        return isSubscription;
    }

    public void setIsSubscription(int isSubscription) {
        this.isSubscription = isSubscription;
    }

    @SerializedName("recent_broad_time")
    private String recentBroadTime;

    @SerializedName("station_create_time")
    private String stationCreateTime;

    @SerializedName("favorite_no")
    private String favoriteNo;

    @SerializedName("is_broad")
    private int isBroad;

    @SerializedName("is_pin")
    private int isPin;

    public int isPin() {return isPin;}

    public void setIsPin(int isPin) {
        this.isPin = isPin;
    }

    public int isBroad() {
        return isBroad;
    }

    public void setIsBroad(int isBroad) {
        this.isBroad = isBroad;
    }

    public String getRecentBroadTime() {
        return recentBroadTime;
    }

    public void setRecentBroadTime(String recentBroadTime) {
        this.recentBroadTime = recentBroadTime;
    }

    public String getStationCreateTime() {
        return stationCreateTime;
    }

    public void setStationCreateTime(String stationCreateTime) {
        this.stationCreateTime = stationCreateTime;
    }

    public String getFavoriteNo() {
        return favoriteNo;
    }

    public void setFavoriteNo(String favoriteNo) {
        this.favoriteNo = favoriteNo;
    }
}

