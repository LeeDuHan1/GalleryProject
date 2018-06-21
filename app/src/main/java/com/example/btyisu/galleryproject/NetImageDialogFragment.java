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
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.example.btyisu.galleryproject.Volley.MyVolley;
import com.example.btyisu.galleryproject.utils.StringUtils;

public class NetImageDialogFragment extends DialogFragment {
    private NetworkImageView imageView;
    public TextView mTitleText;
    public TextView mUserNickText;
    public TextView mViewCntText;
    private StringBuilder mViewCntString = new StringBuilder("");

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
        mTitleText = (TextView) view.findViewById(R.id.title_text);
        mUserNickText = (TextView) view.findViewById(R.id.user_nick_text);
        mViewCntText = (TextView) view.findViewById(R.id.view_cnt_text);

        ViewGroup.LayoutParams mImageParams = imageView.getLayoutParams();
        mImageParams.width = imageSize;
        mImageParams.height = imageSize;
        imageView.setLayoutParams(mImageParams);

        ViewGroup.LayoutParams mTitleParams = mTitleText.getLayoutParams();
        mTitleParams.width = imageSize;
        mTitleText.setLayoutParams(mTitleParams);

        Bundle extra = getArguments();
        imageView.setImageUrl(extra.getString("url"), MyVolley.getInstance(getContext()).getImageLoader());
        mTitleText.setText(extra.getString("title"));
        mUserNickText.setText(extra.getString("userNick"));
        mViewCntString.append(extra.getString("viewCnt")).append("명 시청중");
        mViewCntText.setText(getContext().getString(R.string.string_live_viewer_count,extra.getString("viewCnt")));
        return view;
    }
}
